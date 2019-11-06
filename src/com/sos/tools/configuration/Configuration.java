package com.sos.tools.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.sos.tools.configuration.data.AbstractConfigurationValue;
import com.sos.tools.configuration.data.ConfigurationValue;
import com.sos.tools.configuration.data.NamedValue;
import com.sos.tools.configuration.data.Value;
import com.sos.tools.exception.ConfigurationException;
import com.sos.tools.utilities.collection.MappedMaps;
import com.sos.tools.utilities.collection.UtilIterator;
import com.sos.tools.utilities.collection.UtilList;
import com.sos.tools.utilities.collection.UtilMap;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Configuration 
{

	
	protected MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> config;

	public Configuration()
	{
		
	}
	
	/**
	 * 
	 * @param config
	 * @throws FileNotFoundException 
	 * @throws SAXException 
	 */
	public Configuration(String fileLocation) throws IOException, SAXException, Exception 
	{
		this(new File(fileLocation));
	}
	
	public Configuration(File xmlConfig) throws IOException, SAXException, Exception 
	{
		InputSource src = new InputSource( new FileInputStream( xmlConfig ) );
		ConfigurationReader reader = new ConfigurationReader();
		
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		XMLReader rdr = parser.getXMLReader();
		rdr.setContentHandler( reader );
		rdr.parse( src );
			
		config = reader.getConfiguration();
	}
	
	/**
	 * 
	 * @param configName
	 * @return
	 */
	public Class <Object> getConfigurationType(String configName)
	{
		
		return null;
	}
	
	/**
	 * 
	 * @param type
	 * @param name
	 * @return
	 * @throws ConfigurationException 
	 */
	public Object getConfigurationValue(String name) 
	throws ClassCastException, ConfigurationException
	{
		Object [] path = this.createPath(name);
		Iterator <Object> iterator = new UtilIterator <Object> (path);
		while(iterator.hasNext())
		{
			String pathName = iterator.next().toString();
			if(this.config.containsKey(pathName))
			{
				if(iterator.hasNext())
				{
					String pathNameInner = iterator.next().toString();
					if(this.config.containsInnerKey(pathName, pathNameInner))
					{
						AbstractConfigurationValue <? extends Object> value = 
							this.config.getInner(pathName, pathNameInner);
						
						if(iterator.hasNext())
						{
							Object returnValue = value.find(iterator);
							return returnValue;
						}
						else
						{
							Object returnValue = value.getValue();
							return returnValue;
						}
					}
				}
				else
				{
					return config.get(pathName);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param type
	 * @param name
	 */
	public <T> void setConfigurationValue(T type, String name) throws ClassCastException
	{
		Object [] path = this.createPath(name);
		Iterator <Object> iterator = new UtilIterator <Object> (path);
		Object current = null;
		while(iterator.hasNext())
		{
			current = iterator.next();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception
	 */
	public Integer getInteger(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Integer)getConfigurationValue(name);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Float getFloat(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Float)getConfigurationValue(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Byte getByte(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Byte)getConfigurationValue(name);
	}


	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Short getShort(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Short)getConfigurationValue(name);
	}


	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Boolean getBoolean(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Boolean)getConfigurationValue(name);
	}


	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Character geCharacter(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Character)getConfigurationValue(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Long geLong(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Long)getConfigurationValue(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Double geDouble(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Double)getConfigurationValue(name);
	}


	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getString(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (String)getConfigurationValue(name);
	}


	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	
	
	public List<Value> getList(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (List<Value>)getConfigurationValue(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public List<ConfigurationValue<? extends Object>> getUnit(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (List<ConfigurationValue<? extends Object>>)getConfigurationValue(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Map<String, NamedValue> getMap(String name) throws ClassCastException, 
	InstantiationException, IllegalAccessException, ConfigurationException
	{
		return (Map<String, NamedValue>)getConfigurationValue(name);
	}
	
	/**
	 * 
	 * @param stringPath
	 * @return
	 */
	protected Object [] createPath(String stringPath)
	{
		UtilList <String> list = new UtilList <String> ();
		
		int index = stringPath.indexOf(".");
		
		if(index > -1)
		{
			while(index > -1)
			{
				String subpath = stringPath.substring(0, index);
				stringPath = stringPath.substring(index+1);
				list.add(subpath);
				index = stringPath.indexOf(".");
				if(index == -1 && stringPath.length() > 0)
				{
					list.add(stringPath);
				}
			}
		}
		else
		{
			list.add(stringPath);
		}
		
		return list.getAllValues();
	}
	
	/**
	 * 
	 * @param configName
	 * @param data
	 * @return
	 * @throws ClassCastException
	 * @throws ConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> T populateData(String configName, T data)
	throws ClassCastException, ConfigurationException, InstantiationException, 
	IllegalAccessException
	{
		
		
		Class<? extends Object> clazz = data.getClass();
		
		try
		{
			List<ConfigurationValue<? extends Object>> unitList = getUnit(configName);
			for(ConfigurationValue<? extends Object> value : unitList)
			{
				Object obj = value.getValue();
				
				if(data instanceof List)
				{
					List <Object> list = (List <Object>)data;
					list.add(obj);
				}
				else if(data instanceof Map)
				{
					
				}
				else if(obj instanceof List )
				{
					String name = capName("get", value.getName());
					Method method = clazz.getDeclaredMethod(name, null);
					Class <? extends Object> returnType = method.getReturnType();
					
					Object newdata = null;
					if(returnType.equals(List.class))
					{
						returnType = UtilList.class;
						UtilList <Value> instance = (UtilList<Value>)returnType.newInstance();
						newdata = populateData(value.getName(), (UtilList<Value>)instance, (List)obj);
					}
					else if(returnType.equals(Map.class))
					{
						returnType = UtilMap.class;
						UtilMap <String, NamedValue> instance = (UtilMap<String, NamedValue>)returnType.newInstance();
						Map<String, NamedValue> newInstance = ((Map<String, NamedValue>)List.class.cast(instance));
						newdata = populateData(value.getName(), newInstance, (List)obj);
					}
					else
					{
						Object newInstance = returnType.newInstance();
						newdata = populateData(value.getName(), newInstance, (List)obj);
					}

					if(newdata instanceof List)
					{
						name = capName("set", value.getName());
						Class [] objArry = {List.class};
						method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, newdata);
					}
					else if(newdata instanceof Map)
					{
						name = capName("set", value.getName());
						Class [] objArry = {Map.class};
						method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, newdata);
					}
					else
					{
						name = capName("set", value.getName());
						Class [] objArry = {returnType};
						method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, newdata);
					}
					
				}
				else if (obj instanceof Map)
				{
					String name = capName("get", value.getName());
					Method method = clazz.getDeclaredMethod(name, null);
					Class <? extends Object> returnType = method.getReturnType();
					
					Object newdata = null;
					if(returnType.equals(List.class))
					{
						returnType = UtilList.class;
						UtilList <Value> instance = (UtilList<Value>)returnType.newInstance();
						newdata = populateData(value.getName(), (UtilList<Value>)instance, (List)obj);
					}
					else if(returnType.equals(Map.class))
					{
						returnType = UtilMap.class;
						UtilMap <String, NamedValue> instance = (UtilMap<String, NamedValue>)returnType.newInstance();
						Map<String, NamedValue> newInstance = ((Map<String, NamedValue>)Map.class.cast(instance));
						newdata = populateData(value.getName(), newInstance, (Map)obj);
					}
					else
					{
						Object newInstance = returnType.newInstance();
						newdata = populateData(value.getName(), newInstance, (List)obj);
					}

					if(newdata instanceof List)
					{
						name = capName("set", value.getName());
						Class [] objArry = {List.class};
						method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, newdata);
					}
					else if(newdata instanceof Map)
					{
						name = capName("set", value.getName());
						Class [] objArry = {Map.class};
						method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, newdata);
					}
					else
					{
						name = capName("set", value.getName());
						Class [] objArry = {returnType};
						method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, newdata);
					}
				}
				else
				{
					String name = capName("set", value.getName());
					Class [] objArry = {value.getClassType()};
					Method method = clazz.getDeclaredMethod(name, objArry);
					method.invoke(data, obj);
				}
				
			}
		}
		catch(NoSuchMethodException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * @param configName
	 * @param data
	 * @param list
	 * @return
	 * @throws ClassCastException
	 * @throws ConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected <T> T populateData(String configName, T data, 
	List<ConfigurationValue<? extends Object>> list)
	throws ConfigurationException
	{
		Class<? extends Object> clazz = data.getClass();
		
		try
		{
			for(ConfigurationValue<? extends Object> value : list)
			{
				Object obj = value.getValue();
				
				if(obj instanceof List)
				{
					String name = capName("get", value.getName());
					Method method = clazz.getDeclaredMethod(name, null);
					Class <? extends Object> returnType = method.getReturnType();
					Object newdata = populateData(value.getName(), returnType.newInstance(), (List)obj);
					name = capName("set", value.getName());
					Class [] objArry = {returnType};
					method = clazz.getDeclaredMethod(name, objArry);
					method.invoke(data, newdata);
				}
				else
				{
					
					if(data instanceof List)
					{
						((List<Object>) data).add(obj);
					}
					else if(data instanceof Map)
					{
						((Map<String, Object>) data).put(value.getName(), 
							obj);
					}
					else
					{
						String name = capName("set", value.getName());
						Class [] objArry = {value.getClassType()};
						Method method = clazz.getDeclaredMethod(name, objArry);
						method.invoke(data, obj);
					}
										
				}
				
			}
		}
		catch(NoSuchMethodException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (IllegalArgumentException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (InvocationTargetException e) 
		{
			System.out.println(e.getMessage());
		}
		catch(ClassCastException e)
		{
			System.out.println(e.getMessage());
		}
		catch(InstantiationException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IllegalAccessException e)
		{
			System.out.println(e.getMessage());
		}
		
		return data;
	}
	
	/**
	 * 
	 * @param configName
	 * @param data
	 * @param map
	 * @return
	 * @throws ClassCastException
	 * @throws ConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected <T> T populateData(String configName, T data, 
	Map<String, ConfigurationValue<? extends Object>> map)
	throws ConfigurationException
	{
		Class<? extends Object> clazz = data.getClass();
		
		try
		{
			for(Map.Entry<String, ConfigurationValue<? extends Object>> value : map.entrySet())
			{
				NamedValue obj = (NamedValue)value.getValue();
				String name = value.getKey();
				((Map<String, Object>) data).put(name, obj.getValue());
			}
		}
		catch (IllegalArgumentException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch(ClassCastException e)
		{
			System.out.println(e.getMessage());
		}
		
		return data;
	}

	/**
	 * 
	 * @param preset
	 * @param name
	 * @return
	 */
	protected String capName(String preset, String name)
	{
		StringBuffer buffer = new StringBuffer(preset);
		
		char firstChar = name.charAt(0);
		firstChar = Character.toUpperCase(firstChar);
		buffer.append(firstChar).append(name.substring(1));
		
		return buffer.toString();
	}


}
