package com.sos.tools.configuration.data;

import com.sos.tools.configuration.ConfigurationTypes;

public abstract class AbstractConfigurationValue <V> 
implements ConfigurationValue <V>
{
	
	protected String name;
	protected ConfigurationTypes type;

	
	/**
	 * 
	 * @param name
	 */
	public AbstractConfigurationValue(String name, ConfigurationTypes type)
	{
		setName(name);
		setType(type);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public AbstractConfigurationValue(String name, V value, ConfigurationTypes type)
	{
		setName(name);
		setValue(value);
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public abstract void setValue(V value);
	
	public abstract Class<V> getClassType();
	
	public ConfigurationTypes getType() {
		return type;
	}

	public void setType(ConfigurationTypes type) {
		this.type = type;
	}

	public boolean equals(Object src)
	{
		if(src instanceof String)
		{
			return src.equals(name);
		}
		else
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	
	
}
