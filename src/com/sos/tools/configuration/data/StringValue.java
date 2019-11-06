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
public class StringValue extends AbstractConfigurationValue<String>
{

	protected String value;
	
	
	/**
	 * @param name
	 * @param valueMap
	 */
	public StringValue(String name) 
	{
		super(name, ConfigurationTypes.STRING);
	}

	/**
	 * @param name
	 * @param value
	 */
	public StringValue(String name, String value) 
	{
		super(name, value, ConfigurationTypes.STRING);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<String> getClassType() 
	{
		return String.class;
	}

	public String getValue() 
	{
		return value;
	}

	@Override
	public void setValue(String value) 
	{
		this.value = value;		
	}

	public Object find(Iterator<Object> iterator) {
		return getValue();
	}

}
