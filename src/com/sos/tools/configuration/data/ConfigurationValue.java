package com.sos.tools.configuration.data;

import java.util.Iterator;

import com.sos.tools.configuration.ConfigurationTypes;
import com.sos.tools.exception.ConfigurationException;

public interface ConfigurationValue <V>
{
	public String getName();
	public V getValue();
	public void setValue(V value);
	public Class<V> getClassType();
	public ConfigurationTypes getType();
	public Object find(Iterator <Object> iterator) throws ConfigurationException;
}
