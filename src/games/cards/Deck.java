package games.cards;

import java.util.*;

public class Deck<T extends Card> {
    private final Stack<T> cards;

    public Deck() {
        this.cards = new Stack<>();
    }

    public void addCards(List<T> newCards) {
        for (T card : newCards){
            addCard(card);
        }
        shuffle();
    }

    public void addCard(T newCard) {
        cards.push(newCard);
    }

    public int remainingCards() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public T drawCard() throws EmptyStackException {
        if (remainingCards() <= 0) {
            throw new EmptyStackException();
        }
        return cards.pop();
    }
}
