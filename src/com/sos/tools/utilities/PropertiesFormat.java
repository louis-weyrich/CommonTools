/**
 * 
 */
package com.sos.tools.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sos.tools.utilities.collection.UtilIterator;

/**
 * <p>This class is used to format properties messages with place holders
 * using regular expressions.</p>  
 * 
 * <p>
 * 		The default place holder is \[[a-zA-Z0-9._-]+\]<br>
 * 		A place holder can contain the following sequence.
 * 		<ul>
 * 			<li>It must start with a "[" and end with a "]"</li>
 * 			<li>it can contain any number of characters and numbers</li>
 * 			<li>It can contain these special characters "." or "_" or "-".
 * 			<li>Inside the brackets "[...]" there can be no spaces.
 * 			<li>Example: [firstname] or [first.name] or [first_name] or [first-name]</li>
 * 			<li>If the place holder contains ".", this means it is an Object with nested values.
 * 		</ul>
 * </p>
 * <p>EXAMPLE:</p>
 * <p>
 * <code>
 * 		String propertyMessage = "This operation failed due to [reason]: [failure_message]"<br><br>
 * 		PropertiesFormat pf = new PropertiesFormat();<br>
 * 		pf.setProperty("failure_message", propertyMessage);<br>
 * 		pf.setProperty("reason", "missing identification");<br>
 * 		String message = pf.getProperty("failure.message");<br>
 * 		System.out.println(message);<br>
 * 		Prints "This operation failed due to missing identification."
 * </code>
 * </p>
 * <p>OR</p>
 *  <p>
 * <code>
 * 		User user = new User();<br>
 * 		user.setFirstName("Joe");<br>
 * 		user.setLastName("Dirt");<br>
 * 		user.setAge(44);
 * 
 * 		PropertiesFormat pf2 = new PropertiesFormat();<br>
 *		pf2.setProperty("message", "My [value_type] is [user.firstName] [user.lastName] and I am [user.age] id = [id]");<br>
 *		Map <String, Object> variables = new HashMap <String, Object> (4);<br>
 *		variables.put("age", 44);<br>
 *		variables.put("value_type", "name");<br>
 *		variables.put("user", user);<br>
 *		variables.put("id", 8675309);<br>
 *		String message2 = pf2.compileProperty("message", variables);<br>
 *		System.out.println(message2);<br>
 *		Prints "My name is Joe Dirt and I am 44 id = 8675309"<br>
 * </code>
 * </p>
 * 
 * @author louis.weyrich
 * 
 *
 */
public class PropertiesFormat extends Properties implements Cloneable
{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2520245835218642239L;
	
	/**
	 * The default placeHolder value
	 */
	public static final String PLACE_HOLDER = "\\[[a-zA-Z0-9._-]+\\]";
	
	/**
	 * The placeHolder member.
	 */
	private String placeHolder;

	/**
	 * Default constructor that calls the super no parameter constructor 
	 * and set the placeHolder with the default value of \[[a-zA-Z0-9._-]+\];
	 */
	public PropertiesFormat() 
	{
		this(PLACE_HOLDER);
	}
	
	/**
	 * This constructor sets the placeHolder.
	 * 
	 * @param placeHolder the regular expression placeHolder.
	 */
	public PropertiesFormat(String placeHolder) 
	{
		super();
		setPlaceHolder(placeHolder);
	}

	/**
	 * This constructor takes in a pre-populated Properties object
	 * and adds the properties to this class.
	 * 
	 * @param  defaults  To add properties from another Properties class.
	 */
	public PropertiesFormat(Properties defaults) 
	{
		this(PLACE_HOLDER, defaults);
	}
	
	/**
	 * This constructor takes in a pre-populated Properties object
	 * and adds the properties to this class and sets the placeHolder.
	 * 
	 * @param placeHolder the regular expression placeHolder
	 * @param defaults To add properties from another Properties class.
	 */
	public PropertiesFormat(String placeHolder, Properties defaults) 
	{
		super(defaults);
		setPlaceHolder(placeHolder);
	}
	
	public PropertiesFormat(File propertiesFile, String placeHolder, Properties defaults)
	throws IOException
	{
		this(placeHolder, defaults);
		InputStream inStream = new FileInputStream(propertiesFile);
		this.load(inStream);
	}

	
	public PropertiesFormat(File propertiesFile, String placeHolder)
	throws IOException
	{
		this(placeHolder);
		InputStream inStream = new FileInputStream(propertiesFile);
		this.load(inStream);
	}

	
	public PropertiesFormat(File propertiesFile)
	throws IOException
	{
		this();
		InputStream inStream = new FileInputStream(propertiesFile);
		this.load(inStream);
	}

	
	public PropertiesFormat(String propertiesFile, String placeHolder)
	throws IOException
	{
		this(new File(propertiesFile), placeHolder);
	}

	/**
	 * 
	 * 
	 * @return placeHolder returns the current value of the placeHolder variable.
	 */
	public String getPlaceHolder() {
		return placeHolder;
	}

