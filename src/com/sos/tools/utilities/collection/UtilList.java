/**
 * 
 */
package com.sos.tools.utilities.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * @author lweyrich
 * 
 * This implementation of List has a comparator and a sort function.
 * It does not accept nulls for elements.
 * 
 * The sorting is done by a binary sorting algorithm.  if sort is set to true,
 * it will sort automatically.  If sort is set to false, you can call the sort 
 * method to sort at any time.
 * 
 * There is a default comparator that uses objects equals and hash code to 
 * compare objects.  You can provide your own implementation of comparator to
 * get the results you need.
 * 
 * 
 *
 */
public class UtilList<E> implements List<E>, Serializable, Cloneable, RandomAccess 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6610361039632414239L;
	private static final int DEFAULT_LOAD_FACTOR = 10;
	private static final int DEFAULT_INITIAL_SIZE = 0;
	
	@SuppressWarnings("unchecked")
	private E [] array = (E[]) new Object[0];
	private int loadFactor;
	private int size = 0;
	private boolean dirty = false;
	private boolean sort = false;
	private boolean sorted = true;
	private Comparator <E> comparator;

	/**
	 * 
	 */
	public UtilList() 
	{
		this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
	}
	
	public UtilList(E [] initialArray) 
	{
		this(initialArray, DEFAULT_LOAD_FACTOR, false, null);
	}
	
	public UtilList(E [] initialArray, boolean sort, Comparator <E> comparator) 
	{
		this(initialArray, DEFAULT_LOAD_FACTOR, sort, comparator);
	}

	public UtilList(E [] initialArray, int loadFactor, boolean sort, Comparator <E> comparator) 
	{
		this(initialArray.length, loadFactor);
		setSort(sort);
		setComparator(comparator);
		for(E e : initialArray)
		{
			add(e);
		}
	}
	
	public UtilList(Collection <E> collection, boolean sort, Comparator <E> comparator)
	{
		this(collection.size(), DEFAULT_LOAD_FACTOR);
		setSort(sort);
		setComparator(comparator);
		for(E e : collection)
		{
			add(e);
		}
	}

	/**
	 * 
	 */
	public UtilList(int initialSize) 
	{
		this(initialSize, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public UtilList(int initialSize, int loadFactor) 
	{
		setLoadFactor(loadFactor);
		testArray(initialSize);
		array = (E[]) Array.newInstance(array.getClass().getComponentType(), size);
	}
	
	protected void testArray(int count)
	{
		if(size + count >= array.length)
		{
			@SuppressWarnings("unchecked")
			E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), size+count+loadFactor);
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
		if(index == 0){
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
		
		sorted= true;
	}

	public void setLoadFactor(int loadFactor)
	{
		this.loadFactor = loadFactor;
	}
	
	public int getLoadFactor()
	{
		return this.loadFactor;
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

	public Comparator <E> getComparator()
	{
		if(comparator == null)
			comparator = new ObjectComparator <E> ();
		
		return comparator;
	}
	
	public void setComparator(Comparator <E> comparator)
	{
		this.comparator = comparator;
	}

	public boolean add(E element) 
	{
		boolean added = false;
		
		if(element != null)
		{
			if(sort && sorted)
			{
				int index = indexOfSorted(element);
				testArray(element, index);
			}
			else
			{
				testArray(1);
				array[size++] = element;
				dirty = true;
				sorted = false;
			}
			
			added = true;
		}
		
		return added;
		
	}

	public void add(int index, E element) 
	{
		if(index > size)
		{
			throw new ArrayIndexOutOfBoundsException(
					"index("+index+") is out or bounds: size("+size+")");
		}
		
		testArray(1);
		
		if(element != null)
		{
			
			if(index == 0)
			{
				System.arraycopy(array, 1, array, 0, size-1);
				
			}
			else
			{
				System.arraycopy(array, 0, array, 0, index);
				System.arraycopy(array, index,array, index+1, size-index);
			}
			
			array[index] = element;
			size++;
			dirty = true;
			sorted = false;
		}
		
	}

	public boolean addAll(Collection<? extends E> collection) 
	{
		boolean complete = false;
		
		if(collection != null){
			complete = true;
			//Remove null entries
			Iterator <?> iterator = collection.iterator();
			while(iterator.hasNext())
			{
				Object o = iterator.next();
				if(o == null)
				{
					iterator.remove();
					complete = false;
				}
			}
			
			//Check if array is large enough
			testArray(collection.size());
			
			//Add all elements
			if(sort && sorted)
			{
				for(E e : collection)
				{
					if(e != null){
						add(e);
					}
					else
					{
						complete = false;
					}
				}
			}
			else
			{
				for(E e : collection)
				{
					if(e != null){
						array[size++] = e;
					}
					else
					{
						complete = false;
					}
				}
				
				dirty = true;

			}
		}
		
		return complete;
	}

	@SuppressWarnings("unchecked")
	public boolean addAll(int index, Collection<? extends E> collection) 
	{
		boolean addedAll = false;
		
		if(collection != null)
		{
			addedAll = true;
			
			//Remove null entries
			Iterator <?> iterator = collection.iterator();
			while(iterator.hasNext())
			{
				Object o = iterator.next();
				if(o == null)
				{
					iterator.remove();
					addedAll = false;
				}
			}
			
			testArray(collection.size());
			
			iterator = collection.iterator();
			while(iterator.hasNext())
			{
				add(index++, (E)iterator.next());
			}
			
		}
		
		return addedAll;
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
		int index = (sort && sorted)?indexOfSorted((E)element):indexOf(element);
		return ((index > -1)?(getComparator().compare(array[index], (E)element) == 0):false);
	}

	public boolean containsAll(Collection<?> collection) 
	{
		for(Object element : collection)
		{
			if(!contains(element)) return false;
		}
		
		return true;
	}

	public E get(int index) 
	{
		if(index > size)
		{
			throw new ArrayIndexOutOfBoundsException(
				"index("+index+") is out or bounds: size("+size+")");
		}
		
		return array[index];
	}
	
	@SuppressWarnings("unchecked")
	public E [] getAllValues()
	{
		E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), size);
		System.arraycopy(array, 0, tempArray, 0, size);
		return tempArray;
	}

	@SuppressWarnings("unchecked")
	public int indexOf(Object element) 
	{
		if(element != null)
		{
			for(int index = 0; index < size; index++)
			{
				E e = array[index];
				if(e != null)
				{
					if(getComparator().compare(e, (E)element) == 0)
					{
						return index;
					}
				}
			}
		}
		
		return -1;
	}

	@SuppressWarnings("unchecked")
	protected int compareIndexOf(Object element) 
	{
		if(element != null){
			for(int index = 0; index < size; index++)
			{
				E e = array[index];
				if(e != null)
				{
					if(getComparator().compare((E)element, e) == 0)
					{
						return index;
					}
				}
			}
		}
		
		return -1;
	}

	public int indexOfSorted(E object)
	{
		int first = 0;
		int upto = size;
		
		 while (first < upto && object != null) 
		 {
		        int mid = (first + upto) / 2;  // Compute mid point.
		        E e = array[mid];
		        int value = -(getComparator().compare(object, e));
		        if (value == 1) 
		        {
		            upto = mid;     // repeat search in bottom half.
		        } 
		        else if(value == -1) 
		        {
		            first = mid + 1;  // Repeat search in top half.
		        } 
		        else 
		        {
		            return mid;     // Found it. return position
		        }
		    }
		 
		    return (first);    // Failed to find key

	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public Iterator<E> iterator() 
	{
		return new Iterator<E>()
		{
			
			private int currentIndex = -1;

			public boolean hasNext() 
			{
				return currentIndex+1 < size;
			}

			public E next() 
			{
				if(currentIndex+1 >= size)
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+currentIndex+") is out or bounds: size("+size+")");
				}
				
				return array[++currentIndex];
			}

			public void remove() 
			{
				@SuppressWarnings("unchecked")
				E [] tempArray = (E[]) (E[]) Array.newInstance(array.getClass().getComponentType(), array.length -1);
				if(currentIndex == 0)
				{
					System.arraycopy(array, 1, tempArray, 0, size -1);
				}
				else
				{
					System.arraycopy(array, 0, tempArray, 0, currentIndex);
					System.arraycopy(array, currentIndex+1, tempArray, currentIndex, size-currentIndex);
				}
				array = tempArray;
				size--;
			}
			
		};
	}

	@SuppressWarnings("unchecked")
	public int lastIndexOf(Object element) 
	{
		int index = -1;
		
		for(int i = size; i >= 0; i--)
		{
			E e = array[i];
			if(e != null)
			{
				if(getComparator().compare((E)element, e) == 0)
				{
					index = i;
					break;
				}
			}
		}
		
		return index;
	}

	public ListIterator<E> listIterator() 
	{
		return new ListIterator <E> ()
		{
			
			private int currentIndex = -1;

			@SuppressWarnings("unchecked")
			public void add(E element) 
			{
				if(currentIndex > size)
				{
					throw new ArrayIndexOutOfBoundsException(
							"index("+currentIndex+") is out or bounds: size("+size+")");
				}
				
				if(sort && sorted)
				{
					int index = indexOfSorted(element);
					testArray(element, index);
				}
				else
				{
					if(element != null)
					{
						int index = currentIndex + 1;
						E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), size+1+loadFactor);
						
						if(index == 0)
						{
							System.arraycopy(array, 1, tempArray, 0, size-1);
						}
						else
						{
							System.arraycopy(array, 0, tempArray, 0, index);
							System.arraycopy(array, index, tempArray, index+1, size-index);
						}
						
						tempArray[index] = element;
						array = tempArray;
						size++;
						dirty = true;
						sorted = false;
					}
					else
					{
						dirty = (dirty)?true:false;
					}	
				}
			}

			public boolean hasNext() 
			{
				return (currentIndex + 1) < size;
			}

			public boolean hasPrevious() 
			{
				return (currentIndex -1) > -1;
			}

			public E next() 
			{
				return array[++currentIndex];
			}

			public int nextIndex() 
			{
				return currentIndex+1;
			}

			public E previous() 
			{
				return array[--currentIndex];
			}

			public int previousIndex() 
			{
				return currentIndex-1;
			}
			
			@SuppressWarnings("unchecked")
			public void remove() 
			{
				if(currentIndex > -1 && currentIndex < size)
				{
					E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
					if(currentIndex == 0)
					{
						System.arraycopy(array, 1, tempArray, 0, size - 1);
					}
					else
					{
						System.arraycopy(array, 0, tempArray, 0, currentIndex);
						System.arraycopy(array, currentIndex+1, tempArray, currentIndex, size-currentIndex);
					}
					array = tempArray;
					size--;
				}
				else
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+currentIndex+") is out of bounds: size("+size+")");
				}
				
			}

			public void set(E element) 
			{
				if(currentIndex > size)
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+currentIndex+") is out of bounds: size("+size+")");
				}
				
				if(currentIndex < 0)
				{
					throw new ArrayIndexOutOfBoundsException(
							"index("+currentIndex+") is less than zero");
				}
				
				array[currentIndex] = element;
			}
			
		};
	}

	public ListIterator<E> listIterator(final int index) 
	{
		return new ListIterator <E> ()
		{
			
			int currentIndex = index;

			@SuppressWarnings("unchecked")			public void add(E element) 
			{
				if(currentIndex > size)
				{
					throw new ArrayIndexOutOfBoundsException(
							"index("+currentIndex+") is out or bounds: size("+(size-index)+")");
				}
				
				if(element != null)
				{
					int index = currentIndex + 1;
					E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), size+1+loadFactor);
					if(index == 0)
					{
						System.arraycopy(array, 1, tempArray, 0, size-1);
					}
					else
					{
						System.arraycopy(array, 0, tempArray, 0, index);
						System.arraycopy(array, index, tempArray, index+1, size-index);
					}
					tempArray[index] = element;
					array = tempArray;
					size++;
					dirty = true;
				}
				else
				{
					dirty = (dirty)?true:false;
				}		
			}

			public boolean hasNext() 
			{
				return (currentIndex + 1) < size;
			}

			public boolean hasPrevious() 
			{
				return (currentIndex -1) > index;
			}

			public E next() 
			{
				if(!hasNext())
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+(currentIndex-index)+") is out or bounds: size("+(size-index)+")");
				}
				
				return array[++currentIndex];
			}

			public int nextIndex() 
			{
				return currentIndex+1;
			}

			public E previous() 
			{
				if(!hasPrevious())
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+(currentIndex-index)+") is out or bounds: size("+(size-index)+")");
				}
				
				return array[--currentIndex];
			}

			public int previousIndex() 
			{
				return currentIndex-1;
			}
			
			@SuppressWarnings("unchecked")
			public void remove()
			{
				if(currentIndex > index && currentIndex < size)
				{
					E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
					
					if(currentIndex == 0)
					{
						System.arraycopy(array, 1, tempArray, 0, size - 1);
					}
					else
					{
						System.arraycopy(array, 0, tempArray, 0, currentIndex);
						System.arraycopy(array, currentIndex+1, tempArray, currentIndex, size-currentIndex);
					}
					array = tempArray;
					size--;
				}
				else
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+(currentIndex-index)+") is out of bounds: size("+(size-index)+")");
				}
				
			}

			public void set(E element) 
			{
				if(currentIndex > size)
				{
					throw new ArrayIndexOutOfBoundsException(
						"index("+currentIndex+") is out of bounds: size("+size+")");
				}
				
				if(currentIndex < 0)
				{
					throw new ArrayIndexOutOfBoundsException(
							"index("+currentIndex+") is less than zero");
				}
				
				array[currentIndex] = element;
			}
			
		};
	}

	@SuppressWarnings("unchecked")
	public boolean remove(Object element) 
	{
		boolean removed = false;
		int index = 0;
		index = (sort && sorted)?indexOfSorted((E)element):indexOf(element);
		if(index > -1)
		{
			removed = (remove(index) != null)?true:removed;
		}
		
		return removed;
	}

	public E remove(int index) 
	{
		E e = null;
		if(index > -1 && index < size)
		{
			e = array[index];
			@SuppressWarnings("unchecked")
			E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
			if(index == 0)
			{
				System.arraycopy(array, 1, tempArray, 0, size -1);
			}
			else
			{
				System.arraycopy(array, 0, tempArray, 0, index);
				System.arraycopy(array, index+1, tempArray, index, size-index);
			}
			array = tempArray;
			size--;
			dirty = true;
		}
		
		return e;
	}

	public boolean removeAll(Collection<?> collection) 
	{
		boolean complete = false;
		
		if(collection != null)
		{
			complete = true;
			for(Object o : collection)
			{
				if(o != null)
				{
					if(!remove(o))
					{
						complete = false;
					}
				}
				else
				{
					complete = false;
				}
			}
		}
		
		return complete;
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
			
			for(int index = 0; index < size; index++)
			{
				E e = array[index];
				boolean contains = collection.contains(e);
				
				if(!contains)
				{
					remove(e);
					index--;
				}
			}
		}
		
		return success;
	}

	public E set(int index, E element) 
	{
		if(index > size)
		{
			throw new ArrayIndexOutOfBoundsException(
				"index("+index+") is out of bounds: size("+size+")");
		}
		
		E e = array[index];
		array[index] = element;
		
		return e;
	}

	public int size() 
	{
		return size;
	}

	public List<E> subList(int startIndex, int endIndex) 
	{
		if(startIndex > endIndex)
		{
			throw new ArrayIndexOutOfBoundsException(
					"StartIndex("+startIndex+") is greater than endIndex("+endIndex+")");
		}
		
		if(startIndex < 0)
		{
			throw new ArrayIndexOutOfBoundsException(
					"startIndex("+startIndex+") is less than 0");
		}
		
		if(endIndex > size)
		{
			throw new ArrayIndexOutOfBoundsException(
					"endIndex("+endIndex+") is out of bounds: size("+size+")");
		}
		
		List <E> list = new ArrayList <E> (endIndex-startIndex +1);
		
		for(int index = startIndex; index <= endIndex; index++)
		{
			list.add(get(index));
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public E[] toArray() 
	{
		E [] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), size);
		System.arraycopy(array, 0, tempArray, 0, size);
		return tempArray;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] elementArray) 
	{
		if (elementArray.length < size)
			elementArray = (T[]) Array.newInstance(elementArray.getClass().getComponentType(), size);
		else if (elementArray.length > size)
			elementArray[size] = null;
		
		System.arraycopy(array, 0, elementArray, 0, size);
		return elementArray;
	}

	
	public void sort()
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
					if(getComparator().compare(e,current) > 0 && 
					getComparator().compare(e, largest) > 0)
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
	}
	
	private class ObjectComparator <T> implements Comparator<T> 
	{

		/**
		 * 
		 */
		public ObjectComparator() 
		{
			// Do Nothing
		}

		public int compare(T object1, T object2) 
		{
			
			int returnValue = 0;
			int hash1 = object1.hashCode();
			int hash2 = object2.hashCode();
			
			if(object1 == object2)
			{
				returnValue = 0;
			}
			else if(object1.equals(object2))
			{
				returnValue = 0;
			}
			else if(hash1 == hash2) 
			{
				returnValue = 0;
			}
			else if(hash1 < hash2) 
			{
				returnValue = -1;
			}
			else if(hash1 > hash2) 
			{
				returnValue = 1;
			}
			
			return returnValue;
		}

	}

}
