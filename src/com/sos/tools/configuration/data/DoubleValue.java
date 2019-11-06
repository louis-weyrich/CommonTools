package com.sos.tools.configuration.data;

public class DoubleValue extends PrimitiveValue<Double> 
{

	public DoubleValue(String name) 
	{
		super(name);
	}

	public DoubleValue(String name, String value) 
	{
		super(name, value);
	}

	@Override
	public Double convertValue(String value) {
		return Double.valueOf(value);
	}

	@Override
	public Class<Double> getClassType() {
		return Double.class;
	}

}
