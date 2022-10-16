package discounts;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;

public interface Discounter extends Function<BigDecimal, String> {
    static BigDecimal applyDiscount(BigDecimal ordinaryPrice, String userInputCode) {
        return Stream.of(new FreeShippingOverFifty(), new TenOffOverHundredPromoCode())
                .map(discounter -> discounter.apply(ordinaryPrice, userInputCode))
                .reduce(BigDecimal::min)
                .get();
    }

    static String checkShippingCost(BigDecimal ordinaryPrice, BigDecimal discountedPrice) {
        if (Objects.equals(discountedPrice, ordinaryPrice))
            return "| $7";
        else
            return "| Free";
    }
    static BigDecimal applyDiscount(BigDecimal ordinaryPrice) {
        return Stream.of(new FreeShippingOverFifty())
                .map(discounter -> discounter.apply(ordinaryPrice))
                .reduce(BigDecimal::min)
                .get();
    }
}