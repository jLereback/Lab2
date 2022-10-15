package discounts;

import java.math.BigDecimal;

public class FreeShippingOverFifty implements Discounter {
    @Override
    public BigDecimal apply(BigDecimal totalPrice) {
        if (totalPrice.compareTo(new BigDecimal(50)) >= 0)
            return totalPrice.subtract(BigDecimal.valueOf(7));
        else
            return totalPrice;
    }

}

