package com.weijinglab.selectiongame.model;

public class Action {

	public ActionType type;
	public Area area;

	public Action(ActionType type) {
		this.type = type;
	};

	public void print() {
		String msg = String.format("TYPE=%s, (%s, %s)<->(%s, %s)",
				type, area.start.row, area.start.col, area.end.row, area.end.col);
		System.out.println(msg);
	}
}
