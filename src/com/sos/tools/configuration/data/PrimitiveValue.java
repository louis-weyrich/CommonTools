/**
 * 
 */
package com.sos.tools.configuration.data;

import java.util.Iterator;

import com.sos.tools.configuration.ConfigurationTypes;

/**
 * @author louis.weyrich
 *
 */
public abstract class PrimitiveValue <V> extends AbstractConfigurationValue<V> 
{
	
	protected V value;

	

	public PrimitiveValue(String name) 
	{
		super(name, ConfigurationTypes.PRIMITIVE);
	}

	public PrimitiveValue(String name, String value) 
	{
		super(name, ConfigurationTypes.PRIMITIVE);
		this.setValue(convertValue(value));
	}
		
	public V getValue()
	{
		return value;
	}
	
	@Override
	public void setValue(V value)
	{
		this.value = value;
	}
	
	public void setValueAsString(String value)
	{
		setValue(convertValue(value));
	}
	
	public abstract V convertValue(String value);

	public Object find(Iterator <Object> iterator)
	{
		return getValue();
	}
}
