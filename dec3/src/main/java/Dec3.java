import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.regex.*;
import org.apache.commons.lang3.Range;

public class Dec3 {
    public static void main(String[] args) {
        String[] puzzleInput = loadPuzzleInput();
        System.out.println("The sum of all part numbers in the engine schematic is: "
                + part1(puzzleInput));

        char[][] part2input = Arrays.stream(puzzleInput)
                        .map(String::toCharArray)
                        .toArray(char[][]::new);
        System.out.println("The sum of all gear ratios in the engine schematic is: "
                + part2(part2input));
    }

    public static int part2(char[][] input) {
        int sum = 0;
        for (int i = 1; i < input.length - 1; i++) {
            int above = i - 1;
            int mid   = i;
            int below = i + 1;

            for (int j = 0; j < input[mid].length; j++) {
                if (input[mid][j] == '*') {
                    int product = 0;
                    var hitAbove = hasNumber(input[above], j);
                    var hitMid = hasNumber(input[mid], j);
                    var hitBelow = hasNumber(input[below], j);

                    if (hitAbove && hitBelow) {
                        product = findNumberIn(input[above], j) * findNumberIn(input[below], j);
                    } else if (hitAbove && hitMid) {
                        product = findNumberIn(input[above], j) * findNumberIn(input[mid], j);
                    } else if (hitMid && hitBelow) {
                        product = findNumberIn(input[mid], j) * findNumberIn(input[below], j);
                    } else { // same line hit
                        boolean[][] hits = hitMatrix(input[above], input[mid], input[below], j);
                        if (hits[0][0] && hits[0][2]) {
                            if (!isFalsePositiveHit(hits[0]))
                                product = findSameLineHitProduct(input[above], j);
                        } else if (hits[1][0] && hits[1][2]) {
                            product = findSameLineHitProduct(input[mid], j);
                        } else if (hits[2][0] && hits[2][2]) {
                            if (!isFalsePositiveHit(hits[2]))
                                product = findSameLineHitProduct(input[below], j);
                        } else {
                            System.out.println("No hit at " + i + ":" + j);
                        }
                    }

                    if (product < 100 && product > 0)
                        System.out.println("here at " + (i + 1) + ":" + (j + 1));

                    sum += product;
                }
            }
        }
        return sum;
    }

    public static boolean isFalsePositiveHit(boolean[] hitRow) {
        return hitRow[0] && hitRow[1] && hitRow[2];
    }

    public static boolean hasNumber(char[] string, int gearIndex) {
        for (int i = gearIndex - 1; i <= gearIndex + 1; i++) {
            if (Character.isDigit(string[i]))
                return true;
        }

        return false;
    }


    public static boolean[][] hitMatrix(char[] above, char[] mid, char[] below, int gearIndex) {
        boolean[][] hits = new boolean[3][3];

        int j = 0;
        for (int i = gearIndex - 1; i <= gearIndex + 1; i++, j++) {
            hits[0][j] = Character.isDigit(above[i]);
            hits[1][j] = Character.isDigit(mid[i]);
            hits[2][j] = Character.isDigit(below[i]);
        }

        return hits;
    }


    public static int findSameLineHitProduct(char[] string, int gearIndex) {
        List<Character> leftNum = new ArrayList<>();
        List<Character> rightNum = new ArrayList<>();

        int i = gearIndex - 1;
        while (i >= 0) {
            if (Character.isDigit(string[i]))
                leftNum.add(0, string[i]);
            else
                break;
            i--;
        }

        int j = gearIndex + 1;
        while (j < string.length) {
            if (Character.isDigit(string[j]))
                rightNum.add(string[j]);
            else
                break;
            j++;
        }
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        leftNum.forEach(left::append);
        rightNum.forEach(right::append);

        return Integer.parseInt(left.toString()) * Integer.parseInt(right.toString());
    }


    public static int findNumberIn(char[] string, int gearIndex) {
        List<Character> digitsFound = new ArrayList<>();

        // Looking to the left of the gear index.
        int i = gearIndex - 1;
        while (i >= 0) {
            if (Character.isDigit(string[i]))
                digitsFound.add(0, string[i]);
            else
                break;
            i--;
        }

        if (Character.isDigit(string[gearIndex]))
            digitsFound.add(string[gearIndex]);

        // Looking right of the gear index.
        int j = gearIndex + 1;
        while (j < string.length) {
            if (Character.isDigit(string[j]))
                digitsFound.add(string[j]);
            else
                break;
            j++;
        }
        StringBuilder numberFound = new StringBuilder();
        digitsFound.forEach(numberFound::append);

        return Integer.parseInt(numberFound.toString());
    }



