/**
 * 
 */
package com.sos.tools.utilities.sort;


/**
 * @author louisweyrich
 *
 */
public class SelectionSort extends AbstractSort
{

	
	public SelectionSort(int size, int start, int end)
	{
		super(size, start, end);
	}
	

	@Override
	public void sort() 
	{
		for(int x = 0; x < arraySize; x++)
		{
			int minimum = x;
			
			for(int y = x; y < arraySize; y++)
			{
				if(theArray[minimum] > theArray[y])
				{
					minimum = y;
				}
			}
			
			swapValues(x, minimum);
		}
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SelectionSort sorter = new SelectionSort(15, 0, 100);
		sorter.printHorzArray(0, 15);
		sorter.sort();
		sorter.printHorzArray(0, 15);
		sorter.binarySearch(25);
	}



}
