package games.cards;

// Source for symbols - https://stackoverflow.com/questions/64126796/how-can-i-display-these-cards-suits-symbols-in-red-and-black-in-the-windows-cons
public enum Suit {
    CLUB('♣'),
    DIAMOND('♦'),
    HEART('♥'),
    SPADE('♠');

    private final char value;

    private Suit(char val) {
        this.value = val;
    }

    public char getValue() {
        return this.value;
    }
}
