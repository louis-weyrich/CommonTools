/**
 * 
 */
package com.sos.tools.utilities.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author louis.weyrich
 *
 */
public class TemplateMap implements Map <String, Object>
{
	
	public static final String TEMPLATE_START = "${";
	public static final String TEMPLATE_END = "}";
	
	protected Map <String, Object>  variables;

	/**
	 * 
	 */
	public TemplateMap()
	{
		variables = new HashMap <String, Object> ();
	}

	public Object put(String name, Object value)
	{
		return variables.put(name, value);
	}
	
	public Object remove(Object name)
	{
		return variables.remove(name);
	}
	
	public String get(Object name)
	{
		if(variables.containsKey(name))
		{
			String value = variables.get(name).toString();
			if(value != null) 
			{
				if(value.indexOf(TEMPLATE_START) > -1)
				{
					int length = value.length();
					for(int index = 0; index < length; index++) 
					{
						if(index+1 < length-1)
						{
							String start = value.substring(index, index+1);
							if(start.equals("$") && (index +1) < length)
							{
								String next = value.substring(index+1, index+2);
								if(next.equals("{"))
								{
									int end = index+2;
									for(int endIndex = end; endIndex < length; endIndex++)
									{
										String endValue = value.substring(endIndex, endIndex+1);
										if(endValue.equals(TEMPLATE_END))
										{
											String variable = value.subSequence(index, endIndex+1).toString();
											String newValue = get(variable.substring(2,variable.length() -1));
											value = new StringBuffer()
												.append(value.substring(0, index))
												.append(newValue).append(value.substring(endIndex+1, length))
												.toString();
											length = value.length();
											index += (newValue.length()-1);
											break;
										}
									}
								
								}
							}
						}
					}
					
					return value;
				}
				else
				{
					return value;
				}
			}
			else
			{
				return "";
			}

		}
		else
		{
			return "";
		}
	}
	
	public Object getRaw(String name)
	{
		return variables.get(name);
	}

	public void clear()
	{
		variables.clear();
	}

	public boolean containsKey(Object key)
	{
		return variables.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		return variables.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet()
	{
		return variables.entrySet();
	}

	public boolean isEmpty()
	{
		return variables.isEmpty();
	}

	public Set<String> keySet()
	{
		return variables.keySet();
	}


	public void putAll(Map<? extends String, ? extends Object> map)
	{
		variables.putAll(map);
	}

	public int size()
	{
		return variables.size();
	}

	public Collection<Object> values()
	{
		return variables.values();
	}

}
