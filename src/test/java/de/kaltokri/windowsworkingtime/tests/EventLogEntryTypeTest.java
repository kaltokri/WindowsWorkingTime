package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.eventlog.EventLogEntryType;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EventLogEntryTypeTest {

	@Test
	public void testEventLogEntryTypeShutdown() {
		EventLogEntryType eleType = new EventLogEntryType("13");
		Assert.assertEquals(eleType.getEventLogType(), 13);
	}

	@Test
	public void testEventLogEntryTypeShutdownToString() {
		EventLogEntryType eleType = new EventLogEntryType("13");
		Assert.assertEquals(eleType.toString(), "SHUTDOWN");
	}

	@Test
	public void testEventLogEntryTypeShutdownIsStartupTrue() {
		EventLogEntryType eleType = new EventLogEntryType("12");
		Assert.assertTrue(eleType.isStartup());
	}

	@Test
	public void testEventLogEntryTypeShutdownIsStartupFalse() {
		EventLogEntryType eleType = new EventLogEntryType("13");
		Assert.assertFalse(eleType.isStartup());
	}

	@Test
	public void testEventLogEntryTypeShutdownIsShutdownTrue() {
		EventLogEntryType eleType = new EventLogEntryType("13");
		Assert.assertTrue(eleType.isShutdown());
	}

	@Test
	public void testEventLogEntryTypeShutdownIsShutdownFalse() {
		EventLogEntryType eleType = new EventLogEntryType("12");
		Assert.assertFalse(eleType.isShutdown());
	}

	@Test(expectedExceptions = NumberFormatException.class)
	public void testEventLogEntryTypeForceException() {
		EventLogEntryType eleType = new EventLogEntryType("12");
		eleType.setEventLogType("otto");
	}
}
