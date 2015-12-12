import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Programming exercise from the book Java 8 in action.
 *
 * @author artur
 */
public class Fibonacci {

	public static Stream<int[]> sequenceOf(int n) {
		return sequence().limit(n);
	}

	public static Stream<int[]> sequence() {
		return Stream.iterate(new int[]{0, 1}, previous -> new int[]{previous[1], previous[0] + previous[1]});
	}

	public static Stream<long[]> sequenceLong() {
		return Stream.iterate(new long[]{0, 1}, previous -> new long[]{previous[1], previous[0] + previous[1]});
	}

	public static Stream<long[]> sequenceLongOf(int n) {
		return sequenceLong().limit(n);
	}

	public static List<Integer> numbers(int n) {
		return Stream.iterate(new int[]{0, 1}, previous -> new int[]{previous[1], previous[0] + previous[1]})
				.map(ints -> ints[0])
				.limit(n)
				.collect(toList());
	}
}
