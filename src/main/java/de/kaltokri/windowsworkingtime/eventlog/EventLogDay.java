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
   * Constructor to create a new EventLogDay object with a given Date. It will
   * create an empty List of EventLogEntry's
   *
   * @param eventDayDate
   *          Date object which is used to identify the EventLogDay.
   */
  public EventLogDay(Date eventDayDate) {
    this.eventDayDate = eventDayDate;
    this.eventsOfDay = new ArrayList<EventLogEntry>();
  }

  /**
   * Constructor to create a EventLogDay with a special Date and a EventLogEntry
   * object.
   *
   * @param eventDayDate
   *          Date object where time is set to 00:00:00
   * @param eventLogEntry
   *          EventLogEntry which should be added to the EventLogDay
   */
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

  /**
   * This function fixes the following problems in the EventLogDay object: A day
   * starts with a shutdown, a day ends with a startup and two startups behind
   * each other.
   */
  public void fixMissingEvents() {
    // TODO System.out.println(this.getEventDayDate());
    if (this.eventsOfDay.get(0).getEventType().isShutdown()) {
      this.eventsOfDay.add(0, new EventLogEntry(this.getEventDayDate(), 12));
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
