# Strategy pattern on top of the "better" design

**Problem:** if two cards (say Master and Rupay) share the same refund logic, each card ends up with its own copy of it.

**Fix:** use the Strategy pattern. Whenever there are multiple ways to do something, put each way in its own class (`strategies/refund/`) behind a common interface (`RefundStrategy`). A card holds a strategy and its `doRefund()` just delegates to it.

```java
public class MasterCreditCard extends CreditCard implements RefundCompatibleCreditCard {
    private final RefundStrategy refundStrategy = new BankAccountRefundStrategy();

    @Override
    public void doRefund() {
        refundStrategy.doRefund();
    }
}
```
