package com.sos.tools.test.utilities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sos.tools.test.utilities.dataobjects.Address;
import com.sos.tools.test.utilities.dataobjects.Contact;
import com.sos.tools.test.utilities.dataobjects.User;
import com.sos.tools.utilities.PropertiesFormat;

public class PropertiesFormatTest {
	
	public static final String messageTest = "My [type] is [type_value][user.lastName], [user.firstName] I live in [user.contact.address.city] [user.contact.address.state]";
	public static final String messageTest2 = "My [type] is [type_value] [type_property] [type_property_value] with id = [id]";

	@Test
	public void testPropertiesFormat() {
		PropertiesFormat pf = new PropertiesFormat();
		pf.setProperty("message", messageTest);
		
		Address address = new Address("56009 Vol Walker CV", "", "Austin", "TX","78749");
		Contact contact = new Contact(address,"512-745-2171");
		User user = new User("Louis", "Weyrich", "Strange1", contact);
		pf.setProperty("user", user);
		pf.setProperty("type", "name");
		
		String message = pf.getProperty("message");
		assertTrue(message,"My name is Weyrich, Louis I live in Austin TX".equals(message));
	}
	
	@Test
	public void testCompileMessage()
	{
		PropertiesFormat pf = new PropertiesFormat();
		pf.setProperty("type", "name");
		pf.setProperty("type_value", "[first_name] [last_name]");
		pf.setProperty("type_property", "my age is");
		pf.setProperty("type_property_value", "44");
		pf.setProperty("id", "8675309");
		pf.setProperty("first_name", "Joe");
		pf.setProperty("last_name", "Dirt");
		String message = pf.compileMessage(messageTest2);
		assertTrue(message,"My name is Joe Dirt my age is 44 with id = 8675309".equals(message));
	}
	
	@Test
	public void testCompileProperty()
	{
		PropertiesFormat pf = new PropertiesFormat();
		pf.setProperty("message", messageTest2);
		Map <String, Object> variables = new HashMap <String, Object> ();
		variables.put("type", "name");
		variables.put("type_value", "Joe Dirt");
		variables.put("type_property", "my age is");
		variables.put("type_property_value", "44");
		variables.put("id", "8675309");
		String message = pf.compileProperty("message", variables);
		assertTrue(message,"My name is Joe Dirt my age is 44 with id = 8675309".equals(message));
	}
	
	@Test
	public void testLoadProperties()
	{
		try{
			PropertiesFormat pf = new PropertiesFormat("./config/test.properties", PropertiesFormat.PLACE_HOLDER);
			String message = pf.getProperty("message");
			
			assertTrue(message.equals("My name is Louis Weyrich my age is 44 and \"I am the Myth\"."));
		}
		catch(IOException e)
		{
			fail("Exception:"+e.getMessage());
		}
	}

	
	@Test
	public void testLoadPropertiesWithVariables()
	{
		try{
			PropertiesFormat pf = new PropertiesFormat("./config/test.properties", PropertiesFormat.PLACE_HOLDER);
			
			Map <String, Object> variables = new HashMap <String, Object> ();
			variables.put("field_3", "Mathew Spencer");
			variables.put("catch_phrase", "I am not The Myth" +
					"");
			String message = pf.getProperty("message", variables);
			
			assertTrue(message.equals("My name is Louis Weyrich my age is 44 and \"I am the Myth\"."));
		}
		catch(IOException e)
		{
			fail("Exception:"+e.getMessage());
		}
	}

}
