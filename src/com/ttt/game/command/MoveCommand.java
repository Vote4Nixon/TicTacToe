package com.ttt.game.command;

import com.ttt.game.Game;
import com.ttt.game.model.Square;
import com.ttt.game.model.SquareState;

public class MoveCommand extends Command {

	public MoveCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		if(!Game.INSTANCE.active()) {
			System.out.print("You must first start a game\n\nCommand: ");
			return;
		}
		
		if(args == null || args.length() == 0) {
			System.out.println("\nInvalid move " + args);
			Game.INSTANCE.print();
			System.out.print("Move (row, col): ");
			return;
		}
		
		String[] rowCol = args.split(",");
		if(rowCol.length != 2) {
			System.out.println("\nInvalid move " + args);
			Game.INSTANCE.print();
			System.out.print("Move (row, col): ");
			return;
		}
		
		int row = Integer.parseInt(rowCol[0]);
		int col = Integer.parseInt(rowCol[1]);
		Square square = Game.INSTANCE.state().square(row - 1, col - 1);
		if(square == null || square.state() != SquareState.BLANK) {
			System.out.println("\nInvalid move " + args);
			Game.INSTANCE.print();
			System.out.print("Move (row, col): ");
			return;
		}
		
		Square botSquare = Game.INSTANCE.move(square);
		
		System.out.println("\nPlayer move (" + (square.row() + 1) + ", " + (square.col() + 1) + ")."
				+ (botSquare == null ? "" : "  Bot move (" + (botSquare.row() + 1) + ", " + (botSquare.col() + 1) + ")."));
		Game.INSTANCE.print();
		
		if(Game.INSTANCE.state().won(Game.INSTANCE.playerState())) {
			System.out.print("Game over!  You win!\n\nCommand: ");
		}
		else if(Game.INSTANCE.state().won(Game.INSTANCE.botState())) {
			System.out.print("Game over!  You lose!\n\nCommand: ");
		}
		else if(Game.INSTANCE.state().finished()) {
			System.out.print("Game over!  Draw!\n\nCommand: ");
		}
		else {
			System.out.print("Move (row, col): ");
		}
	}

}
