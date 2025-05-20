package Structural.proxy;

public class ATM implements Account { // This is Structural.proxy to bank account
    @Override
    public void withdraw() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.withdraw();
    }

    @Override
    public void getAccountNumber() {

    }
}
