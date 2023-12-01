import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.Arrays;

public class Dec1 {
    public static void main(String[] args) {
        String puzzleInput = Dec1.loadPuzzleInput();

        System.out.println("The sum of all calibration values is: "
                + part1(puzzleInput));
    }

    public static int part1(String puzzleInput) {
        Integer sum = Arrays.stream(puzzleInput.split("\n"))
                .mapToInt(Dec1::findDigitsIn)
                .sum();

        return sum;
    }

    public static Integer findDigitsIn(String string) {
        List<String> digitsFound = new ArrayList<>();
        Pattern digits = Pattern.compile("[1-9]");
        Matcher matcher = digits.matcher(string);

        while (matcher.find())
            digitsFound.add(matcher.group(0));

        String unparsedDigits = digitsFound.getFirst() + digitsFound.getLast();

        return Integer.parseInt(unparsedDigits);
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
