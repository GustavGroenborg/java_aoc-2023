import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class Dec4 {
    public static void main(String[] args) {
        String pip = loadPuzzleInput();
        System.out.println("The cards with winning numbers is worth: "
                + part1(pip));
        System.out.println("You end up with: " + part2(pip)
                + " " + "Scratchcards after a game");
    }

    public static int part2(String input) {
        List<Card> cards = Arrays.stream(input.split("\n"))
                .map(str -> str.replaceAll("Card\\s+\\d+:\\s+", "").split("\\|\\s+"))
                .map(strArr -> Arrays.stream(strArr)
                        .map(numArr -> Arrays.stream(numArr.split("\\s+"))
                                .mapToInt(Integer::valueOf)
                                .boxed()
                                .toList())
                        .toList())
                .map(nums -> nums.getLast().stream()
                        .filter(num -> nums.getFirst().contains(num))
                        .toList())
                .map(List::size)
                .map(Card::new)
                .toList();

        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.get(i).instances; j++) {
                for (int k = 1; k <= cards.get(i).matchingNumbers && k + i < cards.size(); k++)
                    cards.get(i + k).addCopy();
            }
        }

        return cards.stream()
                .mapToInt(card -> card.instances)
                .sum();
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
