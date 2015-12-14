import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author artur
 */
public class FizzBuzzTest {

	@Test
	public void testPrint() throws Exception {
		List<String> strings = FizzBuzz.print(200);
		strings.forEach(System.out::println);
	}

	@Test
	public void forN35() throws Exception {
		HashMap<String, Long> map = FizzBuzz.print(35).stream().collect(groupingBy(Function.identity(),
			HashMap<String, Long>::new, counting()));

		assertThat(map.get("Fizz"), is(6L));
		assertThat(map.get("Buzz"), is(4L));
		assertThat(map.get("FizzBuzz"), is(1L));
	}
}
