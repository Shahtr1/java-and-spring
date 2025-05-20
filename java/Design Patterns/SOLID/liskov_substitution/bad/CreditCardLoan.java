package SOLID.liskov_substitution.bad;

public class CreditCardLoan implements LoanPayment{
    @Override
    public void doPayment(int amount) {

    }

    @Override
    public void foreCloseLoan() {
        throw new UnsupportedOperationException("Not allowed");
    }

    @Override
    public void doRepayment(int amount) {
        throw new UnsupportedOperationException("Not allowed");
    }
}
