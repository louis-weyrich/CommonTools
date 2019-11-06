package com.sos.tools.utilities;

import com.sos.tools.utilities.collection.UtilList;
import com.sos.tools.utilities.comparitor.CharacterComparator;

public class StringUtilities {

	public StringUtilities() {
		// TODO Auto-generated constructor stub
	}
	
	public static Character [] sortWordByCharacters(String word)
	{
		UtilList <Character> list = new UtilList <Character> (word.length());
		list.setComparator(new CharacterComparator());
		list.setSort(true);
		
		for(char c : word.toCharArray())
		{
			list.add(new Character(c));
		}
		
		
		return list.toArray(new Character[word.length()]);
	}
	
	public static int createWordHash(Character [] chars)
	{
		int value = 1;
		
		for(int index = 0; index < chars.length; index++)
		{
			int temp = Character.getNumericValue(chars[index]);
			value *= (temp * (index +1));
		}
		
		
		return value;
	}
	
	public static int createWordHash(String word)
	{
		return createWordHash(sortWordByCharacters(word));
	}
	

}
