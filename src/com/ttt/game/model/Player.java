package com.ttt.game.model;

public class Player {
	private SquareState state;
	
	public Player(SquareState state) {
		this.state = state;
	}
	
	public SquareState state() {
		return state;
	}
}
