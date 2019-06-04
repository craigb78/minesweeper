/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package minesweeper;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

	private static void play() {
		Board board = new Board();
		Game game = new Game(board);

		printInstructions();

		board.print(System.out);

		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine() && game.inProgress()) {
				String coords = scanner.nextLine();
				if (isLegal(coords)) {
					coords = coords.toUpperCase();
					Column col = Column.parse(coords.charAt(0));
					Row row = Row.parse(coords.charAt(1));

					if (row == null || col == null){
						System.out.println(coords + " is not a valid position");
						game.print(System.out);
					} else {
						game.move(col, row);
						game.print(System.out);
					}
				}
			}
		}
	}

	private static boolean isLegal(String coords) {
		return coords != null && coords.length() == 2;
	}

	private static void printInstructions() {
		System.out.println(" *** MINESWEEPER ***");
		System.out.println("Enter co-ords to test square (A0-H7)");
	}
}