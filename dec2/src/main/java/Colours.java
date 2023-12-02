
public class Colours {
    public static final String number = "[1-9]([0-9]+)?";
    public static final String red = number + " " + "red";
    public static final String green = number + " " + "green";
    public static final String blue = number + " " + "blue";
    public static final String redNumber = String.format("%s(?=\\sred)", number);
    public static final String greenNumber = String.format("%s(?=\\sgreen)", number);
    public static final String blueNumber = String.format("%s(?=\\sblue)", number);
}
