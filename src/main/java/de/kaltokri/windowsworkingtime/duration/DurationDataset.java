package de.kaltokri.windowsworkingtime.duration;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;

import de.kaltokri.windowsworkingtime.eventlog.EventLogDataset;
import de.kaltokri.windowsworkingtime.eventlog.EventLogEntry;

public class DurationDataset {
  private Hashtable<Integer, DurationWeek> allDurationWeeks = new Hashtable<Integer, DurationWeek>();

  public DurationDataset(EventLogDataset eldataset) {

    // Get all keys of the EventLogDataset (= Dates)
    List<Date> tmp = Collections.list(eldataset.keys());

    // sort them
    Collections.sort(tmp);

    // Get actual calendar week to ignore it later
    Calendar cal = new GregorianCalendar();
    cal.setTime(new Date());
    Integer actualWeek = cal.get(java.util.Calendar.WEEK_OF_YEAR);

    // Loop through each key of the EventLogDataset
    Iterator<Date> it = tmp.iterator();
    while (it.hasNext()) {

      // Create calendar week. This is used as key in the hash map
      // allDurationWeeks
      Date key = it.next();
      cal.setTime(key);
      Integer calWeek = cal.get(java.util.Calendar.WEEK_OF_YEAR);

      // Ignore this week because the data set is incompletely and show
      // only the last 4 weeks.
      if (calWeek < actualWeek && (actualWeek - calWeek < 6)) {

        // EventLogEntry List from EventLogDay
        List<EventLogEntry> eventsOfDay = eldataset.get(key).getEventsOfDay();

        // Loop through each second entry (starts only) and create
        // DurationEntry together with the matching shutdown.
        for (int i = 0; i < eventsOfDay.size(); i = i + 2) {
          DurationEntry durEn = new DurationEntry(eventsOfDay.get(i)
              .getEventDate(), eventsOfDay.get(i + 1).getEventDate());
          // System.out.println(durEn.getDuration());

          // Add the DurationEntry to allDurationWeeks -> DurationWeek
          // ->
          // DurationDay
          if (allDurationWeeks.containsKey(calWeek)) {
            allDurationWeeks.get(calWeek).addDurationDay(durEn);
          } else {
            allDurationWeeks.put(calWeek, new DurationWeek(durEn));
          }
        }
      }
    }
  }

  public boolean containsKey(Integer key) {
    return this.allDurationWeeks.containsKey(key);
  }

  public DurationWeek get(Integer key) {
    return this.allDurationWeeks.get(key);
  }

  public List<Integer> keys() {
    List<Integer> list = Collections.list(allDurationWeeks.keys());
    Collections.sort(list);
    // Collections.reverse(list);
    return list;
  }

  public String toString() {
    Reader reader = new InputStreamReader(getClass().getClassLoader()
        .getResourceAsStream("DurationDataset.tpl.txt"));
    VelocityContext context = new VelocityContext();
    context.put("durationDataset", this);
    context.put("displaytool", new DisplayTool());
    context.put("datetool", new DateTool());
    StringWriter writer = new StringWriter();
    Velocity.evaluate(context, writer, "", reader);
    return writer.toString();
  }
}
