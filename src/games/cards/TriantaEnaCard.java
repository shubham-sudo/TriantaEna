package games.cards;

import java.util.HashMap;

public class TriantaEnaCard extends Card {
    private boolean isFaceUp = false;
    public static final String[] faceValues = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "J", "K" };
    public static final HashMap<String, Integer> faceValueToValue = new HashMap<String, Integer>() {
        {
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("10", 10);
            put("Q", 10);
            put("J", 10);
            put("K", 10);
        }
    };

    /**
     * Create new TriantaEna card
     *
     * @param faceValue face value of the card
     * @param suit suit it belongs to
     */
    public TriantaEnaCard(String faceValue, Suit suit) {
        super(faceValue, suit);
    }

    /**
     * Is case visible to everyone
     *
     * @return true if not hidden, false otherwise
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Set card visible to everyone
     */
    public void setFaceUp() {
        this.isFaceUp = true;
    }

    /**
     * Check if card is ace "A"
     *
     * @return true if Ace, false otherwise
     */
    public boolean isAce() {
        return this.faceValue == "A";
    }

    /**
     * Face value of the TriantaEna card
     *
     * @return integer face value of the card
     */
    @Override
    public int value() {
        if (isAce()) {
            return 11;
        }
        return faceValueToValue.get(this.faceValue);
    }

    /**
     * Minimum face value we can use for the card
     *
     * @return min face value of the card
     */
    public int minValue() {
        if (isAce()) {
            return 1;
        }
        return faceValueToValue.get(this.faceValue);
    }

    /**
     * String representation of the hidden cards
     *
     * @return string value
     */
    public String hiddenString(){
        return "Hidden Card[id=" + this.id + ", faceValue=***, suit=***]";
    }
}
