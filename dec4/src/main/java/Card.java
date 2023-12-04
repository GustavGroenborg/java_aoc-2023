import java.util.List;

public class Card {
    public final int matchingNumbers;
    public int instances = 1;

    public Card(int matches) {
        this.matchingNumbers = matches;
    }

    public void addCopy() {
        instances++;
    }
}
