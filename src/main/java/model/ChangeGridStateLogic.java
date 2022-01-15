package model;

import java.util.List;

public class ChangeGridStateLogic {
	public void execute(Game newGame, Integer index) {

		// クリックされたグリッドと駒を取得する。
		Judge judge = newGame.getJudge();
		Table table = judge.getTable();
		Grid aGrid = table.getGrids().get(index);
		Piece aPiece = aGrid.getPiece();

		// プレイヤー全員の情報を取得する。
		List<Player> players = judge.getPlayers();

		if (aPiece.equals(players.get(players.size() - 1).getPiece())) // 駒から空のグリッドに取得する。
			aGrid.setPiece(table.getEmpty());
		else if (aPiece.equals(table.getEmpty())) // 空のグリッドから駒に変更する。
			aGrid.placePiece(table.getWall().getPiece());
		else if (aPiece.getColor() >= players.get(0).getPiece().getColor()) // 次のプレイヤーに変更する。
			aGrid.placePiece(players.get(aPiece.getColor()).getPiece());
		else if (aPiece.equals(table.getWall().getPiece())) // 壁のグリッドから駒に変更する。
			aGrid.placePiece(players.get(0).getPiece());

		return;
	}
}
