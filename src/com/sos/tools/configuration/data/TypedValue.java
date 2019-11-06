package com.sos.tools.configuration.data;


public interface TypedValue <T>
{
	
	public void addValue(T value);

	public String getTypeString();

	public void setTypeString(String typeString);

}
