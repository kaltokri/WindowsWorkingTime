package de.kaltokri.windowsworkingtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogParser {

  private String workingDirectory = "C:/Program Files (x86)/Log Parser 2.2";
  private String command = workingDirectory + "/LogParser.exe";
  private String csvFileName = "Events.csv";
  private String parameters = "\"SELECT TimeGenerated, SourceName, EventID INTO "
      + csvFileName + " FROM System WHERE EventID In (12;13)\"";

  /**
   * This method will use ProcessBuilder to run external application Log Parser
   * and print it's console output.
   *
   * @throws IOException
   *           Can occur if result file is not readable or doesn't exist
   */
  public void execute() throws IOException {
    System.out.println("******************************************");
    System.out.println("Run external application Log Parser");
    System.out.println("******************************************");
    System.out.println(command + " " + parameters);
    ProcessBuilder processBuilder = new ProcessBuilder(command, parameters);
    processBuilder.directory(new File(workingDirectory));
    java.lang.Process proc = processBuilder.start();
    java.io.InputStream is = proc.getInputStream();
    java.io.BufferedReader reader =
        new java.io.BufferedReader(new InputStreamReader(is));

    // And print each line
    String line = null;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
    is.close();
  }

  public String getResultFilepath() {
    return this.workingDirectory + "/" + this.csvFileName;
  }
}
