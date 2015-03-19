package com.tasma.commands;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public abstract class TaskRestorableCommand extends AbstractCommand {

	protected Task task;
	protected Task originalTask;
	
	public TaskRestorableCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId) {
		super(userInterface, collection);
		try {
			task = collection.get(taskId);
			originalTask = task.clone();
		} catch (CloneNotSupportedException e) {
		}
	}

	@Override
	public void undo() throws Exception {
		collection.update(originalTask);
	}

}
