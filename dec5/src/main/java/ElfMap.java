import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElfMap {
    public List<ElfKey> keys;

    public ElfMap(String mapInput) {
        this.keys = Arrays.stream(mapInput.split("\n"))
                .map(ElfKey::new)
                .toList();
    }

    public long map(long num) {
        for (var key : keys) {
            long newNum = key.probe(num);
            if (newNum != - 1)
                return newNum;
        }

        return num;
    }


    public static long mapSeedToLocation(List<ElfMap> maps, long seed) {
        long mappedNum = seed;

        for (var map : maps) {
            mappedNum = map.map(mappedNum);
        }

        return mappedNum;
    }

}
