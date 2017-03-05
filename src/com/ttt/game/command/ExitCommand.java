package com.ttt.game.command;

public class ExitCommand extends Command {

	public ExitCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		System.exit(0);
	}
}
