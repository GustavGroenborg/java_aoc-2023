import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.*;
import java.util.List;
import java.util.Arrays;

public class GameTests {

    @Test
    public void test_setRegex() {
        // Given
        String input = "Game 17: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red; 1 blue";
        List<String> targets = Arrays.asList(
                "8 green, 6 blue, 20 red",
                "5 blue, 4 red, 13 green",
                "5 green, 1 red",
                "1 blue"
                );

        // When
        Matcher matcher = Pattern.compile(Game.setRegex).matcher(input);
        List<String> outputs = new ArrayList<>();
        while (matcher.find())
            outputs.add(matcher.group());

        // Then
        assertEquals(targets.size(), outputs.size());

        assertEquals(targets.get(0), outputs.get(0));
        assertEquals(targets.get(1), outputs.get(1));
        assertEquals(targets.get(2), outputs.get(2));
    }


    @Test
    public void test_possibleGames() {
        // Given
        Limit limit = new Limit(12, 13, 14);
        List<String> possibleGames = Arrays.asList(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        );

        // Then
        possibleGames.forEach(game ->
                assertTrue(Game.GamePossibleWith(limit, game))
        );
    }

    @Test
    public void test_impossibleGames() {
        // Given
        Limit limit = new Limit(12, 13, 14);
        List<String> impossibleGames = Arrays.asList(
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
        );

        // Then
        assertFalse(Game.GamePossibleWith(limit, impossibleGames.get(0)));
        assertFalse(Game.GamePossibleWith(limit, impossibleGames.get(1)));
    }
}
