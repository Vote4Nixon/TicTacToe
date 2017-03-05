package com.ttt.game.model;

import java.util.Set;
import java.util.stream.Collectors;

public class Row extends SquareSet {
	public Row(int num) {
		super(num);
	}
	
	@Override
	public void addAppropriateSquares(Set<Square> squares) {
		this.squares.addAll(squares.stream()
				.filter(s -> s.row() == num)
				.collect(Collectors.toSet())
				);
	}
}
