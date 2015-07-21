package de.kaltokri.windowsWorkingTime;

import java.text.ParseException;
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

	public EventLogDay(String eventDayDate, EventLogEntry eventLogEntry) {
		this.eventDayDate = eventDayDate;
		this.eventsOfDay = new ArrayList<EventLogEntry>();
		this.eventsOfDay.add(eventLogEntry);
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

	public void fixMissingEvents() {
		System.out.println( this.getEventDayDate() );
		if ( this.eventsOfDay.get(0).getEventType().isShutdown() ) {
			System.out.println("Missing first Startup. I'll create one.");
			try {
				this.eventsOfDay.add(0, new EventLogEntry(this.getEventDayDate() + " 00:00:00", "12"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println( this.eventsOfDay.toString() );
	}
}
