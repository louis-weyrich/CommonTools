/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class IntegerValue extends PrimitiveValue<Integer> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public IntegerValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public IntegerValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Integer> getClassType() 
	{
		return Integer.class;
	}

	@Override
	public Integer convertValue(String value) 
	{
		return Integer.valueOf(value);
	}

}
