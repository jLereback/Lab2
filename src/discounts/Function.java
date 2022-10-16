package discounts;
public interface Function<B, S> {
    B apply(B b, S s);

    B apply(B b);
}