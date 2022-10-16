package discounts;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.valueOf;

public class FreeShippingOverFifty implements Discounter {
    @Override
    public BigDecimal apply(BigDecimal ordPrice, String promoCode) {
        if (ordPrice.compareTo(valueOf(50)) >= 0)
            return ordPrice.subtract(valueOf(7));
        else
            return ordPrice;
    }

    @Override
    public BigDecimal apply(BigDecimal price) {
        if (price.compareTo(valueOf(50)) >= 0)
            return price;
        else
            return price.add(valueOf(7));
    }
}