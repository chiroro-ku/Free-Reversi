package model;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
	protected Integer id;
	protected String name;
	protected Table table;
	protected Piece piece;

	public Player() {

	}

	public Player(String name) {
		this.setName(name);
		return;
	}

	public Player(Integer id, String name, Table table) {
		this.setId(id);
		this.setName(name);
		this.setTable(table);
		this.setPiece(new Piece(this.id));
		return;
	}

	public void placePiece(Integer index) {
		Grid aGrid = table.getGrid(index);
		aGrid.placePiece(piece);
		reversi(aGrid);
		return;
	}

	private void reversi(Grid aGrid) {
		List<Grid> nextGrids = aGrid.getNextGrids();
		nextGrids.stream().filter(item -> reversiNext(item, nextGrids.indexOf(item)))
				.forEach(item -> item.placePiece(piece));
		return;
	}

	protected Boolean reversiNext(Grid aGrid, Integer index) {
		Integer color = aGrid.getPiece().getColor();
		if (color > 0 && color != piece.getColor() && reversiColumn(aGrid.getNextGrid(index), index))
			return true;
		return false;
	}

	private Boolean reversiColumn(Grid aGrid, Integer index) {
		Integer color = aGrid.getPiece().getColor();
		if (color <= 0)
			return false;
		if (color == piece.getColor())
			return true;
		if (reversiColumn(aGrid.getNextGrid(index), index)) {
			aGrid.placePiece(piece);
			return true;
		}
		return false;
	}

	public Boolean isComputer() {
		return false;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @param piece セットする piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
