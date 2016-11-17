import java.util.stream.IntStream;

/**
 * @author artur
 */
public class BitOperations {

    public static void main(String... args) {
        IntStream.range(0, 16)
                .forEach(i -> System.out.print("0x" + calc(i, 13, 16) + ","));
    }

    static int calc(int a, int pow, int rest) {
        return a * pow % rest;
    }
}
