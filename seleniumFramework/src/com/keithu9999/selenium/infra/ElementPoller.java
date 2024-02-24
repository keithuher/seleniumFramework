package com.keithu9999.selenium.infra;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A generic poller implementation used to verify a condition that takes time to manifest.
 * 
 * @author keithu9999
 */
public abstract class ElementPoller {
	
	private static final Logger LOG = LoggerFactory.getLogger(ElementPoller.class);
	
	// The initial time that the poller starts executing
	private final long startTime = System.currentTimeMillis();

	/**
	 * @return A description of what the poller is waiting to happen
	 */
	public abstract String conditionDescription();

	/**
	 * @return true when the condition is met, false if it has not yet been met
	 */
	public abstract boolean conditionSatisfied();
	
	/**
	 * Defines the parameters of the poller.
	 * @param interval The time between checks if the condition has been satisfied.
	 * @param timeout The overall time that the poller runs.
	 * @return true if the condition was satisfied, false if not
	 */
	public boolean poll(Duration interval, Duration timeout) {
		
		LOG.info("Starting poller to " + conditionDescription() + " start time " + getStartTime());
		pauseFor(interval.getSeconds());
		boolean result = conditionSatisfied();
		if(result)
			return true;
		
		final long compareTimeMillis = timeout.toMillis();
		long elapsedTimeMillis = 0;
		long lastProgressUpdateMillis = 0;
		
		int counter = 1;
		
		while (elapsedTimeMillis <= compareTimeMillis && (result = conditionSatisfied()) == false) {
			
			++counter;
			if(elapsedTimeMillis - lastProgressUpdateMillis > interval.toMillis())
				lastProgressUpdateMillis = elapsedTimeMillis;
			
			pauseFor(interval.getSeconds());
			elapsedTimeMillis = getElapsedTimeMillis();
			
			LOG.info("Attempting to " + conditionDescription() + " count " + counter);
		}
		
		return result;
	}
	
	public long getElapsedTimeMillis() {
		return System.currentTimeMillis() - getStartTime();
	}
	
	public float getElapsedTime() {
		return getElapsedTimeMillis() / 1000f;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	private void pauseFor(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
