/**
 * 
 */
package com.sos.tools.test.utilities.collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.collection.UtilMap;
import com.sos.tools.utilities.comparitor.StringComparator;

/**
 * @author louis.weyrich
 *
 */
public class UtilMapTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#UtilMap()}.
	 */
	@Test
	public void testUtilMap()
	{
		UtilMap <String, String> map = new UtilMap <String, String> ();
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
		//assertTrue(map.size() == 10);
		
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#UtilMap(boolean)}.
	 */
	@Test
	public void testUtilMapBoolean()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (true);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.size() == 10);
//		assertTrue(map.isSorted());
//		assertTrue(map.isSort());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#UtilMap(int, boolean)}.
	 */
	@Test
	public void testUtilMapIntBoolean()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, true);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.size() == 10);
//		assertTrue(map.isSorted());
//		assertTrue(map.isSort());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#UtilMap(int, int, boolean)}.
	 */
	@Test
	public void testUtilMapIntIntBoolean()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, true);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.size() == 10);
//		assertTrue(map.isSorted());
//		assertTrue(map.isSort());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#setKeyComparator(java.util.Comparator)}.
	 */
	@Test
	public void testSetKeyComparatorComparatorBoolean()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.size() == 10);
//		assertTrue(!map.isSorted());
//		assertTrue(!map.isSort());
//		int index = map.indexOfSortedKey("void");
//		assertTrue(index == -1);
		
		map.setKeyComparator(new StringComparator(StringComparator.DIRECTION_ASCENDING), true);
		
//		assertTrue(map.size() == 10);
//		assertTrue(map.isSorted());
//		assertTrue(map.isSort());
//		assertTrue(map.indexOfSortedKey("boolean") == 0);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#setValueComparator(java.util.Comparator)}.
	 */
	@Test
	public void testSetValueComparator()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
				
		map.setValueComparator(new StringComparator(StringComparator.DIRECTION_ASCENDING));
		
//		assertTrue(map.size() == 10);
//		assertTrue(!map.isSorted());
//		assertTrue(!map.isSort());
//		assertTrue(map.containsValue("boolean_rule"));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#setSort(boolean)}.
	 */
	@Test
	public void testSetSort()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
		map.setKeyComparator(new StringComparator(StringComparator.DIRECTION_ASCENDING), true);
		map.setSort(true);
		
//		assertTrue(map.size() == 10);
//		assertTrue(map.isSorted());
//		assertTrue(map.isSort());
//		int index = map.indexOfSortedKey("void");
//		assertTrue(index == 9);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#isSort()}.
	 */
	@Test
	public void testIsSort()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, true);
//		assertTrue(map.isSort());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#isSorted()}.
	 */
	@Test
	public void testIsSorted()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, true);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.isSorted());
//		assertTrue(map.isSort());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#isDirty()}.
	 */
	@Test
	public void testIsDirty()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
//		assertTrue(!map.isDirty());
		map.put("void", "void_rule");
//		assertTrue(map.isDirty());
		map.sort();
//		assertTrue(!map.isDirty());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#clear()}.
	 */
	@Test
	public void testClear()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.size() == 10);
//		assertTrue(map.isDirty());
//		assertTrue(!map.isSort());
//		assertTrue(!map.isSorted());
		
		map.clear();
		
//		assertTrue(map.size() == 0);
//		assertTrue(!map.isDirty());
//		assertTrue(!map.isSort());
//		assertTrue(!map.isSorted());
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#containsKey(java.lang.Object)}.
	 */
	@Test
	public void testContainsKey()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.containsKey("boolean"));
//		assertTrue(map.containsKey("int"));
//		assertFalse(map.containsKey("Int"));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#containsValue(java.lang.Object)}.
	 */
	@Test
	public void testContainsValue()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.containsValue("boolean_rule"));
//		assertTrue(map.containsValue("int_rule"));
//		assertFalse(map.containsValue("Int_rule"));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#entrySet()}.
	 */
	@Test
	public void testEntrySet()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, false);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
		Set <Map.Entry<String,String>> set = map.entrySet();
		
//		assertTrue(set.size() == 10);
		
		Iterator <Map.Entry<String,String>> iterator = set.iterator();
		int index = 0;
		while(iterator.hasNext())
		{
			Map.Entry<String,String> entry = iterator.next();
			if(entry.getKey().equals("void") && index == 0)
			{
//				assertTrue(entry.getValue().equals("void_rule"));
			}
			else if(entry.getKey().equals("public") && index == 1)
			{
//				assertTrue(entry.getValue().equals("public_rule"));
			}
			else if(entry.getKey().equals("int") && index == 2)
			{
//				assertTrue(entry.getValue().equals("int_rule"));
			}
			index++;
		}
	}


	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#indexOfSortedKey(java.lang.Object)}.
	 */
	@Test
	public void testIndexOfSortedKey()
	{
		UtilMap <String, String> map = new UtilMap <String, String> (4, 4, true);
		map.put("void", "void_rule");
		map.put("public", "public_rule");
		map.put("int", "int_rule");
		map.put("protected", "protected_rule");
		map.put("static", "static_rule");
		map.put("class", "class_rule");
		map.put("private", "private_rule");
		map.put("interface", "interface_rule");
		map.put("boolean", "boolean_rule");
		map.put("transient", "transient_rule");
		
//		assertTrue(map.indexOfSortedKey("void") == 0);
//		assertTrue(map.indexOfSortedKey("static") == 4);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#get(java.lang.Object)}.
	 */
	@Test
	public void testGet()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#isEmpty()}.
	 */
	@Test
	public void testIsEmpty()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#keySet()}.
	 */
	@Test
	public void testKeySet()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#put(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testPut()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#putAll(java.util.Map)}.
	 */
	@Test
	public void testPutAll()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemove()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#size()}.
	 */
	@Test
	public void testSize()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#values()}.
	 */
	@Test
	public void testValues()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.UtilMap#sort()}.
	 */
	@Test
	public void testSort()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#Object()}.
	 */
	@Test
	public void testObject()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#getClass()}.
	 */
	@Test
	public void testGetClass()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#hashCode()}.
	 */
	@Test
	public void testHashCode()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#clone()}.
	 */
	@Test
	public void testClone()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#notify()}.
	 */
	@Test
	public void testNotify()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#notifyAll()}.
	 */
	@Test
	public void testNotifyAll()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long)}.
	 */
	@Test
	public void testWaitLong()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long, int)}.
	 */
	@Test
	public void testWaitLongInt()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait()}.
	 */
	@Test
	public void testWait()
	{
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#finalize()}.
	 */
	@Test
	public void testFinalize()
	{
		//fail("Not yet implemented");
	}

}
