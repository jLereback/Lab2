package discounts;

import resten.LineUp;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;

public interface Discounter extends Function<BigDecimal, String> {
    Scanner sc = new Scanner(System.in);
    static BigDecimal applyDiscount(BigDecimal ordPrice, String userInputCode) {
        return Stream.of(new FreeShippingOverFifty(), new TenOffOverHundredPromoCode())
                .map(discounter -> discounter.apply(ordPrice, userInputCode))
                .reduce(BigDecimal::max)
                .get();
    }

    static String checkShippingCost(BigDecimal ordPrice) {
        if (ordPrice.compareTo(valueOf(50)) >= 0)
            return "| Free" + LineUp.withTab(3) + "|\t$0";
        else
            return "| " + LineUp.withTab(4) + "|\t$7";
    }
    static BigDecimal applyDiscount(BigDecimal price) {
        return Stream.of(new FreeShippingOverFifty())
                .map(discounter -> discounter.apply(price))
                .reduce(BigDecimal::min)
                .get();
    }
    String promoCode = "TenOFF";
}