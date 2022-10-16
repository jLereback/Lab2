package discounts;

import resten.Print;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.valueOf;

public class TenOffOverHundredPromoCode implements Discounter {
    @Override
    public BigDecimal apply(BigDecimal totalPrice, String userInputCode) {
        if (totalPrice.compareTo(valueOf(100)) > 0 && userInputCode.equals("TenOFF")) {
            return totalPrice.multiply(valueOf(0.9));
        }
        else {
            if (userInputCode.equals("TenOFF")) {
                Print.limitedPromoCode();
                return totalPrice;
            } else if (!userInputCode.equals("null")) {
                Print.tryNewPromoCode(userInputCode);
                return totalPrice;
            } else
                return totalPrice;
        }
    }

    @Override
    public BigDecimal apply(BigDecimal bigDecimal) {
        return null;
    }

}