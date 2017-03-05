package com.ttt.game.move;

import com.ttt.game.model.GameState;
import com.ttt.game.model.Square;
import com.ttt.game.model.SquareState;
import com.ttt.game.strategy.Strategy;

public abstract class MoveMechanic {
	public abstract void move(GameState gameState, Square square, SquareState squareState);
	public abstract int depth(Strategy strategy, GameState state);
	public abstract double score(GameState state);
}
