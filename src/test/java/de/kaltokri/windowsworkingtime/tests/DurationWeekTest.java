package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.duration.DurationEntry;
import de.kaltokri.windowsworkingtime.duration.DurationWeek;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Test
public class DurationWeekTest {
	private SimpleDateFormat sdf;
	private Date startDate;
	private Date endDate;
	private DurationEntry duEn;
	private DurationEntry duEn2;

	@BeforeClass
	public void PrepareTest() throws ParseException {
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.startDate = sdf.parse("2015-08-15 13:21:15");
		this.endDate = sdf.parse("2015-08-15 14:21:15");
		this.duEn = new DurationEntry(startDate, endDate);
		this.duEn2 = new DurationEntry(startDate, endDate);
	}

	public void constructorTest() {
		DurationWeek duWe = new DurationWeek(duEn);
		Assert.assertEquals(duWe.getWeekOfYear().intValue(), 33);
	}

	public void getDurationDaysTest() {
		DurationWeek duWe = new DurationWeek(duEn);
		Assert.assertTrue(duWe.getDurationDays().get(0)
				.getDurationEntriesOfDay().contains(duEn));
	}

	public void addDurationDayTest() {
		DurationWeek duWe = new DurationWeek(duEn);
		duWe.addDurationDay(duEn2);
		Assert.assertTrue(duWe.getDurationDays().get(1)
				.getDurationEntriesOfDay().contains(duEn2));
	}

	public void totalDurationTest() {
		DurationWeek duWe = new DurationWeek(duEn);
		Assert.assertEquals(duWe.totalDuration().longValue(),60L);
	}

	public void totalDurationStringTest() {
		DurationWeek duWe = new DurationWeek(duEn);
		Assert.assertEquals(duWe.totalDurationString(),"1h 0min");
	}
}
