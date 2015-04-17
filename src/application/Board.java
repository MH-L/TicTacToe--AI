package application;

import java.util.ArrayList;

public class Board {

	public static final int FIRST_PLAYER = 1;
	public static final int SECOND_PLAYER = 2;
	public static final int CLASSIC_MODE = 1;
	public static final int FANCY_MODE = 2;
	public static final int PURE_MODE = 3;

	private static int width = 4;
	private static int height = 4;
	private static int diag = 4;
	private int[][] grid;

	public static int getWidth() {
		return width;
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
		this.rows = initRows();
		this.columns = initCols();
		this.ulDiags = initDiags();
		this.urDiags = initDiags();
	}

	public ArrayList<int[]> initRows() {
		ArrayList<int[]> retVal = new ArrayList<int[]>();
		for (int i = 0; i < height; i++) {
			retVal.add(new int[width]);
		}
		return retVal;
	}

	public ArrayList<int[]> initCols() {
		ArrayList<int[]> retVal = new ArrayList<int[]>();
		for (int i = 0; i < width; i++) {
			retVal.add(new int[height]);
		}
		return retVal;
	}

	public ArrayList<int[]> initDiags() {
		ArrayList<int[]> retVal = new ArrayList<int[]>();
		for (int i = 0; i < diag; i++) {
			int length = i < width ? i + 1 : 2 * width - i;
			retVal.add(new int[length]);
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
			firstPlayer = 'X';
			secondPlayer = 'O';
			emptySpot = '-';
			break;
		case PURE_MODE:
			firstPlayer = 'X';
			secondPlayer = 'O';
			emptySpot = '-';
			break;
		}
	}

}
