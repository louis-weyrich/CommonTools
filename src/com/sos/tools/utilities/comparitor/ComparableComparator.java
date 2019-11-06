/**
 * 
 */
package com.sos.tools.utilities.comparitor;

import java.util.Comparator;


/**
 * @author louis.weyrich.ext
 *
 */
public class ComparableComparator implements Comparator<Comparable<Object>> 
{

	/**
	 * 
	 */
	public ComparableComparator() 
	{
		// Do Nothing
	}

	public int compare(Comparable<Object> object1, Comparable<Object> object2) 
	{
		return object1.compareTo(object2);
	}

}
