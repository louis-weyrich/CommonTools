package com.sos.tools.utilities.collection;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class GroupedList <T, V>
{	
	protected T [] typeArray = (T[]) new Object[0];
	protected V [] valueArray = (V[]) new Object[0];
	
	protected int size = 0, incrementSize = 10;

	/**
	 * 
	 */
	public GroupedList()
	{
		
	}
	
	/**
	 * 
	 * @param initialSize
	 * @param incrementSize
	 */
	public GroupedList(int initialSize, int incrementSize)
	{
		checkArrayLength(initialSize);
		this.incrementSize = incrementSize;
	}

	/**
	 * 
	 * @param count
	 */
	protected void checkArrayLength(int count)
	{
		if(typeArray.length < count+size)
		{
			T [] tempTypeArray = (T[]) new Object[size+count+incrementSize];
			V [] tempValueArray = (V[]) new Object[size+count+incrementSize];
			
			System.arraycopy(typeArray, 0, tempTypeArray, 0, size);
			System.arraycopy(valueArray, 0, tempValueArray, 0, size);
			
			typeArray = tempTypeArray;
			valueArray = tempValueArray;
		}
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public int getTypeCount(T type)
	{
		int result = 0;
		
		for(int index = 0; index < size; index++)
		{
			T t = typeArray[index];
			if(t.equals(type)) result++;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public List <Grouping<T,V>> getByValue(V value)
	{
		List <Grouping<T,V>> groupingList = new UtilList <Grouping<T,V>> ();
		
		for(int index = 0; index < size; index++)
		{
			if(valueArray[index].equals(value))
			{
				groupingList.add(new Grouping <T,V> (typeArray[index],valueArray[index]));
			}
		}
		
		return groupingList;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List <V> getByType(T type)
	{
		List <V> valueList = new UtilList <V> (getTypeCount(type));
		
		for(int index = 0; index < size; index++)
		{
			if(typeArray[index].equals(type))
			{
				valueList.add(valueArray[index]);
			}
		}
		
		return valueList;
	}
	
	/**
	 * 
	 * @return
	 */
	public List <T> getTypes()
	{
		List <T> types = new UtilList <T> ();
		
		for(int index = 0; index < size; index++)
		{
			if(!types.contains(typeArray[index]))
			{
				types.add(typeArray[index]);
			}
		}
		
		return types;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public Grouping <T,V> get(Integer index)
	{
		if(index > size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds: size="+size+" : index="+index);
		}
		
		T type = typeArray[index];
		V value = valueArray[index];
		
		return new Grouping <T,V> (type, value);
	}
	
	/**
	 * 
	 * @return
	 */
	public List <Grouping<T,V>> getAll()
	{
		List <Grouping<T,V>> list = new UtilList <Grouping<T,V>> (size);
		
		for(int index = 0; index < size; index++)
		{
			T t = typeArray[index];
			V v = valueArray[index];
			
			Grouping <T, V> group = new Grouping <T, V> (t,v);
			list.add(group);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public Iterator <Integer> indexesOf(T type)
	{
		List <Integer> intList = new UtilList <Integer> (getTypeCount(type));
		
		for(int index  = 0; index < size; index++)
		{
			if(typeArray[index].equals(type))
			{
				intList.add(new Integer(index));
			}
		}
		
		return  intList.iterator();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public Integer [] indexesOfValue(V value)
	{
		List <Integer> intList = new UtilList <Integer> ();
		
		for(int index  = 0; index < size; index++)
		{
			if(valueArray[index].equals(value))
			{
				intList.add(index);
			}
		}
		
		return (Integer[]) intList.toArray();
	}
	
	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public Integer indexOf(T type, V value)
	{
		
		for(int index  = 0; index < size; index++)
		{
			if(typeArray[index].equals(type) && valueArray[index].equals(value))
			{
				return index;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public boolean contains(T type, V value)
	{
		return indexOf(type,value) > -1;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean containsType(T type)
	{
		List <T> types = getTypes();
		return types.contains(type);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * 
	 * @param size
	 */
	public void setSize(int size)
	{
		this.size = size;
	}

	/**
	 * 
	 * @return
	 */
	public int getIncrementSize()
	{
		return incrementSize;
	}

	/**
	 * 
	 * @param incrementSize
	 */
	public void setIncrementSize(int incrementSize)
	{
		this.incrementSize = incrementSize;
	}

	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public boolean add(T type, V value)
	{
		boolean contains = contains(type, value);
		
		if(!contains)
		{
			checkArrayLength(1);
			typeArray[size] = type;
			valueArray[size++] = value;
		}
		
		return !contains;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean remove(Integer index)
	{
		if(index > -1 && index < size)
		{
			T [] tempTypeArray = (T []) new Object [typeArray.length - 1];
			V [] tempValueArray = (V []) new Object [valueArray.length - 1];
			
			if(index == 0)
			{
				System.arraycopy(typeArray, 1, tempTypeArray, 0, size - 1);
				System.arraycopy(valueArray, 1, tempValueArray, 0, size - 1);
			}
			else
			{
				System.arraycopy(typeArray, 0, tempTypeArray, 0, index);
				System.arraycopy(valueArray, 0, tempValueArray, 0, index);
				System.arraycopy(typeArray, index+1, tempTypeArray, index, size-index);
				System.arraycopy(valueArray, index+1, tempValueArray, index, size-index);
				
				typeArray = tempTypeArray;
				valueArray = tempValueArray;
			}
			
			size--;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public boolean remove(T type, V value)
	{
		Integer index = indexOf(type, value);
		
		if(index > -1)
		{
			return remove(index);
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean removeByType(T type)
	{
		boolean removed = false;
		
		for(int index = 0; index < size; index++)
		{
			if(typeArray[index].equals(type))
			{
				removed = remove(index);
				if(removed) index--;
			}
		}
		
		return removed;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean removeByValue(V value)
	{
		boolean removed = false;
		
		for(int index = 0; index < size; index++)
		{
			if(valueArray[index].equals(value))
			{
				removed = remove(index);
				if(removed) index--;
			}
		}
		
		return removed;
	}

	/**
	 * 
	 * @return
	 */
	public Grouping<T,V> createNewGrouping()
	{
		return new Grouping <T,V> ();
	}
	
	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public Grouping<T,V> createNewGrouping(T type, V value)
	{
		return new Grouping <T,V> (type, value);
	}

	/**
	 * 
	 * @author lweyrich
	 *
	 * @param <A>
	 * @param <B>
	 */
	public static class Grouping <A,B> implements Serializable, Comparable <A>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3133417632235786582L;
		private A type;
		private B value;
		
		/**
		 * 
		 */
		public Grouping()
		{
			
		}
		
		/**
		 * 
		 * @param type
		 * @param value
		 */
		public Grouping(A type, B value)
		{
			setType(type);
			setValue(value);
		}

		/**
		 * 
		 * @return
		 */
		public A getType()
		{
			return type;
		}

		/**
		 * 
		 * @param type
		 */
		public void setType(A type)
		{
			this.type = type;
		}

		/**
		 * 
		 * @return
		 */
		public B getValue()
		{
			return value;
		}

		/**
		 * 
		 * @param value
		 */
		public void setValue(B value)
		{
			this.value = value;
		}

		/**
		 * 
		 */
		public int compareTo(A type)
		{
			if(this.type.equals(type))
			{
				return 0;
			}
			else if(this.type.hashCode() > type.hashCode())
			{
				return 1;
			}
			else if(this.type.hashCode() < type.hashCode())
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
		
		/**
		 * 
		 */
		public boolean equals(Object obj)
		{
			return type.equals(obj);
		}
		
		/**
		 * 
		 */
		public int hashCode()
		{
			return type.hashCode();
		}
	}

}



