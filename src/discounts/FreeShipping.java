package discounts;

import java.math.BigDecimal;

public class FreeShipping implements Discounter {
    @Override
    public BigDecimal applyDiscount(final BigDecimal amount) {
        return amount.subtract(BigDecimal.valueOf(7));
    }

}
