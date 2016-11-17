import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author artur
 */
public class CSVReaderTest {

	@Test
	public void test() {
		List<String> strings = new ArrayList<>(Arrays.asList(
			"Name;Strasse;Ort;Alter",
			"Peter Pan;Am Hang 5;12345 Einsam;42",
			"Maria Schmitz;Kölner Straße 45;50123 Köln;43",
			"Paul Meier;Münchener Weg 1;87654 München;65"));

		CSVReader csvReader = new CSVReader();
		List<String> list = csvReader.printAsTable(strings);

		list.forEach(System.out::println);
	}
}
