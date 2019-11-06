package com.sos.tools.test.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.collection.MappedMaps;
import com.sos.tools.utilities.collection.UtilMap;

public class MappedMapsTest 
{
	
	
	private static MappedMaps <String, String, String> mappedMaps;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		mappedMaps = null;
	}

	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception 
	{
		
	}

	@Test
	public void testMappedMaps() 
	{
		mappedMaps = new MappedMaps <String, String, String> ();
		mappedMaps.putInner("People", "King", "Louis");
		mappedMaps.putInner("People", "Queen", "Alyona");
		mappedMaps.putInner("People", "Prince", "Eugene");
		mappedMaps.putInner("People", "Duchess", "Roxanna");
		mappedMaps.putInner("People", "Squire", "Bob");
		mappedMaps.putInner("Car", "type", "Honda");
		mappedMaps.putInner("Car", "model", "CRV");
		mappedMaps.putInner("Car", "doors", "5");
		mappedMaps.putInner("Car", "color", "maroon");
		mappedMaps.putInner("Car", "mpg", "32");
		mappedMaps.putInner("Owner", "King", "Honda");
		mappedMaps.putInner("Owner", "Queen", "Mazda");
		mappedMaps.putInner("Owner", "Prince", "Ferrarri");
		
	}

	@Test
	public void testMappedMapsInt() 
	{
		mappedMaps = new MappedMaps <String, String, String> (10);
		mappedMaps.putInner("People", "King", "Louis");
		mappedMaps.putInner("People", "Queen", "Alyona");
		mappedMaps.putInner("People", "Prince", "Eugene");
		mappedMaps.putInner("People", "Duchess", "Roxanna");
		mappedMaps.putInner("People", "Squire", "Bob");
		mappedMaps.putInner("Car", "type", "Honda");
		mappedMaps.putInner("Car", "model", "CRV");
		mappedMaps.putInner("Car", "doors", "5");
		mappedMaps.putInner("Car", "color", "maroon");
		mappedMaps.putInner("Car", "mpg", "32");
		mappedMaps.putInner("Owner", "King", "Honda");
		mappedMaps.putInner("Owner", "Queen", "Mazda");
		mappedMaps.putInner("Owner", "Prince", "Ferrarri");
	}

	@Test
	public void testMappedMapsIntInt() 
	{
		mappedMaps = new MappedMaps <String, String, String> (10, 10);
		mappedMaps.putInner("People", "King", "Louis");
		mappedMaps.putInner("People", "Queen", "Alyona");
		mappedMaps.putInner("People", "Prince", "Eugene");
		mappedMaps.putInner("People", "Duchess", "Roxanna");
		mappedMaps.putInner("People", "Squire", "Bob");
		mappedMaps.putInner("Car", "type", "Honda");
		mappedMaps.putInner("Car", "model", "CRV");
		mappedMaps.putInner("Car", "doors", "5");
		mappedMaps.putInner("Car", "color", "maroon");
		mappedMaps.putInner("Car", "mpg", "32");
		mappedMaps.putInner("Owner", "King", "Honda");
		mappedMaps.putInner("Owner", "Queen", "Mazda");
		mappedMaps.putInner("Owner", "Prince", "Ferrarri");
	}

	@Test
	public void testMappedMapsIntIntIntInt() 
	{
		mappedMaps = new MappedMaps <String, String, String> (10, 10, 5, 5);
		mappedMaps.putInner("People", "King", "Louis");
		mappedMaps.putInner("People", "Queen", "Alyona");
		mappedMaps.putInner("People", "Prince", "Eugene");
		mappedMaps.putInner("People", "Duchess", "Roxanna");
		mappedMaps.putInner("People", "Squire", "Bob");
		mappedMaps.putInner("Car", "type", "Honda");
		mappedMaps.putInner("Car", "model", "CRV");
		mappedMaps.putInner("Car", "doors", "5");
		mappedMaps.putInner("Car", "color", "maroon");
		mappedMaps.putInner("Car", "mpg", "32");
		mappedMaps.putInner("Owner", "King", "Honda");
		mappedMaps.putInner("Owner", "Queen", "Mazda");
		mappedMaps.putInner("Owner", "Prince", "Ferrarri");
	}

	@Test
	public void testMappedMapsIntIntIntIntBoolean() 
	{
		mappedMaps = new MappedMaps <String, String, String> (10, 10, 5, 5, true);
		mappedMaps.putInner("People", "King", "Louis");
		mappedMaps.putInner("People", "Queen", "Alyona");
		mappedMaps.putInner("People", "Prince", "Eugene");
		mappedMaps.putInner("People", "Duchess", "Roxanna");
		mappedMaps.putInner("People", "Squire", "Bob");
		mappedMaps.putInner("Car", "type", "Honda");
		mappedMaps.putInner("Car", "model", "CRV");
		mappedMaps.putInner("Car", "doors", "5");
		mappedMaps.putInner("Car", "color", "maroon");
		mappedMaps.putInner("Car", "mpg", "32");
		mappedMaps.putInner("Owner", "King", "Honda");
		mappedMaps.putInner("Owner", "Queen", "Mazda");
		mappedMaps.putInner("Owner", "Prince", "Ferrarri");
	}

	@Test
	public void testContainsKey() 
	{
		assertTrue(	mappedMaps.containsKey("People") );
		assertFalse( mappedMaps.containsKey("Stuff") );
	}

	@Test
	public void testContainsInnerKey() 
	{
		assertTrue(	mappedMaps.containsInnerKey("People","King") );
		assertTrue(	mappedMaps.containsInnerKey("People","Prince") );
		assertTrue(	mappedMaps.containsInnerKey("Car","type") );
		assertFalse( mappedMaps.containsInnerKey("Car", "engine") );
		assertFalse( mappedMaps.containsInnerKey("Stuff", "things") );
	}

	@Test
	public void testContainsInnerValueKeyValue() 
	{
		assertTrue(	mappedMaps.containsInnerValue("People","King", "Louis") );
		assertTrue(	mappedMaps.containsInnerValue("People","Duchess", "Roxanna") );
		assertFalse( mappedMaps.containsInnerValue("People","King", "Roxanna") );
		assertFalse( mappedMaps.containsInnerValue("People","Student", "Louis") );
	}

	@Test
	public void testContainsInnerValueKeyInnerKeyValue() 
	{
		assertTrue(	mappedMaps.containsInnerValue("People","Louis") );
		assertTrue(	mappedMaps.containsInnerValue("People","Roxanna") );
		assertFalse( mappedMaps.containsInnerValue("People","Steve") );
		assertFalse( mappedMaps.containsInnerValue("Things","Stuff") );
	}

	@Test
	public void testGet() 
	{
		Map <String, String> values = mappedMaps.get("People");
		
		if(values != null)
		{
			assertTrue(	values.get("King").equals("Louis") );
		}
		else
		{
			fail("values for get is null");
		}
	}

	@Test
	public void testGetInner() 
	{
		String value = mappedMaps.getInner("Car", "type");
		
		if(value != null)
		{
			assertTrue(	value.equals("Honda") );
		}
		else
		{
			fail("values for inner get is null");
		}
	}

	@Test
	public void testPutInner() 
	{
		String newPrice = mappedMaps.putInner("Car", "price", "$20,000");
		String oldColor = mappedMaps.putInner("Car", "color", "blue");
		
		assertTrue(	newPrice == null );
		assertTrue(	oldColor.equals("maroon") );
		assertTrue(	mappedMaps.getInner("Car", "color").equals("blue") );
		assertTrue(	mappedMaps.getInner("Car", "price").equals("$20,000") );
		
	}

	@Test
	public void testPut() 
	{
		Map <String, String> map = new UtilMap <String, String> ();
		map.put("Outer", "Space");
		map.put("Inner", "Conversation");
		map.put("Middle", "Earth");
		
		mappedMaps.put("Etc", map);
		assertTrue(mappedMaps.getInner("Etc", "Outer").equals("Space"));
	}

	@Test
	public void testRemoveInner() 
	{
		assertTrue(mappedMaps.containsInnerKey("Owner", "King"));
		
		String removedValue = mappedMaps.removeInner("Owner", "King");
		
		assertTrue(removedValue.equals("Honda"));
		
		assertFalse(mappedMaps.containsInnerKey("Owner", "King"));
	}

	@Test
	public void testRemove() 
	{
		assertTrue(mappedMaps.containsKey("Owner"));
		
		Map <String, String> removedMap = mappedMaps.remove("Owner");
		
		assertTrue(removedMap.get("Queen").equals("Mazda"));
		
		assertFalse(mappedMaps.containsKey("Owner"));
	}

	@Test
	public void testPutAll() 
	{
		Map <String, String> map = new UtilMap <String, String> ();
		map.put("Subject", "Steve");
		map.put("Wench", "Jan");
		map.put("DungFlinger", "Micheal");
		mappedMaps.putAll("People", map);
		
		assertTrue(mappedMaps.containsInnerKey("People", "Subject"));
		assertTrue(mappedMaps.getInner("People", "Subject").equals("Steve"));
		assertTrue(mappedMaps.containsInnerKey("People", "King"));
		assertTrue(mappedMaps.getInner("People", "King").equals("Louis"));
	}

	@Test
	public void testKeySet() 
	{
		Set <String> keySet = mappedMaps.keySet();
		
		if(keySet != null)
		{
			assertTrue("key set is empty", keySet.size() > 0);
			assertTrue("key set does not contain People", keySet.contains("People"));
		}
		else
		{
			fail("key set is null");
		}
	}

	@Test
	public void testKeySetInner() 
	{
		Set <String> keySet = mappedMaps.keySetInner("People");
		
		if(keySet != null)
		{
			assertTrue("inner key set is empty", keySet.size() > 0);
			assertTrue("inner key set does not contain King", keySet.contains("King"));
		}
		else
		{
			fail("inner key set is null");
		}
	}

	@Test
	public void testValues() 
	{
		Collection <Map<String,String>> values = mappedMaps.values();
		
		if(values != null)
		{
			for(Map<String,String> map : values)
			{
				assertTrue(map.size() > 0);
				assertFalse(map.isEmpty());
			}
		}
		else
		{
			fail("values collection is null");
		}
	}

	@Test
	public void testValuesInner() 
	{
		Collection <String> values = mappedMaps.valuesInner("People");
		
		if(values != null)
		{
			for(String value : values)
			{
				assertTrue(value != null);
			}
		}
		else
		{
			fail("inner values collection is null");
		}
	}

	@Test
	public void testClear() 
	{
		int size = mappedMaps.size();
		
		assertTrue(size == 3);
		
		mappedMaps.clear();
		
		assertTrue(mappedMaps.size() == 0);
		assertFalse(mappedMaps.containsKey("People"));
	}

}
