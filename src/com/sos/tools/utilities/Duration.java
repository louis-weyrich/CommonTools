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
public class Duration 
{
	public static final long DURRATION = 0;
	public static final long MILLISECOND = 1;
	public static final long SECOND = 1000 * MILLISECOND;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long MONTH = 30 * DAY;
	public static final long YEAR = 365 * DAY;
	
	
	
	private int years = 0, months = 0, days = 0, hours = 0, minutes = 0, seconds = 0;
	private long durationInMilliSeconds = 0, milliseconds = 0;
	
	// calculation type is used to set the level of calculation.  default is YEAR.
	private long calculationType = YEAR;

	// Default constructor that sets the duration to zero
	public Duration()
	{
		durationInMilliSeconds = 0;
	}

	
	public Duration(long milliseconds) 
	{
		this(YEAR, milliseconds);
	}
	
	public Duration(long calculationType, long milliseconds) 
	{
		this.calculationType = calculationType;
		this.durationInMilliSeconds = milliseconds;
		calculate();
	}

	public Duration(Date startDate, Date endDate)
	{
		this(YEAR, startDate, endDate);
	}
	

	public Duration(long calculationType, Date startDate, Date endDate)
	{
		this(calculationType, endDate.getTime() - startDate.getTime());
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
		
//		if(milliseconds != 0)
//		{
			buffer.append(milliseconds).append("S");
//		}
		
		return buffer.toString();
	}
	