    public static int part1(String[] input) {
        Pattern symbolRegex = Pattern.compile("\\p{Punct}");
        Pattern numberRegex = Pattern.compile("[1-9]([0-9]+)?");
        List<Integer> partNumbers = new ArrayList<>();
        List<Part> potentialParts = new ArrayList<>();
        List<Integer> symbols1 = new ArrayList<>();
        List<Integer> symbols2 = new ArrayList<>();
        List<Integer> symbols3 = new ArrayList<>();

        // Checking the first line
        potentialParts = findPotentialPartNumbersIn(input[0]);
        symbols1 = getSymbolsIndicesFrom(input[0]);
        symbols2 = getSymbolsIndicesFrom(input[1]);
        for (Part part : potentialParts) {
            var sym1iter = symbols1.listIterator();
            var sym2iter = symbols2.listIterator();
            while (sym1iter.hasNext() || sym2iter.hasNext()) {
                if (sym1iter.hasNext() && part.range().contains(sym1iter.next())) {
                    partNumbers.add(part.number());
                    break;
                } else if (sym2iter.hasNext() && part.range().contains(sym2iter.next())) {
                    partNumbers.add(part.number());
                    break;
                }
            }
        }

        for (int i = 1; i < input.length - 1; i++) {
            potentialParts = findPotentialPartNumbersIn(input[i]);
            symbols1 = getSymbolsIndicesFrom(input[i - 1]);
            symbols2 = getSymbolsIndicesFrom(input[i]);
            symbols3 = getSymbolsIndicesFrom(input[i + 1]);
            for (Part part : potentialParts) {
                var sym1iter = symbols1.listIterator();
                var sym2iter = symbols2.listIterator();
                var sym3iter = symbols3.listIterator();

                while (sym1iter.hasNext() || sym2iter.hasNext() || sym3iter.hasNext()) {
                    if (sym1iter.hasNext() && part.range().contains(sym1iter.next())) {
                        partNumbers.add(part.number());
                        break;
                    } else if (sym2iter.hasNext() && part.range().contains(sym2iter.next())) {
                        partNumbers.add(part.number());
                        break;
                    } else if (sym3iter.hasNext() && part.range().contains(sym3iter.next())) {
                        partNumbers.add(part.number());
                        break;
                    }
                }
            }
        }

        // Checking the last line
        symbols2 = getSymbolsIndicesFrom(input[input.length - 2]);
        symbols3 = getSymbolsIndicesFrom(input[input.length - 1]);
        potentialParts = findPotentialPartNumbersIn(input[input.length - 1]);
        for (Part part : potentialParts) {
            var sym2iter = symbols2.listIterator();
            var sym3iter = symbols3.listIterator();
            while (sym2iter.hasNext() || sym3iter.hasNext()) {
                if (sym2iter.hasNext() && part.range().contains(sym2iter.next())) {
                    partNumbers.add(part.number());
                    break;
                } else if (sym3iter.hasNext() && part.range().contains(sym3iter.next())){
                    partNumbers.add(part.number());
                    break;
                }
            }
        }

        return partNumbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<Part> findPotentialPartNumbersIn(String string) {
        List<Part> potentialPartNumbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("[1-9]([0-9]+)?").matcher(string);

        while(matcher.find()) {
            var match = matcher.group();
            var partNumber = Integer.parseInt(match);
            potentialPartNumbers.add(new Part(Integer.parseInt(match), Range.between(matcher.start() - 1, matcher.end())));
        }

        return potentialPartNumbers;
    }
    public static List<Integer> getSymbolsIndicesFrom(String string) {
        List<Integer> symbols = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\p{Punct}").matcher(string.replaceAll("\\.", " "));

        while (matcher.find())
            symbols.add(matcher.start());

        return symbols;
    }

    public static String[] loadPuzzleInput() {
        String path = System.getProperty("user.home")
                //+ "/Downloads/DayThree.txt";
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec3.txt";

        Path fp = Path.of(path);

        try {
            return Files.readString(fp).split("\n");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
