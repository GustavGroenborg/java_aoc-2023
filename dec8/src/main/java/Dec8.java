import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Iterables;

public class Dec8 {
    public static void main(String[] args) {
        String pip = getPuzzleInput();
        System.out.println(part1(pip) + " " + "steps are required to reach ZZZ");
        System.out.println(part2(pip) + " " + "steps have to be taken before all nodes end on Z");
    }

    public static int part1(String pip) {
        var map = generateHashtableFrom(pip);
        var node = "AAA";
        var iterations = 0;
        List<Character> instructions = getInstructionsFrom(pip);
        var iter = instructions.iterator();
        while (!Objects.equals(node, "ZZZ")) {
            if (!iter.hasNext())
                iter = instructions.iterator();
            iterations++;
            node = map.get(node)[iter.next() == 'L' ? 0 : 1];
        }

        return iterations;
    }

    public static long part2(String pip) {
        var map = generateHashtableFrom(pip);
        Iterable<Character> instructionsCycle = Iterables.cycle(getInstructionsFrom(pip));
        var nodes = findNodesEndingIn('A', pip);
        var intervals = new ArrayList<Integer>();

        while (!nodes.isEmpty()) {
            var instructions = instructionsCycle.iterator();
            int iterations = 0;
            var node = nodes.removeFirst();

            while (!node.matches("[\\w]{2}Z") && instructions.hasNext()) {
                var instruction = instructions.next();
                iterations++;

                node = map.get(node)[instruction == 'L' ? 0 : 1];
            }
            intervals.add(iterations);
        }

        long iterations = (long) intervals.removeFirst();
        while (!intervals.isEmpty()) {
            iterations = lcm(iterations, intervals.removeFirst());
        }

        return iterations;
    }


    private static long lcm(long a, long b) {
        long high = Math.max(a, b);
        long low = Math.min(a, b);
        long lcm = high;
        while (lcm % low != 0)
            lcm += high;
        return lcm;
    }

    private static Hashtable<String, String[]> generateHashtableFrom(String string) {
        Hashtable<String, String[]> map = new Hashtable<>();
        Arrays.stream(string.split("\n"))
                .filter(str -> str.indexOf('=') != -1)
                .map(str -> str.replace(" = ", " "))
                .map(str -> str.replaceAll("[(,)]", "").split("\\s"))
                .forEach(arr -> map.put(arr[0], new String[]{ arr[1], arr[2] }));
        return map;
    }


    private static List<String> findNodesEndingIn(char c, String string) {
        List<String> nodes = new ArrayList<>();
        String regex = String.format("[\\w]{2}%c", c);
        Matcher matcher = Pattern.compile(regex).matcher(string);
        while (matcher.find())
            nodes.add(matcher.group());

        return nodes;
    }

    private static ArrayList<Character> getInstructionsFrom(String string) {
        Matcher matcher = Pattern.compile("[RL]+").matcher(string);
        if (!matcher.find())
            throw new Error("Could not find instructions in: " + string);

        return new ArrayList<>(matcher.group().chars().mapToObj(i -> Character.valueOf((char) i)).toList());
    }

    public static String getPuzzleInput() {
        Path fp = Path.of(System.getProperty("user.home")
                + "/AdventOfCode/aoc_2023/puzzleInputs/dec8.txt");

        try {
            return Files.readString(fp);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
