package com.sos.tools.utilities.sort;

import java.util.List;

public class BubbleSort 
{
	
	public static final char ASSENDING = 'A';
	public static final char DESSENDING = 'D';
	
	public static <T extends Comparable<T>> T [] sortArray(T [] unsortedArray, char direction)
	{
		// iterate from the first position the the last
		for(int i = 0; i < unsortedArray.length - 1; i++)
		{
			// swap if index i < index j
			for(int j = 1; j < unsortedArray.length - i; j++)
			{
				switch(direction)
				{
					case 'A' :
					{
						if(unsortedArray[j - 1].compareTo( unsortedArray[j]) == 1)
						{
							T temp = unsortedArray[j];
							unsortedArray[j] = unsortedArray[j-1];
							unsortedArray[j-1] = temp;
						}
						break;
					}
					case 'D' :
					{
						if(unsortedArray[j - 1].compareTo( unsortedArray[j]) == -1)
						{
							T temp = unsortedArray[j];
							unsortedArray[j] = unsortedArray[j-1];
							unsortedArray[j-1] = temp;
						}
						break;
					}
				}
			}
		}

		return unsortedArray;
	}
	
	public static <T extends Comparable<T>> List<T> sortList(List<T>  unsortedList, char direction)
	{
		// iterate from the first position the the last
		for(int i = 0; i < unsortedList.size() - 1; i++)
		{
			// swap if index i < index j
			for(int j = 1; j < unsortedList.size() - i; j++)
			{
				switch(direction)
				{
					case 'A' :
					{
						if(unsortedList.get(j - 1).compareTo( unsortedList.get(j) ) == 1)
						{
							T temp = unsortedList.get(j);
							unsortedList.set(j, unsortedList.get(j-1));
							unsortedList.set(j-1, temp);
							
						}
						break;
					}
					case 'D' :
					{
						if(unsortedList.get(j - 1).compareTo( unsortedList.get(j) ) == -1)
						{
							T temp = unsortedList.get(j);
							unsortedList.set(j, unsortedList.get(j-1));
							unsortedList.set(j-1, temp);
							
						}
						break;
					}
				}
			}
		}

		return unsortedList;
	}
}
