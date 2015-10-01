import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class GameTest {

	public static final char[][] ROUND_0_FEWER = {
			{'*', '.', '*'},
			{'.', '.', '.'},
			{'*', '.', '*'}};
	public static final char[][] ROUND_1_FEWER = {
			{'.', '.', '.'},
			{'.', '.', '.'},
			{'.', '.', '.'}};
	public static final char[][] ROUND_0_MORE = {
			{'*', '*', '*'},
			{'*', '*', '.'},
			{'*', '*', '*'}};
	public static final char[][] ROUND_1_MORE = {
			{'*', '.', '*'},
			{'.', '.', '.'},
			{'*', '.', '*'}};
	public static final char[][] ROUND_0_SURVIVE = {
			{'*', '*', '*'},
			{'*', '*', '*'},
			{'*', '*', '*'}};
	public static final char[][] ROUND_1_SURVIVE = {
			{'*', '.', '*'},
			{'.', '.', '.'},
			{'*', '.', '*'}};
	public static final char[][] ROUND_0_REVIVE = {
			{'.', '*', '.'},
			{'.', '*', '.'},
			{'.', '*', '.'}};
	public static final char[][] ROUND_1_REVIVE = {
			{'.', '.', '.'},
			{'*', '*', '*'},
			{'.', '.', '.'}};

	private Game game;

	@Before
	public void setUp() throws IOException, InvalidLevelException {
		game = new Game();
		game.loadLevel("./src/levelSteffen.txt");
	}

	@Test
	public void gameOnFromFile() {
		for (int i = 0; i < 12; i++) {
			System.out.println("Round " + game.getRound());
			System.out.println("========================");
			for (char[] row : game.getCurrentLevel()) {
				System.out.println(row);
			}
			System.out.println();
			game.nextRound();
		}
	}

	@Test
	public void fewerThanTwoNeighborsDies() {
		game.setCurrentLevel(ROUND_0_FEWER);
		game.nextRound();
		char[][] actual = game.getCurrentLevel();
		char[][] expected = ROUND_1_FEWER;
		assertChar2dArraysAreEqual(actual, expected);
	}

	@Test
	public void moreThanThreeNeighborsDies() {
		game.setCurrentLevel(ROUND_0_MORE);
		game.nextRound();
		char[][] actual = game.getCurrentLevel();
		char[][] expected = ROUND_1_MORE;
		assertChar2dArraysAreEqual(actual, expected);
	}

	@Test
	public void twoOrThreeNeighborsSurvive() {
		game.setCurrentLevel(ROUND_0_SURVIVE);
		game.nextRound();
		char[][] actual = game.getCurrentLevel();
		char[][] expected = ROUND_1_SURVIVE;
		assertChar2dArraysAreEqual(actual, expected);
	}

	@Test
	public void reviveIfThreeNeighbors() {
		game.setCurrentLevel(ROUND_0_REVIVE);
		game.nextRound();
		char[][] actual = game.getCurrentLevel();
		char[][] expected = ROUND_1_REVIVE;
		assertChar2dArraysAreEqual(actual, expected);
	}

	private void assertChar2dArraysAreEqual(char[][] actual, char[][] expected) {
		System.out.println("Round " + game.getRound() + " : " + Arrays.deepToString(actual));
		assertTrue(Arrays.deepEquals(actual, expected));
	}
}
