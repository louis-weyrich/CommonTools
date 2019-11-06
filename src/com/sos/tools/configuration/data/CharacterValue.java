/**
 * 
 */
package com.sos.tools.configuration.data;

/**
 * @author louis.weyrich
 *
 */
public class CharacterValue extends PrimitiveValue<Character> 
{

	/**
	 * @param name
	 * @param valueMap
	 */
	public CharacterValue(String name) 
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public CharacterValue(String name, String value) 
	{
		super(name, value);
	}

	/**
	 * @see com.sos.tools.configuration.data.ConfigurationValue#convertValue(java.lang.String)
	 */
	@Override
	public Character convertValue(String value) 
	{
		return Character.valueOf(value.charAt(0));
	}

	/**
	 * @see com.sos.tools.configuration.data.AbstractConfigurationValue#getClassType()
	 */
	@Override
	public Class<Character> getClassType() 
	{
		return Character.class;
	}

}
