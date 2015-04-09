/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.awt.Point;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import com.tasma.commands.AliasHandler;
import com.tasma.commands.InputSplitter;
import com.tasma.commands.CommandType;

/**
 * The yellow hint frame that shows up for the user as they type.
 * Used in TasmaGUI
 */
@SuppressWarnings("serial")
public class CommandHintFrame extends JFrame {

	/**
	 * The output text area
	 */
	private JTextArea labelOutput = new JTextArea();
	
	/**
	 * A mapping of a command to its hint message
	 */
	private HashMap<CommandType, String> hintMapping = new HashMap<CommandType, String>();
	{
		hintMapping.put(CommandType.ADD, HelpMessage.HINT_ADD);
		hintMapping.put(CommandType.SEARCH, HelpMessage.HINT_SEARCH);
		hintMapping.put(CommandType.LIST, HelpMessage.HINT_LIST);
		hintMapping.put(CommandType.EDIT, HelpMessage.HINT_EDIT);
		hintMapping.put(CommandType.MARK, HelpMessage.HINT_MARK);
		hintMapping.put(CommandType.UNMARK, HelpMessage.HINT_UNMARK);
		hintMapping.put(CommandType.DELETE, HelpMessage.HINT_DELETE);
		hintMapping.put(CommandType.SET, HelpMessage.HINT_SET);
		hintMapping.put(CommandType.ALIAS, HelpMessage.HINT_ALIAS);
		hintMapping.put(CommandType.HELP, HelpMessage.HINT_HELP);
		hintMapping.put(CommandType.TUTORIAL, HelpMessage.HINT_TUTORIAL);
		hintMapping.put(CommandType.EXIT, HelpMessage.HINT_EXIT);
		hintMapping.put(CommandType.UNDO, HelpMessage.HINT_UNDO);
		hintMapping.put(CommandType.REDO, HelpMessage.HINT_REDO);
	}
	
	public CommandHintFrame() {
		decorate();
	}
	
	/**
	 * Decorate the frame
	 */
	protected void decorate() {
		this.setTitle("");
		this.setAlwaysOnTop(true);
		this.setAutoRequestFocus(false);
		this.setUndecorated(true);
		this.setOpacity(0.8f);
		this.setSize(400, 22);
        setFocusable(false);
        setFocusableWindowState(false);
		
		labelOutput.setEditable(false);
		labelOutput.setOpaque(true);
		labelOutput.setBackground(Palette.HINT_FRAME_BACKGROUND);
		labelOutput.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(labelOutput);
	}
	
	/**
	 * Show the window if the input has a command
	 * @param input
	 * @param component
	 * @throws InvalidInputException
	 */
	public void checkHasHint(String input, JComponent component) throws InvalidInputException {
		InputSplitter splitter = new InputSplitter(input);
		String command = splitter.next();
		CommandType type = AliasHandler.normalize(command);
		String hint = hintMapping.get(type);
		if (hint != null) {
			labelOutput.setText(hint);
			this.setSize(labelOutput.getPreferredSize());
			Point location = component.getLocationOnScreen();
			location.move(location.x + 20, location.y + component.getHeight());
			this.setLocation(location);
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * Close the window
	 */
	public void close() {
		this.setVisible(false);
	}
}
