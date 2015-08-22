/**
 *
 */
package de.kaltokri.windowsworkingtime;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

import de.kaltokri.windowsworkingtime.eventlog.EventLogDataset;
import de.kaltokri.windowsworkingtime.duration.DurationDataset;

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
		String resultFilePath;

		/*
		 * Execute external LogParser application
		 */
		LogParser lp = new LogParser();
		lp.execute();
		resultFilePath = lp.getResultFilepath();

		/*
		 * Now we read and analyze the resulting CSV file.
		 */
		LabeledCSVParser csvParser = new LabeledCSVParser(new CSVParser(
				new FileInputStream(resultFilePath)));

		EventLogDataset eld = new EventLogDataset();

		for (String[] cells; (cells = csvParser.getLine()) != null;) {
			eld.put(cells[0], cells[2]);
		}

		// Fix missing events
		eld.fixMissingEvents();

		// Print a report with the resulting EventLogDataset
		// TODO: Activate output of EventLogDataset by a properties file
		System.out.println(eld);

		// Create duration objects
		DurationDataset duda = new DurationDataset(eld);
		System.out.println(duda.toString());
	}

}
