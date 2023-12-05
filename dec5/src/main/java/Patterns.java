public class Patterns {

    public static String seed() {
        String seedNumbersRegex = "(?<=seeds:\\s)(\\d+\\s?)+(?=[\n]{2})";
        return seedNumbersRegex;
    }

    public static String seedMap() {
        return "(?<=:\n)(\\d+[\\s\\n])+\\d+((?=[\n]{2})|(?=\n\\Z))";
    }
}
