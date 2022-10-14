package games.bank;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private static int ID = 0;
    private final int id;
    private double amount;
    private final ArrayList<Transaction> accountSummary;

    public Bank(double initialAmount){
        this.id = ++ID;
        this.accountSummary = new ArrayList<>();
        this.amount = initialAmount;
        this.accountSummary.add(new Transaction("Initial Deposit", initialAmount));
    }

    public Bank(){
        this(0);
    }

    public double amount(){
        return this.amount;
    }

    public void credit(double amount){
        this.amount += amount;
        this.accountSummary.add(new Transaction("Credited", +amount));
    }

    public void debit(double amount){
        if (amount > this.amount){
            throw new IllegalArgumentException();
        }
        this.amount -= amount;
        this.accountSummary.add(new Transaction("Debited", -amount));
    }

    public String accountHistory(){
        return this.accountSummary.stream().map(Transaction::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "Bank[id=" + this.id + ", Balance="+this.amount +"]";
    }
}
