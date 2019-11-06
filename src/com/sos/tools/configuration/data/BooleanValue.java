/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class BooleanValue extends PrimitiveValue<Boolean> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public BooleanValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public BooleanValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.ConfigurationValue#convertValue(java.lang.String)
	 */
	@Override
	public Boolean convertValue(String value) 
	{
		return Boolean.valueOf(value);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Boolean> getClassType() 
	{
		return Boolean.class;
	}

}
