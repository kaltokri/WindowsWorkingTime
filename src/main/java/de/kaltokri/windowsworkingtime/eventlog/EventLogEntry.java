package de.kaltokri.windowsworkingtime.eventlog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventLogEntry {
	private Date eventDate;
	private EventLogEntryType eventType;

	/**
	 * @param eventDate
	 * @param eventType
	 * @throws ParseException
	 */
	public EventLogEntry(String eventDateString, String eventTypeString)
			throws ParseException {
		this.setEventDate(eventDateString);
		this.setEventType(eventTypeString);
	}

	public EventLogEntry(String eventDateString, Integer eventType) throws ParseException {
		this.setEventDate(eventDateString);
		this.setEventType(eventType);
	}

	public EventLogEntry(Date eventDate, String eventTypeString)
			throws ParseException {
		this.setEventDate(eventDate);
		this.setEventType(eventTypeString);
	}

	public EventLogEntry(Date eventDate, Integer eventType)
			throws ParseException {
		this.setEventDate(eventDate);
		this.setEventType(eventType);
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDateString) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.ENGLISH);
		this.eventDate = format.parse(eventDateString);
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public EventLogEntryType getEventType() {
		return eventType;
	}

	public void setEventType(String eventTypeString) {
		this.eventType = new EventLogEntryType(eventTypeString);
	}

	public void setEventType(Integer eventTypeString) {
		this.eventType = new EventLogEntryType(eventTypeString);
	}

	@Override
	public String toString() {
		return "EventLogEntry [eventDate=" + eventDate + ", eventType="
				+ eventType + "]";
	}

}
