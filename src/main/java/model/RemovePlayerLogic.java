package model;

public class RemovePlayerLogic {
	public void execute(Game newGame) {
		Judge judge = newGame.getJudge();
		if (judge.getPlayers().size() <= 2)
			return;
		judge.removePlayerPiece();
		judge.removePlayer();
		return;
	}
}
