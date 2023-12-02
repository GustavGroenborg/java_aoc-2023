import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.*;

public class ColoursTests {

    @Test
    public void test_regexLargeNumber() {
        // Given
        String input = "Something12345somethingSomething";
        String target = "12345";

        // When
        Matcher matcher = Pattern.compile(Colours.number).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }

    @Test
    public void test_regexRed() {
        // Given
        String input = "Game 4: 1 green, 3 red, 6 blue;";
        String target = "3 red";

        // When
        Matcher matcher = Pattern.compile(Colours.red).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }

    @Test
    public void test_regexGreen() {
        // Given
        String input = "Game 4: 1 green, 3 red, 6 blue;";
        String target = "1 green";

        // When
        Matcher matcher = Pattern.compile(Colours.green).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }


    @Test
    public void test_regexBlue() {
        // Given
        String input = "Game 4: 1 green, 3 red, 6 blue;";
        String target = "6 blue";

        // When
        Matcher matcher = Pattern.compile(Colours.blue).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }


    @Test
    public void test_regexRedNumber() {
        // Given
        String input = "Game 4: 1 green, 3 red, 6 blue;";
        String target = "3";

        // When
        Matcher matcher = Pattern.compile(Colours.redNumber).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }

    @Test
    public void test_regexGreenNumber() {
        // Given
        String input = "Game 4: 1 green, 3 red, 6 blue;";
        String target = "1";

        // When
        Matcher matcher = Pattern.compile(Colours.greenNumber).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }

    @Test
    public void test_regexBlueNumber() {
        // Given
        String input = "Game 4: 1 green, 3 red, 6 blue;";
        String target = "6";

        // When
        Matcher matcher = Pattern.compile(Colours.blueNumber).matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }
}
