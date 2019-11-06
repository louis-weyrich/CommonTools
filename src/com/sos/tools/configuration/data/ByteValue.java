/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class ByteValue extends PrimitiveValue<Byte> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public ByteValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public ByteValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Byte> getClassType() 
	{
		return Byte.class;
	}

	@Override
	public Byte convertValue(String value) {
		return Byte.valueOf(value);
	}

}