	/**
	 * Sets
	 * 
	 * @param placeHolder the placeHolder to set as a regular expression.
	 */
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	/**
	 * This overrides the super getProperty(String) method from Properties.
	 * It will compile any properties with placeHolders in them and add them
	 * to the property.
	 * 
	 * @param propertyKey the key name for the property you want.
	 */
	@Override
	public String getProperty(String propertyKey)
	{
		String property = null;
		
		if(propertyKey.contains("."))
		{
			int index = propertyKey.indexOf(".");
			String mainKey = propertyKey.substring(0, index);
			Object mainObject = super.get(mainKey);
		
			if(mainObject != null)
			{
				String remaining = propertyKey.substring(index+1, propertyKey.length());
				Iterator <String> iterator = null;
				
				if(remaining.contains("."))
				{
					
					iterator = getMethodIterator(remaining);
				}
				else
				{
					String [] method = {remaining};
					iterator = new UtilIterator <String> (method);
				}
				

				property = reflectMethod(mainObject, iterator);
			}
		}
		else
		{
			property = super.getProperty(propertyKey);
		}
		
		if(property != null){
			if(property.contains("[") && property.contains("]"))
			{
				Pattern pattern = Pattern.compile(getPlaceHolder());
				Matcher matcher = pattern.matcher(property);
				
				while(matcher.find())
				{
					String group = matcher.group();
					String groupProperty = group.substring(1, group.length() - 1);
					
					String innerProperty = getProperty(groupProperty);
					if(innerProperty != null)
					{
						property = property.replace(group, innerProperty);
					}
				}
			}
		}
		else
		{
			property = "";
		}
		
		return property;
	}
	
	@Override
	public String getProperty(String propertyKey, String defaultValue)
	{
		String property = getProperty(propertyKey);
		if(property == null)
		{
			property = defaultValue;
		}
		
		return property;
	}
	
	/**
	 * This method supplies a map for replacement properties.  If the property
	 * is not in the supplied map, it checks the parents map.  The supplied map
	 * overrides the parent map.
	 * 
	 * @param propertyKey
	 * @param variables
	 * @return
	 */
	public String getProperty(String propertyKey, Map <String, Object> variables)
	{
		String property = this.getProperty(propertyKey);
		
		Pattern pattern = Pattern.compile(getPlaceHolder());
		Matcher matcher = pattern.matcher(property);
		
		while(matcher.find())
		{
			String group = matcher.group();
			String groupProperty = group.substring(1, group.length() - 1);
			
			Object variable = variables.get(groupProperty);
			if(variable != null)
			{
				property = property.replace(group, variable.toString());
			}
			else
			{
				String innerProperty = getProperty(groupProperty);
				if(innerProperty != null)
				{
					property = property.replace(group, innerProperty);
				}
			}
		}		
		
		return property;
	}
	
	/**
	 * This method will add an object value to properties.
	 * 
	 * @param key the key name for the property you want.
	 * @param value the property value to set.
	 */
	public void setProperty(String key, Object value)
	{
		super.put(key, value);
	}
	
	/**
	 * This method will add an int value to properties.
	 * 
	 * @param key the key name for the property you want.
	 * @param value the property value to set.
	 */
	public void setProperty(String key, int value)
	{
		setProperty(key, new Integer(value));
	}
	
	/**
	 * This method will add a float value to properties.
	 * 
	 * @param key the key name for the property you want.
	 * @param value the property value to set.
	 */
	public void setProperty(String key, float value)
	{
		setProperty(key, new Float(value));
	}
	
	/**
	 * This method will add a long value to properties.
	 * 
	 * @param key the key name for the property you want.
	 * @param value the property value to set.
	 */
	public void setProperty(String key, Long value)
	{
		setProperty(key, new Long(value));
	}
	
	/**
	 * This method will add a double value to properties.
	 * 
	 * @param key the key name for the property you want.
	 * @param value the property value to set.
	 */
	public void setProperty(String key, double value)
	{
		setProperty(key, new Double(value));
	}
	
	/**
	 * This method will add a char value to properties.
	 * 
	 * @param key the key name for the property you want.
	 * @param value the property value to set.
	 */
	public void setProperty(String key, char value)
	{
		super.setProperty(key, Character.toString(value));
	}
	
