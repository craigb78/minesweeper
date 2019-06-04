package minesweeper;

public enum Column {
	A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

	private int ordinal;

	Column(int c){
		this.ordinal = c;
	}

	public int getOrdinal(){
		return this.ordinal;
	}

	public static Column parse(char c) {
		int index = c-'A';
		if (index < 0 || index > Column.values().length){
			return null;
		}
		return Column.values()[index];
	}
}
