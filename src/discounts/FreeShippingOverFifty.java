package discounts;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.valueOf;

public class FreeShippingOverFifty implements Discounter {
    @Override
    public BigDecimal apply(BigDecimal ordinaryPrice, String promoCode) {
        if (ordinaryPrice.compareTo(valueOf(50)) > 0)
            return ordinaryPrice.subtract(valueOf(7));
        else
            return ordinaryPrice;
    }

    @Override
    public BigDecimal apply(BigDecimal price) {
        if (price.compareTo(valueOf(50)) > 0)
            return price;
        else
            return price.add(valueOf(7));
    }
}