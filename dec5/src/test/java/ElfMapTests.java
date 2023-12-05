import org.junit.Test;
import junit.framework.TestCase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.List;

import static org.junit.Assert.*;

public class ElfMapTests extends TestCase {
    private String exampleInput;
    private List<ElfMap> maps;

    public void setUp() {
        try {
            this.exampleInput = Files.readString(
                    Path.of(System.getProperty("user.home") + "/AdventOfCode/aoc_2023/exampleInputs/dec5.txt"));
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }

        Matcher mapMatcher = Pattern.compile(Patterns.seedMap()).matcher(exampleInput);
        this.maps = new ArrayList<>();
        while (mapMatcher.find())
            maps.add( new ElfMap(mapMatcher.group()));
    }

    @Test
    public void test_map79() {
        // Given
        long input = 79;
        long target = 82;

        // When
        long output = ElfMap.mapSeedToLocation(this.maps, input);

        // Then
        assertEquals(target, output);
    }

    public void test_map14() {
        // Given
        long input = 14;
        long target = 43;

        // When
        long output = ElfMap.mapSeedToLocation(this.maps, input);

        // Then
        assertEquals(target, output);
    }

    public void test_map13() {
        // Given
        long input = 13;
        long target = 35;

        // When
        long output = ElfMap.mapSeedToLocation(this.maps, input);

        // Then
        assertEquals(target, output);
    }
}
