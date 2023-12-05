import org.junit.Test;
import junit.framework.TestCase;

import java.nio.file.*;

public class Dec5Tests extends TestCase {
    private String exampleInput;
    public void setUp() {
        try {
            this.exampleInput = Files.readString(
                    Path.of(System.getProperty("user.home") + "/AdventOfCode/aoc_2023/exampleInputs/dec5.txt"));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public void test_Part1ExampleInput() {
        // Given
        long target = 35;

        // When
        long output = Dec5.part1(this.exampleInput);

        // Then
        assertEquals(target, output);
    }

    public void test_part2ExampleInput() {
        // Given
        long target = 46;

        // When
        long output = Dec5.part2(this.exampleInput);

        // Then
        assertEquals(target, output);
    }


}
