package com.example.workflow;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class ReserveSeatOnBoat implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String money = "0.0";
        String ticketType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        double moneyDouble = Double.parseDouble(money);

        if(moneyDouble >= 10000){
            ticketType = "First Class";
        } else if (moneyDouble >= 5000) {
            ticketType = "Business Class";
        }

        delegateExecution.setVariable("ticketType", ticketType);
    }
}
