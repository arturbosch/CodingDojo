package de.artismarti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static final String HOW_TO_ROT13_E_D_N_MESSAGE = "\nHowTo: rot13 -e/-d -n 'base' \"message\".";

	public static void main(String[] args) throws Exception {
		List<String> arguments = new ArrayList<>(Arrays.asList(args));
		boolean decrypt;
		if (arguments.contains("-e")) {
			arguments.remove("-e");
			decrypt = false;
		} else if (arguments.contains("-d")) {
			arguments.remove("-d");
			decrypt = true;
		} else {
			throw new Exception(HOW_TO_ROT13_E_D_N_MESSAGE);
		}

		boolean withNumbers = false;
		if (arguments.contains("-n")) {
			arguments.remove("-n");
			withNumbers = true;
		}

		if (arguments.size() == 0) {
			throw new Exception("Too few arguments." + HOW_TO_ROT13_E_D_N_MESSAGE);
		}

		if (arguments.size() > 2) {
			throw new Exception("Too many arguments." + HOW_TO_ROT13_E_D_N_MESSAGE);
		}

		int base;
		String message;
		if (arguments.size() == 2) {
			base = Integer.parseInt(arguments.get(0));
			message = arguments.get(1);
		} else {
			base = Rot13Crypto.SHIFT_BY_13;
			message = arguments.get(0);
		}

		System.out.println(Rot13Crypto.decideWithBase(decrypt, base, withNumbers, message));
	}
}
