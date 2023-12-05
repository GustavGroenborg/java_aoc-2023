import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.List;

public class ElfKey {
    public final Range<Long> destinationRange;
    public final Range<Long> sourceRange;

    public ElfKey(String unparsedMap) {
        final int EXPECTED_LENGTH = 3;
        List<Long> parsedNumbers = Arrays.stream(unparsedMap.split("\\s"))
                .map(Long::parseLong)
                .toList();

        if (parsedNumbers.size() != EXPECTED_LENGTH)
            throw new Error("Unexpected length of parsedNumbers. Expected: "
                    + EXPECTED_LENGTH + " " + "got: " + parsedNumbers.size());

        long sourceStart = parsedNumbers.get(1);
        long destinationStart = parsedNumbers.getFirst();

        // -1 to make range [start, end) instead of [start, end]
        destinationRange = Range.between(destinationStart, destinationStart + parsedNumbers.getLast() - 1);
        sourceRange = Range.between(sourceStart, sourceStart + parsedNumbers.getLast() - 1);
    }

    public long probe(long number) {
        if (sourceRange.contains(number)) {
            long rangeDelta = number - sourceRange.getMinimum();
            return destinationRange.getMinimum() + rangeDelta;
        }

        return -1L;
    }
}
