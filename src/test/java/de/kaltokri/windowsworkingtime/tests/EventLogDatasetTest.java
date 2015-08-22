package de.kaltokri.windowsworkingtime.tests;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.IOUtils;

import de.kaltokri.windowsworkingtime.eventlog.EventLogDataset;

@Test
public class EventLogDatasetTest {
	private SimpleDateFormat sdf;
	private Date key;
	private Date endate;

	@BeforeClass
	public void PrepareTest() throws ParseException {
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.key = sdf.parse("2015-08-15 00:00:00");
		this.endate = sdf.parse("2015-08-15 23:59:59");
	}

	public void EventLogDataset_put_Test() throws ParseException {
		EventLogDataset elds = new EventLogDataset();
		elds.put("2015-08-15 13:21:15", "12");
		elds.put("2015-08-15 14:21:15", "13");
		Assert.assertEquals(elds.keys().nextElement().toString(),
				"Sat Aug 15 00:00:00 CEST 2015");
	}

	public void EventLogDataset_fixMissingEvents() throws ParseException {
		EventLogDataset elds = new EventLogDataset();
		elds.put("2015-08-15 13:21:15", "12");
		elds.fixMissingEvents();
		Assert.assertTrue(elds.get(key).getEventsOfDay().get(1).getEventDate()
				.equals(endate));
	}

	public void EventLogDataset_toString() throws ParseException, IOException {
		EventLogDataset elds = new EventLogDataset();
		elds.put("2015-08-15 13:21:15", "12");

		InputStream expected = getClass().getClassLoader().getResourceAsStream(
				"EventLogDataset-format-expected.dat");
		Assert.assertEquals(elds.toString().trim(),IOUtils.toString(expected).trim());
	}
}
