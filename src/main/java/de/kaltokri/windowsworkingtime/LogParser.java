package de.kaltokri.windowsworkingtime;

import de.kaltokri.windowsworkingtime.eventlog.EventLogDay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class LogParser {
  private static final Logger logger = LoggerFactory
      .getLogger(EventLogDay.class);

  private String workingDirectory = "C:/Program Files (x86)/Log Parser 2.2";
  private String command = workingDirectory + "/LogParser.exe";
  private String csvFileName = "Events.csv";
  private String parameters =
      "\"SELECT TimeGenerated, SourceName, EventID INTO " + csvFileName
          + " FROM System WHERE EventID In (12;13)\"";

  /**
   * This method will use ProcessBuilder to run external application Log Parser
   * and print it's console output.
   *
   * @throws java.io.IOException
   *           Can occur if result file is not readable or doesn't exist
   */
  public void execute() throws java.io.IOException {

    logger.info("Run external application Log Parser.");
    logger.info(command + " " + parameters);

    ProcessBuilder processBuilder = new ProcessBuilder(command, parameters);
    processBuilder.directory(new File(workingDirectory));
    java.lang.Process proc = processBuilder.start();

    InputStream is = null;
    BufferedReader reader = null;

    is = proc.getInputStream();
    try {
      reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      // And print each line
      String line = null;
      while ((line = reader.readLine()) != null) {
        logger.info(line);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
    is.close();
  }

  public String getResultFilepath() {
    return this.workingDirectory + "/" + this.csvFileName;
  }
}
