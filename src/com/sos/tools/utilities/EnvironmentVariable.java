package com.sos.tools.utilities;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentVariable
{
	
	private static EnvironmentVariable instance;
	private Map <String, Object> variables;

	private EnvironmentVariable()
	{
		variables = new HashMap <String, Object> ();
	}
	
	public static EnvironmentVariable instance()
	{
		if(instance == null)
		{
			instance = new EnvironmentVariable();
		}
		
		return instance;
	}
	
	public int size()
	{
		return variables.size();
	}
	
	public <T> T get(String key, Class<T> type)
	{
		if(variables.containsKey(key))
		{
			return type.cast(variables.get(key));
		}
		else
		{
			return null;
		}
	}
	
	public <T> T put(String key, T variable, Class<T> type)
	{
		Object obj = variables.put(key, variable);
		return type.cast(obj);
	}
	
	public <T> T replace(String key, T variable, Class<T> type)
	{
		Object obj = variables.replace(key, variable);
		return type.cast(obj);
	}
	
	public <T> T remove(String key, Class<T> type)
	{
		Object obj = variables.remove(key);
		return type.cast(obj);
	}
	
	public void clear()
	{
		variables.clear();
	}
	
	public <T> boolean containsKey(String key, Class<T> type)
	{
		if(variables.containsKey(key))
		{
			Object obj = variables.get(key);
			return type == obj.getClass();
		}
		else
		{
			return false;
		}
	}
	
	public boolean containsKey(String key)
	{
		return(variables.containsKey(key));
	}

	public boolean containsValue(Object variable)
	{
		return(variables.containsValue(variable));
	}

}
