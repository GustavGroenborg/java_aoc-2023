import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dec8 {
    public static void main(String[] args) {
        String pip = getPuzzleInput();
        System.out.println(part1(pip) + " " + "steps are required to reach ZZZ");
    }

    public static int part1(String pip) {
        Hashtable<String, String[]> map = new Hashtable<>();
        Arrays.stream(pip.split("\n"))
                .filter(str -> str.indexOf('=') != -1)
                .map(str -> str.replace(" = ", " "))
                .map(str -> str.replaceAll("[(,)]", "").split("\\s"))
                .forEach(arr -> map.put(arr[0], new String[]{ arr[1], arr[2] }));
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

    private static List<Character> getInstructionsFrom(String string) {
        Matcher matcher = Pattern.compile("[RL]+").matcher(string);
        if (!matcher.find())
            throw new Error("Could not find instructions in: " + string);

        return matcher.group().chars().mapToObj(i -> Character.valueOf((char) i)).toList();
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
