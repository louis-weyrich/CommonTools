/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class FloatValue extends PrimitiveValue<Float> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public FloatValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public FloatValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Float> getClassType() 
	{
		return Float.class;
	}

	@Override
	public Float convertValue(String value) 
	{
		return Float.valueOf(value);
	}

}
