package com.sos.tools.utilities;

public class BinarySearch {

	public static int findPivot(int [] array)
	{
		int low = 0, high = array.length - 1, mid = 0, n = array.length - 1;
		int result = -1;
		
		while(low <= high)
		{
			mid = (high + low)/2;
			
			int next = (mid+1)%(n);
			int previous = (mid+ n -1)%(n);
			
			if((array[mid] <= array[next]) && (array[mid] <= array[previous]))
			{
				result = mid;
				break;
			}
			else if(array[low] <= array[high])
			{
				return low;
			}
			else if(array[mid] <= array[high])
			{
				high = mid -1;
			}
			else
			{
				low = mid + 1;
			}
			
		}
		
		return result;
	}
	
	public static int findElement(int [] array, int x)
	{
		int low = 0, high = array.length - 1, mid = 0;
		int result = -1;
		
		while(low <= high)
		{
			mid = (high + low)/2;
			
			if(array[mid] == x)
			{
				result = mid;
				low = high + 1;
			}
			else if(array[mid] <= array[high])
			{
				if( x > array[mid] && x <= array[high])
				{
					low = mid + 1;
				}
				else
				{
					high = mid - 1;
				}
			}
			else
			{
				if( x < array[mid] && x >= array[low])
				{
					high = mid - 1;
				}
				else
				{
					low = mid + 1;
				}
			}
			
		}
		
		return result;
	}
	
	public static int findCount(int [] array, int x, boolean firstOccurence)
	{
		int low = 0, high = array.length - 1;
		int result = -1;
		
		while(low <= high)
		{
			int mid = (high+low)/2;
			
			if(array[mid] == x)
			{
				if(firstOccurence)
				{
					high = mid - 1;
					result = mid;
				}
				else
				{
					low = mid + 1;
					result = mid +1;
				}
			}
			else if(array[mid] < x)
			{
				low = mid + 1;
			}
			else
			{
				high = mid - 1;
			}
		}
		
		return result;
	}
	
	public static void main(String [] args)
	{
		int [] array = {48, 50, 55, 58, 60, 73, 88, 90, 94, 99, 4, 6, 17, 21, 45}; 
		
		int pivotIndex = BinarySearch.findPivot(array);
		
		System.out.println("Pivot index = " + pivotIndex);
		
		int index = BinarySearch.findElement(array, 99);
		
		System.out.println("index for 99 = " + index);
		
		index = BinarySearch.findElement(array, 45);
		
		System.out.println("index for 45 = " + index);
		
		index = BinarySearch.findElement(array, 50);
		
		System.out.println("index for 50 = " + index);
		
		int [] array2 = {1, 1, 1, 5, 5, 8, 8, 8, 8, 10, 15, 15, 30, 35, 35, 35};
		
		int result1 = BinarySearch.findCount(array2, 8, true);
		int result2 = BinarySearch.findCount(array2, 8, false);
		
		System.out.println("count for 8 = " + (result2 - result1));
		
		result1 = BinarySearch.findCount(array2, 10, true);
		result2 = BinarySearch.findCount(array2, 10, false);
		
		System.out.println("count for 10 = " + (result2 - result1));

		result1 = BinarySearch.findCount(array2, 35, true);
		result2 = BinarySearch.findCount(array2, 35, false);
		
		System.out.println("count for 35 = " + (result2 - result1));
	}
}
