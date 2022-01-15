package model;

import java.io.Serializable;
import java.util.List;

public class Grid implements Serializable {
	private Integer index;
	private Piece piece;
	private List<Grid> nextGrids;
	private Integer column;
	private Integer row;
	private Boolean placePiece;

	public Grid() {

	}

	public Grid(Integer index, Piece piece, Integer column, Integer row) {
		this.setIndex(index);
		this.setPiece(piece);
		this.setColumn(column);
		this.setRow(row);
		this.setPlacePiece(true);
		return;
	}

	public Grid(Piece piece) {
		this.setIndex(-1);
		this.setPiece(piece);
		this.setPlacePiece(false);
		return;
	}

	public void placePiece(Piece aPiece) {
		this.piece.decrease();
		this.piece = aPiece;
		this.piece.increase();
		this.placePiece = false;
		return;
	}

	/**
	 * @return index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index セットする index
	 */
	public void setIndex(Integer index) {
		this.index = index;
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
		this.piece.increase();
		this.setPlacePiece(true);
		return;
	}

	/**
	 * @return nextGrids
	 */
	public List<Grid> getNextGrids() {
		return nextGrids;
	}

	public Grid getNextGrid(Integer index) {
		return nextGrids.get(index);
	}

	/**
	 * @param nextGrids セットする nextGrids
	 */
	public void setNextGrids(List<Grid> nextGrids) {
		this.nextGrids = nextGrids;
	}

	/**
	 * @return column
	 */
	public Integer getColumn() {
		return column;
	}

	/**
	 * @param column セットする column
	 */
	public void setColumn(Integer column) {
		this.column = column;
	}

	/**
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * @param row セットする row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * @return placePiece
	 */
	public Boolean getPlacePiece() {
		return placePiece;
	}

	/**
	 * @param placePiece セットする placePiece
	 */
	public void setPlacePiece(Boolean placePiece) {
		this.placePiece = placePiece;
	}
}
