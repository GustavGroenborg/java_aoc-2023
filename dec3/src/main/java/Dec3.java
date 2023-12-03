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
                + "/Downloads/DayThree.txt";
                //+ "/AdventOfCode/aoc_2023/puzzleInputs/dec3.txt";

        Path fp = Path.of(path);

        try {
            return Files.readString(fp).split("\n");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
