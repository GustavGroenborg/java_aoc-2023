import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;

public class Dec1Tests {

    @Test
    public void test_digitsFound() {
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
                .mapToInt(Dec1::findDigitsIn)
                .boxed()
                .toList();

        // Then
        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs.get(i));
    }
}
