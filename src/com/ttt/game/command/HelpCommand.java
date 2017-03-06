package com.ttt.game.command;

public class HelpCommand extends Command {

	public HelpCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		System.out.print("\nTo start a game, type start followed by which side you want to play and the size of the board "
				+ "(ie 'start x 3' starts a game where you are X on a 3x3 board, 'start o 5' starts a game where you are O on a 5x5 board, etc).  Just typing 'start' will default to 'start x 3'.\n\n"
				+ "To make a move, type the row and column number (ie '1,1' is the top-left corner, '1,3' is the top-right corner on a 3x3 board, etc).\n\n"
				+ "To quit the current game, type 'quit', and at any point, you can exit the program by typing 'exit'.\n\n"
				+ "For a special Tic Tac Toe variant, use the start command, but type lightsout as the size (ie 'start x lightsout') :).  The rules are the same, except when you pick your move, you also fill in the adjacent squares (top, bottom, left, right).  If an adjacent square already has your mark, nothing happens, and if it has your opponent's mark, then it goes blank.\n\n"
				+ "Command: ");
	}
}
