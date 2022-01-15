package model;

import java.io.Serializable;

public class Game implements Serializable {
	private Integer id;
	private Judge judge;

	public Game() {
		this.setJudge(new Judge());
		return;
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
	 * @return judge
	 */
	public Judge getJudge() {
		return judge;
	}

	/**
	 * @param judge セットする judge
	 */
	public void setJudge(Judge judge) {
		this.judge = judge;
	}
}
