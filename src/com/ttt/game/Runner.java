package com.ttt.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ttt.game.command.Command;

public class Runner {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Welcome to Tic Tac Toe!  Type 'help' to see a list of commands.\n\nCommand: ");
		
		while(true) {
		    String input = reader.readLine();
		    Command c = Command.parse(input);
		    
		    if(c == null) {
		    	System.out.print("Invalid command " + input + "\n\nCommand: ");
		    }
		    else {
		    	c.execute();
		    }
		}
	}
}
