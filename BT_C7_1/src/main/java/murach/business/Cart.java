package murach.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart implements Serializable {
    private final List<LineItem> items = new ArrayList<>();

    public List<LineItem> getItems() { return List.copyOf(items); }
    public void addItem(Product product) {
        for (LineItem item : items) {
            if (item.getProduct().getCode().equals(product.getCode())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new LineItem(product, 1));
    }
    public void updateItem(String code, int quantity) {
        if (quantity < 0 || quantity > 999) {
            throw new IllegalArgumentException("Quantity must be between 0 and 999.");
        }
        if (quantity == 0) { removeItem(code); return; }
        for (LineItem item : items) {
            if (item.getProduct().getCode().equals(code)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    public void removeItem(String code) {
        items.removeIf(item -> item.getProduct().getCode().equals(code));
    }
    public BigDecimal getTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for (LineItem item : items) { total = total.add(item.getTotal()); }
        return total;
    }
    public String getTotalAmountCurrencyFormat() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(getTotalAmount());
    }
}
