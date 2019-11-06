package com.sos.tools.utilities.sort;

import java.util.Random;

public abstract class AbstractSort 
{
	protected int [] theArray;
	protected int arraySize;
	
	public AbstractSort(int size, int start, int end)
	{
		arraySize = size;
		theArray = new int [size];
		
		for(int i = 0; i < size; i++)
		{
			theArray[i] = generateRandom(start, end);
		}
	}
	
	public void printArray()
	{
		System.out.println("----------");
		for(int i = 0; i < arraySize; i++){
			System.out.print("| " + i + " | ");
		    System.out.println(theArray[i] + " |");
		    System.out.println("----------");
		}

	}
	

	public void printHorzArray(int i, int j) {
		for (int n = 0; n < 61; n++)
			System.out.print("-");
		System.out.println();
		for (int n = 0; n < arraySize; n++) {
			System.out.format("| %2s " + " ", n);
		}
		System.out.println("|");
		for (int n = 0; n < 61; n++)
			System.out.print("-");
		System.out.println();
		for (int n = 0; n < arraySize; n++) {
			System.out.print(String.format("| %2s " + " ", theArray[n]));
		}
		System.out.println("|");
		for (int n = 0; n < 61; n++)
			System.out.print("-");
		System.out.println();
		if (i != -1) {
			// Number of spaces to put before the F
			int spacesBeforeFront = 6 * (i + 1) - 5;
			for (int k = 0; k < spacesBeforeFront; k++)
				System.out.print(" ");
			System.out.print("L" + i);
			// Number of spaces to put before the R
			int spacesBeforeRear = 5 * (j + 1) - spacesBeforeFront;
			for (int l = 0; l < spacesBeforeRear; l++)
				System.out.print(" ");
			System.out.print("R" + j);
			System.out.println("\n");
		}
	}

	
	protected void swapValues(int indexOne, int indexTwo)
	{
		int tempValue = theArray[indexOne];
		theArray[indexOne] = theArray[indexTwo];
		theArray[indexTwo] = tempValue;
	}
	
	public int generateRandom(int start, int end)
	{
		return new Random().nextInt(end-start) + start;
	}
	
	public void binarySearch(int value)
	{
		int lowIndex = 0;
		int highIndex = arraySize - 1;
		
		while(lowIndex <= highIndex)
		{
			int middleIndex = (highIndex + lowIndex)/2;
			if(theArray[middleIndex] == value)
			{
				System.out.println("\nFound a Match for " + value + " at Index " + middleIndex);
				lowIndex = highIndex + 1;
			}
			else if(theArray[middleIndex] > value)
			{
				highIndex = middleIndex - 1;
			}
			else if(theArray[middleIndex] > value)
			{
				lowIndex = middleIndex +1;
			}
			else
			{
				System.out.println("\nA close Match for " + value + " at Index " + middleIndex + " = "+theArray[middleIndex]);
				lowIndex = highIndex + 1;
			}
		}
	}
	
	public abstract void sort();

}
