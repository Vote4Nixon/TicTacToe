package com.ttt.game.strategy;

import java.util.Set;

import com.ttt.game.Game;
import com.ttt.game.model.GameState;
import com.ttt.game.model.Square;
import com.ttt.game.model.SquareState;

public class MinimaxStrategy extends Strategy {
	private GameNode root;
	private TranspositionTable table;
	
	public MinimaxStrategy() {
		super();
		table = new TranspositionTable();
	}

	@Override
	public Square findMove() {
		return root.bestChild().changed();
	}

	@Override
	public void update(Square s) {
		GameState rootState = Game.INSTANCE.state().clone();
		int depth = Game.INSTANCE.mechanic().depth(this, rootState);
		
		System.out.println("depth = " + depth);
		
		table.clear();
		root = buildTree(Game.INSTANCE.state().clone(), Game.INSTANCE.botState(), depth);
	}
	
	@Override
	public int maxEvals() {
		return 500000;
	}
	
	private GameNode buildTree(GameState gameState, SquareState squareState, int depth) {
		if(table.contains(gameState)) {
			return table.get(gameState);
		}
		
		boolean maximizing = squareState == Game.INSTANCE.botState();
		double bestValue = maximizing ? -10 : 10;
		
		GameNode node = new GameNode(gameState);
		
		Set<Square> blankSquares = gameState.squares(SquareState.BLANK);
		
		if(depth == 0 || gameState.won(Game.INSTANCE.playerState()) || gameState.won(Game.INSTANCE.botState()) || blankSquares.size() == 0) {
			node.setScore(Game.INSTANCE.mechanic().score(gameState));
			
			return table.put(gameState, node);
		}
		
		for(Square square : blankSquares) {
			GameState childState = gameState.clone();
			Game.INSTANCE.mechanic().move(childState, childState.square(square.row(), square.col()), squareState);
			
			GameNode child = buildTree(childState, SquareState.opposite(squareState), depth - 1);
			child.setChanged(square);
			node.addChild(child);
			
			if(maximizing) {
				bestValue = Math.max(bestValue, child.score());
			}
			else {
				bestValue = Math.min(bestValue, child.score());
			}
		}
		
		node.setScore(bestValue);
		
		return table.put(gameState, node);
	}
}
