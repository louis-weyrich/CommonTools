package com.sos.tools.configuration;

import com.sos.tools.configuration.data.BooleanValue;
import com.sos.tools.configuration.data.ByteValue;
import com.sos.tools.configuration.data.CharacterValue;
import com.sos.tools.configuration.data.DoubleValue;
import com.sos.tools.configuration.data.FloatValue;
import com.sos.tools.configuration.data.IntegerValue;
import com.sos.tools.configuration.data.LongValue;
import com.sos.tools.configuration.data.PrimitiveValue;
import com.sos.tools.configuration.data.ShortValue;

public class PrimitiveValueHelper {

	public static PrimitiveValue<? extends Object> createPrimitiveValue(String name, String value, String type) 
	{
		if(PrimitiveTypes.integer_type.name().equals(type))
		{
			return new IntegerValue(name, value);
		}
		else if(PrimitiveTypes.long_type.name().equals(type))
		{
			return new LongValue(name, value);
		}
		else if(PrimitiveTypes.float_type.name().equals(type))
		{
			return new FloatValue(name, value);
		}
		else if(PrimitiveTypes.double_type.name().equals(type))
		{
			return new DoubleValue(name, value);
		}
		else if(PrimitiveTypes.short_type.name().equals(type))
		{
			return new ShortValue(name, value);
		}
		else if(PrimitiveTypes.byte_type.name().equals(type))
		{
			return new ByteValue(name, value);
		}
		else if(PrimitiveTypes.boolean_type.name().equals(type))
		{
			return new BooleanValue(name, value);
		}
		else if(PrimitiveTypes.character_type.name().equals(type))
		{
			return new CharacterValue(name, value);
		}
		else
		{
			return new IntegerValue(name, value);
		}
		
	}
	
	public static Object createPrimitive(String value, String type) 
	{
		if(PrimitiveTypes.integer_type.name().equals(type))
		{
			return new Integer(value);
		}
		else if(PrimitiveTypes.long_type.name().equals(type))
		{
			return new Long(value);
		}
		else if(PrimitiveTypes.float_type.name().equals(type))
		{
			return new Float(value);
		}
		else if(PrimitiveTypes.double_type.name().equals(type))
		{
			return new Double(value);
		}
		else if(PrimitiveTypes.short_type.name().equals(type))
		{
			return new Short(value);
		}
		else if(PrimitiveTypes.byte_type.name().equals(type))
		{
			return new Byte(value);
		}
		else if(PrimitiveTypes.boolean_type.name().equals(type))
		{
			return new Boolean(value);
		}
		else if(PrimitiveTypes.character_type.name().equals(type))
		{
			return new Character(value.charAt(0));
		}
		else if(PrimitiveTypes.string_type.name().equals(type))
		{
			return new String(value);
		}
		else
		{
			return new String(value);
		}
		
	}

}
