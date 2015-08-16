package de.kaltokri.windowsWorkingTime.duration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DurationEntry {
	private Date startDate;
	private Date endDate;
	private long durationInMin;

	/**
	 * @param startDate
	 * @param endDate
	 */
	public DurationEntry(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationInMin = TimeUnit.MILLISECONDS.toMinutes(endDate.getTime() -startDate
				.getTime());
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Long getDuration() {
		return durationInMin;
	}
	public String getDurationString() {
		Long hours = durationInMin / 60;
		Long minutesRest = durationInMin % 60;
		return hours + "h " + minutesRest + "min";
	}
}
