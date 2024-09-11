package SOLID.liskov_substitution.good;

import SOLID.liskov_substitution.bad.LoanPayment;

public interface SecureLoan extends LoanPayment {
    void foreCloseLoan();
}
