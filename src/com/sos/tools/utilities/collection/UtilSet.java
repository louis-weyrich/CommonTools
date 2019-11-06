/**
 * 
 */
package com.sos.tools.utilities.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import com.sos.tools.utilities.comparitor.ObjectComparator;

/**
 * @author lweyrich
 * @param <E>
 *
 */
public class UtilSet<E> implements Set<E> 
{
	
	
	private static final int DEFAULT_LOAD_FACTOR = 5;
	private static final int DEFAULT_INITIAL_SIZE = 5;
	
	@SuppressWarnings("unchecked")
	private E [] array = (E[]) new Object[0];
	private int loadFactor = DEFAULT_LOAD_FACTOR;
	private int size = 0;
	private boolean dirty = false;
	private boolean sort = false;
	private boolean sorted = true;
	private  Comparator <E> comparator;

	/**
	 * 
	 */
	public UtilSet() 
	{
		this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
	}


	/**
	 * 
	 */
	public UtilSet(boolean sort) 
	{
		this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
		this.setSort(sort);
	}
	

	/**
	 * 
	 */
	public UtilSet(boolean sort, Comparator <E> comparator) 
	{
		this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
		this.setComparator(comparator);
		this.setSort(sort);
	}



	/**
	 * 
	 */
	public UtilSet(int initialSize) 
	{
		this(initialSize, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * 
	 */
	public UtilSet(int initialSize, int loadFactor) 
	{
		setLoadFactor(loadFactor);
		testArray(initialSize);
	}

	/**
	 * 
	 */
	public UtilSet(E [] array, boolean sort) 
	{
		this(array, DEFAULT_LOAD_FACTOR, sort, null);
	}

	/**
	 * 
	 */
	public UtilSet(E [] array, int loadFactor, boolean sort, Comparator <E> comparator) 
	{
		setLoadFactor(loadFactor);
		setSort(sort);
		setComparator(comparator);
		testArray(array.length);
		for(E e : array){
			add(e);
		}
	}

	protected void testArray(int count)
	{
		if(size + count >= array.length)
		{
			@SuppressWarnings("unchecked")
			E [] tempArray = (E[]) new Object[size+count+loadFactor];
			if(size > 0)
			{
				System.arraycopy(array, 0, tempArray, 0, size);
			}
			
			array = tempArray;
		}
	}

	protected void testArray(E element, int index)
	{
		testArray(1);
		if(index <= 0)
		{
			System.arraycopy(array, 0, array, 1, size);
			array[0] = element;
			size++;
		}
		else
		{
			if(size > 0)
			{
				System.arraycopy(array, index, array, index+1, size-index);
			}
			array[index] = element;
			size++;
		}
	}

	public void setSort(boolean sort)
	{
		this.sort = sort;
		if(sort)sort();
	}
	
	public boolean isSort()
	{
		return sort;
	}
	
	public boolean isSorted()
	{
		return sorted;
	}
	
	public void setLoadFactor(int loadFactor)
	{
		this.loadFactor = loadFactor;
	}
	
	public int getLoadFactor()
	{
		return this.loadFactor;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	public boolean clearDirty()
	{
		boolean tempDirty = dirty;
		dirty = false;
		return tempDirty;
	}

	public boolean add(E element) 
	{
		boolean contains = true;
		
		if(element != null)
		{
			if(sort)
			{
				int index = indexOfSorted(element);
				//if(index < 0)testArray(element, -(index+1));
				testArray(element, index);
				contains = (index < 0);
				dirty = true;
				sorted = true;
				
			}
			else
			{
				contains = contains(element);
				
				if(!contains)
				{
					testArray(1);
					array[size++] = element;
					dirty = true;
				}
			}
		}
		
		return !contains;
	}

	public boolean addAll(Collection<? extends E> collection) 
	{
		boolean addAll = false;
		
		if(collection != null)
		{
			addAll = true;
			testArray(collection.size());
			
			for(E item : collection)
			{
				addAll = (add(item))?addAll:false;
			}
		}
		
		return addAll;
	}

	@SuppressWarnings("unchecked")
	public void clear() 
	{
		array = (E[]) new Object[0];
		size = 0;
		dirty = true;
		sorted = true;
	}

	@SuppressWarnings("unchecked")
	public boolean contains(Object element) 
	{
		boolean found = false;
		
		if(element != null)
		{
			if(sort && sorted)
			{
				int index = indexOfSorted((E)element);
				found = (index >= 0);
			}
			else
			{
				for(int index = 0; index < size; index++)
				{
					if(getComparator().compare(array[index],(E)element) == 0)
					{
						found = true;
						break;
					}
				}
			}
		}
		
		return found;
	}

	public boolean containsAll(Collection<?> collection) 
	{
		boolean foundAll = false;
		
		if(collection != null)
		{
			foundAll = true;
			
			for(Object object : collection)
			{
				foundAll = (contains(object)?foundAll:false);
			}
		}
		
		return foundAll;
	}

	public boolean isEmpty() 
	{
		return size > 0;
	}

	public Iterator<E> iterator() 
	{
		return new Iterator <E> ()
		{
			
			private int currentIndex = -1;

			public boolean hasNext() 
			{
				return currentIndex+1 < size;
			}

			public E next() 
			{
				return array[++currentIndex];
			}

			@SuppressWarnings("unchecked")
			public void remove() 
			{
				if(currentIndex > -1 && currentIndex < size)
				{
					E [] tempArray = (E[]) new Object[array.length-1];
					
					if(currentIndex == 0)
					{
						System.arraycopy(array, 1, tempArray, 0, size - 1);
					}
					else
					{
						System.arraycopy(array, 0, tempArray, 0, currentIndex);
						System.arraycopy(array, currentIndex+1, tempArray, currentIndex, size - currentIndex);
					}
					array = tempArray;
					size--;
					currentIndex--;
				}
				else
				{
					throw new ArrayIndexOutOfBoundsException(
						"Index("+currentIndex+
						") is out of bounds: size("+size+")");
				}
				
			}
			
		};
	}
	
	public int indexOf(Object object)
	{
		int index  = -1;
		
		if(object != null)
		{
			for(int i = 0; i < size; i++)
			{
				E e = array[i];
				if(e != null)
				{
					if(e.equals(object))
					{
						index = i;
						break;
					}
				}
			}
		}
		
		return index;
	}
	
	public int indexOfSorted(E object)
	{
		int first = 0;
		int upto = size;
		
		 while (first < upto) 
		 {
		        int mid = (first + upto) / 2;  // Compute mid point.
		        int value = (getComparator().compare(array[mid], object));
		        if (value == 1) 
		        {
		            upto = mid;     // repeat search in bottom half.
		        } 
		        else if (value == -1) 
		        {
		            first = mid + 1;  // Repeat search in top half.
		        } 
		        else 
		        {
		            return mid;     // Found it. return position
		        }
		    }
		 
		    return -(first+1);    // Failed to find key

	}

	@SuppressWarnings("unchecked")
	public boolean remove(Object object) 
	{
		int index = (sort && sorted)?indexOfSorted((E)object):indexOf(object);
		
		if(index > -1)
		{
			E [] tempArray = (E[]) new Object[array.length-1];
			
			if(index == 0)
			{
				System.arraycopy(array, 1, tempArray, 0, size - 1);
			}
			else
			{
				System.arraycopy(array, 0, tempArray, 0, index);
				System.arraycopy(array, index+1, tempArray, index, size - index);
			}
			array = tempArray;
			size --;
		}
		
		return (index > -1);
	}

	public boolean removeAll(Collection<?> collection) 
	{
		boolean removeAll = false;
		
		if(collection != null)
		{
			removeAll = true;
			for(Object object : collection)
			{
				removeAll = (remove(object))?removeAll:false;
			}
		}
		
		return removeAll;
	}

	public boolean retainAll(Collection<?> collection) 
	{
		boolean success = false;
		
		if(collection != null)
		{
			success = true;
			for(Object o : collection)
			{
				boolean contains = contains(o);
				success = (contains)?success:false;
			}
			
			for(E e : array)
			{
				boolean contains = collection.contains(e);
				if(!contains)
				{
					remove(e);
				}
			}
		}
		
		return success;
	}

	public int size() 
	{
		return size;
	}

	public Object[] toArray() 
	{
		return array;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] elementArray)
	{
		if (elementArray.length < size)
			elementArray = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
		else if (elementArray.length > size)
			elementArray[size] = null;
		
		System.arraycopy(array, 0, elementArray, 0, size);
		return elementArray;
	}
	
	public E [] sort()
	{
		boolean finished = false;
		int currentIndex = size;
		int largestIndex = currentIndex;
		E current = null;
		E largest = null;
		
		while(currentIndex > 0)
		{
			current = array[--currentIndex];
			largest = current;
			finished = true;
			
			for(int index = currentIndex; index > -1; index--)
			{
				E e = array[index];
				if(e != null && current != null && largest != null)
				{
					if(getComparator().compare(e,current) > 0 && getComparator().compare(e, largest) > 0)
					{
						largest = e;
						largestIndex = index;
						finished = false;
					}
				}
			}
			
			if(!finished)
			{
				array[largestIndex] = current;
				array[currentIndex] = largest;
			}
			
		}
		
		sorted = true;
		
		return array;
	}
	
	public Comparator <E> getComparator()
	{
		if(comparator == null)
		{
			comparator = new ObjectComparator <E> ();
		}
		
		return comparator;
	}
	
	public void setComparator(Comparator <E> comparator)
	{
		this.comparator = comparator;
	}
	
}
