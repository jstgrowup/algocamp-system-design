package interface_segregation.example1.problem;

import java.util.List;

/** Calls every card through the CreditCard contract; Diners and Rupay blow up (LSP violation). */
public class Main {

    public static void main(String[] args) {
        List<CreditCard> cards = List.of(new AmexCreditCard(), new DinersCreditCard(),
                new MasterCardCreditCard(), new VisaCreditCard(), new RupayCreditCard());

        for (CreditCard card : cards) {
            run(card, "doRefund", card::doRefund);
            run(card, "intlPayment", card::intlPayment);
            run(card, "intlPinPayment", card::intlPinPayment);
        }
    }

    // try/catch is needed only because the cards can't be substituted safely
    private static void run(CreditCard card, String action, Runnable call) {
        try {
            call.run();
        } catch (UnsupportedOperationException e) {
            System.out.println(card.getClass().getSimpleName() + "." + action + "() blew up: " + e.getMessage());
        }
    }
}
