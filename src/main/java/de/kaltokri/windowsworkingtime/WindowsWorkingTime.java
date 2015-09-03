package de.kaltokri.windowsworkingtime;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

import de.kaltokri.windowsworkingtime.duration.DurationDataset;
import de.kaltokri.windowsworkingtime.eventlog.EventLogDataset;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

/**
 * WindowsWorkingTime creates a report of all Windows startup and shutdown
 * registry events. It calculates the uptime for each day and print it as a
 * summary to console.
 *
 * @author kaltokri
 *
 */
public class WindowsWorkingTime {

  /**
   * This is the main class which runs the Log Parser, read the result file,
   * create EventLogDatasets, DurationDatasets and the output to console.
   *
   * @param args
   *          No command line arguments are supported
   * @throws IOException
   *           Can occur if result file of Log Parser is not readable or doesn't
   *           exist
   * @throws ParseException
   *           Occurs if Date string in result file of Log Parser has wrong
   *           format
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
    LabeledCSVParser csvParser =
        new LabeledCSVParser(new CSVParser(new FileInputStream(resultFilePath)));

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
    DurationDataset duda = new DurationDataset(eld, 6);
    System.out.println(duda.toString());
  }

}
