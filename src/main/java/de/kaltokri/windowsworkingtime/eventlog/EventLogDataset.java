package de.kaltokri.windowsworkingtime.eventlog;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;

public class EventLogDataset {
	private Hashtable<Date, EventLogDay> allEventLogDays = new Hashtable<Date, EventLogDay>();

	public void put(String eventDateString, String eventTypeString)
			throws ParseException {
		SimpleDateFormat eventDateStringFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date eventDate = eventDateStringFormat.parse(eventDateString);

		// Generate a Date object where time is set to 0:00:00 to use is as a
		// key in hash maps.
		Calendar cal = new GregorianCalendar();
		cal.setTime(eventDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date eventDateWithoutTime = cal.getTime();

		// Create a new event with the given data
		EventLogEntry theEvent = new EventLogEntry(eventDate, eventTypeString);

		// Add the EventLogEntry to an already existing and matching EventLogDay
		// or create a new EventLogDay and and the EventLogEntry
		if (allEventLogDays.containsKey(eventDateWithoutTime)) {
			allEventLogDays.get(eventDateWithoutTime).addEvent(theEvent);
		} else {
			allEventLogDays.put(eventDateWithoutTime, new EventLogDay(
					eventDateWithoutTime, theEvent));
		}
	}

	public void fixMissingEvents() {
		Enumeration<Date> en = allEventLogDays.keys();

		while (en.hasMoreElements()) {
			allEventLogDays.get(en.nextElement()).fixMissingEvents();
		}
	}

	public Enumeration<Date> keys() {
		return allEventLogDays.keys();
	}

	public EventLogDay get(Date key) {
		return allEventLogDays.get(key);
	}

	public String toString () {
		Reader reader = new InputStreamReader(getClass().getClassLoader()
				.getResourceAsStream("EventLogDataset.tpl.txt"));
		VelocityContext context = new VelocityContext();
		context.put("allEventLogDays", allEventLogDays);
		context.put("displaytool", new DisplayTool());
		context.put("datetool", new DateTool());
		StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "", reader);
		return writer.toString();
	}
}
