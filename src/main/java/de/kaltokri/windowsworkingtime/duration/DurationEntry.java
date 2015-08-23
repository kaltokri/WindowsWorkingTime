package de.kaltokri.windowsworkingtime.duration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DurationEntry {
  private Date startDate;
  private Date endDate;
  private long durationInMin;

  /**
   * Constructor to create a DurationEntry with startup and shutdown Date
   * objects.
   *
   * @param startDate
   *          Date of Windows startup
   * @param endDate
   *          Date of Windows shutdown
   */
  public DurationEntry(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.durationInMin = TimeUnit.MILLISECONDS.toMinutes(endDate.getTime()
        - startDate.getTime());
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Long getDuration() {
    return durationInMin;
  }

  /**
   * Returns the duration as a formated string. For example 77 minutes will be
   * returned as "1h 17min".
   *
   * @return Duration in String format like "Xh XXmin"
   */
  public String getDurationString() {
    Long hours = durationInMin / 60;
    Long minutesRest = durationInMin % 60;
    return hours + "h " + minutesRest + "min";
  }
}
