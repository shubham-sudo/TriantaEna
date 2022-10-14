package games.cards;

public abstract class Card {
    private static int ID = 0;
    protected final int id;

    protected final String faceValue;
    protected final Suit suit;

    public Card(String faceValue, Suit suit) {
        this.id = ++ID;
        this.faceValue = faceValue;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Card[id=" + id + ", faceValue=" + faceValue + ", suit=" + suit.getValue() + "]";
    }

    abstract int value();
}
