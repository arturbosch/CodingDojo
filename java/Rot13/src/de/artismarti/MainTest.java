package de.artismarti;

import org.junit.Test;

public class MainTest {

	public static final String HELLO_WORLD = "Hello, World";
	public static final String HELLO_WORLD_ENC = "URYYB, JBEYQ";

	@Test
	public void testCorrectUsageEncrypt() throws Exception {
		String[] args = {"-e", "13", HELLO_WORLD};
		Main.main(args);
	}

	@Test
	public void testCorrectUsageDecrypt() throws Exception {
		String[] args = {"-d", "13", HELLO_WORLD_ENC};
		Main.main(args);
	}

	@Test
	public void testCorrectUsageBase5WithNumbers() throws Exception {
		String[] args = {"-e", "-n", "5", "H3LL0, W0RLD"};
		Main.main(args);
	}

	@Test(expected = Exception.class)
	public void invalidAmountOfArguments() throws Exception {
		String[] args = {"-e", "13", HELLO_WORLD, HELLO_WORLD_ENC};
		Main.main(args);
	}
}
