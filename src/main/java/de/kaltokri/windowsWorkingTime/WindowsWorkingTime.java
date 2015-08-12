/**
 *
 */
package de.kaltokri.windowsWorkingTime;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

import de.kaltokri.windowsWorkingTime.eventlog.EventLogDataset;
import de.kaltokri.windowsWorkingTime.eventlog.EventLogOutput;

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
		/*
		 * Execute external LogParser application
		 */
		LogParser lp = new LogParser();
		lp.execute();

		/*
		 * Now we read and analyze the result CSV file.
		 */
		LabeledCSVParser csvParser = new LabeledCSVParser(new CSVParser(
				new FileInputStream(
						"C:/Program Files (x86)/Log Parser 2.2/Events.csv")));

		EventLogDataset eld = new EventLogDataset();
		for (String[] cells; (cells = csvParser.getLine()) != null;) {
			eld.put(cells[0], cells[2]);
		}

		// Fix missing events
		eld.fixMissingEvents();

		// Create a report with the result
		EventLogOutput elo = new EventLogOutput();
		System.out.println(elo.format(eld));
	}

}
