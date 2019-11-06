/**
 * 
 */
package com.sos.tools.utilities.comparitor;

import java.util.Comparator;

/**
 * @author louis.weyrich.ext
 *
 */
public class StringComparator implements Comparator<String> 
{
	
	public static final byte DIRECTION_ASCENDING = 1;
	public static final byte DIRECTION_DECENDING = 0;
	public static final boolean CASE_SENSITIVE = true;
	
	private byte direction;
	private boolean caseSensitive = false;
	
	
	public StringComparator()
	{
		this(DIRECTION_ASCENDING);
	}
	
	public StringComparator(byte direction)
	{
		this(direction, CASE_SENSITIVE);
	}

	
	public StringComparator(byte direction, boolean caseSensitive)
	{
		this.direction = direction;
		this.caseSensitive = caseSensitive;
	}

	public int compare(String string1, String string2) 
	{		
		if(string1.equals(string2))
		{
			return 0;
		}
		
		if(!caseSensitive)
		{
			string1 = string1.toLowerCase();
			string2 = string2.toLowerCase();
		}
		
		int value = 0;
		
		int size = (string1.length() < string2.length())?string1.length():string2.length();
		
		for(int index = 0; index < size; index++)
		{
			char character1 = string1.charAt(index);
			char character2 = string2.charAt(index);
			
			if(character1 == character2)
			{
				continue;
			}
			else if(((byte)character1) > ((byte)character2))
			{
				value = (direction == DIRECTION_ASCENDING)?1:-1;
				break;
			}
			else if(((byte)character1) < ((byte)character2))
			{
				value = (direction == DIRECTION_ASCENDING)?-1:1;;
				break;
			}
		}
		
		value = (value == 0 && string1.length() == string2.length())?0:(value == 0 && string1.length() > string2.length())? (direction == DIRECTION_ASCENDING)?1:-1:(value == 0 && string1.length() < string2.length())?(direction == DIRECTION_ASCENDING)?-1:1:value;
		
		return value;
	}

}
