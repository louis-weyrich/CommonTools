package com.sos.tools.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PropertiesMap extends Properties
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2174132088902961957L;

	/**
	 * The default placeHolder value
	 */
	public static final String PLACE_HOLDER = "\\[[a-zA-Z0-9._-]+\\]";
	
	/**
	 * The placeHolder member.
	 */
	private String placeHolder;

	
	
	public PropertiesMap()
	{
		setPlaceHolder(PLACE_HOLDER);
	}
	
	public PropertiesMap(String placeholder, Properties defaults)
	{
		super(defaults);
		setPlaceHolder(placeholder);
	}

	/**
	 * 
	 * 
	 * @return placeHolder returns the current value of the placeHolder variable.
	 */
	public String getPlaceHolder() {
		return placeHolder;
	}

	/**
	 * Sets
	 * 
	 * @param placeHolder the placeHolder to set as a regular expression.
	 */
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}
	
	@Override
	public Object get(Object key)
	{
		Object property = null;
		
		if(key != null)
		{
			if(key instanceof String)
			{
				String keyString = (String)key;
				if(keyString.contains("."))
				{
					int index = keyString.indexOf(".");
					String mainKey = keyString.substring(0, index);
					Object mainObject = super.get(mainKey);
					
					if(mainObject != null)
					{
						String remaining = keyString.substring(index+1, keyString.length());
						if(remaining.contains("."))
						{
							try
							{
								property = reflectMethod(mainObject, getMethodIterator(remaining));
							}
							catch (NoSuchFieldException | SecurityException
							| IllegalAccessException | IllegalArgumentException
							| NoSuchMethodException | InvocationTargetException e)
							{
								
							}
						}
						else
						{
							try
							{
								property = reflectMethod(mainObject, remaining);
							} 
							catch (NoSuchFieldException | SecurityException
							| IllegalAccessException| IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException e)
							{
								System.out.println(e.getMessage());
							}
						}
					}
				}
				else
				{
					property = super.get(key);
				}
			}
			else
			{
				property = super.get(key);
			}
		}
		
		if(property != null){
			String propertyString = property.toString();
			
			if(propertyString.contains("[") && propertyString.contains("]"))
			{
				Pattern pattern = Pattern.compile(getPlaceHolder());
				Matcher matcher = pattern.matcher(propertyString);
				
				while(matcher.find())
				{
					String group = matcher.group();
					String groupProperty = group.substring(1, group.length() - 1);
					
					Object innerProperty = get(groupProperty);
					if(innerProperty != null)
					{
						propertyString = propertyString.replace(group, innerProperty.toString());
					}
				}
				
				property = propertyString;
			}
		}

		
		return property;
	}
	
		
	protected Object reflectMethod(Object object, Iterator <String> methodList) 
	throws NoSuchFieldException, SecurityException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		Object value = null;
		
		while(methodList.hasNext())
		{
			String field = methodList.next();
			value = reflectMethod(object, field);
		}
		
		return value;
	}
	
	
	protected Object reflectMethod(Object object, String fieldName) 
	throws NoSuchFieldException, SecurityException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException
	{
		Object value = null;
		
		
		if(object != null && fieldName != null)
		{
			String methodName = null;
			
			Field field = object.getClass().getField(fieldName);
			if(field.getType() ==Boolean.class)
			{
				char firstChar = fieldName.charAt(0);
				firstChar = Character.toUpperCase(firstChar);
				methodName = "is"+firstChar+fieldName.substring(1, fieldName.length());
			}
			else
			{
				char firstChar = fieldName.charAt(0);
				firstChar = Character.toUpperCase(firstChar);
				methodName = "get"+firstChar+fieldName.substring(1, fieldName.length());
			}
			
			Class<?> [] emptyClass = new Class<?>[0];
			Object [] emptyObject = new Object[0];
			Method method;
			try
			{
				method = object.getClass().getMethod(methodName, emptyClass);
				value = method.invoke(object, emptyObject);
			}
			catch (NoSuchMethodException e)
			{
				char firstChar = fieldName.charAt(0);
				firstChar = Character.toUpperCase(firstChar);
				methodName = "is"+firstChar+fieldName.substring(1, fieldName.length());
				method = object.getClass().getMethod(methodName, emptyClass);
				value = method.invoke(object, emptyObject);
			}
		}
		
		return value;
	}

	protected Iterator <String> getMethodIterator(String key)
	{
		int index = key.indexOf(".");
		List <String> methodList = new ArrayList <String> ();
		
		while(index > -1 || key != null)
		{
			if(index > -1)
			{
				String methodName = key.substring(0, index);
				key = key.substring(index+1);
				methodList.add(methodName);
				index = key.indexOf(".");
			}
			else
			{
				methodList.add(key);
				key = null;
			}
		}
		
		return methodList.iterator();
	}

}
