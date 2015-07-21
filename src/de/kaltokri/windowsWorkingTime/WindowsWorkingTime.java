/**
 *
 */
package de.kaltokri.windowsWorkingTime;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Hashtable;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

/**
 * @author kaltokri
 *
 */
public class WindowsWorkingTime {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		String workingDirectory = "C:/Program Files (x86)/Log Parser 2.2";
		String command = workingDirectory + "/LogParser.exe";
		String cvsFileName = "Events.csv";
		String parameters = "\"SELECT TimeGenerated, SourceName, EventID INTO "
				+ cvsFileName + "2 FROM System WHERE EventID In (12;13)\"";

		java.lang.ProcessBuilder processBuilder = new java.lang.ProcessBuilder(
				command, parameters);
		processBuilder.directory(new java.io.File(workingDirectory));
		java.lang.Process p = processBuilder.start();
		java.io.InputStream is = p.getInputStream();
		java.io.BufferedReader reader = new java.io.BufferedReader(
				new InputStreamReader(is));
		// And print each line
		String s = null;
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
		is.close();

		LabeledCSVParser csvParser = new LabeledCSVParser(new CSVParser(
				new FileInputStream(
						"C:/Program Files (x86)/Log Parser 2.2/Events.csv")));

		Hashtable<String, EventLogDay> allEventLogDays = new Hashtable<String, EventLogDay>();
		for (String[] t; (t = csvParser.getLine()) != null;) {

			// Create a sub string with only the date like 2015-07-18
			String shortDateString = t[0].substring(0, 10);

			// Create an event with date and type
			EventLogEntry theEvent = new EventLogEntry(t[0], t[2]);

			if (allEventLogDays.containsKey(shortDateString)) {
				allEventLogDays.get(shortDateString).addEvent(theEvent);
			} else {
				allEventLogDays.put(shortDateString, new EventLogDay(
						shortDateString, theEvent));
			}

			System.out.println(theEvent.getEventDate() + " "
					+ theEvent.getEventType());

		}

		Enumeration<String> en = allEventLogDays.keys();
		while (en.hasMoreElements()) {
			allEventLogDays.get(en.nextElement()).fixMissingEvents();
		}
	}

}
