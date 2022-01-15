package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Judge implements Serializable {
	private Table table;
	private List<Player> players;
	private Integer playerIndex;

	public Judge() {
		Integer maxColumn = 8;
		Integer maxRow = 8;
		this.setTable(new Table(maxColumn, maxRow));
		this.setPlayers(this.defalutPlayerList());
		this.setPlayerIndex(0);
		return;
	}

	private List<Player> defalutPlayerList() {
		List<Player> aList = new ArrayList<Player>();
		aList.add(new Computer(aList.size() + 1, this.table));
		aList.add(new Computer(aList.size() + 1, this.table));
		return aList;
	}

	public void addPlayer() {
		players.add(new Computer(players.size() + 1, this.table));
		return;
	}

	public void removePlayerPiece() {
		Player removePlayer = players.get(players.size() - 1);
		List<Grid> grids = table.getGrids();
		grids.stream().filter(item -> item.getPiece().equals(removePlayer.getPiece()))
				.forEach(item -> item.setPiece(table.getEmpty()));
		return;
	}

	public void removePlayer() {
		players.remove(players.size() - 1);
		return;
	}

	public void playerPlacePiece(Integer index) {
		Player aPlayer = getCurrentPlayer();
		if (table.isPlacePiece(aPlayer, index)) {
			aPlayer.placePiece(index);
			nextPlayer();
		}
		return;
	}

	private void nextPlayer() {
		playerIndex = (playerIndex + 1) % players.size();
		return;
	}

	public void computerPlacePiece() {
		while (this.getCurrentPlayer().isComputer()) {
			Computer aComputer = (Computer) getCurrentPlayer();
			if (isPlayerPlacePiece(aComputer)) {
				aComputer.placePiece();
			}
			nextPlayer();
//            if (Judge()) // ゲームの状況を更新する。
//                return; // ゲームが終了した時、プログラムを抜ける。
		}
		return;
	}

	private Boolean isPlayerPlacePiece(Player aPlayer) {
		List<Grid> grids = table.getGrids();
		for (Grid aGrid : grids) {
			if (table.isPlacePiece(aPlayer, aGrid))
				return true;
		}
		return false;
	}

	public Boolean isGameEnd() {
		if (table.getEmpty().getNumber() == 0)
			return true;
		Integer current = playerIndex;
		while (!isPlayerPlacePiece(getCurrentPlayer())) {
			nextPlayer();
			if (playerIndex == current)
				return true;
		}
		return false;
	}

	public void winPlayer() {
		List<Integer> aList = new ArrayList<>();
		players.forEach(item -> aList.add(item.getId()));
		Optional<Integer> max = aList.stream().max(Integer::compareTo);
		playerIndex = aList.indexOf(max.get());
		return;
	}

	public void entryPlayer(Player aPlayer) {
		Computer computer = (Computer) players.get(0);
		Player player = computer.replaceToPlayer(aPlayer);
		players.set(0, player);
		return;
	}

	/**
	 * @return table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table セットする table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * @return players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return players.get(playerIndex);
	}

	/**
	 * @param players セットする players
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * @return playerIndex
	 */
	public Integer getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @param playerIndex セットする playerIndex
	 */
	public void setPlayerIndex(Integer playerIndex) {
		this.playerIndex = playerIndex;
	}
}
