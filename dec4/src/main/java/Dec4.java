import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class Dec4 {
    public static void main(String[] args) {
        String pip = loadPuzzleInput();
        System.out.println("The cards with winning numbers is worth: "
                + part1(pip));
    }

    public static int part1(String input) {
        return Arrays.stream(input.split("\n"))
                .map(str -> str.replaceAll("Card\\s+\\d+:\\s+", ""))
                .map(str -> str.split("\\|\\s+"))
                .map(strArr -> Arrays.stream(strArr)
                        .map(numArr -> Arrays.stream(numArr.split("\\s+"))
                                .mapToInt(Integer::valueOf)
                                .boxed()
                                .toList())
                        .toList())
                .mapToInt(Dec4::cardValue)
                .sum();
    }

    public static int cardValue(List<List<Integer>> cards) {
        int cardValue = 0;
        for (Integer num : cards.getLast()) {
            if (cards.getFirst().contains(num))
                cardValue += (cardValue == 0) ? 1 : cardValue;
        }

        return cardValue;
    }

    public static String loadPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec4.txt";
        Path fp = Path.of(path);

        try {
            return Files.readString(fp);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
