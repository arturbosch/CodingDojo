import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

/**
 * @author artur
 */
public class Prims {

	public static Map<Boolean, List<Integer>> partitionFor(int n) {
		return IntStream.rangeClosed(2, n)
			.boxed()
			.collect(partitioningBy(Prims::isPrime));
	}

	private static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot)
			.noneMatch(n -> candidate % n == 0);
	}

	public static List<Integer> sequenceFor(int n) {
		return IntStream.range(2, n)
			.boxed()
			.filter(Prims::isPrime)
			.collect(toList());
	}
}
