import org.junit.Test;

/**
 * @author artur
 */
public class PythagorasTriplesTest {

	@Test
	public void testCalculate() throws Exception {

		PythagorasTriples.calculate(1, 100)
				.limit(10)
				.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

	}
}