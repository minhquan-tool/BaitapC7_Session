package murach.business;

import java.math.BigDecimal;

public class CartSelfTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        Product first = new Product("8601", "86 (the band) - True Life Songs and Pictures", "14.95");
        Product second = new Product("pf01", "Paddlefoot - The first CD", "12.95");
        cart.addItem(first);
        cart.addItem(first);
        cart.addItem(second);
        assert cart.getItems().size() == 2;
        assert cart.getItems().get(0).getQuantity() == 2;
        assert cart.getTotalAmount().compareTo(new BigDecimal("42.85")) == 0;
        cart.updateItem("8601", 3);
        assert cart.getTotalAmount().compareTo(new BigDecimal("57.80")) == 0;
        cart.updateItem("8601", 0);
        assert cart.getItems().size() == 1;
        cart.removeItem("pf01");
        assert cart.getItems().isEmpty();
        assert cart.getTotalAmount().signum() == 0;
        try {
            cart.updateItem("pf01", -1);
            throw new AssertionError("Negative quantities must be rejected");
        } catch (IllegalArgumentException expected) { }
        System.out.println("Cart checks passed");
    }
}
