import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class Dec6 {

    public static void main(String[] args) {
        System.out.println("Part 1: " + part1());
        System.out.println("Part 2: " + part2());
    }

    public static Integer part1() {
        Integer[][] inputs = {
            {44, 208},
            {80, 1581},
            {65, 1050},
            {72, 1102},
        };

        return getPossibleSolutionsFromInput(inputs);
    }

    public static Long part2() {
        Long[][] input = { {44806572L, 208158110501102L} };
        return getPossibleSolutionsFromInput(input);
    }

    public static Long getPossibleSolutionsFromInput(Long[][] input) {
        AtomicReference<Long> product = new AtomicReference(1L);

        Arrays.stream(input)
                .map(nums -> possibleSolutions(nums[0], nums[1]))
                .forEach(v -> product.getAndUpdate(p -> p *= v));

        return product.get();
    }

    public static Integer getPossibleSolutionsFromInput(Integer[][] input) {
        AtomicReference<Integer> product = new AtomicReference(1);

        Arrays.stream(input)
                .map(nums -> possibleSolutions(nums[0], nums[1]))
                .forEach(v -> product.getAndUpdate(p -> p *= v));

        return product.get();
    }

    public static long possibleSolutions(long time, long distance) {
        double d = (time * time - (4.0 * distance));
        double maxHold = (time + Math.sqrt(d)) / 2.0;
        double minHold = (time - Math.sqrt(d)) / 2.0;

        return (long) (Math.ceil(maxHold - 1) - Math.floor(minHold + 1)) + 1;
    }

    public static int possibleSolutions(int time, int distance) {
        double d = (time * time - (4.0 * distance));
        double maxHold = (time + Math.sqrt(d)) / 2.0;
        double minHold = (time - Math.sqrt(d)) / 2.0;

        return (int) (Math.ceil(maxHold - 1) - Math.floor(minHold + 1)) + 1;
    }
}
