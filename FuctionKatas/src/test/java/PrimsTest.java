import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author artur
 */
public class PrimsTest {

	@Test
	public void forNis15PartitionPrims() {
		Map<Boolean, List<Integer>> prims = Prims.partitionFor(20);
		prims.forEach((aBoolean, integers) -> System.out.println(aBoolean + "=" + integers));
	}

	@Test
	public void printPrimsUntilN() {
		List<Integer> prims = Prims.sequenceFor(100);
		System.out.println(prims);
	}
}
