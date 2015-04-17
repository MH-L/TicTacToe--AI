package application;

import java.util.ArrayList;

public class Board {

	public static final int FIRST_PLAYER = 1;
	public static final int SECOND_PLAYER = 2;
	public static final int EMPTY_SPOT = 3;
	public static final int CLASSIC_MODE = 1;
	public static final int FANCY_MODE = 2;
	public static final int PURE_MODE = 3;
	public static final int CHECK_MODE = 4;

	private static int width = 4;
	private static int height = 4;
	private static int diag = 4;
	private int[][] grid;
	private static ArrayList<Grid> player1stones;
	private static ArrayList<Grid> player2stones;

	public static int getWidth() {
		return width;
	}

	public static ArrayList<Grid> getPlayer1stones() {
		return player1stones;
	}

	public static ArrayList<Grid> getPlayer2stones() {
		return player2stones;
	}

	public static int getHeight() {
		return height;
	}

	public static int getDiag() {
		return diag;
	}

	public int[][] getGrid() {
		return grid;
	}

	public ArrayList<int[]> getRows() {
		return rows;
	}

	public ArrayList<int[]> getColumns() {
		return columns;
	}

	public ArrayList<int[]> getUlDiags() {
		return ulDiags;
	}

	public ArrayList<int[]> getUrDiags() {
		return urDiags;
	}

	private ArrayList<int[]> rows;
	private ArrayList<int[]> columns;
	private ArrayList<int[]> ulDiags;
	private ArrayList<int[]> urDiags;

	public Board() {
		this(4);
	}

	public Board(int sideLen) {
		this.width = sideLen;
		this.height = sideLen;
		this.diag = width + height - 1;
		this.grid = initGrid();
		this.rows = initRows();
		this.columns = initCols();
		this.ulDiags = initDiags();
		this.urDiags = initDiags();
	}

	private int[][] initGrid() {
		int[][] gameGrid = new int[height][width];
		for (int j = 0; j < height; j++)
			for (int k = 0; k < width; k++)
				gameGrid[j][k] = 0;
		return gameGrid;
	}

	public ArrayList<int[]> initRows() {
		ArrayList<int[]> retVal = new ArrayList<int[]>();
		for (int i = 0; i < height; i++) {
			int[] array = new int[width];
			for (int k = 0; k < width; k++)
				array[k] = 0;
			retVal.add(array);
		}
		return retVal;
	}

	public ArrayList<int[]> initCols() {
		ArrayList<int[]> retVal = new ArrayList<int[]>();
		for (int i = 0; i < width; i++) {
			int[] array = new int[height];
			for (int j = 0; j < height; j++)
				array[j] = 0;
			retVal.add(array);
		}
		return retVal;
	}

	public ArrayList<int[]> initDiags() {
		ArrayList<int[]> retVal = new ArrayList<int[]>();
		for (int i = 0; i < diag; i++) {
			int len = i < width ? i + 1 : 2 * width - i;
			int[] array = new int[len];
			for (int j = 0; j < len; j++)
				array[j] = 0;
			retVal.add(array);
		}
		return retVal;
	}

	public void renderBoard(int mode) {
		char firstPlayer;
		char secondPlayer;
		char emptySpot;
		switch (mode) {
		case CLASSIC_MODE:
			firstPlayer = 'X';
			secondPlayer = 'O';
			emptySpot = '-';
			break;
		case FANCY_MODE:
			firstPlayer = '\u25cb';
			secondPlayer = '\u25cf';
			emptySpot = '\u25a1';
			break;
		case PURE_MODE:
			firstPlayer = '\u263b';
			secondPlayer = '\u263a';
			emptySpot = '\u25a1';
			break;
		case CHECK_MODE:
			firstPlayer = '\u2611';
			secondPlayer = '\u2612';
			emptySpot = '\u2610';
		default:
			firstPlayer = '1';
			secondPlayer = '2';
			emptySpot = '3';
			break;
		}

		if (width == 4) {
			System.out.println("  1 2 3 4");
		} else if (width == 5) {
			System.out.println("  1 2 3 4 5");
		} else if (width == 3) {
			System.out.println("  1 2 3");
		}
		for (int i = 0; i < grid.length; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0)
					System.out.print(" " + emptySpot);
				else if (grid[i][j] == 1)
					System.out.print(" " + firstPlayer);
				else
					System.out.print(" " + secondPlayer);
			}
			System.out.println("");
		}
	}

	public boolean checkLine(boolean isLarge) {
		int checkNum = isLarge ? 4 : 3;
		int consectCount = 0;
		int prev = EMPTY_SPOT;
		for (int[] line : rows) {
			for (int i = 0; i < line.length; i++) {
				if (line[i] == prev && line[i] != EMPTY_SPOT)
					consectCount++;
				else if (line[i] == EMPTY_SPOT)
					consectCount = 0;
				else
					consectCount = 1;
				if (consectCount == checkNum)
					return true;
				prev = line[i];
			}
			consectCount = 0;
			prev = EMPTY_SPOT;
		}

		consectCount = 0;
		prev = EMPTY_SPOT;

		for (int[] line : columns) {
			for (int i = 0; i < line.length; i++) {
				if (line[i] == prev && line[i] != EMPTY_SPOT)
					consectCount++;
				else if (line[i] == EMPTY_SPOT)
					consectCount = 0;
				else
					consectCount = 1;
				if (consectCount == checkNum)
					return true;
				prev = line[i];
			}
			consectCount = 0;
			prev = EMPTY_SPOT;
		}

		return false;
	}

	public boolean checkDiag(boolean isLarge) {
		int checkNum = isLarge ? 4 : 3;
		int consectCount = 0;
		int prev = EMPTY_SPOT;
		for (int[] line : ulDiags) {
			if (line.length < checkNum)
				continue;
			for (int i = 0; i < line.length; i++) {
				if (line[i] == prev && line[i] != EMPTY_SPOT)
					consectCount++;
				else if (line[i] == EMPTY_SPOT)
					consectCount = 0;
				else
					consectCount = 1;
				if (consectCount == checkNum)
					return true;
				prev = line[i];
			}
			consectCount = 0;
			prev = EMPTY_SPOT;
		}
		return false;
	}

	public boolean update(Grid move) {
		return false;
	}

	public static boolean isGridValid(Grid g) {
		return g != null && g.getXPos() < width && g.getYPos() < height
				&& g.getXPos() > -1 && g.getYPos() > -1;
	}

}
