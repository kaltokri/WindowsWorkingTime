package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.eventlog.EventLogDay;
import de.kaltokri.windowsworkingtime.eventlog.EventLogEntry;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Test
public class EventLogDayTest {
  private SimpleDateFormat sdf;
  private Date elDate1;
  private Date elDate2;
  private EventLogEntry elEntry1;
  private EventLogEntry elEntry2;

  /**
   * Creates two EventLogEntries.
   *
   * @throws ParseException
   *           If the given fixed Date String cannot be parsed to a Date.
   */
  @BeforeClass
  public void prepareTest() throws ParseException {
    this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.elDate1 = sdf.parse("2015-08-15 00:00:00");
    this.elDate2 = sdf.parse("2015-08-15 01:00:00");
    this.elEntry1 = new EventLogEntry(elDate1, 12);
    this.elEntry2 = new EventLogEntry(elDate2, 13);
  }

  public void constructor_Date_Test() {
    EventLogDay eld = new EventLogDay(elDate1);
    Assert.assertEquals(eld.getEventDayDate().toString(), elDate1.toString());
  }

  public void constructor_EventLogEntry_Test() {
    EventLogDay eld = new EventLogDay(elDate1, elEntry1);
    Assert.assertTrue(eld.getEventsOfDay().contains(elEntry1));
  }

  /**
   * Test for setEventDayDate.
   */
  public void setEventDayDate_Test() {
    EventLogDay eld = new EventLogDay(elDate1, elEntry1);
    eld.setEventDayDate(elDate2);
    Assert.assertEquals(eld.getEventDayDate().toString(), elDate2.toString());
  }

  /**
   * Test for addEvent.
   */
  public void addEvent_Test() {
    EventLogDay eld = new EventLogDay(elDate1, elEntry1);
    eld.addEvent(elEntry2);
    Assert.assertTrue(eld.getEventsOfDay().contains(elEntry2));
  }

  /**
   * fixMissingEvents_fixMissingStartup_Test.
   */
  public void fixMissingEvents_fixMissingStartup_Test() {
    EventLogDay eld = new EventLogDay(elDate1, elEntry2);
    eld.fixMissingEvents();
    Assert.assertEquals(eld.getEventsOfDay().get(0).getEventType()
        .getEventLogType(), 12);
  }

  /**
   * fixMissingEvents_fixMissingShutdown_Test.
   */
  public void fixMissingEvents_fixMissingShutdown_Test() {
    EventLogDay eld = new EventLogDay(elDate1, elEntry1);
    eld.fixMissingEvents();
    Assert.assertEquals(eld.getEventsOfDay().get(1).getEventType()
        .getEventLogType(), 13);
  }

  /**
   * fixMissingEvents_fixDoubleStartup_Test. @see prepareTest
   */
  public void fixMissingEvents_fixDoubleStartup_Test() {
    EventLogDay eld = new EventLogDay(elDate1, elEntry1);
    eld.addEvent(elEntry1);
    eld.addEvent(elEntry2);

    eld.fixMissingEvents();
    Assert.assertEquals(eld.getEventsOfDay().indexOf(elEntry2), 1);
  }
}
