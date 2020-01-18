/**
 * 
 */
package com.sos.tools.utilities.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import com.sos.tools.utilities.comparitor.ObjectComparator;

/**
 * @author lweyrich
 * @param <V>
 *
 */
public class UtilMap<K, V> implements Map<K, V>
{
	
	public static final int DEFAULT_LOAD_FACTOR = 5;
	public static final int DEFAULT_INITIAL_SIZE = 5;

	
	private int size = 0;
	private int loadFactor = DEFAULT_LOAD_FACTOR;
	private boolean sort = false;
	private boolean sorted = true;
	private boolean dirty = false;
	private Comparator <K> keyComparator = null;
	private Comparator <V> valueComparator = null;
	
	@SuppressWarnings("unchecked")
	private K [] keys = (K[]) new Object[0];
	
	@SuppressWarnings("unchecked")
	private V [] values = (V[]) new Object[0];


	/**
	 * 
	 */
	public UtilMap() 
	{
		this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR, false);
	}

	/**
	 * 
	 */
	public UtilMap(boolean sort) 
	{
		this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR, sort);
	}

	/**
	 * 
	 */
	public UtilMap(int initialSize, boolean sort) 
	{
		this(initialSize, DEFAULT_LOAD_FACTOR, sort);
	}

	/**
	 * 
	 */
	public UtilMap(int initialSize, int loadFactor, boolean sort) 
	{
		setLoadFactor(loadFactor);
		setSort(sort);
		testArray(initialSize);
	}

	
	@SuppressWarnings("unchecked")
	protected int testArray(int count)
	{
		if(size + count >= keys.length)
		{
			K [] tempKeys = (K[]) new Object[size+count+loadFactor];
			V [] tempValues = (V[]) new Object[size+count+loadFactor];
			
			if(size > 0)
			{
				System.arraycopy(keys, 0, tempKeys, 0, size);
				System.arraycopy(values, 0, tempValues, 0, size);
			}
			
			keys = tempKeys;
			values = tempValues;
			
			return size+count+loadFactor;
		}
		else
		{
			return keys.length;
		}
		
		
	}
	
	protected void testArray(K key, V value, int index)
	{
		testArray(1);
		if(index == 0)
		{
			if(size > 0)
			{
				System.arraycopy(keys, 0, keys, 1, size);
				System.arraycopy(values, 0, values, 1, size);
			}
			
			keys[0] = key;
			values[0] = value;
			size++;
		}
		else
		{
			if(size > 0)
			{
				System.arraycopy(keys, index, keys, index+1, size-index);
				System.arraycopy(values, index, values, index+1, size-index);
			}
			keys[index] = key;
			values[index] = value;
			size++;
		}
	}
	
	public Comparator <K> getKeyComparator()
	{
		if(keyComparator == null)
			keyComparator = new ObjectComparator <K> ();
		
		return keyComparator;
	}
	
	public void setKeyComparator(Comparator <K> comparator)
	{
		this.keyComparator = comparator;
	}
	
	public void setKeyComparator(Comparator <K> comparator, boolean sort)
	{
		this.keyComparator = comparator;
		if(sort)this.sort();
	}

	public Comparator <V> getValueComparator()
	{
		if(valueComparator == null)
			valueComparator = new ObjectComparator <V> ();
		
		return valueComparator;
	}
	
	public void setValueComparator(Comparator <V> comparator)
	{
		this.valueComparator = comparator;
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
	
	public void clearDirty()
	{
		dirty = false;
	}
	
	public void setLoadFactor(int loadFactor)
	{
		this.loadFactor = loadFactor;
	}
	
	public int getLoadFactor()
	{
		return this.loadFactor;
	}

	@SuppressWarnings("unchecked")
	public void clear() 
	{
		keys = (K[]) new Object[0];
		values = (V[]) new Object[0];
		size = 0;
		dirty = false;
	}

	@SuppressWarnings("unchecked")
	public boolean containsKey(Object key) 
	{
		int index = ((sort && sorted)?indexOfSortedKey((K)key):indexOfKey((K)key));
		return index > -1;
	}

	@SuppressWarnings("unchecked")
	public boolean containsValue(Object value) 
	{
		boolean found = false;
		
		for(int index = 0; index < size; index++)
		{
			if(getValueComparator().compare(values[index], (V)value) == 0)
			{
				found = true;
				break;
			}
		}
		
		return found;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() 
	{
		Set <java.util.Map.Entry<K,V>> set = new UtilSet<java.util.Map.Entry<K,V>>(size);
		
		for(int index = 0; index < size; index++)
		{
			set.add(new MapEntry<K,V>(keys[index],values[index]));
		}
		
		return set;
	}
	
	@SuppressWarnings("unchecked")
	public int indexOfKey(Object key)
	{
		for(int index = 0; index < size; index++)
		{
			if(getKeyComparator().compare(keys[index], (K)key) == 0)
			{
				return index;
			}
		}
		
		return -1;
	}
	
	public int indexOfSortedKey(K object)
	{
		if(!sorted) return -1;
		
		int first = -1;
		int upto = size;
		
		 while (first < upto && upto > 0) 
		 {
			 int mid = (((first == -1)?0:first) + upto) / 2;  // Compute mid point.
		     int value = -1;
		     if(first > -1)
		     {
		        value = -(getKeyComparator().compare( object, keys[mid]));
		     }
		        
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
		 
		 return (first==-1)?0:first;    // Failed to find key
	}

	@SuppressWarnings("unchecked")
	public V get(Object key) 
	{
		int index = (sort && sorted)?indexOfSortedKey((K)key):indexOfKey(key);
		
		if(index > -1)
		{
			return values[index];
		}
		
		return null;
	}

	public boolean isEmpty() 
	{
		return !(size > 0);
	}

	public Set<K> keySet() 
	{
		return new UtilSet<K>(keys, sort);
	}

	public V put(K key, V value) 
	{
		V oldValue = null;
		
		if(sort && sorted)
		{
			int index = indexOfSortedKey((K)key);
			
		}
		else
		{
			int index = indexOfKey((K)key);
		}
		
		
		dirty = true;
		return oldValue;
	}

	public void putAll(Map<? extends K, ? extends V> map) 
	{
		testArray(map.size());
		
		for(Entry<? extends K, ? extends V> entry : map.entrySet())
		{
			put(entry.getKey(), entry.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	public V remove(Object key) 
	{
		int index = (sort && sorted)?indexOfSortedKey((K)key):indexOfKey(key);
		
		V removedValue = null;
		
		if(index > -1)
		{
			removedValue = values[index];
			
			K [] tempKeys = (K[]) new Object[keys.length-1];
			V [] tempValues = (V[]) new Object[values.length-1];
			
			if(index == 0)
			{
				System.arraycopy(keys, 1, tempKeys, 0, size -1);
				System.arraycopy(values, 1, tempValues, 0, size -1);
			}
			else
			{
				System.arraycopy(keys, 0, tempKeys, 0, index);
				System.arraycopy(values, 0, tempValues, 0, index);
				
				System.arraycopy(keys, index + 1, tempKeys, index, size - index);
				System.arraycopy(values, index + 1, tempValues, index, size - index);
			}
			
			keys = tempKeys;
			values = tempValues;
			size--;
		}
		
		return removedValue;
	}

	public int size() 
	{
		return size;
	}

	public Collection<V> values() 
	{
		return new UtilList <V> (values);
	}
	
	
	
	private class MapEntry <T, D> implements java.util.Map.Entry<K, V>
	{

		private K key;
		private V value;

		public MapEntry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		public K getKey() 
		{
			return key;
		}

		public V getValue() 
		{
			return value;
		}

		public V setValue(V newValue) 
		{
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
		
		public String toString()
		{
			return key.toString();
		}
		
		public int hashCode()
		{
			return key.hashCode();
		}
		
		public boolean equals(Object object)
		{
			return key.equals(object);
		}
		
	}

	public void sort()
	{
		boolean finished = false;
		int currentIndex = size;
		int largestIndex = currentIndex;
		K currentKey = null;
		K largestKey = null;
		V currentValue = null;
		V largestValue = null;
		
		while(currentIndex > 0)
		{
			currentKey = keys[--currentIndex];
			currentValue = values[currentIndex];
			largestKey = currentKey;
			largestValue = currentValue;
			finished = true;
			
			for(int index = currentIndex; index > -1; index--)
			{
				K k = keys[index];
				if(k != null && currentKey != null && largestKey != null)
				{
					if(getKeyComparator().compare(k,currentKey) > 0 && getKeyComparator().compare(k, largestKey) > 0)
					{
						largestKey = k;
						largestValue = values[index];
						largestIndex = index;
						finished = false;
					}
				}
			}
			
			if(!finished)
			{
				keys[largestIndex] = currentKey;
				keys[currentIndex] = largestKey;
				values[largestIndex] = currentValue;
				values[currentIndex] = largestValue;
			}
			
		}
		
		this.dirty = false;
		this.sorted = true;
		this.sort = true;
	}



}
