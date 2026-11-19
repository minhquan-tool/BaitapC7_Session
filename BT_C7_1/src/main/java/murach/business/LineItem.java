package murach.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class LineItem implements Serializable {
    private final Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        setQuantity(quantity);
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 0 || quantity > 999) {
            throw new IllegalArgumentException("Quantity must be between 0 and 999.");
        }
        this.quantity = quantity;
    }
    public BigDecimal getTotal() { return product.getPrice().multiply(BigDecimal.valueOf(quantity)); }
    public String getTotalCurrencyFormat() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(getTotal());
    }
}
