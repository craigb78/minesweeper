package minesweeper;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
	public static final int ROW_LENGTH = 8;
	private static final int NUM_SQUARES = ROW_LENGTH * ROW_LENGTH;
	private static final int NUM_MINES = (int) (NUM_SQUARES * .1);

	private Square [][] squares;

	public Board(){
		build();
	}

	private void build()
	{
		Random random = new Random();
		Set<Integer> minedIds = random.ints(NUM_MINES,0, NUM_SQUARES).boxed().collect(Collectors.toSet());

		squares = new Square[ROW_LENGTH][ROW_LENGTH];
		for (int r = 0; r < ROW_LENGTH; r++){
			for (int c = 0; c < ROW_LENGTH; c++){
				Square [] row = squares[r];
				row[c] = new Square(Row.values()[r], Column.values()[c], minedIds.contains((r * ROW_LENGTH) + c));
			}
		}
	}

	public boolean move(Column column, Row row){
		Square sq = squares[row.getOrdinal()][column.getOrdinal()];

		if (sq.isUncovered()) {
			return false;
		}
		boolean exploded = sq.uncover();
		if (!exploded) {
			uncoverSurroundingSquares(sq, row, column, 1);

		}
		return exploded;

	}

	private void uncoverSurroundingSquares(Square sq, Row row, Column column, int iteration) {
		if (iteration > ROW_LENGTH){
			return;
		}

		int count = 0;
		List<Square> surrounding = getSurrounding(row.getOrdinal(), column.getOrdinal());
		for (Square n : surrounding) {
			if (n.isMined()) {
				++count;
			} else {
				n.uncover();
			}
		}
		if (count != 0) {
			sq.setHint("" + count);
		} else {
			for (Square n : surrounding) {
				uncoverSurroundingSquares(n, n.getRow(), n.getColumn(), iteration + 1);
			}
		}
	}

	private List<Square> getSurrounding(int row, int col) {
		List<Square> surrounding = new ArrayList<>();


		final int [] ABOVE = {1, 0}; // ROW ADJUST, COL ADJUST
		final int [] BELOW = {-1, 0};
		final int [] RIGHT = {0, 1};
		final int [] LEFT = {0, -1};

		final int [] DIAGONAL_UP_LEFT = {1, -1};
		final int [] DIAGONAL_UP_RIGHT = {1, 1};
		final int [] DIAGONAL_DOWN_LEFT = {-1, -1};
		final int [] DIAGONAL_DOWN_RIGHT = {-1, 1};

		int [][] adjusters = {ABOVE, BELOW, RIGHT, LEFT, DIAGONAL_UP_LEFT, DIAGONAL_UP_RIGHT, DIAGONAL_DOWN_LEFT, DIAGONAL_DOWN_RIGHT};

		for (int i = 0; i < adjusters.length; i++){
			int [] nextAdjuster = adjusters[i];
			int nextRow = row+nextAdjuster[0];
			int nextCol = col+nextAdjuster[1];
			if (nextRow >= 0 && nextRow < ROW_LENGTH){
				if (nextCol>=0 && nextCol < ROW_LENGTH){
					Square nextSq = squares[nextRow][nextCol];
					surrounding.add(nextSq);
				}
			}
		}

		return surrounding;
	}

	public boolean uncoveredAllUnminedSquares(){
		int uncoveredCount = 0;
		for (int r = ROW_LENGTH - 1; r >= 0; r--) {
			for (int c = 0; c < ROW_LENGTH; c++) {
				Square[] row = squares[r];
				Square sq = row[c];
				if (sq.isUncovered() && !sq.isMined()){
					uncoveredCount++;
				}
			}
		}
		return uncoveredCount == (NUM_SQUARES - NUM_MINES);
	}

	public void print(PrintStream out){
		for (int r = ROW_LENGTH - 1; r >= 0; r--){
			Square [] row = squares[r];
			out.print(row[0].getRow());
			for (int c = 0; c < ROW_LENGTH; c++){
				out.print("|");
				Square next = row[c];
				next.print(out);
			}
			out.print("|");
			out.println();
		}

		// col labels
		String colLabels = IntStream.range('A', 'A'+ROW_LENGTH).mapToObj(i->(char)i).map(String::valueOf).collect(Collectors.joining("|"));
		out.print("  |");
		out.print(colLabels);
		out.println("|");

		out.println();
	}

}
