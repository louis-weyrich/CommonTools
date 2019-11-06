package com.sos.tools.test.utilities;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.Duration;

public class DurationTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testDuration()
	{
		Duration duration = new Duration();

		duration.addMilliSecond(Calendar.getInstance().getTimeInMillis());
		duration.setCalculationType(Duration.YEAR);
		String durationString = duration.toString();
		
		System.out.println("testDuration = "+durationString);
	}

	@Test
	public void testDurationLong()
	{
		Duration duration = new Duration(Calendar.getInstance().getTimeInMillis());
		String durationString = duration.toString();
		
		System.out.println("testDurationLong = "+durationString);
	}

	@Test
	public void testDurationLongLong()
	{
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, 2016);
		now.add(Calendar.MONTH, Calendar.AUGUST);
		now.add(Calendar.DAY_OF_MONTH, 19);
		
		Calendar myBday = Calendar.getInstance();
		myBday.add(Calendar.YEAR, 1969);
		myBday.add(Calendar.MONTH, Calendar.APRIL);
		myBday.add(Calendar.DAY_OF_MONTH, 17);
		
		Duration duration = new Duration(Duration.YEAR, now.getTimeInMillis() - myBday.getTimeInMillis());
		duration.calculate();
		String durationString = duration.toString();
		
		System.out.println("testDurationLongLong = "+durationString);
	}

	@Test
	public void testDurationDateDate()
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, 2016);
		now.add(Calendar.MONTH, Calendar.AUGUST);
		now.add(Calendar.DAY_OF_MONTH, 19);
		
		Calendar myBday = Calendar.getInstance();
		myBday.add(Calendar.YEAR, 1969);
		myBday.add(Calendar.MONTH, Calendar.APRIL);
		myBday.add(Calendar.DAY_OF_MONTH, 17);
		
		Duration duration = new Duration(myBday.getTime(), now.getTime());
		String durationString = duration.toString();
		
		System.out.println("testDurationDateDate = "+durationString);
	}

	@Test
	public void testDurationLongDateDate()
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, 2016);
		now.add(Calendar.MONTH, Calendar.AUGUST);
		now.add(Calendar.DAY_OF_MONTH, 19);
		
		Calendar myBday = Calendar.getInstance();
		myBday.add(Calendar.YEAR, 1969);
		myBday.add(Calendar.MONTH, Calendar.APRIL);
		myBday.add(Calendar.DAY_OF_MONTH, 17);
		
		Duration duration = new Duration(Duration.YEAR, myBday.getTime(), now.getTime());
		String durationString = duration.toString();
		
		System.out.println("testDurationLongDateDate = "+durationString);
	}

	@Test
	public void testToString()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetCalculationType()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetCalculationType()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetYears()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetYears()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetMonths()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetMonths()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetDays()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetDays()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetHours()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetHours()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetMinutes()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetMinutes()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetSeconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetSeconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetDurationInMilliSeconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetDurationInMilliSeconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetMilliseconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSetMilliseconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGet()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculate()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToYears()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToMonths()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToDays()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToHours()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToMinutes()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToSeconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCalculateToMilliSeconds()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddDuration()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractDuration()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAdd()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtract()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddYear()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddMonth()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddDay()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddHour()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddMinute()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddSecond()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddMilliSecond()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractYear()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractMonth()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractDay()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractHour()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractMinute()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractSecond()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractMilliSecond()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddByRatioFloat()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddByRatioDoubleFloat()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAddByRatioDurationFloat()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractByRatioFloat()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractByRatioDoubleFloat()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testSubtractByRatioDurationFloat()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testReset()
	{
		//fail("Not yet implemented");
	}

}
