package de.kaltokri.windowsworkingtime.eventlog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EventLogDay {
	private Date eventDayDate;
	private List<EventLogEntry> eventsOfDay;

	/**
	 * @param eventDayDate
	 */

	public EventLogDay(Date eventDayDate) {
		this.eventDayDate = eventDayDate;
		this.eventsOfDay = new ArrayList<EventLogEntry>();
	}

	public EventLogDay(Date eventDayDate, EventLogEntry eventLogEntry) {
		this.eventDayDate = eventDayDate;
		this.eventsOfDay = new ArrayList<EventLogEntry>();
		this.eventsOfDay.add(eventLogEntry);
	}

	public Date getEventDayDate() {
		return eventDayDate;
	}

	public void setEventDayDate(Date eventDayDate) {
		this.eventDayDate = eventDayDate;
	}

	public List<EventLogEntry> getEventsOfDay() {
		return eventsOfDay;
	}

	public void addEvent(EventLogEntry eventToAdd) {
		this.eventsOfDay.add(eventToAdd);
	}

	public void fixMissingEvents() {
		// TODO System.out.println(this.getEventDayDate());
		if (this.eventsOfDay.get(0).getEventType().isShutdown()) {
			this.eventsOfDay.add(0,
					new EventLogEntry(this.getEventDayDate(), 12));
		}
		if (this.eventsOfDay.get(this.eventsOfDay.size() - 1).getEventType()
				.isStartup()) {
			// TODO
			// System.out.println("Missing last shutdown. I'll create one.");

			Calendar cal = new GregorianCalendar();
			cal.setTime(this.getEventDayDate());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			Date eventDateLastSecond = cal.getTime();
			this.eventsOfDay.add(new EventLogEntry(eventDateLastSecond,
					EventLogEntryType.SHUTDOWN));
		}
		// TODO System.out.println(this.eventsOfDay.toString());

		// Fix two startups behind each other. Delete the first one.
		for (int i = 0; i < eventsOfDay.size(); i = i + 2) {
			if (this.eventsOfDay.get(i + 1).getEventType().isStartup()) {
				this.eventsOfDay.remove(i);
			}
		}
	}
}
