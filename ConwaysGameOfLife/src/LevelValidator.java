import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelValidator {

	public static char[][] loadLevel(String pathToLevel) throws IOException, InvalidLevelException {
		List<String> level = Files.readAllLines(Paths.get(pathToLevel));
		if (areDimensionsValid(level)) {
			throw new InvalidLevelException("Invalid Dimensions!");
		}
		char[][] levelAsArray = convertTo2dArray(level);
		if (isValid(levelAsArray)) {
			return levelAsArray;
		} else {
			throw new InvalidLevelException("Invalid Cell Structure!");
		}
	}

	private static boolean areDimensionsValid(List<String> level) {
		int currentSize, lastSize = -1;
		for (String row : level) {
			currentSize = row.length();
			if (lastSize == -1) {
				lastSize = currentSize;
			}
			if (currentSize != lastSize) {
				return true;
			}
			lastSize = currentSize;
		}
		return false;
	}

	private static char[][] convertTo2dArray(List<String> level) {
		char[][] result = new char[level.size()][];
		for (int i = 0; i < level.size(); i++) {
			String row = level.get(i);
			result[i] = row.toCharArray();
		}
		return result;
	}

	private static boolean isValid(char[][] level) {
		for (char[] aLevel : level) {
			for (int j = 0; j < level[0].length; j++) {
				if (aLevel[j] != '.' & aLevel[j] != '*') {
					return false;
				}
			}
		}
		return true;
	}
}
