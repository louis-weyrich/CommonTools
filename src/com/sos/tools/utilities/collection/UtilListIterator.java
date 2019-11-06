package com.sos.tools.utilities.collection;

import java.util.List;
import java.util.ListIterator;

public class UtilListIterator <E> implements ListIterator<E>
{
	
	@SuppressWarnings("unchecked")
	protected E [] array = (E[]) new Object [0];
	protected int index = 0, size = 0;
	

	public UtilListIterator()
	{
		// TODO Auto-generated constructor stub
	}

	public UtilListIterator(E [] array)
	{
		this.array = array;
		this.size = array.length;
	}

	@SuppressWarnings("unchecked")
	public UtilListIterator(List <E> list)
	{
		this.array = (E[])list.toArray();
		this.size = array.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(E e)
	{
		E [] tempArray = (E[]) new Object [++size];
		
		if(index > 0)
		{
			System.arraycopy(array, 0, tempArray, 0, index);
			System.arraycopy(array, index, tempArray, index+1, size - index);
		}
		
		array[index] = e;
		array = tempArray;
	}

	@Override
	public boolean hasNext()
	{
		return index < size;
	}

	@Override
	public boolean hasPrevious()
	{
		return index > 0;
	}

	@Override
	public E next()
	{
		return array[index++];
	}

	@Override
	public E previous()
	{
		index --;
		return array[--index];
	}

	@Override
	public void remove()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(E e)
	{
		array[index] = e;		
	}


	@Override
	public int nextIndex()
	{
		return index;
	}

	@Override
	public int previousIndex()
	{
		// TODO Auto-generated method stub
		return index - 1;
	}

}
