package model;

public class NewPlayerLogic {
	public void execute(Game newGame) {
		Judge judge = newGame.getJudge();
		judge.addPlayer();
		return;
	}
}
