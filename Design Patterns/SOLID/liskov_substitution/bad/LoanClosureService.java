package SOLID.liskov_substitution.bad;

/**
 * we are unable to substitute subtype with super type.
 * That is violation of Liskov Substitution rule.
 */

public class LoanClosureService {
    private final LoanPayment loanPayment;

    public LoanClosureService(LoanPayment loanPayment) {
        this.loanPayment = loanPayment;
    }

    public void foreCloseLoan(){
        // if we pass credit card, it will throw exception
        loanPayment.foreCloseLoan();
    }
}
