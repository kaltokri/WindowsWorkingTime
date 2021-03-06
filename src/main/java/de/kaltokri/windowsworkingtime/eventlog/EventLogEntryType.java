package de.kaltokri.windowsworkingtime.eventlog;

public class EventLogEntryType {
  public static final Integer STARTUP = 12;
  public static final Integer SHUTDOWN = 13;

  private Integer eventLogType;

  public EventLogEntryType(Integer eventLogType) {
    this.setEventLogType(eventLogType);
  }

  public EventLogEntryType(String eventLogType) {
    this.setEventLogType(eventLogType);
  }

  @Override
  public String toString() {
    if (eventLogType.equals(STARTUP)) {
      return "STARTUP".toString();
    } else {
      return "SHUTDOWN".toString();
    }
  }

  public int getEventLogType() {
    return eventLogType;
  }

  public void setEventLogType(int eventLogType) {
    this.eventLogType = eventLogType;
  }

  public void setEventLogType(String eventLogType) throws NumberFormatException {
    this.eventLogType = Integer.parseInt(eventLogType);
  }

  public boolean isStartup() {
    return this.eventLogType.equals(STARTUP) ? true : false;
  }

  public boolean isShutdown() {
    return this.eventLogType.equals(SHUTDOWN) ? true : false;
  }
}
