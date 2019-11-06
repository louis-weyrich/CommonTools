package com.sos.tools.configuration.data;

public class NamedValue <T> extends Value <T>
{

	protected String name;

	
	public NamedValue() {
		super();
	}


	public NamedValue(String name) {
		this();
		this.name = name;
	}

	public NamedValue(String name, T value) {
		super(value);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object src)
	{
		return src.equals(name);
	}

	
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
}
