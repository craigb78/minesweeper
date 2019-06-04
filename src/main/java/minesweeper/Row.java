package minesweeper;

public enum Row {
	_0(0), _1(1), _2(2), _3(3), _4(4), _5(5), _6(6), _7(7);

	private int ordinal;

	Row(int r){
		this.ordinal= r;
	}

	public int getOrdinal(){
		return ordinal;
	}

	public static Row parse(char ch){
		int index = ch - '0';
		if (index < 0 || index > Row.values().length){
			return null;
		}
		return Row.values()[index];
	}
}
