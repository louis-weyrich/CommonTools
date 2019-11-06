/**
 * 
 */
package com.sos.tools.utilities.sort;

/**
 * @author louisweyrich
 *
 */
public class InsertionSort extends AbstractSort {

	/**
	 * @param size
	 * @param start
	 * @param end
	 */
	public InsertionSort(int size, int start, int end) 
	{
		super(size, start, end);
	}

	/* 
	 * 
	 */
	@Override
	public void sort() 
	{
		for(int i = 0; i < arraySize; i++)
		{
			int j = i;
			int toInsert = theArray[i];
			
			while((j > 0) && (theArray[j - 1] > toInsert))
			{
				theArray[j] = theArray[j-1];
				j--;
			}
		
			theArray[j] = toInsert;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		InsertionSort sorter = new InsertionSort(15, 0, 100);
		sorter.printHorzArray(0, 14);
		sorter.sort();
		sorter.printHorzArray(0, 14);
		sorter.binarySearch(25);
	}

}
