import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class PatternsTests {
    private String input = """
            seeds: 12 34 56 78 9
                        
            seed-to-soil map:
            12 34 56
            89 10 11
                        
            soil-to-fertilizer map:
            12 13 14
            15 16 17
            18 19 2
                        
            fertilizer-to-water map:
            0 21 2
            2 23 24
            25 26 27
            28 29 30
                        
            water-to-light map:
            31 32 33
            34 35 36
                        
            light-to-temperature map:
            37 38 39
            40 41 42
            43 44 45
                        
            temperature-to-humidity map:
            46 47 48
            49 50 51
                        
            humidity-to-location map:
            52 53 54
            55 56 57
            """;


    @Test
    public void test_seed() {
        // Given
        String target = "12 34 56 78 9";

        // When
        Matcher m = Pattern.compile(Patterns.seed()).matcher(this.input);

        // Then
        assertTrue(m.find());
        assertEquals(target, m.group());
    }

    @Test
    public void test_seedMap() {
        // Given
        String[] targets = {
                "12 34 56\n89 10 11",
                "12 13 14\n15 16 17\n18 19 2",
                "0 21 2\n2 23 24\n25 26 27\n28 29 30",
                "31 32 33\n34 35 36",
                "37 38 39\n40 41 42\n43 44 45",
                "46 47 48\n49 50 51",
                "52 53 54\n55 56 57",
        };

        // When
        Matcher m = Pattern.compile(Patterns.seedMap()).matcher(this.input);
        List<String> outputs = new ArrayList<>();

        while (m.find())
            outputs.add(m.group());

        // Then
        assertEquals(targets.length, outputs.size());

        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs.get(i));
    }
}
