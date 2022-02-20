package model;

public class BankAccount {
    private int balance = 0;

    public void addMoney(int amount) {
        balance += amount;
    }
    public boolean withdrawMoney(int amount) {
        if (amount > balance)
            return false;
        balance -= amount;
        return true;
    }

    public int getBalance() {
        return balance;
    }
}
