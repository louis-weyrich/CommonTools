package com.sos.tools.configuration.data;

import java.util.Iterator;

import com.sos.tools.configuration.ConfigurationTypes;
import com.sos.tools.exception.ConfigurationException;

public class Value <T> implements ConfigurationValue<T>{
	
	protected T value;
	protected String type;

	public Value() {
		
	}

	public Value(T value) {
		this.value = value;
	}

	public T getValue()
	{
		return value;
	}
	
	public void setValue(T value)
	{
		this.value = value;
	}

	public String getStringType() {
		return type;
	}

	public void setStringType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return value.toString();
	}
	
	@Override
	public boolean equals(Object src)
	{
		return src.equals(value);
	}
	
	@Override
	public int hashCode()
	{
		return value.hashCode();
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getClassType() {
		return (Class<T>)value.getClass();
	}

	public ConfigurationTypes getType() {
		return ConfigurationTypes.VALUE;
	}

	public Object find(Iterator<Object> iterator) throws ConfigurationException {
		// TODO Auto-generated method stub
		return null;
	}

}
