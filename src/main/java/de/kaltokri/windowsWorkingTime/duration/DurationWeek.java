package de.kaltokri.windowsWorkingTime.duration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DurationWeek {
	private Integer weekOfYear;
	private List<DurationDay> durationDays;

	public DurationWeek(DurationEntry durEntry) {
		this.durationDays = new ArrayList<DurationDay>();
		DurationDay durDay = new DurationDay(durEntry);
		this.durationDays.add(durDay);

		Calendar cal = new GregorianCalendar();
		cal.setTime(durEntry.getStartDate());
		this.weekOfYear = cal.get(java.util.Calendar.WEEK_OF_YEAR);
	}

	public Integer getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(Integer weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public List<DurationDay> getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(List<DurationDay> durationDays) {
		this.durationDays = durationDays;
	}

	public void addDurationDay(DurationDay duraDay) {
		this.durationDays.add(duraDay);
	}

	public void addDurationDay(DurationEntry durEntry) {
		this.durationDays.add(new DurationDay(durEntry));
	}

	public Long totalDuration() {
		long totalDuration = 0;
		for (DurationDay durDay : this.durationDays) {
			for (DurationEntry durEn : durDay.getDurationEntriesOfDay()) {
				totalDuration = totalDuration + durEn.getDuration();
			}
		}
		return totalDuration;
	}

	public String totalDurationString() {
		Long totalMinutes = this.totalDuration();
		Long hours = totalMinutes / 60;
		Long minutesRest = totalMinutes % 60;
		return hours + "h " + minutesRest + "min";
	}
}
