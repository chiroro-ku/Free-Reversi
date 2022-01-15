package model;

public class PlacePieceLogic {
	public void execute(Game game, Integer index) {
		Judge judge = game.getJudge();
		judge.playerPlacePiece(index);
		judgeGameEnd(judge);
		judge.computerPlacePiece();
		judgeGameEnd(judge);
		return;
	}

	private void judgeGameEnd(Judge judge) {
		if (judge.isGameEnd()) { // ゲームの終了処理
			judge.winPlayer();
		}
		return;
	}
}
