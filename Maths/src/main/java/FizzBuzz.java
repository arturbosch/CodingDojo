import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author artur
 */
public class FizzBuzz {

	public static List<String> print(int n) {
		List<String> result = new ArrayList<>();
		IntStream.rangeClosed(1, n).forEach(value -> {

			if (value % 5 == 0) {

				if (value % 7 == 0) {
					result.add("FizzBuzz");
				} else {
					result.add("Fizz");
				}

			} else if (value % 7 == 0) {
				result.add("Buzz");
			}

		});
		return result;
	}
}
