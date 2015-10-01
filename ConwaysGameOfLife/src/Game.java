import java.io.IOException;

public class Game {

	public static final char LIVING_CELL = '*';
	public static final char DEAD_CELL = '.';

	private char[][] currentLevel;
	private int round;

	public boolean loadLevel(String pathToLevel) throws IOException, InvalidLevelException {
		currentLevel = LevelValidator.loadLevel(pathToLevel);
		round = 0;
		return true;
	}

	public void nextRound() {
		round++;
		char[][] result = new char[currentLevel.length][currentLevel[0].length];
		int neighbors;
		for (int row = 0; row < currentLevel.length; row++) {
			for (int column = 0; column < currentLevel[0].length; column++) {
				neighbors = countLivingNeighbors(row, column);
				result[row][column] =
						applyRules(currentLevel[row][column], neighbors) ? LIVING_CELL : DEAD_CELL;
			}
		}
		currentLevel = result;
	}

	private int countLivingNeighbors(int row, int column) {
		int limitX = currentLevel.length;
		int limitY = currentLevel[0].length;
		int neighbors = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				if (i == row && j == column) {
					continue;
				}
				if ((i >= 0 && i < limitX) && (j >= 0 && j < limitY)) {
					neighbors = currentLevel[i][j] == LIVING_CELL ? neighbors + 1 : neighbors;
				}
			}
		}
		return neighbors;
	}

	private boolean applyRules(char c, int neighbors) {
		if (c == LIVING_CELL) {
			return neighbors >= 2 && neighbors <= 3;
		}
		return c == DEAD_CELL && neighbors == 3;
	}

	public char[][] getCurrentLevel() {
		return currentLevel;
	}

	void setCurrentLevel(char[][] newLevel) {
		currentLevel = newLevel;
	}

	public int getRound() {
		return round;
	}
}
