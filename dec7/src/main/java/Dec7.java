import java.nio.file.*;
import java.util.Arrays;


public class Dec7 {
    public static void main(String[] args) {
        String[] pip = getPuzzleInput();
        System.out.println("The total winnings are: " + part1(pip));
        System.out.println("The total new winnings are: " + part2(pip));
    }

    public static int part1(String[] pip) {
        CamelHand[] hands = Arrays.stream(pip)
                .map(string -> new CamelHand(string, false))
                .sorted(CamelHand::compareTo)
                .toArray(CamelHand[]::new);

        int winnings = 0;
        for (int i = hands.length - 1; i >= 0; i--) {
            winnings += hands[i].bid * (i + 1);
        }

        return winnings;
    }


    public static int part2(String[] pip) {
        CamelHand[] hands = Arrays.stream(pip)
                .map(string -> new CamelHand(string, true))
                .sorted(CamelHand::compareTo)
                .toArray(CamelHand[]::new);

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
