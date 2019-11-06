/**
 * 
 */
package com.sos.tools.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sos.tools.exception.CloneCopyException;

/**
 * @author louis.weyrich
 *
 */
public abstract class CloneCopy extends Object
{

	public void shallowCopy(Object obj, boolean ignoreMissingFields) 
	throws CloneCopyException
	{
		Class <?> thisClass = this.getClass();
		
		Class <?> objClass = obj.getClass();
		Field [] fields = objClass.getDeclaredFields();
		
		for(Field field : fields)
		{

			try
			{
				String fieldName = field.getName();
				Class <?> fieldType = field.getType();
				String getMethodName = null;
				
				if(fieldType.getName().equalsIgnoreCase("boolean"))
					getMethodName = capName("is", fieldName);
				else
					getMethodName = capName("get", fieldName);
				
				String setMethodName = capName("set", fieldName);
				
				@SuppressWarnings("all")
				Method getMethod = objClass.getDeclaredMethod(getMethodName, null);
				Object value = getMethod.invoke(obj);
				
				Method setMethod = thisClass.getDeclaredMethod(setMethodName, getMethod.getReturnType());
				setMethod.invoke(this, value);
			
			}
			catch(NoSuchMethodException e)
			{
				if(!ignoreMissingFields)
					new CloneCopyException(e.getMessage(), e);
			} 
			catch (IllegalArgumentException e) 
			{
				if(!ignoreMissingFields)
					new CloneCopyException(e.getMessage(), e);
			} 
			catch (IllegalAccessException e) 
			{
				new CloneCopyException(e.getMessage(), e);
			} 
			catch (InvocationTargetException e) 
			{
				new CloneCopyException(e.getMessage(), e);
			} 
		}
	}
	

	private String capName(String preset, String name)
	{
		StringBuffer buffer = new StringBuffer(preset);
		
		char firstChar = name.charAt(0);
		firstChar = Character.toUpperCase(firstChar);
		buffer.append(firstChar).append(name.substring(1));
		
		return buffer.toString();
	}


}
