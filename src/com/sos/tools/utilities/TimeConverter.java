package com.sos.tools.utilities;

import java.text.ParseException;
import java.util.StringTokenizer;

public class TimeConverter {

	public static String addTimeInMinutes(String timeString, int minutes) throws ParseException
	{
		if(minutes < 1) throw new IllegalArgumentException("minutes is lass than 1.  No time will be added.");
		
		StringTokenizer tokens = new StringTokenizer(timeString, ": ", true);
		if(tokens.countTokens() == 5)
		{
			String shour= tokens.nextToken();
			String colon = tokens.nextToken();
			String sminute = tokens.nextToken();
			String space = tokens.nextToken();
			String ampm = tokens.nextToken().toUpperCase();
			
			if(!colon.equals(":"))
				throw new ParseException("Wrong time format: missing colon between hour and minute", 2);
			
			if(!space.equals(" "))
				throw new ParseException("Wrong time format: missing space between minute and am/pm marker", 5);
			
			int hour = 0;
			int minute = 0;

			try{
				hour = Integer.parseInt(shour);
			}catch(NumberFormatException e){
				throw new ParseException("Wrong time format: "+shour+" is not a number (format \"HH:MM {AM|PM}\")", 0);
			}
			
			try{
				minute = Integer.parseInt(sminute);
			}catch(NumberFormatException e){
				throw new ParseException("Wrong time format: "+sminute+" is not a number (format \"HH:MM {AM|PM}\")", 3);
			}
			
			minute += minutes;
			
			if(minute >= 60)
			{
				int tempMinute = minute%60;
				hour += minute/60;
				minute = tempMinute;
				
				if(hour > 12)
				{
					int hourConversion = (hour/12)%2;
					hour = hour%12;
					if(ampm.equals("AM") && hourConversion == 1)
					{
						ampm = "PM";
					}
					else if(ampm.equals("PM") && hourConversion == 1)
					{
						ampm = "AM";
					}
				}
			}
			
			return hour+colon+((minute < 10)?"0"+minute:minute)+space+ampm;
		}
		else
		{
			throw new ParseException("Wrong time format: HH:MM {AM|PM} ("+tokens.countTokens()+" tokens)", -1);
		}
	}

}
