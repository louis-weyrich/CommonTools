/**
 * 
 */
package com.sos.tools.utilities.sort;

/**
 * @author louisweyrich
 *
 */
public class MergeSort extends AbstractSort {

	/**
	 * @param size
	 * @param start
	 * @param end
	 */
	public MergeSort(int size, int start, int end) {
		super(size, start, end);
	}

	/* (non-Javadoc)
	 * @see ads.sort.AbstractSort#sort()
	 */
	@Override
	public void sort() {
		int mid = (arraySize/2);
		int remaining = arraySize % mid;
		int index1 = 0;
		int index2 = mid+1;
		int mainIndex = 0;
		
		

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MergeSort sorter = new MergeSort(15, 0,100);
		sorter.printHorzArray(0, 14);
		sorter.sort();
		sorter.printHorzArray(0, 14);
		sorter.binarySearch(40);
	}

}
