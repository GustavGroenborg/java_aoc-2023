import junit.framework.TestCase;
import java.nio.file.*;

public class Dec7Tests extends TestCase {
    private String[] exampleInput;

    public void setUp() {
        try {
            String path = System.getProperty("user.home")
                    + "/AdventOfCode/aoc_2023/exampleInputs/dec7.txt";
            this.exampleInput = Files.readString(Path.of(path)).split("\n");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public void test_part1ExampleInput() {
        // Given
        int target = 6440;

        // When
        int output = Dec7.part1(exampleInput);

        // Then
        assertEquals(target, output);
    }
}
