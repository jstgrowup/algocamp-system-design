package strategy.example2.problem;

public class Main {
    public static void main(String[] args) {
        PriceCalculator calc = new PriceCalculator();
        System.out.println("Regular : " + calc.finalPrice(100, 12, CustomerType.REGULAR));
        System.out.println("Festival: " + calc.finalPrice(100, 12, CustomerType.FESTIVAL));
        System.out.println("Member  : " + calc.finalPrice(100, 12, CustomerType.MEMBER));
        System.out.println("Bulk    : " + calc.finalPrice(100, 12, CustomerType.BULK));
    }
}
