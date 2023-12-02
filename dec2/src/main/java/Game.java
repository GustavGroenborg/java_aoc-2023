import java.util.ArrayList;
import java.util.regex.*;
import java.util.List;

public class Game {
    private static final String colourRegex = String.format(
            "%s\\s[\\w]+", Colours.number
    );
    public static final String setRegex = String.format(
            "((%s,\\s)+)?%s((?=$)|(?=;))", Game.colourRegex, Game.colourRegex
    );
    public static final String gameNumberRegex = String.format(
            "(?<=Game\\s)%s", Colours.number
    );

    public static Boolean GamePossibleWith(Limit limit, String unparsedGame) {
        Matcher setMatcher = Pattern.compile(setRegex).matcher(unparsedGame);
        List<String> sets = new ArrayList<>();
        while (setMatcher.find())
            sets.add(setMatcher.group());

        for (String set : sets) {
            if (!setPossibleWith(limit, new GameSet(set)))
                return false;
        }

        return true;
    }

    public static Boolean setPossibleWith(Limit limit, GameSet set) {
        return (set.getRed() <= limit.red()
                && set.getGreen() <= limit.green()
                && set.getBlue() <= limit.blue());
    }


    public static int getGameNumberFrom(String string) {
        Matcher matcher = Pattern.compile(gameNumberRegex).matcher(string);
        if (matcher.find())
            return Integer.parseInt(matcher.group());
        else
            throw new Error("Could not find game number in: " + string);
    }

    public static GameSet findMinimumRequiredCubesIn(String unparsedGame) {
        int red = 0; int green = 0; int blue = 0;
        Matcher setMatcher = Pattern.compile(setRegex).matcher(unparsedGame);
        List<String> unparsedSets = new ArrayList<>();
        while (setMatcher.find())
            unparsedSets.add(setMatcher.group());
        List<GameSet> sets = unparsedSets.stream().map(GameSet::new).toList();

        for (GameSet set : sets) {
            if (set.getRed() > 0 && set.getRed() > red)
                red = set.getRed();
            if (set.getGreen() > 0 && set.getGreen() > green)
                green = set.getGreen();
            if (set.getBlue() > 0 && set.getBlue() > blue)
                blue = set.getBlue();
        }

        return new GameSet(red, green, blue);
    }
}
