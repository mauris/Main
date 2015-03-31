/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.List;

import com.tasma.HelpMessage;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class HelpCommand extends AbstractCommand {

	protected CommandType commandType = CommandType.HELP;
	
	public HelpCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String command) {
		super(userInterface, collection);
		if (!command.equals("")) {
			this.commandType = AliasHandler.normalize(command);			
		}
	}

	@Override
	public void execute() throws Exception {
		String message = "";
		switch (commandType) {
			case ADD:
				message = HelpMessage.HELP_ADD;
				break;
			case SEARCH:
				message = HelpMessage.HELP_SEARCH;
				break;
			case LIST:
				message = HelpMessage.HELP_LIST;
				break;
			case MARK:
				message = HelpMessage.HELP_MARK;
				break;
			case EDIT:
				message = HelpMessage.HELP_EDIT;
				break;
			case DELETE:
				message = HelpMessage.HELP_DELETE;
				break;
			case SET:
				message = HelpMessage.HELP_SET;
				break;
			case HELP:
				message = HelpMessage.HELP_HELP;
				break;
			case TUTORIAL:
				message = HelpMessage.HELP_TUTORIAL;
				break;
			case EXIT:
				message = HelpMessage.HELP_EXIT;
				break;
			default:
				// probably an invalid command
				break;
		}
		String displayMessage = String.format(
				HelpMessage.HELP_TEMPLATE,
				commandType.toString(),
				message,
				listImplode(AliasHandler.aliases(commandType))
			);
		userInterface.displayMessage(displayMessage);
	}
	
	protected static String listImplode(List<String> list) {
		StringBuilder builder = new StringBuilder();
		
		if (list.isEmpty()) {
			builder.append("none");
		} else {
			builder.append(list.remove(0));
			for (String s: list) {
				builder.append(", ");
				builder.append(s);
			}
		}
		return builder.toString();
	}

}
