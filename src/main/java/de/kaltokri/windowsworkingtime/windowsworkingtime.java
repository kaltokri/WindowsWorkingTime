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
public class windowsworkingtime {

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
		// EventLogOutput elo = new EventLogOutput();
		//System.out.println(elo.format(eld));

		// Create duration objects
		DurationDataset duda = new DurationDataset(eld);
		System.out.println(duda.toString());
	}

}
