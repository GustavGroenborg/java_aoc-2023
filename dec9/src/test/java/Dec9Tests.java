import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class Dec9Tests extends TestCase {
    private List<ArrayList<Long>> exampleInput;

    public void setUp() {
        try {
            String path = System.getProperty("user.home")
                    + "/AdventOfCode/aoc_2023/exampleInputs/dec9.txt";
            this.exampleInput = Stream.of(Files.readString(Path.of(path)).split("\n"))
                    .map((str) -> Stream.of(str.split("\s"))
                            .map(Long::parseLong)
                            .collect(toCollection(ArrayList<Long>::new)))
                    .toList();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

    public void test_exampleSequence1() {
        // Given
        Long target = 18L;

        // When
        ArrayList<Long> input = exampleInput.getFirst();
        Long output = new OASISReport(input).getNextValue();

        // Then
        assertEquals(target, output);
    }

    public void test_exampleSequence2() {
        // Given
        Long target = 28L;

        // When
        ArrayList<Long> input = exampleInput.get(1);
        Long output = new OASISReport(input).getNextValue();

        // Then
        assertEquals(target, output);
    }

    public void test_exampleInput() {
        // Given
        Long target = 114L;

        // When
        Long output = exampleInput.stream()
                .map(OASISReport::new)
                .mapToLong(OASISReport::getNextValue)
                .sum();

        // Then
        assertEquals(target, output);
    }

    public void test_part2exampleInput() {
        // Given
        Long target = 5L;
        ArrayList<Long> input = new ArrayList<>(List.of(10L, 13L, 16L, 21L, 30L, 45L));

        // When
        Collections.reverse(input);
        Long output = new OASISReport(input).getNextValue();

        // Then
        assertEquals(target, output);
    }
}
