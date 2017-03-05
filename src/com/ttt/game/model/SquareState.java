package com.ttt.game.model;

public enum SquareState {
	X("x"),
	O("o"),
	BLANK(" ");
	
	private String printName;
	
	private SquareState(String printName) {
		this.printName = printName;
	}
	
	@Override
	public String toString() {
		return printName;
	}
	
	public static SquareState opposite(SquareState state) {
		if(state == SquareState.X) {
			return SquareState.O;
		}
		else if(state == SquareState.O) {
			return SquareState.X;
		}
		else {
			return SquareState.BLANK;
		}
	}
}
