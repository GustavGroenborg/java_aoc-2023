import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import org.apache.commons.lang3.Range;

public class Dec5 {
    public static void main(String[] args) {
        String pip = getPuzzleInput();
        System.out.println("The lowest location number that corresponds to one of the initial seed inputs is: "
                + part1(pip));

        System.out.println("The lowest number that corresponds to one of the initial seed pairs is: "
                + part2(pip));
    }

    public static long part1(String input) {
        List<ElfMap> maps = getElfMapsFrom(input);
        List<Long> seeds = getSeedsFrom(input);
        long lowestLocationNumber = Long.MAX_VALUE;

        for (var seed : seeds) {
            long locationNumber = ElfMap.mapSeedToLocation(maps, seed);
            if (locationNumber < lowestLocationNumber)
                lowestLocationNumber = locationNumber;
        }

        return lowestLocationNumber;
    }

    public static long part2(String input) {
        List<ElfMap> maps = getElfMapsFrom(input);
        List<long[]> seedPairs = getSeedPairsFrom(input);

        List<Long> lowestLocations = seedPairs.parallelStream()
                .map(pair -> {
                    long lowestLocationNumber = Long.MAX_VALUE;
                    System.out.println("\t"
                            + Thread.currentThread().getName() + ":"
                            + " " + "Mapping pair: " + "[" + pair[0] + ", " + pair[1] + "]");
                    for (long seed = pair[0]; seed < pair[0] + pair[1]; seed++) {
                        if (seed % 5_000_000 == 0) {
                            double pc = ( (seed - pair[0]) / (double) pair[1] ) * 100;
                            System.out.println("\t"
                                    + Thread.currentThread().getName() + ":"
                                    + " " + Math.floor(pc) + "% computed");
                        }
                        long locationNumber = ElfMap.mapSeedToLocation(maps, seed);
                        if (locationNumber < lowestLocationNumber)
                            lowestLocationNumber = locationNumber;
                    }
                    return lowestLocationNumber;
                })
                .toList();

        /*
        for (var pair : seedPairs) {
            System.out.println("Mapping pair: " + "[" + pair[0] + ", " + pair[1] + "]");
            for (long seed = pair[0]; seed < pair[0] + pair[1]; seed++) {
                if (seed % 1_000_000 == 0)
                    System.out.println("\tMapping seed: " + seed);
                long locationNumber = ElfMap.mapSeedToLocation(maps, seed);
                if (locationNumber < lowestLocationNumber)
                    lowestLocationNumber = locationNumber;
            }
        }
         */

        return lowestLocations.stream().mapToLong(v -> v).min().orElseThrow();
    }

    private static List<ElfMap> getElfMapsFrom(String input) {
        List<ElfMap> maps = new ArrayList<>();
        Matcher mapMatcher = Pattern.compile(Patterns.seedMap()).matcher(input);

        while (mapMatcher.find())
            maps.add(new ElfMap(mapMatcher.group()));

        return maps;
    }

    private static List<Long> getSeedsFrom(String input) {
        List<Long> seeds = new ArrayList<>();
        Matcher seedMatcher = Pattern.compile(Patterns.seed()).matcher(input);
        String seedInput;
        if (seedMatcher.find())
            seedInput = seedMatcher.group();
        else
            throw new Error("Could not match seed in: " + input);

        Matcher numberMatcher = Pattern.compile("\\d+").matcher(seedInput);
        while (numberMatcher.find())
            seeds.add(Long.parseLong(numberMatcher.group()));

        return seeds;
    }

    private static List<long[]> getSeedPairsFrom(String input) {
        Matcher seedInputMatcher = Pattern.compile(Patterns.seed()).matcher(input);
        if (!seedInputMatcher.find())
            throw new Error("Could not find seed input in: " + input);
        String seedInput = seedInputMatcher.group();

        List<long[]> seedPairs = new ArrayList<>();
        Matcher seedPairMatcher = Pattern.compile("\\d+\\s\\d+").matcher(seedInput);
        while (seedPairMatcher.find()) {
            String[] pair = seedPairMatcher.group().split("\\s");
            seedPairs.add(new long[]{ Long.parseLong(pair[0]), Long.parseLong(pair[1]) });
        }

        return seedPairs;
    }


    private static boolean rangesFullyContains(Range<Long> rangeToCheck, List<Range<Long>> ranges) {
        for (var range : ranges) {
            if (range.containsRange(rangeToCheck))
                return true;
        }

        return false;
    }

    public static String getPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec5.txt";
        Path fp = Path.of(path);

        try {
            return Files.readString(fp);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
