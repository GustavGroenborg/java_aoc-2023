import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ElfKeyTests {
    @Test
    public void testProbeWithRange2() {
        // Given
        String input = "50 98 2";
        long[] inputSeeds = { 97, 98, 99, 100};
        Long[] targets = { -1L, 50L, 51L, -1L };

        // When
        ElfKey e = new ElfKey(input);
        List<Long> outputs = new ArrayList<>();
        for (var num : inputSeeds)
            outputs.add(e.probe(num));

        // Then
        assertEquals(targets.length, outputs.size());

        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs.get(i));

    }
}
