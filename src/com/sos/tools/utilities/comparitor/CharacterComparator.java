package com.sos.tools.utilities.comparitor;

import java.util.Comparator;

public class CharacterComparator implements Comparator<Character> {
	
	public static final byte DIRECTION_ASCENDING = 1;
	public static final byte DIRECTION_DECENDING = 0;
	public static final boolean CASE_SENSITIVE = true;
	public static final boolean CASE_INSENSITIVE = false;
	
	private byte direction = DIRECTION_ASCENDING;
	private boolean caseSensitive = CASE_SENSITIVE;

	public CharacterComparator() {
		this(DIRECTION_ASCENDING, CASE_SENSITIVE);
	}
	
	public CharacterComparator(byte direction) {
		this(direction, CASE_SENSITIVE);
	}
	
	public CharacterComparator(boolean caseSensitive) {
		this(DIRECTION_ASCENDING, caseSensitive);
	}
	
	public CharacterComparator(byte direction, boolean caseSensitive) {
		this.direction = direction;
		this.caseSensitive = caseSensitive;
	}

	@Override
	public int compare(Character char1, Character char2) {
		
		if(char1.equals(char2))
		{
			return 0;
		}
		
		if(caseSensitive == CASE_INSENSITIVE)
		{
			char1 = Character.toLowerCase(char1);
			char2 = Character.toLowerCase(char2);
		}
		
		int char1int = Character.getNumericValue(char1);
		int char2int = Character.getNumericValue(char2);
		
		if(char1int == char2int)
		{
			return 0;
		}
		else if(char1int < char2int)
		{
			return (direction == DIRECTION_ASCENDING)?-1:1;
		}
		else
		{
			return (direction == DIRECTION_ASCENDING)?1:-1;
		}
		
	}

}
