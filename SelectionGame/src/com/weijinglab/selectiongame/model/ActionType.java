package com.weijinglab.selectiongame.model;

public enum ActionType {

	PLUS,
	MINUS,
	REVERSE;
	
	public String toString() {
		if(this == ActionType.PLUS)
			return "PLUS";
		else if(this == ActionType.MINUS)
			return "MINUS";
		else if(this == ActionType.REVERSE)
			return "REVERSE";
		return "NULL";
	}
	
}
