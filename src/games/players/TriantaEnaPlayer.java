package games.players;

import games.bank.Bank;
import games.inventory.TriantaEnaPlayerInventory;

public class TriantaEnaPlayer extends Player implements Comparable<TriantaEnaPlayer> {
    private final TriantaEnaPlayerInventory inventory;
    private final Bank bank;
    private double betValue;

    /**
     * Create TriantaEna game player object
     *
     * @param name name of the player
     * @param amount initial amount to open an account
     */
    public TriantaEnaPlayer(String name, double amount) {
        super(name);
        this.bank = new Bank(amount);
        this.inventory = new TriantaEnaPlayerInventory();
    }

    /**
     * Create a TriantaEna player object with zero initial value
     *
     * @param name name of the player
     */
    public TriantaEnaPlayer(String name){
        this(name, 0);
    }

    /**
     * Getter for the player inventory object
     *
     * @return players inventory
     */
    public TriantaEnaPlayerInventory inventory(){
        return this.inventory;
    }

    /**
     * Total amount of the players bank
     *
     * @return balance amount in players bank
     */
    public double amount(){
        return this.bank.amount();
    }

    /**
     * Print all the transactions happened on the players bank account
     */
    public void transactions(){
        System.out.println(this.bank.accountHistory());
    }

    /**
     * Place a bet for the player, also throws an error
     * if amount is insufficient in the bank
     *
     * @param value bet amount
     */
    public void placeBet(double value) {
        this.bank.debit(value);
        this.betValue = value;
    }

    /**
     * Getter for the bet value
     * @return betValue
     */
    public double betValue() {
        return this.betValue;
    }

    /**
     * Player loose game and the amount
     *
     * @param amount amount to be deducted
     */
    public void loose(double amount){
        this.bank.debit(amount);
        System.out.println(amount + " $ deducted from " + this.name() + "'s Account");
    }

    /**
     * Player won the game
     *
     * @param amount amount to be credited
     */
    public void win(double amount){
        this.bank.credit(amount);
        System.out.println(amount + " $ credited to " + this.name() + "'s Account");
    }

    /**
     * Reset the bet value for the player
     */
    public void resetBetValue() {
        this.betValue = 0;
    }

    /**
     * This implements the comparable interface method
     * To provide comparison on bank amount value
     *
     * @param o player to be compared
     * @return positive value if player amount is greater
     * than other player, negative otherwise and zero if same
     */
    @Override
    public int compareTo(TriantaEnaPlayer o) {
        double amount = o.amount();
        return (int) (this.amount() - amount);
    }

}
