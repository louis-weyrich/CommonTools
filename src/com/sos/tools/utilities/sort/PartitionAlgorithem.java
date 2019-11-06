/**
 * 
 */
package com.sos.tools.utilities.sort;

/**
 * @author louisweyrich
 *
 */
public class PartitionAlgorithem extends AbstractSort {

	/**
	 * @param size
	 * @param start
	 * @param end
	 */
	public PartitionAlgorithem(int size, int start, int end) {
		super(size, start, end);
	}

	/* (non-Javadoc)
	 * @see ads.sort.AbstractSort#sort()
	 */
	@Override
	public void sort() {
		
	}
	

	public void partitionArray(int pivot) {
		int leftPointer = -1;
		int rightPointer = arraySize;

		while (true) {
			while (leftPointer < (arraySize + 1) && theArray[++leftPointer] < pivot)
				;
			
			System.out.println(theArray[leftPointer] + " int index " + leftPointer + " is bigger than pivot");
			this.printHorzArray(leftPointer, rightPointer);

			while (rightPointer > 0 && theArray[--rightPointer] > pivot)
				;
			
			System.out.println(theArray[rightPointer] + " int index " + rightPointer + " is smaller than pivot");
			this.printHorzArray(leftPointer, rightPointer);

			if (leftPointer >= rightPointer)
				break;
			else {
				swapValues(leftPointer, rightPointer);
			}
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


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuickSort sorter = new QuickSort(15, 0, 100);
		sorter.sort();
		sorter.binarySearch(25);
	}

}
