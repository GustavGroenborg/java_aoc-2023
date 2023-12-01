import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.Arrays;

public class Dec1 {
    public static void main(String[] args) {
        String puzzleInput = Dec1.loadPuzzleInput();

        System.out.println("The sum of all calibration values, in part 1, is: "
                + part1(puzzleInput));

        System.out.println("The sum of all calibration values, in part 2, is: "
                + part2(puzzleInput));
    }

    public static int part1(String puzzleInput) {
        Integer sum = Arrays.stream(puzzleInput.split("\n"))
                .mapToInt(Dec1::findFirstAndLastNumericDigitsIn)
                .sum();

        return sum;
    }

    public static Integer findFirstAndLastNumericDigitsIn(String string) {
        List<Integer> digitsFound = findAllNumericDigitsIn(string);

        return (digitsFound.getFirst() * 10) + digitsFound.getLast();
    }


    public static List<Integer> findAllNumericDigitsIn(String string) {
        List<String> unparsedDigits = new ArrayList<>();
        Pattern digits = Pattern.compile("\\d");
        Matcher matcher = digits.matcher(string);

        while (matcher.find())
            unparsedDigits.add(matcher.group());

        return unparsedDigits.stream().mapToInt(Integer::parseInt).boxed().toList();
    }


    public static int part2(String puzzleInput) {
        Integer sum = Arrays.stream(puzzleInput.split("\n"))
                .mapToInt(Dec1::findFirstAndLastDigitsIn)
                .sum();
        return sum;
    }


    public static Integer findFirstAndLastDigitsIn(String string) {
        List<Integer> foundDigits = findAllDigitsIn(string);

        return (foundDigits.getFirst() * 10) + foundDigits.getLast();
    }


    public static List<Integer> findAllDigitsIn(String string) {
        String unscannedString = string;
        Pattern writtenDigits = Pattern.compile( "one|two|three|four|five|six|seven|eight|nine");
        Matcher writtenDigitsMatcher = writtenDigits.matcher(unscannedString);

        while (writtenDigitsMatcher.find()) {
            String match = writtenDigitsMatcher.group();
            int matchIndex = unscannedString.indexOf(match);
            unscannedString = unscannedString.substring(0, matchIndex)
                    + parseWrittenDigitFrom(match)
                    + unscannedString.substring(matchIndex + 1);
            writtenDigitsMatcher = writtenDigits.matcher(unscannedString);
        }

        Pattern digits = Pattern.compile("\\d");
        Matcher digitMatcher = digits.matcher(unscannedString);
        List<String> scannedDigits = new ArrayList<>();
        while (digitMatcher.find())
            scannedDigits.add(digitMatcher.group());

        List<Integer> parsedDigits = parseDigitsFrom(scannedDigits);
        return parsedDigits;
    }


    public static List<Integer> parseDigitsFrom(List<String> unparsedDigits) {
        List<Integer> parsedDigits = new ArrayList<>();

        for (String unparsedDigit : unparsedDigits) {
            try {
                parsedDigits.add(Integer.parseInt(unparsedDigit));
            } catch (Exception e) {
                if (e.getClass() == NumberFormatException.class) {
                    parsedDigits.add(parseWrittenDigitFrom(unparsedDigit));
                } else {
                    throw new Error(e.getMessage());
                }
            }
        }

        return parsedDigits;
    }


    /***
     * @param writtenDigit The written digit.
     */
    public static Integer parseWrittenDigitFrom(String writtenDigit) {
        List<String> writtenDigits = Arrays.asList(
                "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine");

        if (writtenDigits.contains(writtenDigit))
            return writtenDigits.indexOf(writtenDigit) + 1;

        throw new Error("Could not parse written digit. Got: " + writtenDigit);

    }


    public static String loadPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec1.txt";

        Path fp = Path.of(path);

        try {
            String inputString = Files.readString(fp);
            return inputString;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
