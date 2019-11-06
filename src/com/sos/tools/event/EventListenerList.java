package com.sos.tools.event;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.EventListener;


public class EventListenerList implements Serializable

{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3467617101094530666L;
	protected static final Object [] EMPTY_ARRAY = new Object [0];
	protected Object [] listenerList = EMPTY_ARRAY;
	protected int size = 0;
	protected int increaseSize = 1;
	

	public EventListenerList()
	{
		this(1);
	}
	
	public EventListenerList(int initialSize)
	{
		increaseArraySize(initialSize);
		size = initialSize;
	}
	
	
	protected void increaseArraySize(int size)
	{
		if(listenerList.length < (this.size + size) )
		{
			Object [] tempArray = new Object [this.size + size + this.increaseSize];
			
			if(listenerList.length > 0)
			{
				System.arraycopy(listenerList, 0, tempArray, 0, size);
			}
			
			listenerList = tempArray;
		}
	}

	public <T extends EventListener> void addListener(Class <T> t, T listener)
	{
		if(listener == null) return;
		
		if(!t.isInstance(listener))
		{
			throw new IllegalArgumentException();
		}
		
		increaseArraySize(1);
		listenerList[size++] = listener;
	}
	
	public int getListenerCount()
	{
		return size;
	}
	
	public int getListenerCount(Class <?> t)
	{
		int count = 0;
		
		for(int index = 0; index < size; index++)
		{
			if(t.isInstance(listenerList[index]))
			{
				count++;
			}
		}
		
		return count;
	}
	
	public Object [] getListenerList()
	{
		return listenerList;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EventListener> T[] getEventListeners(Class <T> c)
	{
		int count, f;
		EventListener[] result;
		count = getListenerCount(c);
		result = (EventListener[]) Array.newInstance(c, count);
		f = 0;
		for (int i = listenerList.length - 2; i >= 0; i -= 2)
		{
			if (listenerList[i] == c)
			{
				result[f++] = (EventListener) listenerList[i + 1];
			}
		}
		return (T[]) result;

	}
	
	public <T extends EventListener> void remove(Class<T> t)
	{
		int tempCount = this.getListenerCount(t);
		Object [] tempList = new Object [listenerList.length - tempCount];
		
		for(int index = 0, tempSize = 0; index < size; index ++)
		{
			if(!t.isInstance(listenerList[index]))
			{
				tempList[tempSize++] = listenerList[index];
			}
		}
		
		listenerList = tempList;
		size -= tempCount;
	}
	
	public <T extends EventListener> void remove(Class<T> t, T listener)
	{
		for(int index = 0; index < size; index++)
		{
			if(t.isInstance(listenerList[index]) && listenerList[index] == listener)
			{
				Object [] tempList = new Object [listenerList.length - 1];
				
				if(index > 0)
					System.arraycopy(listenerList, 0, tempList, 0, index);
				if(index < listenerList.length)
					System.arraycopy(listenerList, index+1, tempList, index, listenerList.length-1);
				
				listenerList = tempList;
			}
		}
	}

}
