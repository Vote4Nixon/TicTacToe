package com.ttt.game.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.ttt.game.Game;

public class Diagonal extends SquareSet {

	public Diagonal(int num) {
		super(num);
	}
	
	@Override
	public void addAppropriateSquares(Set<Square> squares) {
		if(num == 0) {
			this.squares.addAll(squares.stream()
					.filter(s -> s.row() == s.col())
					.collect(Collectors.toSet())
					);
		}
		else if(num == 1) {
			this.squares.addAll(squares.stream()
					.filter(s -> s.row() + s.col() == Game.INSTANCE.size() - 1)
					.collect(Collectors.toSet())
					);
		}
	}
}