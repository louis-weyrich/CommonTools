package com.sos.tools.utilities.comparitor;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>
{

	public IntegerComparator()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Integer int1, Integer int2)
	{
		if(int1 == int2)
		{
			return 0;
		}
		else if(int1 > int2)
		{
			return 1;
		}
		else if(int1 < int2)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}
