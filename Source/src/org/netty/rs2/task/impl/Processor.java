package org.netty.rs2.task.impl;

import java.util.concurrent.TimeUnit;

import org.netty.rs2.task.Task;

/**
 * The main processor of the game world.
 * 
 * @author Michael P
 *
 */
public class Processor extends Task {

	/**
	 * Constructs a new processor.
	 */
	public Processor() {
		super(600, TimeUnit.MILLISECONDS);
	}

	@Override
	protected void execute() {
		// TODO World processing.
	}

}
