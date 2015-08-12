package de.kaltokri.windowsWorkingTime;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogParser {

	private String workingDirectory = "C:/Program Files (x86)/Log Parser 2.2";
	private String command = workingDirectory + "/LogParser.exe";
	private String cvsFileName = "Events.csv";
	private String parameters = "\"SELECT TimeGenerated, SourceName, EventID INTO "
			+ cvsFileName + " FROM System WHERE EventID In (12;13)\"";

	public void execute() throws IOException {
		System.out.println("******************************************");
		System.out.println("Run external application Log Parser");
		System.out.println("******************************************");
		System.out.println(command + " " + parameters);
		ProcessBuilder processBuilder = new ProcessBuilder(command, parameters);
		processBuilder.directory(new File(workingDirectory));
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
	}
}
