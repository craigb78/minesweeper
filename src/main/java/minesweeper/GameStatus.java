package minesweeper;

public enum GameStatus {
	IN_PROGRESS("Try again!"), GAME_OVER("You hit a mine! Game Over"), YOU_HAVE_WON("You have won!");

	private String description;

	GameStatus(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}
}
