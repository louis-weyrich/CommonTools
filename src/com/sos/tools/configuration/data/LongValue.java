/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class LongValue extends PrimitiveValue<Long> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public LongValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public LongValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Long> getClassType() 
	{
		return Long.class;
	}

	@Override
	public Long convertValue(String value) 
	{
		return Long.valueOf(value);
	}

}
