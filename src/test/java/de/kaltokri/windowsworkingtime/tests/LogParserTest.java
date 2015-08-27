package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.LogParser;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public class LogParserTest {
  /**
   * @throws IOException
   *           If Events.csv cannot be loaded.
   */
  public void executeTest() throws IOException {
    LogParser lp = new LogParser();
    lp.execute();
    Assert.assertEquals(lp.getResultFilepath(),
        "C:/Program Files (x86)/Log Parser 2.2/Events.csv");
  }
}
