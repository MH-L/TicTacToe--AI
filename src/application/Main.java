package application;

import java.util.Scanner;

import exception.InvalidMoveException;

public class Main {
	private static Game game;

	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		game = Game.getInstance();
		System.out.println("Welcome to tic tac toe game!");
		System.out
				.println("Please enter 1 as multiplayer mode and 2 as single player mode.");
		String mode = reader.nextLine();
		while (!mode.equals("1") && !mode.equals("2")) {
			System.out.println("The mode you entered is invalid! "
					+ "Please re-enter your last choice.");
			mode = reader.nextLine();
		}
		if (Integer.parseInt(mode) == 1) {
			game.initGame(Integer.parseInt(mode));
		}
		System.out.println("Please select the board size:");
		System.out.println("(1) Small board (3*3)\n(2) Medium board (4*4)"
				+ "\n(3) Large board(5*5)");
		System.out.println("Note that if you choose large board, "
				+ "in order to win,\n you must have four pegs in a row.");
		String boardSize = reader.nextLine();
		while (!boardSize.equals("1") && !boardSize.equals("2")
				&& !boardSize.equals("3")) {
			System.out.println("The board size you entered is invalid. "
					+ "Please re-enter your choice below.");
			boardSize = reader.nextLine();
		}
		switch (Integer.parseInt(boardSize)) {
		case 1:
			Game.initGameBoard(3);
			break;
		case 2:
			Game.initGameBoard(4);
			break;
		case 3:
			Game.initGameBoard(5);
			break;
		default:
			Game.initGameBoard(4);
			break;
		}
		System.out.println("Please select the display mode:");
		System.out.println("(1) Classic Mode\n"
				+ "(2) Fancy Mode\n(3) Pure Mode\n(4) Check Mode");
		String dispMode = reader.nextLine();
		while (!dispMode.equals("1") && !dispMode.equals("2")
				&& !dispMode.equals("3") && !dispMode.equals("4")) {
			System.out.println("The display mode you entered is invalid!"
					+ " Please re-enter your last choice.");
			dispMode = reader.nextLine();
		}
		printInstructions();
		System.out.println("Here is the game board.");
		game.getBoard().renderBoard(Integer.parseInt(dispMode));
		while (!game.gameover()) {
			String dispString = game.getActivePlayer() ? "Player 1, " : "Player 2, ";
			System.out.println(dispString + "it is your turn now.");
			String userIn = reader.nextLine();
			String[] split = userIn.split(",");
			if (split.length != 2) {
				System.out.println("The location you entered is invalid!"
						+ " Please re-enter.");
				continue;
			} else {
				if (!isNumeric(split[1]) || !isNumeric(split[0])) {
					System.out.println("The location you entered is invalid!"
							+ " Please re-enter.");
				} else {
					int xPos = Integer.parseInt(split[0]);
					int yPos = Integer.parseInt(split[1]);
					Grid toPlaceStone = new Grid(yPos - 1, xPos - 1);
					try {
						game.update(toPlaceStone);
					} catch (InvalidMoveException e) {
						System.out.println(e.getMessage());
						continue;
					}
				}
			}
		}
	}

	public static void printInstructions() {
		System.out.println("To place a stone, "
				+ "enter two numbers separated by a comma.");
		System.out.println("e.g. \"1,2\", \"3,3\" are all valid inputs.");
		System.out.println("However, inputs such as \"4,5,6\","
				+ " \"A,2\", \"3\" are not valid.");
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
