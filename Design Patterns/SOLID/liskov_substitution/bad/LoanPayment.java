package SOLID.liskov_substitution.bad;

public interface LoanPayment {
    void doPayment(int amount);
    void foreCloseLoan();
    void doRepayment(int amount);
}
