package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.eventlog.EventLogDataset;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Test
public class EventLogDatasetTest {
  private SimpleDateFormat sdf;
  private Date key;
  private Date endate;

  /**
   * Prepare the Test and create a key and an endDate with fixed values.
   *
   * @throws ParseException
   *           If String can not be parsed to a Date
   */
  @BeforeClass
  public void prepareTest() throws ParseException {
    this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.key = sdf.parse("2015-08-15 00:00:00");
    this.endate = sdf.parse("2015-08-15 23:59:59");
  }

  /**
   * Creates an empty EventLogDataset and puts a startDate and a endDate to it.
   *
   * @throws ParseException
   *           If String can not be parsed to a Date
   */
  public void putTest() throws ParseException {
    EventLogDataset elds = new EventLogDataset();
    elds.put("2015-08-15 13:21:15", "12");
    elds.put("2015-08-15 14:21:15", "13");
    Assert.assertEquals(elds.keys().nextElement().toString(),
        "Sat Aug 15 00:00:00 CEST 2015");
  }

  /**
   * Creates an empty EventLogDataset, add a start event with put and call
   * fixMissingEvents. Check if the secoand (generated) EventLogEntry has the
   * specified endDate.
   *
   * @throws ParseException
   *           If String can not be parsed to a Date
   */
  public void fixMissingEventsTest() throws ParseException {
    EventLogDataset elds = new EventLogDataset();
    elds.put("2015-08-15 13:21:15", "12");
    elds.fixMissingEvents();
    Assert.assertTrue(elds.get(key).getEventsOfDay().get(1).getEventDate()
        .equals(endate));
  }

  /**
   * Creates an empty EventLogDataset, add a start event with put and compare
   * output of toString with a reference file.
   *
   * @throws ParseException
   *           If String can not be parsed to a Date
   * @throws IOException
   *           If file for expected pattern cannot be loaded.
   */
  public void toStringTest() throws ParseException, IOException {
    EventLogDataset elds = new EventLogDataset();
    elds.put("2015-08-15 13:21:15", "12");

    InputStream expected =
        getClass().getClassLoader().getResourceAsStream(
            "EventLogDataset-format-expected.dat");
    Assert.assertEquals(elds.toString().trim(), IOUtils.toString(expected)
        .trim());
  }
}
