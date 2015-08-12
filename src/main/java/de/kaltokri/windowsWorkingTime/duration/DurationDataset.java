package de.kaltokri.windowsWorkingTime.duration;

import java.util.Hashtable;

public class DurationDataset {
	private Hashtable<String, DurationWeek> allDurationWeeks = new Hashtable<String, DurationWeek>();

	public boolean containsKey(String key) {
		return this.allDurationWeeks.containsKey(key);
	}

	public void put(String key, DurationWeek value) {
		this.allDurationWeeks.put(key, value);
	}
}
