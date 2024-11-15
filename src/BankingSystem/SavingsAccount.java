package BankingSystem;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.02;

    public SavingsAccount(int accountNumber, String accountHolderName, double initialDeposit) {
        super(accountNumber, accountHolderName, initialDeposit);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount + (amount * INTEREST_RATE);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }
}
