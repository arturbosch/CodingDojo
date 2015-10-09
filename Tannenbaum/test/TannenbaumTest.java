import de.artismarti.Tannenbaum;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TannenbaumTest {

	public static final String TREE =
			"    X    \n" +
			"   XXX   \n" +
			"  XXXXX  \n" +
			" XXXXXXX \n" +
			"XXXXXXXXX\n" +
			"    X    \n";

	public static final String TREE_WITH_STAR =
			"    *    \n" + TREE;

	@Test
	public void testDrawTannenbaumWithoutStar() {
		String output = Tannenbaum.draw(5, false);
		assertThat(output, is(TREE));
	}

	@Test
	public void testDrawTannenbaumWithStar() {
		String output = Tannenbaum.draw(5, true);
		assertThat(output, is(TREE_WITH_STAR));
	}
}
