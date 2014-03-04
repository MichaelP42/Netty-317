package org.netty.rs2.task;

import java.util.LinkedList;
import java.util.List;


/**
 * A thread which manages server <code>Task</code>s.
 * 
 * @author Michael P
 *
 */
public final class TaskManager implements Runnable {
	
	/**
	 * A listing of submitted task.
	 */
	private static final List<Task> tasks = new LinkedList<Task>();
	
	/**
	 * Submits a new <code>Task</code>.
	 * 
	 * @param task
	 *            The task to submit.
	 */
	public static void submit(Task task) {
		tasks.add(task);
		task.giveLife();
	}
	
	@Override
	public void run() {
		for (Task task : tasks) {
			if (task.isPaused())
				continue;
			if (!task.isRunning())
				tasks.remove(task);
			task.execute();
		}
	}

}
