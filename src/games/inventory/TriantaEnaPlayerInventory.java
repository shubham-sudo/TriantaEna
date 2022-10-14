package games.inventory;

import games.cards.*;

import java.util.*;


public class TriantaEnaPlayerInventory extends Inventory<TriantaEnaCard>{
    public TriantaEnaPlayerInventory(){
        super();
        this.items = new ArrayList<>();
    }

    public List<TriantaEnaCard> cards(){
        return this.items;
    }

    public void  addItems(ArrayList<TriantaEnaCard> cards){
        for (TriantaEnaCard card : cards){
            this.addItem(card);
        }
    }

    @Override
    public void addItem(TriantaEnaCard item) {
        this.items.add(item);
    }

    @Override
    public void reset() {
        this.items = new ArrayList<>();
    }

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

    public void playerDisplay(String name){
        System.out.println("**************** " + name + " Cards ****************");
        for (TriantaEnaCard card : this.items){
            System.out.println("\t" + card);
        }
        System.out.println("**********************************************");
    }

    public int score(){
        int score = 0;
        for (TriantaEnaCard card : this.items){
            score += card.value();
        }
        return score;
    }

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
