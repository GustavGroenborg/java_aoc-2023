import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class Dec3Tests {
    @Test
    public void test_part1ExampleInput() {
        // Given
        int target = 4361;
        String string = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """;
        String[] input = string.split("\n");

        // When
        int output = Dec3.part1(input);

        // Then
        assertEquals(target, output);
    }


    @Test
    public void test_part1AnotherExampleInput() {
        // Given
        int target = 925;
        String string = """
                12.......*..
                +.........34
                .......-12..
                ..78........
                ..*....60...
                78.........9
                .5.....23..$
                8...90*12...
                ............
                2.2......12.
                .*.........*
                1.1..503+.56
                """;
        String[] input = string.split("\n");

        // When
        int output = Dec3.part1(input);

        // Then
        assertEquals(target, output);
    }


    @Test
    public void test_part2TrainingInput() {
        // Given
        int target = 1;
        String string = """
                ...1.......
                ...*.......
                ....1......
                ...........
                .....576...
                ......*....
                ...........
                ......*....
                .....495...
                """;
        char[][] input = Arrays.stream(string.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);

        // When
        int output = Dec3.part2(input);

        // Then
        assertEquals(target, output);
    }
}
