package helpers;

public class BitManipulation {
    public static Integer getPosition(Integer input, Integer position) {
        return (input >> position) & 1;
    }

    public static int flipBit(Integer input, int position) {
        return input ^ (1 << position);
    }

    public static Integer setBit(Integer input, int position) {
        int mask = 1 << position;
        return input | mask;
    }
}
