package de.kaltokri.windowsWorkingTime.eventlog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

	public void setEventsOfDay(List<EventLogEntry> eventsOfDay) {
		this.eventsOfDay = eventsOfDay;
	}

	public void addEvent(EventLogEntry eventToAdd) {
		this.eventsOfDay.add(eventToAdd);
	}

	public void fixMissingEvents() {
		// TODO System.out.println(this.getEventDayDate());
		if (this.eventsOfDay.get(0).getEventType().isShutdown()) {
			// TODO
			// System.out.println("Missing first Startup. I'll create one.");
			try {
				this.eventsOfDay.add(0,
						new EventLogEntry(this.getEventDayDate(), "12"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (this.eventsOfDay.get(this.eventsOfDay.size() - 1).getEventType()
				.isStartup()) {
			// TODO
			// System.out.println("Missing last shutdown. I'll create one.");
			try {
				this.eventsOfDay.add(new EventLogEntry(this.getEventDayDate(),
						EventLogEntryType.SHUTDOWN));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// TODO System.out.println(this.eventsOfDay.toString());
	}
}
