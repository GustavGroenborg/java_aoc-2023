import java.util.Arrays;
import java.util.Objects;
import java.util.regex.*;

public class CamelHand implements Comparable<CamelHand> {
    private final int MAX_CARDS = 5;
    CardType type;
    Integer[] cardValues = new Integer[MAX_CARDS];
    Integer bid;

    public CamelHand(String input, boolean part2) {
        final int JOKER = 11;
        this.bid = getBidFrom(input);
        char[] unparsedCards = input.split("\\s")[0].toCharArray();

        if (unparsedCards.length != cardValues.length)
            throw new Error("Too many unparsed cards");

        Integer[] cardStacks = new Integer[15];
        Arrays.fill(cardStacks, 0);
        for (int i = 0; i < unparsedCards.length; i++) {
            this.cardValues[i] = getValueFrom(unparsedCards[i]);
            cardStacks[getValueFrom(unparsedCards[i])]++;
        }

        if (part2 && cardStacks[JOKER] > 0) {
            int[][] occurrences = Arrays.stream(cardValues)
                    .distinct()
                    .filter(val -> val != JOKER)
                    .map(card -> new int[]{ card, cardStacks[card] })
                    .sorted((arr1, arr2) -> Integer.compare(arr2[1], arr1[1]))
                    .toArray(int[][]::new);

            for (int[] occurrence : occurrences) {
                while (cardStacks[JOKER] > 0 && occurrence[1] < 5) {
                    occurrence[1]++;
                    cardStacks[JOKER]--;
                }
                cardStacks[occurrence[0]] = occurrence[1];
            }

            // Making the joker the weakest card.
            cardValues = Arrays.stream(cardValues)
                    .map(val -> val == 11 ? 1 : val)
                    .toArray(Integer[]::new);
        }


        var currentCardType = CardType.highCard;
        for (int i = cardStacks.length - 1; i > 1; i--) {
            if (cardStacks[i] == 0)
                continue;

            switch (cardStacks[i]) {
                case 5:
                    currentCardType = CardType.fiveOfAKind;
                    break;
                case 4:
                    currentCardType = CardType.fourOfAKind;
                    break;
                case 3:
                    if (currentCardType == CardType.onePair)
                        currentCardType = CardType.fullHouse;
                    else
                        currentCardType = CardType.threeOfAKind;
                    break;
                case 2:
                    if (currentCardType == CardType.onePair)
                        currentCardType = CardType.twoPairs;
                    else if (currentCardType == CardType.threeOfAKind)
                        currentCardType = CardType.fullHouse;
                    else
                        currentCardType = CardType.onePair;
                    break;
                case 1:
                default:
                    break;
            }
        }

        this.type = currentCardType;
    }


    private static int getBidFrom(String string) {
        Matcher bidMatcher = Pattern.compile("(?<=\\s)\\d+(?=$)").matcher(string);

        if (!bidMatcher.find())
            throw new Error("Could not match bid in: " + string);

        return Integer.parseInt(bidMatcher.group());
    }

    private static int getValueFrom(char value) {
        switch (value) {
            case 'A':
                return 14;
            case 'K':
                return 13;
            case 'Q':
                return 12;
            case 'J':
                return 11;
            case 'T':
                return 10;
            default:
                try {
                    return Integer.parseInt(String.valueOf(value));
                } catch (NumberFormatException e) {
                    throw new Error("Could not parse value as integer: " + value);
                } catch (Exception e) {
                    throw new Error(e.getMessage());
                }
        }
    }

    @Override
    public int compareTo(CamelHand otherHand) {
        if (this.type.value > otherHand.type.value)
            return 1;
        else if (this.type.value < otherHand.type.value)
            return -1;
        else {
            for (int i = 0; i < MAX_CARDS; i++) {
                if (this.cardValues[i] > otherHand.cardValues[i])
                    return 1;
                else if (this.cardValues[i] < otherHand.cardValues[i])
                    return -1;
            }
        }
        return 0;
    }
}
