public enum CardType {
    fiveOfAKind(7),
    fourOfAKind(6),
    fullHouse(5),
    threeOfAKind(4),
    twoPairs(3),
    onePair(2),
    highCard(1);

    public final Integer value;
    private CardType(Integer value) { this.value = value; }
}
