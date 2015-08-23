package de.kaltokri.windowsworkingtime.eventlog;

import java.text.ParseException;
import java.util.Date;

public class EventLogEntry {
  private Date eventDate;
  private EventLogEntryType eventType;

  /**
   * @param eventDate
   *          Date of the EventLogEntry.
   * @param eventTypeString
   *          Type of the EventLogEntry.
   * @throws ParseException
   *           eventTypeString will be parsed to get an Integer value.
   */
  public EventLogEntry(Date eventDate, String eventTypeString)
      throws ParseException {
    this.setEventDate(eventDate);
    this.setEventType(eventTypeString);
  }

  public EventLogEntry(Date eventDate, Integer eventType) {
    this.setEventDate(eventDate);
    this.setEventType(eventType);
  }

  public Date getEventDate() {
    return eventDate;
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
    return "EventLogEntry [eventDate=" + eventDate + ", eventType=" + eventType
        + "]";
  }

}
