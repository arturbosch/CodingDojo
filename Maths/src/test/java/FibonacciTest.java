import org.junit.Test;

/**
 * @author artur
 */
public class FibonacciTest {

	@Test
	public void testSequenceOf() throws Exception {
		Fibonacci.sequenceOf(10)
				.forEach(pair -> System.out.println(pair[0] + ", " + pair[1]));
	}

	@Test
	public void testSequenceOfLong() throws Exception {
		Fibonacci.sequenceLongOf(1_000/*_000*/)
				.forEach(pair -> System.out.println(pair[0] + ", " + pair[1]));
	}

	@Test
	public void testNumbers() throws Exception {
		Fibonacci.numbers(1000).forEach(System.out::println);
	}
}