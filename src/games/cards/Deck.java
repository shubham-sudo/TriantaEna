package games.cards;

import java.util.*;

public class Deck<T extends Card> {
    private final Stack<T> cards;

    /**
     * Create a deck stack of cards
     */
    public Deck() {
        this.cards = new Stack<>();
    }

    /**
     * Add cards to the deck
     *
     * @param newCards new list of cards
     */
    public void addCards(List<T> newCards) {
        for (T card : newCards){
            addCard(card);
        }
        shuffle();
    }

    /**
     * Add card to the stack
     *
     * @param newCard new card to be added
     */
    public void addCard(T newCard) {
        cards.push(newCard);
    }

    /**
     * Number of remaining cards in the deck
     *
     * @return size of the deck
     */
    public int remainingCards() {
        return cards.size();
    }

    /**
     * Shuffle the cards of deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Pop one card from the deck and return it
     *
     * @return card which is drawn from stack
     * @throws EmptyStackException if the deck is empty
     */
    public T drawCard() throws EmptyStackException {
        if (remainingCards() <= 0) {
            throw new EmptyStackException();
        }
        return cards.pop();
    }
}
