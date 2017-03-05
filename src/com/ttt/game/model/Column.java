package com.ttt.game.model;

import java.util.Set;
import java.util.stream.Collectors;

public class Column extends SquareSet {
	public Column(int num) {
		super(num);
	}
	
	@Override
	public void addAppropriateSquares(Set<Square> squares) {
		this.squares.addAll(squares.stream()
				.filter(s -> s.col() == num)
				.collect(Collectors.toSet())
				);
	}
}
