/**
 * 
 */
package com.sos.tools.utilities.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lweyrich
 *
 */
public class MappedLists <K,V> 
{

	private UtilMap <K, List<V>> map;
	
	public MappedLists() 
	{
		map = new UtilMap <K, List<V>> ();
	}

	public void clear() 
	{
		map.clear();
	}

	public boolean containsKey(Object key) 
	{
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) 
	{
		Iterator <K> keys = map.keySet().iterator();
		while(keys.hasNext())
		{
			Iterator <V> values = map.get(keys.next()).iterator();
			while(values.hasNext())
			{
				V v = values.next();
				if(v != null)
				{
					if(v.equals(value))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}


	public boolean containsValue(K key, Object value) 
	{
		if(map.containsKey(key))
		{
			Iterator <V> values = map.get(key).iterator();
			while(values.hasNext())
			{
				V v = values.next();
				if(v != null)
				{
					if(v.equals(value))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}

	public Set<java.util.Map.Entry<K, List<V>>> entrySet() 
	{
		return map.entrySet();
	}

	public List<V> get(Object key) 
	{
		if(containsKey(key))
		{
			return map.get(key);
		}
		else
		{
			return null;
		}
	}

	public boolean isEmpty() 
	{
		return map.isEmpty();
	}

	public Set<K> keySet() 
	{
		return map.keySet();
	}

	public V put(K k, V v)
	{
		V value = v;
		if(map.containsKey(k))
		{
			List <V> list = map.get(k);
			if(list.contains(v))
			{
				int index = list.indexOf(v);
				value = list.get(index);
			}
			list.add(v);
		}
		else
		{
			List <V> list = new ArrayList <V> ();
			list.add(v);
			map.put(k, list);
		}
		
		return value;
	}

	public void putAll(Map<? extends K, ? extends List<V>> newMap) 
	{
		map.putAll(newMap);	
	}

	public List<V> removeKey(K key) 
	{
		return map.remove(key);
	}


	public V removeValue(V value) 
	{
		Iterator <K> keys = keySet().iterator();
		while(keys.hasNext())
		{
			List <V> values = map.get(keys.next());
			if(values.contains(value))
			{
				int valueIndex = values.indexOf(value);
				value = values.remove(valueIndex);
			}
		}
		
		return value;
	}

	
	public int keySize() 
	{
		return map.size();
	}
	
	public int size()
	{
		return map.size();
	}
	
	public int valueSize(K key)
	{
		int valueSize = 0;
		
		if(map.containsKey(key))
		{
			valueSize = map.get(key).size();
		}
		
		return valueSize;
	}

	public int valuesSize()
	{
		int count = 0;
		
		Iterator <K> keys = keySet().iterator();
		while(keys.hasNext())
		{
			count += valueSize(keys.next());
		}
		
		return count;
	}
	
	public List <V> allValues() 
	{
		List <V> list = new ArrayList <V> (valuesSize());
		
		Iterator <K> keys = keySet().iterator();
		while(keys.hasNext())
		{
			K k = keys.next();
			List <V> values = map.get(k);
			Iterator <V> iterator = values.iterator();
			while(iterator.hasNext())
			{
				list.add(iterator.next());
			}
		}
		
		return list;
	}
	
	public List<V> values(K key)
	{
		return map.get(key);
	}

}
