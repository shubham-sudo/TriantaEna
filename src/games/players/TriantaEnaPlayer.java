package games.players;

import games.bank.Bank;
import games.inventory.TriantaEnaPlayerInventory;

public class TriantaEnaPlayer extends Player implements Comparable<TriantaEnaPlayer> {
    private final TriantaEnaPlayerInventory inventory;
    private final Bank bank;
//    private double amount;
    private double betValue;

    public TriantaEnaPlayer(String name, double amount) {
        super(name);
        this.bank = new Bank(amount);
        this.inventory = new TriantaEnaPlayerInventory();
    }

    public TriantaEnaPlayerInventory inventory(){
        return this.inventory;
    }

    public TriantaEnaPlayer(String name){
        this(name, 0);
    }

    public double amount(){
        return this.bank.amount();
    }

    public void transactions(){
        System.out.println(this.bank.accountHistory());
    }

    public boolean canPlaceBet(double amount){
        return amount <= this.bank.amount();
    }

    public void placeBet(double value) {
        this.bank.debit(value);
        this.betValue = value;
    }

    public double betValue() {
        return this.betValue;
    }

    public void loose(double amount){
        this.bank.debit(amount);
        System.out.println(amount + " $ deducted from " + this.name() + "'s Account");
    }

    public void win(double amount){
        this.bank.credit(amount);
        System.out.println(amount + " $ credited to " + this.name() + "'s Account");
    }

    public void resetBetValue() {
        this.betValue = 0;
    }

    @Override
    public int compareTo(TriantaEnaPlayer o) {
        double amount = o.bank.amount();
        return (int) (this.bank.amount() - amount);
    }

}
