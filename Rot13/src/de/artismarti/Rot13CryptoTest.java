package de.artismarti;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Rot13CryptoTest {

	public static final String HELLO_WORLD = "Hello, World";
	public static final String HELLO_WORLD_ENC = "URYYB, JBEYQ";

	@Test
	public void encryptString() {
		String enc = Rot13Crypto.encryptWithBase13(HELLO_WORLD);
		assertThat(enc, is(HELLO_WORLD_ENC));
	}

	@Test
	public void testEncryptWithBase5() {
		assertThat(Rot13Crypto.encryptWithBase(5, true, "H3ll0, W0rld"), is("M8QQ5, 15WQI"));
	}

	@Test
	public void decryptString() {
		String dec = Rot13Crypto.decryptWithBase(13, false, HELLO_WORLD_ENC);
		assertThat(dec, is(HELLO_WORLD.toUpperCase()));
	}

	@Test
	public void testDecryptWithBase5() {
		assertThat(Rot13Crypto.decryptWithBase(5, true, "M8QQ5, 15WQI"), is("H3LL0, W0RLD"));
	}
}
