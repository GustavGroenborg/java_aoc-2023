import java.nio.file.*;
import java.util.List;
import java.util.Arrays;

public class Dec2 {
    public static void main(String[] args) {
        List<String> puzzleInput = Arrays.asList(loadPuzzleInput());
        Limit part1Limit = new Limit(12, 13, 14);

        System.out.println("The sum of possible game IDs is: "
                + part1(puzzleInput, part1Limit));

        System.out.println("The sum of powers of minimum game qubes in sets is: "
                + part2(puzzleInput));
    }

    public static int part1(List<String> games, Limit limit) {
        return games.stream()
                .filter(game -> Game.GamePossibleWith(limit, game))
                .mapToInt(Game::getGameNumberFrom)
                .sum();
    }

    public static int part2(List<String> games) {
        return games.stream()
                .map(Game::findMinimumRequiredCubesIn)
                .mapToInt(GameSet::powerOf)
                .sum();
    }

    public static String[] loadPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec2.txt";
        Path fp = Path.of(path);

        try {
            return Files.readString(fp).split("\n");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
