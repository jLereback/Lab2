package discounts;

import misc.Print;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static shop.PointOfSale.tryAgain;

public class TenOffOverHundredPromoCode implements Discounter {

    @Override
    public BigDecimal apply(BigDecimal totalPrice, String userInputCode) {

        if (totalPrice.compareTo(valueOf(100)) > 0 && userInputCode.equals(promoCode)) {
            return totalPrice.multiply(valueOf(0.9));
        }
        else {
            if (userInputCode.equals(promoCode)) {
                Print.limitedPromoCode();
                return totalPrice;
            } else if (!userInputCode.equals("null")) {
                tryAgain(sc);
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