package de.kaltokri.windowsworkingtime.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.kaltokri.windowsworkingtime.LogParser;

@Test
public class LogParserTest {
	public void executeTest() throws IOException {
		LogParser lp = new LogParser();
		lp.execute();
		Assert.assertEquals(lp.getResultFilepath(),
				"C:/Program Files (x86)/Log Parser 2.2/Events.csv");
	}
}
