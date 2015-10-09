package de.artismarti;

public class Tannenbaum {

	public static final String SPACE = " ";
	public static final char X_CHAR = 'X';
	public static final char STAR = '*';

	public static String draw(int size, boolean withStar) {
		if (size < 2) {
			throw new IllegalArgumentException("Size must be >= 2!");
		}

		StringBuilder result = new StringBuilder();

		if (withStar) {
			result.append(drawLine(size - 1, 1, STAR)).append("\n");
		}
		result.append(drawLine(size - 1, 1)).append("\n");

		int height = size - 1;
		int amountOfX = 1;
		while (height > 0) {
			amountOfX = amountOfX + 2;
			result.append(drawLine(--height, amountOfX)).append("\n");
		}

		result.append(drawLine(size - 1, 1)).append("\n");

		return result.toString();
	}

	private static String drawLine(int i, int amountOfChar, char c) {
		StringBuilder baseline = new StringBuilder();
		appendSpaces(i, baseline);
		for (int j = 0; j < amountOfChar; j++) {
			baseline.append(c);
		}
		appendSpaces(i, baseline);
		return baseline.toString();
	}

	private static String drawLine(int i, int amountOfX) {
		return drawLine(i, amountOfX, X_CHAR);
	}

	private static void appendSpaces(int i, StringBuilder baseline) {
		while (i > 0) {
			baseline.append(SPACE);
			i--;
		}
	}

}
