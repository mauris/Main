/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tasma.commands.CommandFactory;
import com.tasma.commands.CommandInterface;
import com.tasma.commands.UndoNotSupportedException;

/**
 * The main controller logic
 * Tip: Good, cheap, fast - choose any two. (:
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class Controller {
	private static final Logger logger = Log.getLogger( Controller.class.getName() );
	
	/**
	 * The user interface to call the output methods from
	 */
	protected TasmaUserInterface userInterface;
	
	/**
	 * The task collection to work with
	 */
	protected TaskCollection collection;
	
	/**
	 * The command factory
	 */
	protected CommandFactory commandFactory;
	
	/**
	 * The last action to undo
	 */
	protected Stack<CommandInterface> undoStack = new Stack<CommandInterface>();
	
	public Controller() {
		this(new TaskCollection());
	}
	
	public Controller(TaskCollection collection) {
		this.collection = collection;
	}
	
	/**
	 * Performs initialization of the controller.
	 * The user interface (i.e. TasmaUserInterface implementation) must have been set via the setUserInterface method prior to calling this method.
	 * @throws Exception Thrown when the user interface for the controller is not set before initializing.
	 */
	public void initialize() throws Exception {
		if (userInterface == null) {
			Exception exception = new Exception("The user interface for the controller has not been set.");
			logger.log(Level.FINER, exception.toString(), exception);
			throw exception;
		}
		assert userInterface != null;
		collection.loadFromFile();
		commandFactory = new CommandFactory(userInterface, collection);
	}
	
	/**
	 * Set the user interface to be used by the controller.
	 * @param ui The interface to be called by the controller.
	 */
	public void setUserInterface(TasmaUserInterface ui) {
		assert ui != null;
		userInterface = ui;
		logger.log(Level.FINE, "Using {0} as the user interface now.", ui.getClass().getName());
	}
	
	/**
	 * Execute operations based on the user's input
	 * @param input The input string of the user
	 */
	public void executeInput(String input) {
		if (input.trim().equals("undo")) {
			if (undoStack.size() > 0) {
				CommandInterface command = undoStack.pop();
				try {
					command.undo();
				} catch (UndoNotSupportedException ex) {
					
				} catch (Exception e) {
					displayException(e);
				}
			} else {
				// TODO: show message that there is no more to undo
			}
		} else {
			CommandInterface command = commandFactory.getCommand(input);
			try {
				command.execute();
				undoStack.push(command);
			} catch (Exception ex) {
				displayException(ex);
			}
		}
	}
	
	/**
	 * Displays an exception message to the user
	 * @param exception The exception to show
	 */
	protected void displayException(Exception exception) {
		assert exception != null;
		
		logger.log(Level.FINE, exception.toString(), exception);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_EXCEPTION, exception.getMessage()));
	}
}
