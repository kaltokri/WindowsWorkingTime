package de.kaltokri.windowsworkingtime.duration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DurationDay {
  private Date durationDayDate;
  private List<DurationEntry> durationEntriesOfDay;

  /**
   * @param durationEntry
   *          Needs a DurationEntry object to create a DurationDay.
   */
  public DurationDay(DurationEntry durationEntry) {
    this.durationEntriesOfDay = new ArrayList<DurationEntry>();
    this.durationEntriesOfDay.add(durationEntry);

    Calendar cal = new GregorianCalendar();
    cal.setTime(durationEntry.getStartDate());
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    this.durationDayDate = cal.getTime();
  }

  public Date getDurationDayDate() {
    return new Date(durationDayDate.getTime());
  }

  public List<DurationEntry> getDurationEntriesOfDay() {
    return durationEntriesOfDay;
  }

  public void addDurationEntry(DurationEntry entry) {
    this.durationEntriesOfDay.add(entry);
  }
}
