import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Programming exercise from the book Java 8 in action.
 *
 * @author artur
 */
public class PythagorasTriples {

	public static Stream<double[]> calculate(int from, final int to) {

		return IntStream.rangeClosed(from, to)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, to)
						.mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
						.filter(triple -> triple[2] % 1 == 0)
				);

	}

}
