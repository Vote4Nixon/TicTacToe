package com.ttt.game.move;

import com.ttt.game.Game;
import com.ttt.game.model.GameState;
import com.ttt.game.model.Square;
import com.ttt.game.model.SquareState;
import com.ttt.game.strategy.Strategy;

public class StandardMove extends MoveMechanic {

	@Override
	public void move(GameState gameState, Square square, SquareState squareState) {
		square.setState(squareState);
	}

	@Override
	public int depth(Strategy strategy, GameState state) {
		int depth = 0;
		int blankCount = state.squares(SquareState.BLANK).size();
		double evals = strategy.maxEvals();
		
		while(evals > 1 && blankCount > depth) {
			evals /= blankCount - depth;
			depth++;
		}
		if(evals < 1) {
			depth--;
		}
		
		return depth;
	}

	@Override
	public double score(GameState state) {
		if(state.won(Game.INSTANCE.playerState())) {
			return -1;
		}
		else if(state.won(Game.INSTANCE.botState())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
