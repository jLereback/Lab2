package discounts;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class FreeShippingOverFifty implements Discounter {
    @Override
    public BigDecimal apply(BigDecimal totalPrice) {
        if (totalPrice.compareTo(valueOf(50)) >= 0)
            return totalPrice.subtract(valueOf(7));
        else
            return totalPrice;
    }
}