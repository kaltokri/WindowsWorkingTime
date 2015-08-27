package de.kaltokri.windowsworkingtime.tests;

import de.kaltokri.windowsworkingtime.duration.DurationDataset;
import de.kaltokri.windowsworkingtime.eventlog.EventLogDataset;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

@Test
public class DurationDatasetTest {
  EventLogDataset elds = new EventLogDataset();

  /**
   * Prepare the test and adds two startup and two shutdown events.
   *
   * @throws ParseException
   *           if the date cannot be parsed
   */
  public void prepareTest() throws ParseException {
    // TODO: Must be replaced by dynamic dates of last week
    this.elds.put("2015-08-15 13:21:15", "12");
    this.elds.put("2015-08-15 14:21:15", "13");
    this.elds.put("2015-08-16 13:21:15", "12");
    this.elds.put("2015-08-16 14:21:15", "13");
  }

  public void constructorTest() {
    DurationDataset duda = new DurationDataset(this.elds);
    Assert.assertTrue(duda.containsKey(33));
  }

  public void getTest() {
    DurationDataset duda = new DurationDataset(this.elds);
    Assert.assertEquals(33, duda.get(33).getWeekOfYear().intValue());
  }

  public void keysTest() {
    DurationDataset duda = new DurationDataset(this.elds);
    Assert.assertTrue(duda.keys().contains(33));
  }

  /**
   * Compares the output with a reference file.
   *
   * @throws IOException
   *           If reading of the expected output file is not possible
   */
  public void toStringTest() throws IOException {
    DurationDataset duda = new DurationDataset(this.elds);

    InputStream expected =
        getClass().getClassLoader().getResourceAsStream(
            "DurationDataset-format-expected.dat");
    Assert.assertEquals(duda.toString().trim(), IOUtils.toString(expected)
        .trim());
  }
}
