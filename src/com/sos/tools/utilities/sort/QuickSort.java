/**
 * 
 */
package com.sos.tools.utilities.sort;

/**
 * @author louisweyrich
 *
 *
 *         uses partitioning
 */
public class QuickSort extends AbstractSort {


	/**
	 * @param size
	 * @param start
	 * @param end
	 */
	public QuickSort(int size, int start, int end) {
		super(size, start, end);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ads.sort.AbstractSort#sort()
	 */
	@Override
	public void sort() {
		sort(0, arraySize - 1);
	}

	public void sort(int left, int right) {
		if (right - left <= 0) {
			return;
		} else {
			int pivot = theArray[right];

			System.out.println("Value in the right " + theArray[right] + " is made the pivot");
			System.out.println("Left= " + left + " right= " + right + " pivot= " + pivot + " sent to be partitioned");

			int pivotLocation = partitionArray(left, right, pivot);

			System.out.println("Value in left " + theArray[left] + " is made the pivot");

			sort(left, pivotLocation - 1);
			sort(pivotLocation + 1, right);
		}

	}

	public int partitionArray(int left, int right, int pivot) {
		int leftPointer = left - 1;
		int rightPointer = right;

		while (true) {
			while (theArray[++leftPointer] < pivot)
				;

			System.out.println(theArray[leftPointer] + " int index " + leftPointer + " is bigger than pivot");
			this.printHorzArray(leftPointer, rightPointer);

			while (rightPointer > 0 && theArray[--rightPointer] > pivot)
				;
			
			System.out.println(theArray[rightPointer] + " int index " + rightPointer + " is smaller than pivot");
			this.printHorzArray(leftPointer, rightPointer);
			
			if(leftPointer >= rightPointer)
			{
				System.out.println("left is >= right so break");
				break;
			}
			else
			{
				swapValues(leftPointer, rightPointer);
			}
		}
		
		swapValues(leftPointer, right);
		
		return leftPointer;
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
