package de.kaltokri.windowsWorkingTime.eventlog;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;

public class EventLogOutput {

	public String format(EventLogDataset allEventLogDays) {
		Reader reader = new InputStreamReader(getClass().getClassLoader()
				.getResourceAsStream("EventLog.tpl.txt"));
		VelocityContext context = new VelocityContext();
		context.put("allEventLogDays", allEventLogDays);
		context.put("displaytool", new DisplayTool());
		context.put("datetool", new DateTool());
		StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "", reader);
		return writer.toString();
	}
}
