package discounts;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface Discounter extends UnaryOperator<BigDecimal> {
    static BigDecimal applyDiscount(BigDecimal totalPrice) {
        return Stream.of(new FreeShippingOverFifty(), new TenOffOverHundred())
                .map(discounter -> discounter.apply(totalPrice))
                .reduce(BigDecimal::min)
                .get();
    }
}