package model;

public class PlayerEntryGameLogic {
	public void execute(Game game, Player player) {
		Judge judge = game.getJudge();
		judge.entryPlayer(player);
		return;
	}
}
