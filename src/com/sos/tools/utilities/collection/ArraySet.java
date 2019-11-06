package com.sos.tools.utilities.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArraySet <E> implements Set<E>
{

	public static final Object [] EMPTY_ARRAY = new Object[0];
	
	@SuppressWarnings("unchecked")
	private E [] arraySet = (E[]) EMPTY_ARRAY;
	private int size = 0;
	private boolean iterating = false;
	
	public ArraySet()
	{
		// Do Nothing
	}
	
	@SuppressWarnings("unchecked")
	public ArraySet(int initialSize)
	{
		arraySet = (E[]) new Object[initialSize];
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean contains(Object src)
	{
		if(src == null)
		{
			return true;
		}
		else
		{
			for(E e : arraySet)
			{
				if(e != null && e.equals(src) && e.hashCode() == src.hashCode())
				{
					return true;
				}
			}
			
			return false;
		}
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator <E> ()
		{
			int index = 0;

			@Override
			public boolean hasNext()
			{
				if(index < size)
				{
					return true;
				}
				else
				{
					iterating = false;
					return false;
				}
			}

			@Override
			public E next()
			{
				iterating = true;
				return arraySet[index++];
			}
	
		};
	}

	@Override
	public Object[] toArray()
	{
		return Arrays.copyOf(arraySet, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] array)
	{
		if (array.length < size)
			 array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
		else if (array.length > size)
			 array[size] = null;
		
		System.arraycopy(arraySet, 0, array, 0, size);
		return array;
	}
	
	public E get(int index)
	{
		if(index < size)
		{
			return arraySet[index];
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		if(iterating)
		{
			throw new ConcurrentModificationException("You can not modify a set while iterating.");
		}
		
		if(arraySet == EMPTY_ARRAY)
		{
			E [] tempNodes = (E[])new Object[1];
			tempNodes[size++] = e;
			arraySet = tempNodes;
			return true;
		}
		else
		{
			if(!this.contains(e) && e != null)
			{
				E [] tempNodes = null;
				
				if(arraySet.length <= size)
				{
					tempNodes = (E[])new Object[size  + 1];
				}
				else
				{
					tempNodes = (E[])new Object[arraySet.length];
				}
				
				System.arraycopy(arraySet, 0, tempNodes, 0, size);
				tempNodes[size++] = e;
				arraySet = tempNodes;
				return true;
			}
		}

		return false;
	}
	
	public int findIndex(Object obj)
	{
		int index = -1;
		
		for(int i = 0; i < arraySet.length; i++)
		{
			E e = arraySet[i];
			if(e.equals(obj))
			{
				index = i;
				break;
			}
		}
		
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object obj)
	{
		if(iterating)
		{
			throw new ConcurrentModificationException("You can not modify a set while iterating.");
		}
		
		int index = findIndex(obj);
		
		if(index > -1)
		{
			E [] tempArray = (E[])new Object[arraySet.length - 1];
			
			if(index == 0)
			{
				System.arraycopy(arraySet, 1, tempArray, 0, --size);
			}
			else
			{
				System.arraycopy(arraySet, 0, tempArray, 0, index);
				System.arraycopy(arraySet, index+1, tempArray, index, (--size) - index);
			}
			
			arraySet = tempArray;
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public E[] getDeltas(E [] array)
	{
		List <E> list = new UtilList <E> (array.length);
		
		for(E e : array)
		{
			if(!this.contains(e))
			{
				list.add(e);
			}
		}
		E [] returnArray = (E[])Array.newInstance(array.getClass().getComponentType(), list.size());
		
		return list.toArray(returnArray);
	}

	@Override
	public boolean containsAll(Collection<?> collection)
	{
		boolean containsAll = true;
		
		for(Object object : collection)
		{
			if(!this.contains(object) && containsAll)
			{
				containsAll = false;
				break;
			}
		}
		
		return containsAll;
	}

	
	public boolean containsAll(E [] array)
	{
		boolean containsAll = true;
		
		for(Object object : array)
		{
			if(!this.contains(object) && containsAll)
			{
				containsAll = false;
				break;
			}
		}
		
		return containsAll;
	}

	@Override
	public boolean addAll(Collection<? extends E> collection)
	{
		boolean all = true;
		
		for(E e : collection)
		{
			if(!add(e) && all)
			{
				all = false;
			}
		}
		
		return all;
	}
	
	public boolean addAll(E [] collection)
	{
		boolean all = true;
		
		for(E e : collection)
		{
			if(!add(e) && all)
			{
				all = false;
			}
		}
		
		return all;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> collection)
	{
		boolean retainAll = true;
		
		
		for(E einner : this.arraySet)
		{
			if(einner != null && !collection.contains(einner))
			{
				if(!remove(einner))
				{
					retainAll = false;
				}
			}
		}
		
		for(E eouter : (Collection<E>)collection)
		{
			if(!this.contains(eouter) && retainAll)
			{
				retainAll = false;
			}
		}
		
		return retainAll;
	}

	public boolean retainAll(E [] array)
	{
		boolean retainAll = true;
		List <E> list = new UtilList <E> (array);
		
		for(E einner : this.arraySet)
		{
			if(einner != null && !list.contains(einner))
			{
				if(!remove(einner))
				{
					retainAll = false;
				}
			}
		}
		
		for(E eouter : list)
		{
			if(!this.contains(eouter) && retainAll)
			{
				retainAll = false;
			}
		}
		
		return retainAll;
	}
	
	@Override
	public boolean removeAll(Collection<?> collection)
	{
		boolean removeAll = true;
		
		for(Object object : collection)
		{
			if(contains(object))
			{
				if(!remove(object))
				{
					removeAll = false;
				}
			}
			else
			{
				removeAll = false;
			}
		}
		
		return removeAll;
	}

	public boolean removeAll(E [] array)
	{
		boolean removeAll = true;
		
		for(E object : array)
		{
			if(contains(object))
			{
				if(!remove(object))
				{
					removeAll = false;
				}
			}
			else
			{
				removeAll = false;
			}
		}
		
		return removeAll;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear()
	{
		this.arraySet = (E[])EMPTY_ARRAY;
		this.size = 0;
	}

	public E popFromBottom()
	{
		E returnValue = arraySet[size-1];
		remove(returnValue);
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public E[] popFromBottom(int count, E [] array)
	{
		List <E> list = new UtilList <E> (count);
		
		for(int index = 0; index < count; index++)
		{
			list.add(popFromBottom());
		}
		
		E [] returnArray = (E[])Array.newInstance(array.getClass().getComponentType(), list.size());
		
		return list.toArray(returnArray);
	}

	public E popFromTop()
	{
		E returnValue = arraySet[0];
		remove(returnValue);
		return returnValue;
	}
}
