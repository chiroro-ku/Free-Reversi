package model;

public class NewTableLogic {
	public void execute(Game newGame, Integer maxColumn, Integer maxRow) {
		Judge judge = newGame.getJudge();
		judge.setTable(new Table(maxColumn, maxRow));
		return;
	}
}
