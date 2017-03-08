package com.ttt.game.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ttt.game.Game;

public class GameState {
	private Map<Integer, Map<Integer, Square>> squares;
	private Set<Square> flatSquares;
	private Map<Integer, Row> rows;
	private Map<Integer, Column> cols;
	private Map<Integer, Diagonal> diags;
	private Set<SquareSet> flatSets;
	
	public void initialize() {
		squares = new HashMap<>();
		flatSquares = new HashSet<>();
		for(int i = 0; i < Game.INSTANCE.size(); i++) {
			squares.put(i, new HashMap<>());
			
			for(int j = 0; j < Game.INSTANCE.size(); j++) {
				Square s = new Square(i, j);
				
				squares.get(i).put(j, s);
				flatSquares.add(s);
			}
		}
		
		flatSets = new HashSet<>();
		rows = new HashMap<>();
		cols = new HashMap<>();
		for(int i = 0; i < Game.INSTANCE.size(); i++) {
			Row r = new Row(i);
			r.addAppropriateSquares(flatSquares);
			rows.put(i, r);
			flatSets.add(r);
			
			Column c = new Column(i);
			c.addAppropriateSquares(flatSquares);
			cols.put(i, c);
			flatSets.add(c);
		}
		
		diags = new HashMap<>();
		for(int i = 0; i < 2; i++) {
			Diagonal d = new Diagonal(i);
			d.addAppropriateSquares(flatSquares);
			diags.put(i, d);
			flatSets.add(d);
		}
	}
	
	public GameState clone() {
		GameState state = new GameState();
		state.initialize();
		
		flatSquares.forEach(s -> {
			state.square(s.row(), s.col()).setState(s.state());
		});
		
		return state;
	}
	
	public Square square(int row, int col) {
		if(squares.get(row) == null) {
			return null;
		}
		return squares.get(row).get(col);
	}
	
	public boolean finished() {
		return !flatSquares.stream()
				.anyMatch(s -> s.state() == SquareState.BLANK);
	}
	
	public boolean won(SquareState state) {		
		return flatSets.stream()
				.anyMatch(s -> s.count(state) == Game.INSTANCE.size());
	}
	
	public Set<Square> squares() {
		return flatSquares;
	}
	
	public Set<Square> squares(SquareState state) {
		return flatSquares.stream()
				.filter(s -> s.state() == state)
				.collect(Collectors.toSet());
	}
	
	public Set<SquareSet> sets() {
		return flatSets;
	}
}
