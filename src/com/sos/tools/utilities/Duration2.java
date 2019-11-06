package com.sos.tools.utilities;

import java.util.Date;

/**
 * 
 * @author lweyrich
 *
 * This Object is used for doing date duration calculations.
 * 
 * By setting the calculation type, the level of duration will be calculated 
 * from this point.  The default is YEAR, so it will calculate the duration from year
 * down to milliseconds.  If calculation type is set to month, then the highest level
 * of calculation will be from the months, and so on.
 */
public class Duration2 
{
	public static final long DURRATION = 0;
	public static final long NANOSECOND = 1;
	public static final long MILLISECOND = 1000000 * NANOSECOND;
	public static final long SECOND = 1000 * MILLISECOND;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long MONTH = 30 * DAY;
	public static final long YEAR = 365 * DAY;
	
	
	
	private int years = 0, months = 0, days = 0, hours = 0, minutes = 0, seconds = 0;
	private long durationInMilliSeconds = 0, milliseconds = 0, nanosecond = 0;
	
	// calculation type is used to set the level of calculation.  default is YEAR.
	private long calculationType = YEAR;
	
	private long baseCalculation = MILLISECOND;

	// Default constructor that sets the duration to zero
	public Duration2()
	{
		durationInMilliSeconds = 0;
	}

	
	public Duration2(long milliseconds) 
	{
		this(YEAR, milliseconds);
	}
	
	public Duration2(long calculationType, long milliseconds) 
	{
		this.calculationType = calculationType;
		this.durationInMilliSeconds = milliseconds;
		calculate();
	}

	
	public Duration2(long baseCalculation, long calculationType, long milliseconds) 
	{
		this.calculationType = calculationType;
		this.durationInMilliSeconds = milliseconds;
		this.baseCalculation = baseCalculation;
		calculate();
	}

	public Duration2(Date startDate, Date endDate)
	{
		this(YEAR, startDate, endDate);
	}
	

	public Duration2(long calculationType, Date startDate, Date endDate)
	{
		this(MILLISECOND, calculationType, startDate, endDate);
	}

	public Duration2(long baseCalculation, long calculationType, Date startDate, Date endDate)
	{
		this(baseCalculation, calculationType, endDate.getTime() - startDate.getTime());
	}

	
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		
		if(years != 0)
		{
			buffer.append(years).append("y");
		}
		
		if(months != 0)
		{
			buffer.append(months).append("M");
		}
		
		if(days != 0)
		{
			buffer.append(days).append("d");
		}
		
		if(hours != 0)
		{
			buffer.append(hours).append("H");
		}
		
		if(minutes != 0)
		{
			buffer.append(minutes).append("m");
		}
		
		if(seconds != 0)
		{
			buffer.append(seconds).append("s");
		}
		
		if(milliseconds != 0)
		{
			buffer.append(milliseconds).append("S");
		}
		
		if(nanosecond != 0)
		{
			buffer.append(nanosecond).append("n");
		}
		
