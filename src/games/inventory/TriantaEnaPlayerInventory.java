package games.inventory;

import games.cards.*;

import java.util.*;


public class TriantaEnaPlayerInventory extends Inventory<TriantaEnaCard>{

    /**
     * Create a new inventory and initialize items
     */
    public TriantaEnaPlayerInventory(){
        super();
        this.items = new ArrayList<>();
    }

    /**
     * Getter for the items of the inventory
     *
     * @return items list
     */
    public List<TriantaEnaCard> cards(){
        return this.items;
    }

    /**
     * Add new item into the inventory list
     *
     * @param item item to be added
     */
    @Override
    public void addItem(TriantaEnaCard item) {
        this.items.add(item);
    }

    /**
     * Reset the inventory
     */
    @Override
    public void reset() {
        this.items = new ArrayList<>();
    }

    /**
     * Display all visible items of the inventory
     *
     * @param name name of the player to show print statement
     */
    @Override
    public void display(String name) {
        System.out.println("**************** " + name + " Cards ****************");
        for (TriantaEnaCard card : this.items){
            if (card.isFaceUp()) {
                System.out.println("\t" + card);
            } else {
                System.out.println("\t" + card.hiddenString());
            }
        }
        System.out.println("**********************************************");
    }

    /**
     * Display all items (including hidden) of the inventory
     *
     * @param name name of the player for print statement
     */
    public void playerDisplay(String name){
        System.out.println("**************** " + name + " Cards ****************");
        for (TriantaEnaCard card : this.items){
            System.out.println("\t" + card);
        }
        System.out.println("**********************************************");
    }

    /**
     * Total max score of the inventory
     *
     * @return total value of the items in the inventory
     */
    public int score(){
        int score = 0;
        for (TriantaEnaCard card : this.items){
            score += card.value();
        }
        return score;
    }

    /**
     * Total min score of the inventory
     *
     * @return total value of the items in the inventory
     */
    public int minScore(){
        boolean isAceUsed = false;
        int score = 0;

        for (TriantaEnaCard card : this.items){
            if (card.isAce() && !isAceUsed){
                score += card.minValue();
                isAceUsed = true;
            } else {
                score += card.value();
            }
        }
        return score;
    }
}
