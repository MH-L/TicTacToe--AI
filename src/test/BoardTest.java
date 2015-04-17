package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.Board;

public class BoardTest {
	private Board board;

	@Before
	public void initialize() {
		this.board = new Board();
	}

	@Test
	public void testConstructor() {
		assertEquals(board.getRows().size(), 4);
		assertEquals(board.getColumns().size(), 4);
		assertEquals(board.getUlDiags().size(), 7);
		assertEquals(board.getUrDiags().size(), 7);
	}

	@Test
	public void testCheckLine() {

	}
}
