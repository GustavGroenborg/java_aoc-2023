import java.util.ArrayList;
import java.util.List;

public class OASISReport {
    private final ArrayList<ArrayList<Long>> sequences;

    OASISReport(ArrayList<Long> history) {
        sequences = new ArrayList<>();
        sequences.add(history);
        while (true) {
            var row = new ArrayList<Long>();
            if (sequences.getLast().size() == 1) {
                sequences.add(new ArrayList<>(List.of(0L)));
                break;
            } else {
                for (int i = 1; i < sequences.getLast().size(); i++)
                    row.add(sequences.getLast().get(i) - sequences.getLast().get(i - 1));
                sequences.add(row);

                if (row.stream().allMatch(x -> x == 0)) break;
            }
        }
    }

    public Long getNextValue() {
        return this.sequences.stream()
                .mapToLong(ArrayList::getLast)
                .sum();
    }
}
