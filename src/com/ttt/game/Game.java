package com.ttt.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ttt.game.model.GameState;
import com.ttt.game.model.Player;
import com.ttt.game.model.Square;
import com.ttt.game.model.SquareState;
import com.ttt.game.move.MoveMechanic;
import com.ttt.game.strategy.Strategy;

public enum Game {
	INSTANCE;
	
	private boolean active;
	private int size;
	private Player player;
	private GameState state;
	private Strategy strategy;
	private MoveMechanic mechanic;
	
	public void start(SquareState playerState, int size, Strategy strategy, MoveMechanic mechanic) {
		this.size = size;
		player = new Player(playerState);
		
		state = new GameState();
		state.initialize();
		
		this.strategy = strategy;
		this.mechanic = mechanic;
		
		active = true;
		
		if(playerState == SquareState.O) {
			move(null);
		}
	}
	
	public void stop() {
		active = false;
	}
	
	public void print() {
		String divider = Stream.iterate("---", n -> n)
				.limit(size)
				.collect(Collectors.joining("|"));
		
		System.out.println();
		
		for(int i = 0; i < size; i++) {
			List<String> rowValues = new ArrayList<>();
			
			for(int j = 0; j < size; j++) {
				rowValues.add(" " + state.square(i, j).state().toString() + " ");
			}
			
			System.out.println(rowValues.stream()
					.collect(Collectors.joining("|")));
			if(i < size - 1) {
				System.out.println(divider);
			}
		}
		
		System.out.println();
	}
	
	public Square move(Square square) {
		if(square != null) {
			mechanic.move(state, square, playerState());
			
			if(state.finished() || state.won(playerState())) {
				stop();
				return null;
			}
		}
		
		strategy.update(square);
		Square botSquare = strategy.findMove();
		
		mechanic.move(state, state.square(botSquare.row(), botSquare.col()), botState());
		
		if(state.finished() || state.won(botState())) {
			stop();
			return botSquare;
		}
		
		return botSquare;
	}
	
	public boolean active() {
		return active;
	}
	
	public int size() {
		return size;
	}
	
	public GameState state() {
		return state;
	}
	
	public Strategy strategy() {
		return strategy;
	}
	
	public MoveMechanic mechanic() {
		return mechanic;
	}
	
	public SquareState playerState() {
		return player.state();
	}
	
	public SquareState botState() {
		return SquareState.opposite(player.state());
	}
}