	/**
	 * This method reflects the value from the class.
	 * 
	 * @param object
	 * @param methodClass
	 * @param methods
	 * @return
	 */
	protected String reflectMethod(Object object, Iterator <String> methods)
	{
		String value = null;
		
		while(methods.hasNext())
		{
			String methodName = methods.next();
			char firstChar = methodName.charAt(0);
			firstChar = Character.toUpperCase(firstChar);
			methodName = "get"+firstChar+methodName.substring(1, methodName.length());
			
			Class<?> [] emptyClass = new Class<?>[0];
			Object [] emptyObject = new Object[0];
			
			try {
				Method method = object.getClass().getMethod(methodName, emptyClass);
				Object valueObject = method.invoke(object, emptyObject);
				if(methods.hasNext())
				{
					value = reflectMethod(valueObject, methods);
				}
				else
				{
					value = valueObject.toString();
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	protected Iterator <String> getMethodIterator(String key)
	{
		int index = key.indexOf(".");
		List <String> methodList = new ArrayList <String> ();
		
		while(index > -1 || key != null)
		{
			if(index > -1)
			{
				String methodName = key.substring(0, index);
				key = key.substring(index+1);
				methodList.add(methodName);
				index = key.indexOf(".");
			}
			else
			{
				methodList.add(key);
				key = null;
			}
		}
		
		return methodList.iterator();
	}
	
	/**
	 * This method will compile a message you pass in and replace any placeHold values
	 * from the properties.
	 * 
	 * @param message the message with placeHolders.
	 * @return the compiled message.
	 */
	public String compileMessage(String message)
	{
		return compileMessage(message, getPlaceHolder());
	}
	
	/**
	 * This method will compile a message you pass in and replace any placeHold values
	 * from the properties using the provides placeHolder Regular Expression you pass in.
	 * 
	 * @param message the message with placeHolders.
	 * @param placeHolderPattern a regular expression value.
	 * @return the compiles message.
	 */
	public String compileMessage(String message, String placeHolderPattern)
	{
		setPlaceHolder(placeHolderPattern);
		Pattern pattern = Pattern.compile(getPlaceHolder());
		Matcher matcher = pattern.matcher(message);
		
		while(matcher.find())
		{
			String group = matcher.group();
			String groupProperty = group.substring(1, group.length() - 1);
			
			String innerProperty = getProperty(groupProperty);
			message = message.replace(group, innerProperty);
		}
		
		return message;
	}
	
	/**
	 * The will compile a property with the name of "propertyKey" 
	 * and uses the provided map passed to replace the placeHolder values 
	 * or values from the properties.
	 * 
	 * @param propertyKey the property with placeHolders to compile
	 * @param variables the variables to use to fill the placeHolder values.
	 * @return the compiled message
	 */
	public String compileProperty(String propertyKey, Map <String,Object> variables)
	{
		return compileProperty(propertyKey, getPlaceHolder(), variables);
	}
	
	/**
	 * The will compile a property with the name of "propertyKey" 
	 * and uses the provided map passed to replace the placeHolder values 
	 * or values from the properties using the provided regular expression 
	 * placeHolder value provided.
	 * 
	 * @param propertyKey the property with placeHolders to compile
	 * @param variables the variables to use to fill the placeHolder values.
	 * @param placeHolder the regular expression placeHolder
	 * @return the compiled message
	 */
	public String compileProperty(String propertyKey, String placeHolder, Map <String,Object> variables)
	{
		setPlaceHolder(placeHolder);
		String property = super.getProperty(propertyKey);
		Pattern pattern = Pattern.compile(getPlaceHolder());
		Matcher matcher = pattern.matcher(property);
		
		while(matcher.find())
		{
			String group = matcher.group();
			String groupProperty = group.substring(1, group.length() - 1);
			
			if(variables.containsKey(groupProperty))
			{
				String innerProperty = variables.get(groupProperty).toString();
				property = property.replace(group, innerProperty);
			}
			else if(this.containsKey(groupProperty))
			{
				String innerProperty = getProperty(groupProperty);
				property = property.replace(group, innerProperty);
			}
		}
		
		return property;
	}
	
	public static String compileString(String message, Map <String,Object> variables)
	{
		return compileString(message, PropertiesFormat.PLACE_HOLDER, variables);
	}
	
	/**
	 * This static method does the same thing that compileProperty and compileMessage does.
	 * It does not use the properties, because it is static.
	 * 
	 * @param message the message to compile
	 * @param placeHolder the placeHolder to use
	 * @param variables the variable to replace with
	 * @return the compiled message
	 */
	public static String compileString(String message, String placeHolder, Map <String,Object> variables)
	{
		Pattern pattern = Pattern.compile(placeHolder);
		Matcher matcher = pattern.matcher(message);
		
		while(matcher.find())
		{
			String group = matcher.group();
			String groupProperty = group.substring(1, group.length() - 1);
			
			if(variables.containsKey(groupProperty))
			{
				String innerProperty = variables.get(groupProperty).toString();
				message = message.replace(group, innerProperty);
			}
			
		}
		
		return message;
	}
	
	public boolean addAllProperties(Properties props)
	{
		boolean addedAll = true;
		
		Set <Map.Entry<Object,Object>> set = props.entrySet();
		for(Map.Entry<Object,Object> entry : set)
		{
			Object key = entry.getKey();
			Object value = entry.getValue();
			this.put(key,value);
		}
		
		return addedAll;
	}
	
	public PropertiesFormat clone()
	{
		return (PropertiesFormat) super.clone();
	}

}
