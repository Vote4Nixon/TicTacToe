package com.ttt.game.model;

import java.util.Comparator;
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
		
		rows = new HashMap<>();
		cols = new HashMap<>();
		for(int i = 0; i < Game.INSTANCE.size(); i++) {
			Row r = new Row(i);
			r.addAppropriateSquares(flatSquares);
			rows.put(i, r);
			
			Column c = new Column(i);
			c.addAppropriateSquares(flatSquares);
			cols.put(i, c);
		}
		
		diags = new HashMap<>();
		for(int i = 0; i < 2; i++) {
			Diagonal d = new Diagonal(i);
			d.addAppropriateSquares(flatSquares);
			diags.put(i, d);
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
	
	public long maxCount(SquareState state) {
		long rowMax = rows.values().stream()
				.map(s -> s.count(state))
				.max(Comparator.comparingLong(s -> s))
				.get();
		long colMax = cols.values().stream()
				.map(s -> s.count(state))
				.max(Comparator.comparingLong(s -> s))
				.get();
		long diagMax = diags.values().stream()
				.map(s -> s.count(state))
				.max(Comparator.comparingLong(s -> s))
				.get();
		
		return Math.max(rowMax, Math.max(colMax, diagMax));
	}
	
	public boolean won(SquareState state) {
		return maxCount(state) == Game.INSTANCE.size();
	}
	
	public Set<Square> squares() {
		return flatSquares;
	}
	
	public Set<Square> squares(SquareState state) {
		return flatSquares.stream()
				.filter(s -> s.state() == state)
				.collect(Collectors.toSet());
	}
}
