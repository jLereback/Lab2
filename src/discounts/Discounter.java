package discounts;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;

public interface Discounter extends UnaryOperator {

    BigDecimal HIGHEST_DISCOUNT = valueOf(2000);
    BigDecimal LOWEST_DISCOUNT = valueOf(1000);


    static BigDecimal applyDiscount(BigDecimal totalPrice) {
        return Stream.of(new FreeShippingOverFifty(), new TenOffOverHundred())
                .map(discounter -> discounter.apply(totalPrice))
                .reduce(BigDecimal::min)
                .get();
    }

    BigDecimal apply(BigDecimal totalPrice);
}