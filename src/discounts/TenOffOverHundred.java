package discounts;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class TenOffOverHundred implements Discounter {
    @Override
    public BigDecimal apply(BigDecimal totalPrice) {
        if (totalPrice.compareTo(valueOf(100)) >= 0)
            return totalPrice.multiply(valueOf(0.9));
        else
            return totalPrice;
    }
}