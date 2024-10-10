package chainOfResponsibility;

public class PaypalPaymentHandler extends PaymentHandler {
    @Override
    public void handlePayment(double amount) {
        if (amount <= 1500) {
            System.out.println("Paid using paypal: $" + amount);
        } else {
            next.handlePayment(amount);
        }
    }
}
