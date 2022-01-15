package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Table implements Serializable {

	/**
	 * テーブルの最大列数を束縛している。
	 */
	private Integer maxColumn;

	/**
	 * テーブルの最大行数を束縛している。
	 */
	private Integer maxRow;

	/**
	 * 壁のグリッドを束縛している。
	 */
	private Grid wall;

	/**
	 * 空のグリッドのに配置する駒を束縛している。
	 */
	private Piece empty;

	/**
	 * テーブル上での方向性を束縛している。
	 */
	private List<Integer> direction;

	/**
	 * グリッド全体のインスタンスを束縛している。
	 */
	private List<Grid> grids;

	public Table() {

	}

	public Table(Integer maxColumn, Integer maxRow) {

		this.setMaxColumn(maxColumn);
		this.setMaxRow(maxRow);
		this.setWall(new Grid(new Piece(-1)));
		this.setEmpty(new Piece(0));
		this.setDirection(this.defaltDirectionList());
		this.setGrids(this.gridList());

		grids.forEach(item -> item.setNextGrids(nextGrids(item)));

		return;
	}

	private List<Grid> gridList() {
		List<Grid> aList = new ArrayList<Grid>();
		IntStream.range(0, maxColumn * maxRow).forEach(i -> aList.add(new Grid(i, empty, gridColumn(i), gridRow(i))));
		return aList;
	}

	/**
	 * インデックスから列数を応答する。
	 * 
	 * @param index インデックス
	 * @return 列数
	 */
	private Integer gridColumn(Integer index) {
		return index % maxColumn;
	}

	/**
	 * インデックスから行数を応答する。
	 * 
	 * @param index 行数
	 * @return 行数
	 */
	private Integer gridRow(Integer index) {
		return index / maxColumn;
	}

	/**
	 * プレイヤーとグリッドから、駒の配置の可不可に応答する。
	 * 
	 * @param aPlayer プレイヤー
	 * @param aGrid   グリッド
	 * @return 駒の配置の可不可
	 */
	public Boolean isPlacePiece(Player aPlayer, Grid grid) {
		Integer index = grid.getIndex();
		return this.isPlacePiece(aPlayer, index);
	}

	/**
	 * プレイヤーとインデックスから、駒の配置の可不可に応答する。
	 * 
	 * @param aPlayer プレイヤー
	 * @param aGrid   グリッド
	 * @return 駒の配置の可不可
	 */
	public Boolean isPlacePiece(Player aPlayer, Integer index) {
		Grid aGrid = grids.get(index);
		Integer color = aPlayer.getPiece().getColor();
		if (!aGrid.getPlacePiece())
			return false;
		Integer i = 0;
		for (Grid aNextGrid : aGrid.getNextGrids()) {
			Integer nextColor = aNextGrid.getPiece().getColor();
			if (nextColor > 0 && nextColor != color && isPlacePieceColumn(color, aNextGrid.getNextGrid(i), i))
				return true;
			else
				i++;
		}
		return false;
	}

	/**
	 * 駒の配置の可不可を列を見て判断する。
	 * 
	 * @param color     配置する駒の色
	 * @param aNextGrid 隣のグリッド
	 * @param index     インデックス
	 * @return 駒の配置の可不可
	 */
	private Boolean isPlacePieceColumn(Integer color, Grid aNextGrid, Integer index) {
		Integer nextColor = aNextGrid.getPiece().getColor();
		if (nextColor <= 0)
			return false;
		if (nextColor == color)
			return true;
		return isPlacePieceColumn(color, aNextGrid.getNextGrid(index), index);
	}

	/**
	 * 全ての隣のグリッドに応答する。
	 * 
	 * @param aGrid 中心のグリッド
	 * @return グリッドのリスト
	 */
	private List<Grid> nextGrids(Grid aGrid) {
		List<Grid> aList = new ArrayList<>();
		direction.forEach(item -> aList.add(nextGrid(aGrid, item)));
		return aList;
	}

	/**
	 * 隣のグリッドに応答する。
	 * 
	 * @param aGrid          中心のグリッド
	 * @param directionIndex 方向
	 * @return 隣のグリッド
	 */
	private Grid nextGrid(Grid aGrid, Integer directionIndex) {
		Integer index = aGrid.getIndex() + directionIndex;
		if (isWall(aGrid, directionIndex))
			return wall;
		return grids.get(index);
	}

	/**
	 * 隣のグリッドの壁の是非を応答する。
	 * 
	 * @param aGrid         中心のグリッド
	 * @param nextGridIndex 方向
	 * @return 壁の是非
	 */
	private Boolean isWall(Grid aGrid, Integer nextGridIndex) {
		Integer endGridColumn = maxColumn - 1;
		Integer endGridRow = maxRow - 1;
		Integer column = aGrid.getColumn();
		Integer row = aGrid.getRow();
		if (column == 0 && isDirectionLeft(nextGridIndex))
			return true;
		if (column == endGridColumn && isDirectionRight(nextGridIndex))
			return true;
		if (row == 0 && isDirectionUp(nextGridIndex))
			return true;
		if (row == endGridRow && isDirectionDown(nextGridIndex))
			return true;
		return false;
	}

	/**
	 * インデックスが上方向か判断する。
	 * 
	 * @param index インデックス
	 * @return 方向の是非
	 */
	private Boolean isDirectionUp(Integer index) {
		if (index == getDirectionUp())
			return true;
		if (index == getDirectionUpRight())
			return true;
		if (index == getDirectionUpLeft())
			return true;
		return false;
	}

	/**
	 * インデックスが右方向か判断する。
	 * 
	 * @param index インデックス
	 * @return 方向の是非
	 */
	private Boolean isDirectionRight(Integer index) {
		if (index == getDirectionRight())
			return true;
		if (index == getDirectionUpRight())
			return true;
		if (index == getDirectionDownRight())
			return true;
		return false;
	}

	/**
	 * インデックスが下方向か判断する。
	 * 
	 * @param index インデックス
	 * @return 方向の是非
	 */
	private Boolean isDirectionDown(Integer index) {
		if (index == getDirectionDown())
			return true;
		if (index == getDirectionDownRight())
			return true;
		if (index == getDirectionDownLeft())
			return true;
		return false;
	}

	/**
	 * インデックスが左方向か判断する。
	 * 
	 * @param index インデックス
	 * @return 方向の是非
	 */
	private Boolean isDirectionLeft(Integer index) {
		if (index == getDirectionLeft())
			return true;
		if (index == getDirectionUpLeft())
			return true;
		if (index == getDirectionDownLeft())
			return true;
		return false;
	}

	private List<Integer> defaltDirectionList() {
		List<Integer> aList = new ArrayList<Integer>();
		aList.add(this.getDirectionUp());
		aList.add(this.getDirectionRight());
		aList.add(this.getDirectionDown());
		aList.add(this.getDirectionLeft());
		aList.add(this.getDirectionUpRight());
		aList.add(this.getDirectionUpLeft());
		aList.add(this.getDirectionDownRight());
		aList.add(this.getDirectionDownLeft());
		return aList;
	}

	/**
	 * 上のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionUp() {
		return -maxColumn;
	}

	/**
	 * 下のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionDown() {
		return maxColumn;
	}

	/**
	 * 右のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionRight() {
		return 1;
	}

	/**
	 * 左のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionLeft() {
		return -1;
	}

	/**
	 * 右上のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionUpRight() {
		return getDirectionUp() + getDirectionRight();
	}

	/**
	 * 左上のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionUpLeft() {
		return getDirectionUp() + getDirectionLeft();
	}

	/**
	 * 右下のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionDownRight() {
		return getDirectionDown() + getDirectionRight();
	}

	/**
	 * 左下のグリッドのインデックスに応答する。
	 * 
	 * @return インデックス
	 */
	public Integer getDirectionDownLeft() {
		return getDirectionDown() + getDirectionLeft();
	}

	/**
	 * @return maxColumn
	 */
	public Integer getMaxColumn() {
		return maxColumn;
	}

	/**
	 * @param maxColumn セットする maxColumn
	 */
	public void setMaxColumn(Integer maxColumn) {
		this.maxColumn = maxColumn;
	}

	/**
	 * @return maxRow
	 */
	public Integer getMaxRow() {
		return maxRow;
	}

	/**
	 * @param maxRow セットする maxRow
	 */
	public void setMaxRow(Integer maxRow) {
		this.maxRow = maxRow;
	}

	/**
	 * @return grids
	 */
	public List<Grid> getGrids() {
		return grids;
	}

	public Grid getGrid(Integer index) {
		return grids.get(index);
	}

	/**
	 * @param grids セットする grids
	 */
	public void setGrids(List<Grid> grids) {
		this.grids = grids;
	}

	/**
	 * @return wall
	 */
	public Grid getWall() {
		return wall;
	}

	/**
	 * @param wall セットする wall
	 */
	public void setWall(Grid wall) {
		this.wall = wall;
	}

	/**
	 * @return empty
	 */
	public Piece getEmpty() {
		return empty;
	}

	/**
	 * @param empty セットする empty
	 */
	public void setEmpty(Piece empty) {
		this.empty = empty;
	}

	/**
	 * @return direction
	 */
	public List<Integer> getDirection() {
		return direction;
	}

	/**
	 * @param direction セットする direction
	 */
	public void setDirection(List<Integer> direction) {
		this.direction = direction;
	}
}
