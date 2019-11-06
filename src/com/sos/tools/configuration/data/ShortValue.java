/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class ShortValue extends PrimitiveValue<Short> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public ShortValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public ShortValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.ConfigurationValue#convertValue(java.lang.String)
	 */
	@Override
	public Short convertValue(String value) 
	{
		return Short.valueOf(value);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Short> getClassType() 
	{
		return Short.class;
	}

}
