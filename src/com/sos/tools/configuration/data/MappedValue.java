package com.sos.tools.configuration.data;

import java.util.Iterator;

import com.sos.tools.configuration.ConfigurationTypes;
import com.sos.tools.exception.ConfigurationException;
import com.sos.tools.utilities.collection.UtilMap;

@SuppressWarnings("rawtypes")
public class MappedValue extends AbstractConfigurationValue<UtilMap<String, NamedValue>> 
implements TypedValue <NamedValue>
{
	
	protected UtilMap <String, NamedValue> valueMap;
	protected String typeString;
	
	
	public MappedValue(String name)
	{
		super(name, ConfigurationTypes.MAP);
	}

	public UtilMap<String, NamedValue> getValue()  
	{
		if(valueMap == null)
		{
			valueMap = new UtilMap<String, NamedValue> (false);
		}
		
		return valueMap;
	}

	@Override
	public void setValue(UtilMap<String, NamedValue> value) 
	{
		this.valueMap = value;
	}
	
	public void addValue(NamedValue nv)
	{
		getValue().put(nv.getName(), nv);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<UtilMap<String, NamedValue>> getClassType() {
		return (Class<UtilMap<String, NamedValue>>)valueMap.getClass();
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public Object find(Iterator<Object> iterator) throws ConfigurationException {
		if(iterator.hasNext())
		{
			String path = iterator.next().toString();
			if(iterator.hasNext())
			{
				throw new ConfigurationException("mappedValues can not contain children");
			}
			else if(getValue().containsKey(path))
			{
				return getValue().get(path).getValue();
			}
		}
		
		return null;
	}

}
