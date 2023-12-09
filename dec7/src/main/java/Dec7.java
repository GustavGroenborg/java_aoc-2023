import java.nio.file.*;
import java.util.Arrays;


public class Dec7 {
    public static void main(String[] args) {
        String[] pip = getPuzzleInput();
        System.out.println("The total winnings are: " + part1(pip));
    }

    public static int part1(String[] pip) {
        CamelHand[] hands = Arrays.stream(pip)
                .map(CamelHand::new)
                .sorted(CamelHand::compareTo)
                .toArray(CamelHand[]::new);

        Arrays.stream(hands)
                .forEach(hand -> System.out.println(Arrays.toString(hand.cardValues)));
        int winnings = 0;
        for (int i = hands.length - 1; i >= 0; i--) {
            winnings += hands[i].bid * (i + 1);
        }

        return winnings;
    }

    public static String[] getPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec7.txt";

        Path fp = Path.of(path);

        try {
            return Files.readString(fp).split("\n");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
