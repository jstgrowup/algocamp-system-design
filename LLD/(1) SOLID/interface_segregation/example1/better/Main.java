package interface_segregation.example1.better;

import java.util.List;

/** Optional capabilities are checked with instanceof, so nothing is ever "not supported" (ISP + LSP). */
public class Main {

    public static void main(String[] args) {
        List<CreditCard> cards = List.of(new AmexCreditCard(), new DinersCreditCard(),
                new MasterCreditCard(), new VisaCreditCard(), new RupayCreditCard());

        for (CreditCard card : cards) {
            card.swipeAndPay(); // every card supports the base behaviours, no try/catch needed
            if (card instanceof RefundCompatibleCreditCard r) r.doRefund();
            if (card instanceof UpiCompatibleCreditCard u) u.upiPayment();
            if (card instanceof IntlPinCompatibleCreditCard p) p.intlPinPayment(); // only Amex and Visa
        }
    }
}
