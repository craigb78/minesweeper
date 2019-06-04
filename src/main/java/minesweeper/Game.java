package minesweeper;

import java.io.PrintStream;

public class Game {
	private Board board;
	private GameStatus status = GameStatus.IN_PROGRESS;

	public Game(Board b){
		this.board = b;
	}

	public void move(Column c, Row r){
		boolean hitMine = board.move(c, r);

		if (hitMine){
			status = GameStatus.GAME_OVER;
		} else if (board.uncoveredAllUnminedSquares()){
			status = GameStatus.YOU_HAVE_WON;
		} else {
			status = GameStatus.IN_PROGRESS;
		}
	}

	public boolean inProgress() {
		return status == GameStatus.IN_PROGRESS;
	}

	public void print(PrintStream w){
		board.print(w);
		w.println(status.getDescription());
	}


}
