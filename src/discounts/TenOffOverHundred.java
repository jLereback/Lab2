package discounts;

import java.math.BigDecimal;

public class TenOffOverHundred implements Discounter {

    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.9));
    }
}