package com.ttt.game.command;

import com.ttt.game.Game;
import com.ttt.game.model.SquareState;
import com.ttt.game.move.LightsOutMove;
import com.ttt.game.move.StandardMove;
import com.ttt.game.strategy.MinimaxStrategy;

public class StartCommand extends Command {

	public StartCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		SquareState playerState = SquareState.X;
		int size = 3;
		
		if(args != null && args.length() > 0) {
			String[] parsedArgs = args.trim().split(" ");
			
			if(parsedArgs.length >= 1) {
				if(parsedArgs[0].toLowerCase().equals("x")) {
					playerState = SquareState.X;
				}
				else if(parsedArgs[0].toLowerCase().equals("o")) {
					playerState = SquareState.O;
				}
				else if(parsedArgs[0].toLowerCase().equals("lightsout")) {
					Game.INSTANCE.start(SquareState.X, 6, new MinimaxStrategy(), new LightsOutMove());
					Game.INSTANCE.print();
					System.out.print("Move (row, col): ");
					return;
				}
				else {
					System.out.println("\nInvalid player state " + parsedArgs[0]);
					System.out.print("\nCommand: ");
					return;
				}
			}
			
			if(parsedArgs.length >= 2) {
				size = Integer.parseInt(parsedArgs[1]);
			}
		}

		Game.INSTANCE.start(playerState, size, new MinimaxStrategy(), new StandardMove());
		Game.INSTANCE.print();
		System.out.print("Move (row, col): ");
	}

}
