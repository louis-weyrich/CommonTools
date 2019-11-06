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
 * @author louis.weyrich
 *
 */
@SuppressWarnings("unchecked")
public class MappedMaps <K, IK, V>
{
	
	private static final Object [] EMPTY_KEY_ARRAY = new Object[0];
	private final Map <IK,V> [] EMPTY_MAP = (Map<IK,V> []) new Map[0];
	
	protected K [] keys = (K[])EMPTY_KEY_ARRAY;
	protected Map <IK,V> [] mappedValues = EMPTY_MAP;
	private Comparator <K> keyComparator = null;
	protected int size;
	protected int loadFactor = UtilMap.DEFAULT_LOAD_FACTOR;
	protected int innerInitSize; 
	protected int innerLoadFactor;
	protected boolean sort;
	
	
	/**
	 * 
	 */
	public MappedMaps()
	{
		this(UtilMap.DEFAULT_INITIAL_SIZE, UtilMap.DEFAULT_LOAD_FACTOR, 1, UtilMap.DEFAULT_LOAD_FACTOR, false);
	}
	
	/**
	 * 
	 * @param initSize
	 */
	public MappedMaps(int initSize)
	{
		this(initSize, UtilMap.DEFAULT_LOAD_FACTOR, 1, UtilMap.DEFAULT_LOAD_FACTOR, false);
	}
	
	/**
	 * 
	 * @param initSize
	 * @param loadFactor
	 */
	public MappedMaps(int initSize, int loadFactor)
	{
		this(initSize, loadFactor, 1, UtilMap.DEFAULT_LOAD_FACTOR, false);
	}
	
	/**
	 * 
	 * @param initSize
	 * @param loadFactor
	 * @param innerInitSize
	 * @param innerLoadFactor
	 */
	public MappedMaps(int initSize, int loadFactor, int innerInitSize, 
	int innerLoadFactor)
	{
		this(initSize, loadFactor, innerInitSize, innerLoadFactor, false);
	}
	
	/**
	 * 
	 * @param initSize
	 * @param loadFactor
	 * @param innerInitSize
	 * @param innerLoadFactor
	 * @param sort
	 */
	public MappedMaps(int initSize, int loadFactor, int innerInitSize, 
	int innerLoadFactor, boolean sort)
	{
		this.loadFactor = loadFactor;
		this.innerInitSize = innerInitSize;
		this.innerLoadFactor = innerLoadFactor;
		testArraySize(initSize);
	}
	
	/**
	 * 
	 * @param newSize
	 */
	protected void testArraySize(int newSize)
	{
		if(size + newSize >= keys.length)
		{
			K [] tempKeys = (K[]) new Object[size+newSize+loadFactor];
			Map <IK,V> [] tempValues = (Map<IK,V> []) new Map [size+newSize+loadFactor];
			
			
			if(size > 0)
			{
				System.arraycopy(keys, 0, tempKeys, 0, size);
				System.arraycopy(mappedValues, 0, tempValues, 0, size);
			}
			
			keys = tempKeys;
			mappedValues = tempValues;

		}
	}

	/**
	 * 
	 * @return
	 */
	public int size() 
	{
		return size;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() 
	{
		return size > 0;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key) 
	{
		int index = indexOfKey((K)key);
		return index  > -1;
	}

	public boolean containsInnerKey(K key, IK innerKey) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index].containsKey(innerKey);
		}
		else
		{
			return false;
		}
	}

	
	protected int indexOfKey(Object key)
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

	/**
	 * 
	 * @param key
	 * @param innerKey
	 * @param value
	 * @return
	 */
	public boolean containsInnerValue(K key, V value) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			boolean found = false;
			Collection <V> values = mappedValues[index].values();
			
			for(V v : values)
			{
				if(value.equals(v))
				{
					found = true;
					break;
				}
			}
			
			return found;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @param key
	 * @param innerKey
	 * @param value
	 * @return
	 */
	public boolean containsInnerValue(K key, IK innerKey, V value) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return value.equals(mappedValues[index].get(innerKey));
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Map<IK,V> get(K key) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index];
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param innerKey
	 * @return
	 */
	public V getInner(K key, IK innerKey)
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index].get(innerKey);
		}
		else
		{
			return null;
		}
	}

	/**
	 * 
	 * @param key
	 * @param innerKey
	 * @param value
	 * @return
	 */
	public V putInner(K key, IK innerKey, V value) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index].put(innerKey, value);
		}
		else
		{
			this.testArraySize(1);
			mappedValues[size] = new UtilMap <IK,V> (innerInitSize, innerLoadFactor, sort);
			mappedValues[size].put(innerKey, value);
			keys[size] = key;
			size++;
			
			return value;
		}
	}

	public Map<IK,V> put(K key, Map<IK,V> map) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			Map<IK,V> existingMap = mappedValues[index];
			mappedValues[index] = map;
			return existingMap;
		}
		else
		{
			this.testArraySize(1);
			mappedValues[size] = map;
			keys[size] = key;
			size++;
			return map;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Map<IK,V> remove(K key) 
	{
		int index = indexOfKey((K)key);
		
		Map<IK,V> removedMap = null;
		
		if(index > -1)
		{
			removedMap = mappedValues[index];
			
			K [] tempKeys = (K[]) new Object[keys.length-1];
			Map<IK,V> [] tempValues = (Map<IK,V> []) new Map[mappedValues.length-1];
			
			if(index == 0)
			{
				System.arraycopy(keys, 1, tempKeys, 0, size -1);
				System.arraycopy(mappedValues, 1, tempValues, 0, size -1);
			}
			else
			{
				System.arraycopy(keys, 0, tempKeys, 0, index);
				System.arraycopy(mappedValues, 0, tempValues, 0, index);
				
				System.arraycopy(keys, index + 1, tempKeys, index, size - index);
				System.arraycopy(mappedValues, index + 1, tempValues, index, size - index);
			}
			
			keys = tempKeys;
			mappedValues = tempValues;
			size--;
		}
		
		return removedMap;
	}

	/**
	 * 
	 * @param key
	 * @param innerKey
	 * @return
	 */
	public V removeInner(K key, IK innerKey) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index].remove(innerKey);
		}
		else
		{
			return null;
		}
	}


	/**
	 * 
	 * @param key
	 * @param m
	 */
	public void putAll(K key, Map<? extends IK, ? extends V> m) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			mappedValues[index].putAll(m);
		}
			
	}

	/**
	 * 
	 */
	public void clear() 
	{
		keys = (K[]) new Object[0];
		mappedValues = (Map<IK,V> []) new Map[0];
		size = 0;
	}

	/**
	 * 
	 * @return
	 */
	public Set<K> keySet() 
	{
		return new UtilSet<K>(keys, sort);
	}

	public Set<IK> keySetInner(K key) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index].keySet();
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Collection <Map<IK, V>> values() 
	{
		return new UtilList <Map<IK, V>> (mappedValues);
	}
	
	public Collection<V> valuesInner(K key) 
	{
		int index = indexOfKey((K)key);
		
		if(index > -1)
		{
			return mappedValues[index].values();
		}
		else
		{
			return null;
		}
	}

	
	/**
	 * 
	 * @return
	 */
	public Comparator <K> getKeyComparator()
	{
		if(keyComparator == null)
			keyComparator = new ObjectComparator <K> ();
		
		return keyComparator;
	}
	
	/**
	 * 
	 * @param comparator
	 */
	public void setKeyComparator(Comparator <K> comparator)
	{
		this.keyComparator = comparator;
	}

}
