package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.eventlog.EventLogEntry;
import de.kaltokri.windowsworkingtime.eventlog.EventLogEntryType;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Test
public class EventLogEntryTest {
  private SimpleDateFormat sdf;
  private Date elDate;

  @BeforeClass
  public void prepareTest() throws ParseException {
    this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.elDate = sdf.parse("2015-08-15 13:21:15");
  }

  /**
   * constructor_testWithDateAndInt_Test.
   */
  public void constructor_testWithDateAndInt_Test() {
    EventLogEntry elEntry = new EventLogEntry(this.elDate, 12);
    Date elEntryDate = elEntry.getEventDate();
    Assert.assertEquals(elDate.toString(), elEntryDate.toString());
  }

  /**
   * @throws ParseException
   *           If EventLogEnrtyType cannot be parsed from a String.
   */
  public void testWithDateAndString_Test() throws ParseException {
    EventLogEntryType eleType = new EventLogEntryType("12");
    EventLogEntry elEntry2 = new EventLogEntry(elDate, "12");
    Assert.assertEquals(elEntry2.getEventType().toString(), eleType.toString());
  }

  /**
   * @throws ParseException
   *           If EventLogEnrtyType cannot be parsed from a String.
   */
  public void toString_Test() throws ParseException {
    String result =
        "EventLogEntry [eventDate=Sat Aug 15 13:21:15 CEST 2015, eventType=STARTUP]";
    EventLogEntry elEntry2 = new EventLogEntry(elDate, "12");
    Assert.assertEquals(elEntry2.toString(), result);
  }
}
