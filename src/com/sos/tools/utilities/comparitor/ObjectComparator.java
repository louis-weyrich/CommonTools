/**
 * 
 */
package com.sos.tools.utilities.comparitor;

import java.util.Comparator;

/**
 * @author louis.weyrich.ext
 *
 */
public class ObjectComparator <T> implements Comparator<T> 
{

	/**
	 * 
	 */
	public ObjectComparator() 
	{
		// Do Nothing
	}

	public int compare(T object1, T object2) 
	{
		if(object1 == null)
		{
			return 1;
		}
		
		if(object2 == null)
		{
			return -1;
		}
		
		int returnValue = 0;
		int hash1 = object1.hashCode();
		int hash2 = object2.hashCode();
		
		if(object1 == object2)
		{
			returnValue = 0;
		}
		else if(object1.equals(object2))
		{
			returnValue = 0;
		}
		else if(hash1 == hash2) 
		{
			returnValue = 0;
		}
		else if(hash1 < hash2) 
		{
			returnValue = -1;
		}
		else if(hash1 > hash2) 
		{
			returnValue = 1;
		}
		
		return returnValue;
	}

}
