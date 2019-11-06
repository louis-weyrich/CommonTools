package com.sos.tools.utilities;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

	public static List <Long> getSequence(int toPosition)
	{
		List <Long> results = new ArrayList <Long> (toPosition);
		long result = 0;
		
		for(int i = 0; i < toPosition; i++)
		{
			result += (i == 0)? 1 : (i == 1)? 0 : results.get(i -2);
			results.add(new Long(result));
		}
		
		return results;
	}

}
