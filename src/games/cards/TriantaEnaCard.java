package games.cards;

import java.util.HashMap;

public class TriantaEnaCard extends Card {
    private boolean isFaceUp = false;
    public static final String[] faceValues = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "J", "K" };
    public static final HashMap<String, Integer> faceValueToValue = new HashMap<>() {
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

    public TriantaEnaCard(String faceValue, Suit suit) {
        super(faceValue, suit);
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp() {
        this.isFaceUp = true;
    }


    public boolean isAce() {
        return this.faceValue == "A";
    }

    @Override
    public int value() {
        if (isAce()) {
            return 11;
        }
        return faceValueToValue.get(this.faceValue);
    }

    public int minValue() {
        if (isAce()) {
            return 1;
        }
        return faceValueToValue.get(this.faceValue);
    }

    public String hiddenString(){
        return "Hidden Card[id=" + this.id + ", faceValue=***, suit=***]";
    }
}
