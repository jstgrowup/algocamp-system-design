package strategy.example2.better;

public class Main {

    // Client-side choice at runtime. If you want to avoid even this switch,
    // keep a Map<String, DiscountStrategy> and look the strategy up by key.
    static DiscountStrategy pick(String customerType) {
        return switch (customerType) {
            case "FESTIVAL" -> new FestivalDiscount();
            case "MEMBER" -> new MemberDiscount();
            case "BULK" -> new BulkDiscount();
            default -> new NoDiscount();
        };
    }

    public static void main(String[] args) {
        Cart cart = new Cart(100, 12);

        // 1) pick a strategy at runtime
        for (String type : new String[]{"REGULAR", "FESTIVAL", "MEMBER", "BULK"}) {
            cart.setDiscount(pick(type));
            System.out.println(type + " -> " + cart.total());
        }

        // 2) swap at runtime: same cart, new rule
        cart.setDiscount(new MemberDiscount());
        System.out.println("Member    -> " + cart.total());
        cart.setDiscount(new FestivalDiscount());
        System.out.println("Swapped to festival -> " + cart.total());

        // 3) lambda strategy: a brand-new rule, no new class
        cart.setDiscount((total, qty) -> total - 50); // flat Rs 50 off coupon
        System.out.println("Coupon (lambda) -> " + cart.total());
    }
}
