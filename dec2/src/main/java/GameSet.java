import java.util.regex.*;

public class GameSet {
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    public GameSet(String unparsedSet) {
        if (unparsedSet.contains("red")) {
            Matcher redNumberMatcher =
                    Pattern.compile(Colours.redNumber).matcher(unparsedSet);
            if (redNumberMatcher.find())
                this.red = Integer.parseInt(redNumberMatcher.group());
            else
                throw new Error("Unexpected error. Could not find red number in: "
                        + unparsedSet);
        }
        if (unparsedSet.contains("green")) {
            Matcher greenNumberMatcher =
                    Pattern.compile(Colours.greenNumber).matcher(unparsedSet);
            if (greenNumberMatcher.find())
                this.green = Integer.parseInt(greenNumberMatcher.group());
            else
                throw new Error("Unexpected error. Could not find red number in: "
                        + unparsedSet);
        }
        if (unparsedSet.contains("blue")) {
            Matcher blueNumberMatcher =
                    Pattern.compile(Colours.blueNumber).matcher(unparsedSet);
            if (blueNumberMatcher.find())
                this.blue = Integer.parseInt(blueNumberMatcher.group());
            else
                throw new Error("Unexpected error. Could not find red number in: "
                        + unparsedSet);
        }
    }

    public GameSet(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static int powerOf(GameSet set) {
        return set.getRed() * set.getGreen() * set.getBlue();
    }

    public int getRed() { return this.red; }
    public int getGreen() { return this.green; }
    public int getBlue() { return this.blue; }
}