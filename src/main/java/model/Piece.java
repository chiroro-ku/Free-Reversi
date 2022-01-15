package model;

import java.io.Serializable;

public class Piece implements Serializable {
	private Integer color;
	private Integer number;

	public Piece(Integer color) {
		this.setColor(color);
		this.setNumber(0);
		return;
	}

	public void increase() {
		this.number++;
		return;
	}

	public void decrease() {
		this.number--;
		return;
	}

	/**
	 * @return color
	 */
	public Integer getColor() {
		return color;
	}

	/**
	 * @param color セットする color
	 */
	public void setColor(Integer color) {
		this.color = color;
	}

	/**
	 * @return number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number セットする number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
}
