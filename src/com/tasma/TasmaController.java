/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.ArrayList;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class TasmaController {
	
	public void initialize() {
		
	}
	
	public void execute(String input) {
		String[] inputParts = splitArguments(input);
		String command = inputParts[0];
		String argument = inputParts[1];
		
		CommandType commandType = normalizeCommand(command);
		switch (commandType) {
			case ADD:
				break;
			case SEARCH:
				break;
			case LIST:
				break;
			case MARK:
				break;
			case EDIT:
				String[] argumentParts = splitArguments(argument, 1);
				String taskId = argumentParts[0];
				argument = argumentParts[1];
				break;
			case ARCHIVE:
				break;
			default:
				break;
		}
	}
	
	protected static CommandType normalizeCommand(String command) {
		switch(command.trim().toLowerCase()) {
			case "add":
			case "a":
			case "insert":
			case "create":
			case "c":
			case "set":
				return CommandType.ADD;
			case "s":
			case "search":
			case "find":
			case "f":
			case "q":
			case "query":
				return CommandType.SEARCH;
			case "e":
			case "edit":
			case "update":
			case "change":
				return CommandType.EDIT;
			case "u":
			case "upcoming":
			case "up":
			case "l":
			case "list":
				return CommandType.LIST;
			case "m":
			case "mark":
			case "do":
			case "done":
				return CommandType.MARK;
			case "arc":
			case "archive":
				return CommandType.ARCHIVE;
			
		}
		return CommandType.INVALID;
	}
	
	private static String[] splitArguments(String input) {
		return splitArguments(input, 1);
	}
	
	private static String[] splitArguments(String input, int argumentCount) {
		ArrayList<String> arguments = new ArrayList<String>();
		for (int i = 0; i < argumentCount; ++i) {
			int intSpacePos = input.indexOf(" ");
			if (intSpacePos == -1) {
				arguments.add(input);
				break;
			} else {
				arguments.add(input.substring(0, intSpacePos));
				input = input.substring(intSpacePos + 1);
			}
		}
		return (String[])arguments.toArray();
	}
}
