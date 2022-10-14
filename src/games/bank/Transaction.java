package games.bank;

public class Transaction {
    private final String message;
    private final double amount;

    public Transaction(String message, double value){
        this.message = message;
        this.amount = value;
    }

    @Override
    public String toString() {
        return "Transaction[Action: " + this.message + "Value: " + this.amount + "]";
    }
}
