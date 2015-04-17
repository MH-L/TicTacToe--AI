package test;

import org.junit.Before;
import org.junit.Test;

import application.Game;

public class GameTest {
	private Game game;

	@Before
	public void init() {
		this.game = Game.getInstance();
	}

	@Test
	public void testGameOver() {

	}

}
