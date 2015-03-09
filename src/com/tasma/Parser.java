package com.tasma;

import java.util.ArrayList;

/**
 * @author Manika Agarwal
 *
 */

public class Parser {
	private Task parsedTask;
	
	private static final String EMPTY_STRING = "";
	private static String taskDetails; 
	
	public Task parse(String details) {
		parsedTask = new Task();
		taskDetails = details;
		
		getWhat();
		
		getWhen();
		
		getWhere();
		
		return parsedTask;
	}


	private void getWhat() {
		String[] keywords = {"on", "at", "in"};
		int keywordsSize = 3, index = 0; 
		
		if (!taskDetails.contains("on") || !taskDetails.contains("at")) {  //command does not contain when or where details
			taskDetails = EMPTY_STRING;
			//still in preliminary phases as task may not contain 'on' or 'at' but still have when and/or where details
		} else {
			int i;
			
			for (i = 0; i < 3; i++) {
				if (taskDetails.contains(keywords[i])) {
					index = taskDetails.indexOf(keywords[i]);		
				}
			}
		}
		
		parsedTask.setDetails(taskDetails);
		taskDetails = taskDetails.substring(index); 
	}
	
	private void getWhen() {
		if (taskDetails != EMPTY_STRING) {
			
		}
	}
	
	private void getWhere() {
		
	}	
}