package org.netty.rs2.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a single server task.
 * 
 * @author Michael P
 *
 */
public abstract class Task {
	
	/**
	 * Constructs a new task.
	 * 
	 * @param delay
	 *            The delay time between cycles.
	 * @param timeUnit
	 *            The <code>TimeUnit</code> of the task.
	 */
	public Task(int delay, TimeUnit timeUnit) {
		this.delay = delay;
		this.timeUnit = timeUnit;
	}
	
	/**
	 * The delay time between cycles.
	 */
	private final int delay;

	/**
	 * The task running flag.
	 */
	private boolean running;
	
	/**
	 * The <code>TimeUnit</code> of the task.
	 */
	private final TimeUnit timeUnit;
	
	/**
	 * The task paused flag.
	 */
	private boolean paused;
	
	/**
	 * The service scheduler of the task.
	 */
	private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * Executes the server task.
	 */
	protected abstract void execute();
	
	/**
	 * A method skeleton for handling anything needed once the task is killed.
	 */
	protected void onKill() {
		
	}
	
	/**
	 * Pauses the task.
	 */
	public void pause() {
		paused = true;
	}
	
	/**
	 * Gives life to the task.
	 */
	public void giveLife() {
		paused = false;
		if (!running || service.isShutdown())
			service.scheduleAtFixedRate(new TaskManager(), 0, delay, timeUnit);
		running = true;
	}
	
	/**
	 * Kills the task.
	 */
	public void kill() {
		running = false;
		service.shutdown();
		onKill();
	}
	
	/**
	 * Checks to see if the task is running.
	 * @return If the task is running <code>true</code>,
	 * if the task isn't running <code>false</code>.
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Gets the <code>TimeUnit</code> of the task.
	 * @return The task's <code>TimeUnit</code>.
	 */
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	
	/**
	 * Is the task paused?
	 * @return If the task is paused <code>true</code>,
	 * if the task isn't paused <code>false</code>.
	 */
	public boolean isPaused() {
		return paused;
	}
	
}
