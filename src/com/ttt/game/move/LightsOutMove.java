package com.ttt.game.move;

import com.ttt.game.Game;
import com.ttt.game.model.GameState;
import com.ttt.game.model.Square;
import com.ttt.game.model.SquareState;
import com.ttt.game.strategy.Strategy;

public class LightsOutMove extends MoveMechanic {

	@Override
	public void move(GameState gameState, Square square, SquareState squareState) {
		Square top = gameState.square(square.row() - 1, square.col());
		Square bottom = gameState.square(square.row() + 1, square.col());
		Square left = gameState.square(square.row(), square.col() - 1);
		Square right = gameState.square(square.row(), square.col() + 1);
		
		square.setState(squareState);

		moveAdjacent(top, squareState);
		moveAdjacent(bottom, squareState);
		moveAdjacent(left, squareState);
		moveAdjacent(right, squareState);
	}

	@Override
	public int depth(Strategy strategy, GameState state) {
		double percentBlank = state.squares(SquareState.BLANK).size() / (double)state.squares().size();
		
		if(percentBlank == 0) {
			return 10;
		}
		else {
			return (int)Math.round(3 / Math.pow(percentBlank, 0.25));
		}
	}

	@Override
	public double score(GameState state) {
		long playerCount = state.maxCount(Game.INSTANCE.playerState());
		long botCount = state.maxCount(Game.INSTANCE.botState());
		
		if(botCount == Game.INSTANCE.size()) {
			return Game.INSTANCE.size() + 1;
		}
		else if(playerCount == Game.INSTANCE.size()) {
			return -1 * (Game.INSTANCE.size() + 1);
		}
		else {
			return botCount - playerCount;
		}
	}
	
	private void moveAdjacent(Square square, SquareState state) {
		if(square != null) {
			if(square.state() == SquareState.opposite(state)) {
				square.setState(SquareState.BLANK);
			}
			else {
				square.setState(state);
			}
		}
	}

}
