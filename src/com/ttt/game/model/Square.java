package com.ttt.game.model;

public class Square {
	private SquareState state;
	private int row;
	private int col;
	
	public Square(int row, int col) {
		this.row = row;
		this.col = col;
		
		state = SquareState.BLANK;
	}
	
	public SquareState state() {
		return state;
	}
	
	public int row() {
		return row;
	}
	
	public int col() {
		return col;
	}
	
	public void setState(SquareState state) {
		this.state = state;
	}
}
