/**
 * 
 */
package com.sos.tools.configuration.data;

import java.util.Iterator;

import com.sos.tools.configuration.ConfigurationTypes;
import com.sos.tools.exception.ConfigurationException;
import com.sos.tools.utilities.collection.UtilList;

/**
 * @author louis.weyrich
 *
 */
@SuppressWarnings("rawtypes")
public class ListValue extends AbstractConfigurationValue<UtilList<Value>> implements TypedValue <Value>
{
	
	protected UtilList <Value> value;
	protected String typeString;
	
	
	public ListValue(String name)
	{
		super(name, ConfigurationTypes.LIST);
	}

	public UtilList<Value> getValue()
	{
		if(value == null)
		{
			value = new UtilList <Value> (4,4);
		}
		
		return value;
	}

	
	@Override
	public void setValue(UtilList<Value> value) 
	{
		this.value = value;		
	}

	public void addValue(Value value) 
	{
		getValue().add(value);		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<UtilList<Value>> getClassType() 
	{
		return (Class<UtilList<Value>>)getValue().getClass();
	}

	public Object find(Iterator<Object> iterator) throws ConfigurationException
	{

		if(iterator.hasNext())
		{
			String path = iterator.next().toString();
			if(iterator.hasNext())
			{
				String string = iterator.next().toString();
				throw new ConfigurationException(string+": mappedValues can not contain children");
			}
			else if(path.matches("[0-9]+"))
			{
				int index = Integer.parseInt(path);
				if(getValue().size() >= index)
				{
					return value.get(index).getValue();
				}
			}
			else
			{
				throw new ConfigurationException();
			}
		}
		
		return null;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;		
	}

}
