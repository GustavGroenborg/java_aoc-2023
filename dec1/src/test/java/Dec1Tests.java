import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Dec1Tests {

    @Test
    public void test_findFirstAndLastNumericDigits() {
        // Given
        String[] input = {
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet",
        };
        Integer[] targets = {
                12, 38, 15, 77
        };

        // When
        List<Integer> outputs = Arrays.stream(input)
                .mapToInt(Dec1::findFirstAndLastNumericDigitsIn)
                .boxed()
                .toList();

        // Then
        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs.get(i));
    }


    @Test
    public void test_findFirstAndLastDigits() {
        // Given
        String[] input = {
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen",
        };
        Integer[] targets = {
                29, 83, 13, 24, 42, 14, 76
        };

        // When
        List<Integer> outputs = Arrays.stream(input)
                .mapToInt(Dec1::findFirstAndLastDigitsIn)
                .boxed()
                .toList();

        // Then
        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs.get(i));
    }


    @Test
    public void test_findAllDigitsIn() {
        // Given
        String input = "1twothree4";
        Integer[] targets = { 1, 2, 3, 4 };

        // When
        List<Integer> outputs = Dec1.findAllDigitsIn(input);

        // Then
        if (targets.length != outputs.size())
            fail("Unexpected length of outputs."
                    + " " + "Expected: " + targets.length
                    + " " + "got: " + outputs.size());

        assertEquals(targets[0], outputs.get(0));
        assertEquals(targets[1], outputs.get(1));
        assertEquals(targets[2], outputs.get(2));
        assertEquals(targets[3], outputs.get(3));
    }


    @Test
    public void test_parseWrittenDigitAndNumericDigit() {
        // Given
        List<String> input = Arrays.asList("1", "two");
        Integer[] targets = { 1, 2 };

        // When
        List<Integer> outputs = Dec1.parseDigitsFrom(input);

        if (targets.length != outputs.size())
            fail("Unexpected length of outputs. Expected: " + targets.length
                    + " " + "got: " + outputs.size());

        // Then
        assertEquals(targets[0], outputs.get(0));
        assertEquals(targets[1], outputs.get(1));
    }


    @Test
    public void test_FindAllWrittenDigits() {
        // Given
        List<String> inputs = Arrays.asList(new String[]{
                "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine",
        });
        List<Integer> targets = IntStream.range(1, 10).boxed().toList();

        // When
        List<Integer> outputs = Dec1.parseDigitsFrom(inputs);

        // Then
        if (targets.size() != outputs.size())
            fail("Unexpected size of outputs."
                    + " " + "Expected: " + targets.size()
                    + " " + "got: " + outputs.size());

        for (int i = 0; i < targets.size(); i++)
            assertEquals(targets.get(i), outputs.get(i));
    }


    @Test
    public void test_part2ExampleInput() {
        // Given
        String input = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;
        Integer target = 281;

        // When
        Integer output = Dec1.part2(input);

        // Then
        assertEquals(target, output);
    }


    @Test
    public void test_findAllDigitsIn_findsAllNumericDigits() {
        // Given
        String input = "123456789";
        List<Integer> targets = IntStream.range(1,10).boxed().toList();

        // When
        List<Integer> outputs = Dec1.findAllDigitsIn(input);

        // Then
        if (targets.size() != outputs.size())
            fail("Unexpected size of outputs."
                    + " " + "Expected: " + targets.size()
                    + " " + "got: " + outputs.size());

        for (int i = 0; i < targets.size(); i++)
            assertEquals(targets.get(i), outputs.get(i));
    }

    @Test
    public void test_findAllDigitsIn_eighthree() {
        // Given
        String input = "eighthree";
        List<Integer> targets = Arrays.asList(8, 3);

        // When
        List<Integer> outputs = Dec1.findAllDigitsIn(input);

        // Then
        if (targets.size() != outputs.size())
            fail("Unexpected size of outputs."
                    + " " + "Expected: " + targets.size()
                    + " " + "got: " + outputs.size());

        for (int i = 0; i < targets.size(); i++)
            assertEquals(targets.get(i), outputs.get(i));
    }


    @Test
    public void test_stringReplacement() {
        // Given
        String input = "foo";
        String target = "oo";

        // When
        String output = input.replaceFirst("\\w", "");

        // Then
        assertEquals(target, output);
    }
}
