package com.sos.tools.configuration;

public enum ConfigurationTypes 
{
	PRIMITIVE, STRING, UNIT, LIST, MAP, VALUE, NAMEDVALUE;
	
	public static boolean isPrimitiveType(ConfigurationTypes type)
	{
		return (type == ConfigurationTypes.PRIMITIVE);
	}
	
	public static boolean isStringType(ConfigurationTypes type)
	{
		return (type == ConfigurationTypes.STRING);
	}
	
	public static boolean isUnitType(ConfigurationTypes type)
	{
		return (type == ConfigurationTypes.UNIT);
	}
	
	public static boolean isListType(ConfigurationTypes type)
	{
		return (type == ConfigurationTypes.LIST);
	}
	
	public static boolean isMapType(ConfigurationTypes type)
	{
		return (type == ConfigurationTypes.MAP);
	}


}
