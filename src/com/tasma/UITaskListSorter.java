/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Provides support for sorting the task list state into various headings for UI presentation 
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class UITaskListSorter {

	/**
	 * Sort the list of tasks into its headings
	 * @param tasks The list of tasks to be sorted
	 * @return Returns a list that is ready to be presented
	 */
	public static List<Object> sort(List<Task> tasks) {
		LinkedList<Object> listFloating = new LinkedList<Object>();
		LinkedList<Object> listOverdue = new LinkedList<Object>();
		LinkedList<Object> listToday = new LinkedList<Object>();
		LinkedList<Object> listTomorrow = new LinkedList<Object>();
		LinkedList<Object> listRemaining = new LinkedList<Object>();

		int taskIndex = 0;
		for (Task task: tasks) {
			Map.Entry<Integer, Task> entry = new AbstractMap.SimpleEntry<Integer, Task>(taskIndex, task);
			switch (task.getState()) {
				case OVERDUE:
					listOverdue.add(entry);
					break;
				case TODAY:
					listToday.add(entry);
					break;
				case TOMORROW:
					listTomorrow.add(entry);
					break;
				case UPCOMING:
					listRemaining.add(entry);
					break;
				default:
					listFloating.add(entry);
					break;
			}
			++taskIndex;
		}

		LinkedList<Object> finalList = new LinkedList<Object>();
		if (listFloating.size() > 0) {
			listFloating.add(0, String.format(UIMessage.SECTION_HEADER_FLOATING, listFloating.size()));
			finalList.addAll(listFloating);
		}
		if (listOverdue.size() > 0) {
			listOverdue.add(0, String.format(UIMessage.SECTION_HEADER_OVERDUE, listOverdue.size()));
			finalList.addAll(listOverdue);
		}
		if (listToday.size() > 0) {
			listToday.add(0, String.format(UIMessage.SECTION_HEADER_TODAY, listToday.size()));
			finalList.addAll(listToday);
		}

		if (listTomorrow.size() > 0) {
			listTomorrow.add(0, String.format(UIMessage.SECTION_HEADER_TOMORROW, listTomorrow.size()));
			finalList.addAll(listTomorrow);
		}

		if (listRemaining.size() > 0) {
			listRemaining.add(0, String.format(UIMessage.SECTION_HEADER_REMAINING, listRemaining.size()));
			finalList.addAll(listRemaining);
		}
		
		// since the list is empty, we should show some message to indicate
		if (finalList.size() == 0) {
			finalList.add(UIMessage.TASK_LIST_EMPTY);
		}
		return finalList;
	}
}