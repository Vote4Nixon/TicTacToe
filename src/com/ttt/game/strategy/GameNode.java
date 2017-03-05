package com.ttt.game.strategy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.ttt.game.model.GameState;
import com.ttt.game.model.Square;

public class GameNode {
	private GameState state;
	private Square changed;
	private Set<GameNode> children;
	private double score;
	
	public GameNode(GameState state) {
		this(state, null);
	}
	
	public GameNode(GameState state, Square changed) {
		this.state = state;
		this.changed = changed;
		children = new HashSet<>();
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public void setChanged(Square changed) {
		this.changed = changed;
	}
	
	public void addChild(GameNode child) {
		children.add(child);
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public GameNode bestChild() {
		return children.stream()
				.max(Comparator.comparingDouble(n -> n.score()))
				.get();
	}
	
	public GameState state() {
		return state;
	}
	
	public Square changed() {
		return changed;
	}
	
	public double score() {
		return score;
	}
}
