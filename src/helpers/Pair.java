package helpers;

public class Pair<T, H> {
    private final T key;
    private final H value;

    public Pair(T t, H h) {
        key = t;
        value = h;
    }

    public T getKey() {
        return key;
    }

    public H getValue() {
        return value;
    }
}
