package games.bank;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private static int ID = 0;
    private final int id;
    private double balance;
    private final ArrayList<Transaction> accountSummary;

    /**
     * Create a new bank with given amount
     *
     * @param initialAmount first amount
     */
    public Bank(double initialAmount){
        this.id = ++ID;
        this.accountSummary = new ArrayList<>();
        this.balance = initialAmount;
        this.accountSummary.add(new Transaction(" Initial Deposit ", initialAmount));
    }

    /**
     * Create a new Bank with amount zero
     */
    public Bank(){
        this(0);
    }

    /**
     * Return the balance of the bank
     *
     * @return balance amount
     */
    public double amount(){
        return this.balance;
    }

    /**
     * Credit amount to the bank
     *
     * @param amount amount to add
     */
    public void credit(double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Invalid amount!!");
        }
        this.balance += amount;
        this.accountSummary.add(new Transaction(" Credited ", +amount));
    }

    /**
     * Debit amount from the bank
     *
     * @param amount amount to debit
     */
    public void debit(double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Invalid amount!!!");
        }
        if (amount > this.balance){
            throw new IllegalArgumentException("Not enough amount");
        }
        this.balance -= amount;
        this.accountSummary.add(new Transaction(" Debited ", -amount));
    }

    /**
     * Generate the account transaction history
     *
     * @return String of transaction messages
     */
    public String accountHistory(){
        return this.accountSummary.stream().map(Transaction::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "Bank[id=" + this.id + ", Balance="+this.balance +"]";
    }
}
