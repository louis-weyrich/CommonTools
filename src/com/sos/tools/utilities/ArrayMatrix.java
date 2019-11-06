package com.sos.tools.utilities;

import java.util.ArrayList;
import java.util.List;

public class ArrayMatrix {

	public static List<Integer> spiralOrder(int [][] array, int row, int column)
	{
		List <Integer> list = new ArrayList <Integer> ();
		
		int direction = 0, top = 0, right = column - 1, bottom = row - 1, left = 0;
		
		// direction = 0 left to right
		// direction = 1 top to bottom
		// direction = 2 right to left
		// direction = 3 bottom to top
		
		while(top <= bottom && left <= right)
		{
			if(direction == 0)
			{
				for(int i = left; i <= right; i++)
				{
					list.add(array[top][i]);
				}
				
				top++;
				direction =1;
			}
			else if(direction == 1)
			{
				for(int i = top; i <= bottom; i++)
				{
					list.add(array[i][right]);
				}
				
				right--;
				direction = 2;
			}
			else if(direction == 2)
			{
				for(int i = right; i >= left; i--)
				{
					list.add(array[bottom][i]);
				}
				
				bottom--;
				direction = 3;
			}
			else
			{
				for(int i = bottom; i >= top; i--)
				{
					list.add(array[i][left]);
				}
				
				left++;
				direction = 0;
			}
		}
		
		return list;
	}
	
	public static void main(String [] args)
	{
		int [][] matrix = 
		{
			{1,  2,  3,  4,  5},
			{16, 17, 18, 19, 6},
			{15, 24, 25, 20, 7},
			{14, 23, 22, 21, 8},
			{13, 12, 11, 10, 9}
		};
		
		List<Integer> list = ArrayMatrix.spiralOrder(matrix, 5, 5);
		System.out.println("List: "+list);
		
	}
}
