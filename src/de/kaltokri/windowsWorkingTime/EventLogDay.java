package de.kaltokri.windowsWorkingTime;

import java.util.ArrayList;
import java.util.List;


public class EventLogDay {
	private String eventDayDate;
	private List<EventLogEntry> eventsOfDay;

	/**
	 * @param eventDayDate
	 */
	public EventLogDay(String eventDayDate) {
		this.eventDayDate = eventDayDate;
		this.eventsOfDay = new ArrayList<EventLogEntry>();
	}

	public String getEventDayDate() {
		return eventDayDate;
	}

	public void setEventDayDate(String eventDayDate) {
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
}
