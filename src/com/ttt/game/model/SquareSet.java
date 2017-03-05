package com.ttt.game.model;

import java.util.HashSet;
import java.util.Set;

public abstract class SquareSet {
	protected int num;
	protected Set<Square> squares;
	
	public SquareSet(int num) {
		this.num = num;
		
		squares = new HashSet<>();
	}
	
	public int num() {
		return num;
	}
	
	public Set<Square> squares() {
		return squares;
	}
	
	public void addAppropriateSquares(Set<Square> squares) {
		this.squares.addAll(squares);
	}
	
	public long count(SquareState state) {
		return squares.stream()
				.filter(s -> s.state() == state)
				.count();
	}
}
