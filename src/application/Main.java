package application;

import java.util.Scanner;

public class Main {
	private static Game game;

	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to tic tac toe game!");
		System.out.println("Please enter 1 as multiplayer mode and 2 as single player mode.");
		String mode = reader.nextLine();
		while (!mode.equals("1") && !mode.equals("2")) {
			System.out.println("The mode you entered is invalid! Please re-enter your last choice.");
			mode = reader.nextLine();
		}
		if (Integer.parseInt(mode) == 1) {
			game.startSingleplayer();
		}
	}
}
