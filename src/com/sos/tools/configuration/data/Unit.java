/**
 * 
 */
package com.sos.tools.configuration.data;

import java.util.Iterator;

import com.sos.tools.configuration.ConfigurationTypes;
import com.sos.tools.exception.ConfigurationException;
import com.sos.tools.utilities.collection.UtilList;

/**
 * @author louis.weyrich
 *
 */
public class Unit extends AbstractConfigurationValue<UtilList<ConfigurationValue<? extends Object>>> 
{

	//protected String value;
	protected UtilList<ConfigurationValue<? extends Object>> children;

	


	public Unit(String name)
	{
		super(name, ConfigurationTypes.UNIT);
	}

	public UtilList<ConfigurationValue<? extends Object>> getValue() 
	{
		return getChildren();
	}

	@Override
	public void setValue(UtilList<ConfigurationValue<? extends Object>> value) 
	{
		this.children = value;		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<UtilList<ConfigurationValue<? extends Object>>> getClassType() 
	{
		return (Class<UtilList<ConfigurationValue<? extends Object>>>)children.getClass();
	}

	public UtilList<ConfigurationValue<? extends Object>> getChildren()
	{
		if(children == null)
		{
			children = new UtilList <ConfigurationValue<? extends Object>>(5,5);
		}
		
		return children;
	}
	
	public void addChild(ConfigurationValue<? extends Object> child)
	{
		getChildren().add(child);
	}
	
	public boolean hasChildren()
	{
		if(type != ConfigurationTypes.PRIMITIVE)
		{
			if(children != null)
			{
				return(getChildren().size() > 0);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public Object find(Iterator<Object> iterator) throws ConfigurationException
	{
		if(iterator.hasNext())
		{
			String path = iterator.next().toString();
			
			for(ConfigurationValue<? extends Object> value : children)
			{
				if(value.equals(path))
				{
					if(iterator.hasNext())
					{
						return value.find(iterator);
					}
					else
					{
						return value.getValue();
					}
				}
			}
		}
		else
		{
			return getValue();
		}
		
		return null;
	}

}
