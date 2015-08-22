package de.kaltokri.windowsworkingtime.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.kaltokri.windowsworkingtime.duration.DurationEntry;

@Test
public class DurationEntryTest {
	private SimpleDateFormat sdf;
	private Date startDate;
	private Date endDate;

	@BeforeClass
	public void PrepareTest() throws ParseException {
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.startDate = sdf.parse("2015-08-15 13:21:15");
		this.endDate = sdf.parse("2015-08-15 14:21:15");
	}

	public void constructorTestWithDates() {
		DurationEntry duEn = new DurationEntry(this.startDate, this.endDate);
		Assert.assertEquals(duEn.getDuration().longValue(), 60L);
	}

	public void getStartDateTest() {
		DurationEntry duEn = new DurationEntry(this.startDate, this.endDate);
		Assert.assertEquals(duEn.getStartDate(), this.startDate);
	}

	public void getEndDateTest() {
		DurationEntry duEn = new DurationEntry(this.startDate, this.endDate);
		Assert.assertEquals(duEn.getEndDate(), this.endDate);
	}

	public void getDurationStringTest() {
		DurationEntry duEn = new DurationEntry(this.startDate, this.endDate);
		Assert.assertEquals(duEn.getDurationString(), "1h 0min");
	}
}
