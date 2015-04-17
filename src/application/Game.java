package application;

public class Game {
	public static final int SINGLEPLAYER_MODE = 2;
	public static final int MULTIPLAYER_MODE = 1;
	public static final int NUM_PEG_TO_WIN = 3;
	public static final int NUM_PEG_TO_WIN_LARGE = 4;
	private static int mode;
	private static Board board;
	private static Player player1;
	private static Player player2;
	private static Game instance = null;
	private boolean activePlayer;

	private Game() {
		this.player1 = new Player();
	}

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	public static void initGameBoard(int size) {
		board = new Board(size);
	}

	public void initGame(int mode) {
		this.mode = mode;
	}
	public Board getBoard() {
		return board;
	}

}
