package com.ttt.game.command;

import com.ttt.game.Game;

public class QuitCommand extends Command {

	public QuitCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		Game.INSTANCE.stop();
		System.out.print("\nCommand: ");
	}

}
