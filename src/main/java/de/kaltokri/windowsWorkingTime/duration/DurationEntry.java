package de.kaltokri.windowsWorkingTime.duration;

import java.time.LocalDate;
import java.time.Period;

public class DurationEntry {
	private LocalDate  startDate;
	private LocalDate  endDate;
	private Period duration;

	/**
	 * @param startDate
	 * @param endDate
	 */
	public DurationEntry(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = Period.between(startDate, endDate);
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Period getDuration() {
		return duration;
	}

	public void setDuration(Period duration) {
		if ( startDate != null && endDate != null ) {
			this.duration = Period.between(startDate, endDate);
		} else {
			// Throw exception
		}
	}


}
