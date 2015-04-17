package application;

import java.util.Scanner;

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
		System.out.println("Here is the game board.");
		game.getBoard().renderBoard(Integer.parseInt(dispMode));
	}
}
