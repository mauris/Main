package com.tasma.commands;

import com.tasma.Parser;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class EditCommand extends TaskRestorableCommand {

	protected String details;
	
	public EditCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId, String details) {
		super(userInterface, collection, taskId);
	}

	@Override
	public void execute() throws Exception {
		if (details.equals("")) {
			userInterface.editCmdDisplay(String.format("edit %s %s", task.getTaskId(), task.toString()));
		} else {
			Parser parser = new Parser();
			Task updatedTask = parser.parse(details);
			if (updatedTask.getDetails() != null) {
				task.setDetails(updatedTask.getDetails());
			}

			if (updatedTask.getLocation() != null) {
				task.setLocation(updatedTask.getLocation());
			}

			if (updatedTask.getStartDateTime() != null) {
				task.setStartDateTime(updatedTask.getStartDateTime());
			}

			if (updatedTask.getEndDateTime() != null) {
				task.setEndDateTime(updatedTask.getEndDateTime());
			}
			userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_SUCCESS, task.getTaskId()));
		}
	}

}