/**
 * 
 */
package com.sos.tools.utilities.collection;

import java.util.Iterator;

/**
 * @author louis.weyrich
 *
 */
public class UtilIterator <E> implements Iterator<E> {
	
	protected E [] values = null;
	protected int index = 0;

	
	public UtilIterator(){} // Does nothing

	/**
	 * 
	 */
	public UtilIterator(E [] values) {
		this.values = values;
	}
	
	public void setValues(E [] values)
	{
		this.values = values;
	}

	public boolean hasNext() 
	{
		if(index < values.length)
		{
			return(values[index] != null);
		}
		
		return false;
	}

	public E next() {
		return values[index++];
	}

	public void remove() {
		@SuppressWarnings("unchecked")
		E [] tempValues = (E[])new Object[values.length - 1];
		if(index - 1 == 0)
		{
			System.arraycopy(values, index, tempValues, 0, values.length-1);
		}
		else
		{
			System.arraycopy(values, 0, tempValues, 0, index);
			System.arraycopy(values, index, tempValues, index, values.length-(index+1));
		}
		values = tempValues;
		--index;
	}

}