	public long getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(long calculationType) {
		this.calculationType = calculationType;
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
		
		if(milliseconds >= YEAR)
		{
			years = (int) (milliseconds / YEAR);
			milliseconds = (milliseconds % YEAR);
		}
		
//		if(milliseconds >= MONTH)
//		{
//			months = (int) (milliseconds / MONTH);
//			milliseconds = (milliseconds % MONTH);
//		}
		
		if(milliseconds >= DAY)
		{
			days = (int) (milliseconds / DAY);
			milliseconds = (milliseconds % DAY);
		}
		
		if(milliseconds >= HOUR)
		{
			hours = (int) (milliseconds / HOUR);
			milliseconds = (milliseconds % HOUR);
		}
		
		if(milliseconds >= MINUTE)
		{
			minutes = (int) (milliseconds / MINUTE);
			milliseconds = (milliseconds % MINUTE);
		}
		
		if(milliseconds >= SECOND)
		{
			seconds = (int) (milliseconds / SECOND);
			milliseconds = (milliseconds % SECOND);
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
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
		
		if(milliseconds >= MONTH)
		{
			months = (int) (milliseconds / MONTH);
			milliseconds = (milliseconds % MONTH);
		}
		
		if(milliseconds >= DAY)
		{
			days = (int) (milliseconds / DAY);
			milliseconds = (milliseconds % DAY);
		}
		
		if(milliseconds >= HOUR)
		{
			hours = (int) (milliseconds / HOUR);
			milliseconds = (milliseconds % HOUR);
		}
		
		if(milliseconds >= MINUTE)
		{
			minutes = (int) (milliseconds / MINUTE);
			milliseconds = (milliseconds % MINUTE);
		}
		
		if(milliseconds >= SECOND)
		{
			seconds = (int) (milliseconds / SECOND);
			milliseconds = (milliseconds % SECOND);
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
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
		
		if(milliseconds >= DAY)
		{
			days = (int) (milliseconds / DAY);
			milliseconds = (milliseconds % DAY);
		}
		
		if(milliseconds >= HOUR)
		{
			hours = (int) (milliseconds / HOUR);
			milliseconds = (milliseconds % HOUR);
		}
		
		if(milliseconds >= MINUTE)
		{
			minutes = (int) (milliseconds / MINUTE);
			milliseconds = (milliseconds % MINUTE);
		}
		
		if(milliseconds >= SECOND)
		{
			seconds = (int) (milliseconds / SECOND);
			milliseconds = (milliseconds % SECOND);
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
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
		
		if(milliseconds >= HOUR)
		{
			hours = (int) (milliseconds / HOUR);
			milliseconds = (milliseconds % HOUR);
		}
		
		if(milliseconds >= MINUTE)
		{
			minutes = (int) (milliseconds / MINUTE);
			milliseconds = (milliseconds % MINUTE);
		}
		
		if(milliseconds >= SECOND)
		{
			seconds = (int) (milliseconds / SECOND);
			milliseconds = (milliseconds % SECOND);
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
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
		
		
		if(milliseconds >= MINUTE)
		{
			minutes = (int) (milliseconds / MINUTE);
			milliseconds = (milliseconds % MINUTE);
		}
		
		if(milliseconds >= SECOND)
		{
			seconds = (int) (milliseconds / SECOND);
			milliseconds = (milliseconds % SECOND);
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
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
				
		if(milliseconds >= SECOND)
		{
			seconds = (int) (milliseconds / SECOND);
			milliseconds = (milliseconds % SECOND);
		}
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
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
		this.durationInMilliSeconds = milliseconds;
		
		if(milliseconds > 0)
		{
			this.milliseconds = milliseconds;
		}	
	}

	public void addDuration(Duration duration)
	{
		this.durationInMilliSeconds += duration.getDurationInMilliSeconds();
		calculate();
	}

	public void subtractDuration(Duration duration)
	{
		this.durationInMilliSeconds -= duration.getDurationInMilliSeconds();
		calculate();
	}

	public Duration add(long type, long length)
	{
		durationInMilliSeconds += (type * length);
		calculate();
		return this;
	}
	
	public Duration subtract(long type, long length)
	{
		durationInMilliSeconds -= (type * length);
		calculate();
		return this;
	}
	
	public Duration addYear(int length)
	{
		return add(Duration.YEAR, length);
	}
	
	public Duration addMonth(int length)
	{
		return add(Duration.MONTH, length);
	}
	
	public Duration addDay(int length)
	{
		return add(Duration.DAY, length);
	}

	public Duration addHour(int length)
	{
		return add(Duration.HOUR, length);
	}
	
	public Duration addMinute(int length)
	{
		return add(Duration.MINUTE, length);
	}
	
	public Duration addSecond(int length)
	{
		return add(Duration.SECOND, length);
	}
	
	public Duration addMilliSecond(long length)
	{
		return add(Duration.MILLISECOND, length);
	}

	public Duration subtractYear(int length)
	{
		return subtract(Duration.YEAR, length);
	}
	
	public Duration subtractMonth(int length)
	{
		return subtract(Duration.MONTH, length);
	}
	
	public Duration subtractDay(int length)
	{
		return subtract(Duration.DAY, length);
	}

	public Duration subtractHour(int length)
	{
		return subtract(Duration.HOUR, length);
	}
	
	public Duration subtractMinute(int length)
	{
		return subtract(Duration.MINUTE, length);
	}
	
	public Duration subtractSecond(int length)
	{
		return subtract(Duration.SECOND, length);
	}

	public Duration subtractMilliSecond(long length)
	{
		return subtract(Duration.MILLISECOND, length);
	}
	
	public Duration addByRatio(float ratio)
	{
		Double tempValue = new Double(getDurationInMilliSeconds() * ratio);
		long longValue = tempValue.longValue();
		setDurationInMilliSeconds(longValue);
		calculate();
		
		return this;
	}
	
	public Duration addByRatio(double duration, float ratio)
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
	
	public Duration addByRatio(Duration duration, float ratio)
	{
		return subtractByRatio(duration.getDurationInMilliSeconds(), ratio);
	}

	
	public Duration subtractByRatio(float ratio)
	{
		double currentDuration = getDurationInMilliSeconds();
		Double tempValue = new Double(currentDuration / ratio);
		long longValue = tempValue.longValue();
		setDurationInMilliSeconds(longValue);
		calculate();
				
		return this;
	}
	
	
	public Duration subtractByRatio(double duration, float ratio)
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
	
	public Duration subtractByRatio(Duration duration, float ratio)
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
