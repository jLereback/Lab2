package discounts;

import java.math.BigDecimal;

public class TenOffOverHundred implements Discounter {

    @Override
    public BigDecimal apply(BigDecimal totalPrice) {
        if (totalPrice.compareTo(new BigDecimal(100)) >= 0)
            return totalPrice.multiply(BigDecimal.valueOf(0.9));
        else
            return totalPrice;
    }

}