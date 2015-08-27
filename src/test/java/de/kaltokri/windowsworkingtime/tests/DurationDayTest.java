package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.duration.DurationDay;
import de.kaltokri.windowsworkingtime.duration.DurationEntry;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Test
public class DurationDayTest {
  private SimpleDateFormat sdf;
  private Date startDate;
  private Date endDate;
  private Date duDaDate;
  private DurationEntry duEn;
  private DurationEntry duEn2;

  /**
   * Prepare the Test and create two DurationEntries with a fixed Date.
   *
   * @throws ParseException
   *           If String can not be parsed to a Date
   */
  @BeforeClass
  public void prepareTest() throws ParseException {
    this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.startDate = sdf.parse("2015-08-15 13:21:15");
    this.endDate = sdf.parse("2015-08-15 14:21:15");
    this.duDaDate = sdf.parse("2015-08-15 00:00:00");
    this.duEn = new DurationEntry(startDate, endDate);
    this.duEn2 = new DurationEntry(startDate, endDate);
  }

  public void constructorTest() {
    DurationDay duDa = new DurationDay(duEn);
    Assert.assertTrue(duDa.getDurationDayDate().equals(duDaDate));
  }

  /**
   * Test for getDurationEntriesOfDay, which creates a DurationDay with a first
   * DurationEntry and adds a second one.
   */
  public void getDurationEntriesOfDayTest() {
    DurationDay duDa = new DurationDay(duEn);
    duDa.addDurationEntry(duEn2);
    Assert.assertEquals(duDa.getDurationEntriesOfDay().indexOf(duEn2), 1);
  }

}
