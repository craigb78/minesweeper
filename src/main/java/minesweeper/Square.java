package minesweeper;

import java.io.PrintStream;

public class Square {

	private Row row;
	private Column column;
	private boolean mined;
	private boolean uncovered;
	private String hint;

	public Square(Row row, Column column, boolean mined) {

		this.row = row;
		this.column = column;
		this.mined = mined;
		this.uncovered = false;
		this.hint = "";
	}

	public Row getRow() {
		return row;
	}

	public Column getColumn() {
		return column;
	}

	public boolean isUncovered(){
		return uncovered;
	}

	public boolean uncover(){
		this.uncovered = true;
		return this.mined;
	}

	public boolean isMined() {
		return mined;
	}

	public void setHint(String h){
		this.hint = h;
	}

	public void print(PrintStream p){

		if(uncovered && mined){
			p.print("*"); // exploded
		} else if (hint != null && hint.length() > 0) {
			p.print(hint);
		} else if (!uncovered){
			p.print("X");
		} else {
			p.print(" ");
		}
	}

	@Override
	public String toString(){
		return this.row.toString() + this.column.toString()+"";
	}
}