		return buffer.toString();
	}
	
	public long getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(long calculationType) {
		this.calculationType = calculationType;
	}

	/**
	 * @return the nanosecond
	 */
	public long getNanoseconds()
	{
		return nanosecond;
	}


	/**
	 * @param nanosecond the nanosecond to set
	 */
	public void setNanoseconds(long nanosecond)
	{
		this.nanosecond = nanosecond;
	}


	/**
	 * @return the baseCalculation
	 */
	public long getBaseCalculation()
	{
		return baseCalculation;
	}


	/**
	 * @param baseCalculation the baseCalculation to set
	 */
	public void setBaseCalculation(long baseCalculation)
	{
		this.baseCalculation = baseCalculation;
	}


	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public long getDurationInMilliSeconds() {
		return durationInMilliSeconds;
	}

	public void setDurationInMilliSeconds(long durrationInMilliSeconds) {
		this.durationInMilliSeconds = durrationInMilliSeconds;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(long milliseconds) {
		this.milliseconds = milliseconds;
	}
	
	public long get(long type)
	{
		if(type == YEAR)
		{
			return years;
		}
		else if(type == MONTH)
		{
			return months;
		}
		else if(type == DAY)
		{
			return days;
		}
		else if(type == HOUR)
		{
			return hours;
		}
		else if(type == MINUTE)
		{
			return minutes;
		}
		else if(type == SECOND)
		{
			return seconds;
		}
		else if(type == MILLISECOND)
		{
			return milliseconds;
		}
		else if(type == NANOSECOND)
		{
			return nanosecond;
		}
		else if(type == DURRATION)
		{
			return durationInMilliSeconds;
		}
		else
		{
			return durationInMilliSeconds;
		}
			
	}
	
	/**
	 * This method is used to determine how to calculate the duration.
	 * 
	 */
	public void calculate()
	{
		if(calculationType == YEAR)
		{
			calculateToYears();
		}
		else if(calculationType == MONTH)
		{
			calculateToMonths();
		}
		else if(calculationType == DAY)
		{
			calculateToDays();
		}
		else if(calculationType == HOUR)
		{
			calculateToHours();
		}
		else if(calculationType == MINUTE)
		{
			calculateToMinutes();
		}
		else if(calculationType == SECOND)
		{
			calculateToSeconds();
		}
		else if(calculationType == MILLISECOND)
		{
			calculateToMilliSeconds();
		}
		else if(calculationType == NANOSECOND)
		{
			calculateToNanoSeconds();
		}
		else
		{
			calculateToYears();
		}
	}
	
	public int calculateToYears()
	{
		setCalculationType(YEAR);
		calculateToYears(this.durationInMilliSeconds);
		return years;
	}
	
	private void calculateToYears(long milliseconds)
	{
		reset();
		
		this.durationInMilliSeconds = milliseconds;
		
		if(milliseconds >= YEAR/baseCalculation)
		{
			years = (int) (milliseconds / YEAR/baseCalculation);
			milliseconds = (milliseconds % YEAR/baseCalculation);
		}
		
		if(milliseconds >= DAY/baseCalculation)
		{
			days = (int) (milliseconds / DAY/baseCalculation);
			milliseconds = (milliseconds % DAY/baseCalculation);
		}
		
		if(milliseconds >= HOUR/baseCalculation)
		{
			hours = (int) (milliseconds / HOUR/baseCalculation);
			milliseconds = (milliseconds % HOUR/baseCalculation);
		}
		
		if(milliseconds >= MINUTE/baseCalculation)
		{
			minutes = (int) (milliseconds / MINUTE/baseCalculation);
			milliseconds = (milliseconds % MINUTE/baseCalculation);
		}
		
		if(milliseconds >= SECOND/baseCalculation)
		{
			seconds = (int) (milliseconds / SECOND/baseCalculation);
			milliseconds = (milliseconds % SECOND/baseCalculation);
		}
		
		if(milliseconds > MILLISECOND/baseCalculation)
		{
			this.milliseconds = milliseconds;
			milliseconds = (milliseconds % MILLISECOND/baseCalculation);
		}
		
		if(milliseconds > NANOSECOND)
		{
			this.nanosecond = milliseconds;
			milliseconds = (milliseconds % NANOSECOND);
		}
	}
	
	
	public int calculateToMonths()
	{
		setCalculationType(MONTH);
		calculateToMonths(this.durationInMilliSeconds);
		return months;
	}
	
	private void calculateToMonths(long milliseconds)
	{
		reset();
		this.durationInMilliSeconds = milliseconds;
		
		if(milliseconds >= MONTH/baseCalculation)
		{
			months = (int) (milliseconds / MONTH/baseCalculation);
			milliseconds = (milliseconds % MONTH/baseCalculation);
		}
		
		if(milliseconds >= DAY/baseCalculation)
		{
			days = (int) (milliseconds / DAY/baseCalculation);
			milliseconds = (milliseconds % DAY/baseCalculation);
		}
		
		if(milliseconds >= HOUR/baseCalculation)
		{
			hours = (int) (milliseconds / HOUR/baseCalculation);
			milliseconds = (milliseconds % HOUR/baseCalculation);
		}
		
		if(milliseconds >= MINUTE/baseCalculation)
		{
			minutes = (int) (milliseconds / MINUTE/baseCalculation);
			milliseconds = (milliseconds % MINUTE/baseCalculation);
		}
		
		if(milliseconds >= SECOND/baseCalculation)
		{
			seconds = (int) (milliseconds / SECOND/baseCalculation);
			milliseconds = (milliseconds % SECOND/baseCalculation);
		}
		
		if(milliseconds > MILLISECOND/baseCalculation)
		{
			this.milliseconds = milliseconds;
			milliseconds = (milliseconds % MILLISECOND/baseCalculation);
		}
		
		if(milliseconds > NANOSECOND)
		{
			this.nanosecond = milliseconds;
			milliseconds = (milliseconds % NANOSECOND);
		}
	}

	public int calculateToDays()
	{
		setCalculationType(DAY);
		calculateToDays(this.durationInMilliSeconds);
		return days;
	}
	
	private void calculateToDays(long milliseconds)
	{
		reset();
		this.durationInMilliSeconds = milliseconds;
		
		
		if(milliseconds >= DAY/baseCalculation)
		{
			days = (int) (milliseconds / DAY/baseCalculation);
			milliseconds = (milliseconds % DAY/baseCalculation);
		}
		
		if(milliseconds >= HOUR/baseCalculation)
		{
			hours = (int) (milliseconds / HOUR/baseCalculation);
			milliseconds = (milliseconds % HOUR/baseCalculation);
		}
		
		if(milliseconds >= MINUTE/baseCalculation)
		{
			minutes = (int) (milliseconds / MINUTE/baseCalculation);
			milliseconds = (milliseconds % MINUTE/baseCalculation);
		}
		
		if(milliseconds >= SECOND/baseCalculation)
		{
			seconds = (int) (milliseconds / SECOND/baseCalculation);
			milliseconds = (milliseconds % SECOND/baseCalculation);
		}
		
		if(milliseconds > MILLISECOND/baseCalculation)
		{
			this.milliseconds = milliseconds;
			milliseconds = (milliseconds % MILLISECOND/baseCalculation);
		}
		
		if(milliseconds > NANOSECOND)
		{
			this.nanosecond = milliseconds;
			milliseconds = (milliseconds % NANOSECOND);
		}
	}
	

	public int calculateToHours()
	{
		setCalculationType(HOUR);
		calculateToHours(this.durationInMilliSeconds);
		return hours;
	}
	
	private void calculateToHours(long milliseconds)
	{
		reset();
		this.durationInMilliSeconds = milliseconds;
		
		
		if(milliseconds >= HOUR/baseCalculation)
		{
			hours = (int) (milliseconds / HOUR/baseCalculation);
			milliseconds = (milliseconds % HOUR/baseCalculation);
		}
		
		if(milliseconds >= MINUTE/baseCalculation)
		{
			minutes = (int) (milliseconds / MINUTE/baseCalculation);
			milliseconds = (milliseconds % MINUTE/baseCalculation);
		}
		
		if(milliseconds >= SECOND/baseCalculation)
		{
			seconds = (int) (milliseconds / SECOND/baseCalculation);
			milliseconds = (milliseconds % SECOND/baseCalculation);
		}
		
		if(milliseconds > MILLISECOND/baseCalculation)
		{
			this.milliseconds = milliseconds;
			milliseconds = (milliseconds % MILLISECOND/baseCalculation);
		}
		
		if(milliseconds > NANOSECOND)
		{
			this.nanosecond = milliseconds;
			milliseconds = (milliseconds % NANOSECOND);
		}
	}

	public int calculateToMinutes()
	{
		setCalculationType(MINUTE);
		calculateToMinutes(this.durationInMilliSeconds);
		return minutes;
	}
	
	private void calculateToMinutes(long milliseconds)
	{
		reset();
		this.durationInMilliSeconds = milliseconds;
		
		
		if(milliseconds >= MINUTE/baseCalculation)
		{
			minutes = (int) (milliseconds / MINUTE/baseCalculation);
			milliseconds = (milliseconds % MINUTE/baseCalculation);
		}
		
		if(milliseconds >= SECOND/baseCalculation)
		{
			seconds = (int) (milliseconds / SECOND/baseCalculation);
			milliseconds = (milliseconds % SECOND/baseCalculation);
		}
		
		if(milliseconds > MILLISECOND/baseCalculation)
		{
			this.milliseconds = milliseconds;
			milliseconds = (milliseconds % MILLISECOND/baseCalculation);
		}
		
		if(milliseconds > NANOSECOND)
		{
			this.nanosecond = milliseconds;
			milliseconds = (milliseconds % NANOSECOND);
		}
	}

	public int calculateToSeconds()
	{
		setCalculationType(SECOND);
		calculateToSeconds(this.durationInMilliSeconds);
		return seconds;
	}
	
	private void calculateToSeconds(long milliseconds)
	{
		reset();
		this.durationInMilliSeconds = milliseconds;
				
		if(milliseconds >= SECOND/baseCalculation)
		{
			seconds = (int) (milliseconds / SECOND/baseCalculation);
			milliseconds = (milliseconds % SECOND/baseCalculation);
		}
		
		if(milliseconds > MILLISECOND/baseCalculation)
		{
			this.milliseconds = milliseconds;
			milliseconds = (milliseconds % MILLISECOND/baseCalculation);
		}
		
		if(milliseconds > NANOSECOND)
		{
			this.nanosecond = milliseconds;
			milliseconds = (milliseconds % NANOSECOND);
		}

	}


	public long calculateToMilliSeconds()
	{
		setCalculationType(MILLISECOND);
		calculateToMilliSeconds(this.durationInMilliSeconds);
		return milliseconds;
	}
	
	private void calculateToMilliSeconds(long milliseconds)
	{
		reset();
		
		if(baseCalculation == MILLISECOND)
		{
			this.durationInMilliSeconds = milliseconds;
		}
		else
		{
			this.durationInMilliSeconds = milliseconds * MILLISECOND;
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
		}	
	}
	
	public long calculateToNanoSeconds()
	{
		setCalculationType(NANOSECOND);
		calculateToNanoSeconds(this.durationInMilliSeconds);
		return nanosecond;
	}
	
	private void calculateToNanoSeconds(long nanoseconds)
	{
		
	}

	public void addDuration(Duration2 duration)
	{
		this.durationInMilliSeconds += duration.getDurationInMilliSeconds();
		calculate();
	}

	public void subtractDuration(Duration2 duration)
	{
		this.durationInMilliSeconds -= duration.getDurationInMilliSeconds();
		calculate();
	}

	public Duration2 add(long type, long length)
	{
		durationInMilliSeconds += (type * length);
		calculate();
		return this;
	}
	
	public Duration2 subtract(long type, long length)
	{
		durationInMilliSeconds -= (type * length);
		calculate();
		return this;
	}
	
	public Duration2 addYear(int length)
	{
		return add(Duration2.YEAR, length);
	}
	
	public Duration2 addMonth(int length)
	{
		return add(Duration2.MONTH, length);
	}
	
	public Duration2 addDay(int length)
	{
		return add(Duration2.DAY, length);
	}

	public Duration2 addHour(int length)
	{
		return add(Duration2.HOUR, length);
	}
	
	public Duration2 addMinute(int length)
	{
		return add(Duration2.MINUTE, length);
	}
	
	public Duration2 addSecond(int length)
	{
		return add(Duration2.SECOND, length);
	}
	
	public Duration2 addMilliSecond(long length)
	{
		return add(Duration2.MILLISECOND, length);
	}

	public Duration2 subtractYear(int length)
	{
		return subtract(Duration2.YEAR, length);
	}
	
	public Duration2 subtractMonth(int length)
	{
		return subtract(Duration2.MONTH, length);
	}
	
	public Duration2 subtractDay(int length)
	{
		return subtract(Duration2.DAY, length);
	}

	public Duration2 subtractHour(int length)
	{
		return subtract(Duration2.HOUR, length);
	}
	
	public Duration2 subtractMinute(int length)
	{
		return subtract(Duration2.MINUTE, length);
	}
	
	public Duration2 subtractSecond(int length)
	{
		return subtract(Duration2.SECOND, length);
	}

	public Duration2 subtractMilliSecond(long length)
	{
		return subtract(Duration2.MILLISECOND, length);
	}
	
	public Duration2 addByRatio(float ratio)
	{
		Double tempValue = new Double(getDurationInMilliSeconds() * ratio);
		long longValue = tempValue.longValue();
		setDurationInMilliSeconds(longValue);
		calculate();
		
		return this;
	}
	
	public Duration2 addByRatio(double duration, float ratio)
	{
		
		long currentDuration = getDurationInMilliSeconds();
		Double tempValue = new Double(duration / ratio);
		long longValue = tempValue.longValue();
		if(longValue < getDurationInMilliSeconds())
		{
			setDurationInMilliSeconds(currentDuration + longValue);
			calculate();
		}
				
		return this;
	}
	
	public Duration2 addByRatio(Duration2 duration, float ratio)
	{
		return subtractByRatio(duration.getDurationInMilliSeconds(), ratio);
	}

	
	public Duration2 subtractByRatio(float ratio)
	{
		double currentDuration = getDurationInMilliSeconds();
		Double tempValue = new Double(currentDuration / ratio);
		long longValue = tempValue.longValue();
		setDurationInMilliSeconds(longValue);
		calculate();
				
		return this;
	}
	
	
	public Duration2 subtractByRatio(double duration, float ratio)
	{
		
			long currentDuration = getDurationInMilliSeconds();
			Double tempValue = new Double(duration / ratio);
			long longValue = tempValue.longValue();
			if(longValue < getDurationInMilliSeconds())
			{
				setDurationInMilliSeconds(currentDuration - longValue);
				calculate();
			}
				
		return this;
	}
	
	public Duration2 subtractByRatio(Duration2 duration, float ratio)
	{
		return subtractByRatio(duration.getDurationInMilliSeconds(), ratio);
	}

	public void reset()
	{
		years = 0; 
		months = 0; 
		days = 0; 
		hours = 0; 
		minutes = 0; 
		seconds = 0;
		milliseconds = 0;
	}
	
}
