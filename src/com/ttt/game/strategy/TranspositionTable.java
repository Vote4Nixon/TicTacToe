package com.ttt.game.strategy;

import java.util.HashMap;
import java.util.Map;

import com.ttt.game.Game;
import com.ttt.game.model.GameState;

public class TranspositionTable {
	private Map<String, GameNode> table;
	
	public TranspositionTable() {
		table = new HashMap<>();
	}
	
	public void clear() {
		table.clear();
	}
	
	public boolean contains(GameState state) {
		return table.containsKey(convert(state));
	}
	
	public GameNode get(GameState state) {
		return table.get(convert(state));
	}
	
	public GameNode put(GameState state, GameNode node) {
		table.put(convert(state), node);
		
		return node;
	}
	
	private String convert(GameState state) {
		String stateStr = "";
		
		for(int i = 0; i < Game.INSTANCE.size(); i++) {
			for(int j = 0; j < Game.INSTANCE.size(); j++) {
				stateStr += state.square(i, j).state().toString();
			}
		}
		
		return stateStr;
	}
}
