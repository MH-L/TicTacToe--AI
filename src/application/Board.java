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
	private static boolean isLarge;

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
		if (sideLen > 4)
			isLarge = true;
		else
			isLarge = false;
		this.width = sideLen;
		this.height = sideLen;
		this.diag = width + height - 1;
		this.grid = initGrid();
		this.rows = initRows();
		this.columns = initCols();
		this.ulDiags = initDiags();
		this.urDiags = initDiags();
		this.player1stones = new ArrayList<Grid>();
		this.player2stones = new ArrayList<Grid>();
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

	public boolean checkLine() {
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

	public boolean checkDiag() {
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

	public static Grid convertToXY(int diag, int subIndex, boolean isUL) {
		if (isUL)
			if (diag < height)
				return new Grid(subIndex, subIndex - diag + width
						- 1);
			else
				return new Grid(diag + subIndex - width + 1,
						subIndex);
		else {
			if (diag < height)
				return new Grid(subIndex, diag - subIndex);
			else
				return new Grid(diag + subIndex - width + 1,
						width - 1 - subIndex);
		}
	}

	public static int getURDiagIndex(Grid loc) {
		return loc.getXPos() + loc.getYPos();
	}

	public static int getULDiagIndex(Grid loc) {
		return loc.getYPos() - loc.getXPos() + width - 1;
	}

	public static int getURDiagSubIndex(Grid loc) {
		int urIndex = getURDiagIndex(loc);
		if (urIndex >= width)
			return width - 1 - loc.getXPos();
		else
			return loc.getYPos();
	}

	public static int getULDiagSubIndex(Grid loc) {
		int ulIndex = getULDiagIndex(loc);
		if (ulIndex >= width)
			return loc.getXPos();
		else
			return loc.getYPos();
	}

	public boolean update(Grid move, boolean first) {
		if (!isGridValid(move))
			return false;
		int marker = first ? FIRST_PLAYER : SECOND_PLAYER;
		int uldiagindex = getULDiagIndex(move);
		int urdiagindex = getURDiagIndex(move);
		int uldiagsub = getULDiagSubIndex(move);
		int urdiagsub = getURDiagSubIndex(move);
		grid[move.getYPos()][move.getXPos()] = marker;
		rows.get(move.getYPos())[move.getXPos()] = marker;
		columns.get(move.getXPos())[move.getYPos()] = marker;
		ulDiags.get(uldiagindex)[uldiagsub] = marker;
		urDiags.get(urdiagindex)[urdiagsub] = marker;
		if (first)
			player1stones.add(move);
		else
			player2stones.add(move);
		return true;
	}

	public boolean withdraw(Grid move) {
		if (!isGridValid(move) || isOccupied(move))
			return false;
		if (!player1stones.contains(move) && !player2stones.contains(move))
			return false;
		int uldiagindex = getULDiagIndex(move);
		int urdiagindex = getURDiagIndex(move);
		int uldiagsub = getULDiagSubIndex(move);
		int urdiagsub = getURDiagSubIndex(move);
		grid[move.getYPos()][move.getXPos()] = EMPTY_SPOT;
		rows.get(move.getYPos())[move.getXPos()] = EMPTY_SPOT;
		columns.get(move.getXPos())[move.getYPos()] = EMPTY_SPOT;
		ulDiags.get(uldiagindex)[uldiagsub] = EMPTY_SPOT;
		urDiags.get(urdiagindex)[urdiagsub] = EMPTY_SPOT;
		if (player1stones.contains(move))
			player1stones.remove(move);
		else
			player2stones.remove(move);
		return true;
	}

	public static boolean isGridValid(Grid g) {
		return g != null && g.getXPos() < width && g.getYPos() < height
				&& g.getXPos() > -1 && g.getYPos() > -1;
	}

	public boolean isFull() {
		for (int[] arr : grid)
			for (int i : arr)
				if (i == EMPTY_SPOT)
					return false;
		return true;
	}

	public boolean isOccupied(Grid g) {
		return grid[g.getYPos()][g.getYPos()] != 0;
	}

}
