import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LevelValidatorTest {

	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}

	@Test
	public void readValidLevelFromFile() throws IOException, InvalidLevelException {
		assertTrue(game.loadLevel("./src/level.txt"));
		assertTrue(game.getCurrentLevel().length == 4);
		assertTrue(game.getCurrentLevel()[0].length == 8);
	}

	@Test(expected = InvalidLevelException.class)
	public void readLevelWithInvalidSizes() throws IOException, InvalidLevelException {
		assertFalse(game.loadLevel("./src/invalidSizes.txt"));
	}

	@Test(expected = InvalidLevelException.class)
	public void readLevelWithInvalidSymbols() throws IOException, InvalidLevelException {
		assertFalse(game.loadLevel("./src/invalidSymbols.txt"));
	}
}
