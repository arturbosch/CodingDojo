package de.artismarti;

public class Rot13Crypto {

	private final static String ENCRYPTED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String ENCRYPTED_CHARS_PLUS_NUMBERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int ENCRYPTED_CHARS_LENGTH = ENCRYPTED_CHARS.length();
	private static final int ENCRYPTED_CHARS_PLUS_NUMBERS_LENGTH = ENCRYPTED_CHARS_PLUS_NUMBERS.length();

	final static int SHIFT_BY_13 = 13;

	static String decideWithBase(boolean decrypt, int base, boolean useCharsAndNumbers, String message) {
		if (decrypt) {
			return decryptWithBase(base, useCharsAndNumbers, message);
		} else {
			return encryptWithBase(base, useCharsAndNumbers, message);
		}
	}

	public static String encryptWithBase13(String message) {
		return encryptWithBase(SHIFT_BY_13, false, message);
	}

	public static String encryptWithBase(int base, boolean useCharsAndNumbers, String message) {
		String normalizedMessage = normalize(message.toUpperCase());
		return rotX(normalizedMessage, base, useCharsAndNumbers, false);
	}

	public static String decryptWithBase(int base, boolean useCharsAndNumbers, String message) {
		return rotX(message.toUpperCase(), base, useCharsAndNumbers, true);
	}

	private static String normalize(String message) {
		StringBuilder result = new StringBuilder();
		for (char c : message.toCharArray()) {
			if (c == 'Ä') {
				result.append("AE");
			} else if (c == 'Ü') {
				result.append("UE");
			} else if (c == 'Ö') {
				result.append("OE");
			} else if (c == 'ß') {
				result.append("SS");
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}

	private static String rotX(String message, int shiftBy, boolean useCharsAndNumbers, boolean decrypt) {
		char[] result = message.toCharArray();
		for (int i = 0; i < result.length; i++) {
			char c = result[i];
			if (Character.isLetterOrDigit(c)) {
				result[i] = getNewCharacter(shiftBy, useCharsAndNumbers, c, decrypt);
			}
		}
		return new String(result);
	}

	private static char getNewCharacter(int shiftBy, boolean useCharsAndNumbers, char c, boolean decrypt) {
		char newC;
		if (useCharsAndNumbers) {
			newC = shift(c, shiftBy, decrypt,
					ENCRYPTED_CHARS_PLUS_NUMBERS, ENCRYPTED_CHARS_PLUS_NUMBERS_LENGTH);
		} else {
			newC = shift(c, shiftBy, decrypt,
					ENCRYPTED_CHARS, ENCRYPTED_CHARS_LENGTH);
		}
		return newC;
	}

	private static char shift(char c, int shiftBy, boolean decrypt, String pattern, int patternLength) {
		int numberInPattern = pattern.indexOf(c);
		int newCharAt;
		if (decrypt) {
			newCharAt = numberInPattern - shiftBy;
			if (newCharAt < 0) {
				newCharAt += patternLength;
			}
		} else {
			newCharAt = (numberInPattern + shiftBy) % patternLength;
		}
		return pattern.charAt(newCharAt);
	}
}
