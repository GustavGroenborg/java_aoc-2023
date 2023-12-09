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


    public void test_part2ExampleInput() {
        // Given
        int target = 5905;

        // When
        int output = Dec7.part2(exampleInput);

        // Then
        assertEquals(target, output);
    }

    public void test_part2ExampleInput2() {
        // Given
        int target = 6839;
        String[] input = new String[]{
                "2345A 1",
                "J345A 2",
                "2345J 3",
                "32T3K 5",
                "KK677 7",
                "T3Q33 11",
                "Q2KJJ 13",
                "T3T3J 17",
                "Q2Q2Q 19",
                "2AAAA 23",
                "T55J5 29",
                "QQQJA 31",
                "KTJJT 34",
                "JJJJJ 37",
                "JJJJ2 41",
                "JAAAA 43",
                "AAAAJ 59",
                "AAAAA 61",
                "2JJJJ 53",
        };

        // When
        int output = Dec7.part2(input);

        // Then
        assertEquals(target, output);
    }

}
