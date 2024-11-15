package BankingSystem;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts = new ArrayList<>();
    private int accountCounter = 1001;

    public void openAccount(String type, String holderName, double initialDeposit) {
        Account account;
        if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(accountCounter++, holderName, initialDeposit);
        } else if (type.equalsIgnoreCase("checking")) {
            account = new CheckingAccount(accountCounter++, holderName, initialDeposit);
        } else {
            System.out.println("Invalid account type.");
            return;
        }
        accounts.add(account);
        System.out.println("Account created: " + account);
    }

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful. New Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            System.out.println("Withdrawal successful. New Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }
}
