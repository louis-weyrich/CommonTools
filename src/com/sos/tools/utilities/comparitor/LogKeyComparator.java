/**
 * 
 */
package com.sos.tools.utilities.comparitor;

import java.util.Comparator;

import com.sos.tools.logging.LogKey;

/**
 * @author lweyrich
 *
 */
public class LogKeyComparator implements Comparator<LogKey>
{

	/**
	 * 
	 */
	public LogKeyComparator()
	{
		// TODO Auto-generated constructor stub
	}

	public int compare(LogKey className1, LogKey className2)
	{
		if(className2.getClassPath().startsWith(className1.getClassPath()))
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}

}
