import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toCollection;
import java.util.stream.Stream;

public class Dec9 {
    public static void main(String[] args) {
        var puzzleInput = getPuzzleInput();
        System.out.println("The sum of extrapolated values is: " + part1(puzzleInput));
        System.out.println("The sum of extrapolated previous values is: " + part2(puzzleInput));
    }

    public static Long part1(List<ArrayList<Long>> input) {
        return input.stream()
                .map(OASISReport::new)
                .mapToLong(OASISReport::getNextValue)
                .sum();
    }

    public static Long part2(List<ArrayList<Long>> input) {
        input.forEach(Collections::reverse);
        return part1(input);

    }

    public static List<ArrayList<Long>> getPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec9.txt";
        Path fp = Path.of(path);

        try {
            return Stream.of(Files.readString(fp).split("\n"))
                    .map(str -> Stream.of(str.split("\s"))
                            .map(Long::parseLong)
                            .collect(toCollection(ArrayList<Long>::new)))
                    .toList();
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
