package com.ttt.game.command;

public abstract class Command {
	protected String args;
	
	public Command(String args) {
		this.args = args;
	}
	
	public abstract void execute();
	
	public static Command parse(String input) {
		if(input == null || input.length() == 0) {
			return null;
		}
		
		if(input.startsWith("start")) {
			return new StartCommand(input.replaceFirst("start", "").trim());
		}
		else if(input.startsWith("quit")) {
			return new QuitCommand(input.replaceFirst("quit", "").trim());
		}
		else if(input.startsWith("help")) {
			return new HelpCommand(input.replaceFirst("help", "").trim());
		}
		else if(input.startsWith("exit")) {
			return new ExitCommand(input.replaceFirst("exit", "").trim());
		}
		else {
			return new MoveCommand(input.trim());
		}
	}
}
