package com.sos.tools.test.utilities.collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.collection.UtilList;
import com.sos.tools.utilities.comparitor.StringComparator;

public class UtilListTest
{

	private UtilList <String> list;
	private static final String [] array = {"Louis","Sofia","Docia","Roxanna"};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		
	}

	@Before
	public void setUp() throws Exception
	{
		list = new UtilList<String>();
		list.add("Louis Weyrich");		//  3
		list.add("Sofia Romanchuk");	//  6
		list.add("Roxanna Dyrved");		//  5
		list.add("Docia Weyrich"); 		//  0
		list.add("Mike Atchison");		//  4
		list.add("Ken Greer");			//  2
		list.add("Geoff Wilder");		//	1
		list.add("Taft McWhorter");		//  7
	}

	@After
	public void tearDown() throws Exception
	{
		list.clear();
		list = null;
	}


	@Test
	public void testUtilList()
	{
		assertTrue(list.size() == 8);
		assertTrue(list.get(1) == "Sofia Romanchuk");
	}

	@Test
	public void testUtilListEArray()
	{
		list = new UtilList<String>(array);
		
		assertTrue(list.size() == 4);
		assertTrue(list.get(1) == "Sofia");
	}

	@Test
	public void testUtilListEArrayBooleanComparatorOfE()
	{
		list = new UtilList<String>(array, true, new StringComparator());
		
		assertTrue(list.size() == 4);
		assertTrue(list.get(1) == "Louis");
		
		list = new UtilList<String>(array, false, null);
		
		assertTrue(list.get(1) == "Sofia");
	}

	@Test
	public void testUtilListEArrayIntBooleanComparatorOfE()
	{
		String [] array = 
		{
			"Louis Weyrich","Sofia Romanchuk", "Roxanna Dyrved", 
			"Docia Weyrich", "Mike Atchison","Ken Greer", "Geoff Wilder",
			"Taft McWhorter"
		};
		
		list = new UtilList<String>(array, 5, true, new StringComparator());
		
		assertTrue(list.size() == 8);
		assertTrue(list.get(0) == "Docia Weyrich");
		assertTrue(list.get(6) == "Sofia Romanchuk");
		assertTrue(list.get(7) == "Taft McWhorter");
	}

	@Test
	public void testUtilListCollectionOfEBooleanComparatorOfE()
	{
		List <String> collection = new ArrayList <String> (8);
		collection.add("Louis Weyrich");
		collection.add("Sofia Romanchuk");
		collection.add("Roxanna Dyrved");
		collection.add("Docia Weyrich");
		collection.add("Mike Atchison");
		collection.add("Ken Greer");
		collection.add("Geoff Wilder");
		collection.add("Taft McWhorter");
		
		list = new UtilList<String>(collection, true, new StringComparator());
		assertTrue(list.size() == 8);
		assertTrue(list.get(0) == "Docia Weyrich");
		assertTrue(list.get(6) == "Sofia Romanchuk");
		assertTrue(list.get(7) == "Taft McWhorter");

	}

	@Test
	public void testUtilListInt()
	{
		list = new UtilList<String>(5);
		
		List <String> collection = new ArrayList <String> (8);
		collection.add("Louis Weyrich");
		collection.add("Sofia Romanchuk");
		collection.add("Roxanna Dyrved");
		collection.add("Docia Weyrich");
		collection.add("Mike Atchison");
		collection.add("Ken Greer");
		collection.add("Geoff Wilder");
		collection.add("Taft McWhorter");
		
		list.addAll(collection);
		assertTrue(list.size() == 8);
		assertTrue(list.get(0) == "Louis Weyrich");
		assertTrue(list.get(6) == "Geoff Wilder");
		assertTrue(list.get(7) == "Taft McWhorter");
	}

	@Test
	public void testUtilListIntInt()
	{
list = new UtilList<String>(5, 5);
		
		List <String> collection = new ArrayList <String> (8);
		collection.add("Louis Weyrich");
		collection.add("Sofia Romanchuk");
		collection.add("Roxanna Dyrved");
		collection.add("Docia Weyrich");
		collection.add("Mike Atchison");
		collection.add("Ken Greer");
		collection.add("Geoff Wilder");
		collection.add("Taft McWhorter");
		
		list.addAll(collection);
		assertTrue(list.size() == 8);
		assertTrue(list.get(0) == "Louis Weyrich");
		assertTrue(list.get(6) == "Geoff Wilder");
		assertTrue(list.get(7) == "Taft McWhorter");
	}

	@Test
	public void testSetSort()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		
		for(int index = 0; index < list.size(); index++)
		{
			String value = list.get(index);
			
			if(index == 0) assertTrue(value == "Docia Weyrich");
			else if(index == 2) assertTrue(value == "Ken Greer");
			else if(index == 4) assertTrue(value == "Mike Atchison");
			else if(index == 6) assertTrue(value == "Sofia Romanchuk");
		}
	}

	@Test
	public void testIsSort()
	{
		assertTrue(!list.isSort());
		assertTrue(!list.isSorted());
		assertTrue(list.get(0) == "Louis Weyrich");
		list.setComparator(new StringComparator());
		list.setSort(true);
		assertTrue(list.isSort());
		assertTrue(list.isSorted());
		assertTrue(list.get(0) == "Docia Weyrich");
	}

	@Test
	public void testIsSorted()
	{
		assertFalse(list.isSorted());
		list.setSort(true);
		assertTrue(list.isSorted());
	}

	@Test
	public void testIsDirty()
	{
		assertTrue(list.isDirty());
	}

	@Test
	public void testClearDirty()
	{
		assertTrue(list.isDirty());
		assertTrue(list.clearDirty());
		assertFalse(list.isDirty());
	}

	@Test
	public void testAddE()
	{
		list.setComparator(new StringComparator());
		assertTrue(list.size() == 8);
		assertTrue(list.add("New Element"));
		assertTrue(list.size() == 9);
	}

	@Test
	public void testAddIntE()
	{
		list.setSort(false);
		list.setComparator(new StringComparator());
		assertTrue(list.size() == 8);
		list.add(5, "New Element");
		assertTrue(list.size() == 9);
		assertTrue(list.get(4) == "Mike Atchison");
		assertTrue(list.get(5) == "New Element");
		assertTrue(list.get(6) == "Ken Greer");
		
		list.setSort(true);
		assertTrue(list.get(4) == "Mike Atchison");
		assertTrue(list.get(5) == "New Element");
		assertTrue(list.get(6) == "Roxanna Dyrved");
	}

	@Test
	public void testAddAllCollectionOfQextendsE()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		
		assertTrue(list.size() == 8);
		
		List <String> collection = new ArrayList <String> (3);
		collection.add("Sofia Weyrich"); 		// 7
		collection.add("Baby Boy Weyrich"); 	// 0
		collection.add("Baby Girl Weyrich"); 	// 1
		
		assertTrue(list.addAll(collection));
		assertTrue(list.get(8) == "Sofia Romanchuk");
		assertTrue(list.get(9) == "Sofia Weyrich");
		assertTrue(list.get(0) == "Baby Boy Weyrich");
		assertTrue(list.get(1) == "Baby Girl Weyrich");
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE()
	{
		list.setComparator(new StringComparator());
		list.setSort(false);
		assertTrue(list.size() == 8);
		
		List <String> collection = new ArrayList <String> (3);
		collection.add("Sofia Weyrich"); 		// 7
		collection.add("Baby Boy Weyrich"); 	// 8
		collection.add("Baby Girl Weyrich"); 	// 9

		assertTrue(list.addAll(7,collection));
		assertTrue(list.get(6).equals("Geoff Wilder"));
		assertTrue(list.get(7) == "Sofia Weyrich");
		assertTrue(list.get(8) == "Baby Boy Weyrich");
		assertTrue(list.get(9) == "Baby Girl Weyrich");
		assertTrue(list.get(10) == "Taft McWhorter");
	}

	@Test
	public void testClear()
	{
		assertTrue(list.size() == 8);
		list.clear();
		assertTrue(list.size() == 0);
	}

	@Test
	public void testContains()
	{
		list.setComparator(new StringComparator());
		assertTrue(list.contains("Sofia Romanchuk"));
		assertFalse(list.contains("sofia romanchuk"));
	}

	@Test
	public void testContainsAll()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		
		List <String> collection = new ArrayList <String> (3);
		collection.add("Sofia Romanchuk");
		collection.add("Louis Weyrich");
		collection.add("Mike Atchison");
		
		assertTrue(list.containsAll(collection));
		
		collection.add("Sofia Weyrich");
		
		assertFalse(list.containsAll(collection));
	}

	@Test
	public void testGet()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		
		assertTrue(list.get(0) == "Docia Weyrich");
		assertTrue(list.get(2) == "Ken Greer");
		assertTrue(list.get(4) == "Mike Atchison");
		assertTrue(list.get(6) == "Sofia Romanchuk");
	}

	@Test
	public void testGetAllValues()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		Object [] array = list.getAllValues();
		int index = 0;
		
		for(Object value : array)
		{
			if(index == 0) assertTrue(value.toString() == "Docia Weyrich");
			else if(index == 2) assertTrue(value.toString() == "Ken Greer");
			else if(index == 4) assertTrue(value.toString() == "Mike Atchison");
			else if(index == 6) assertTrue(value.toString() == "Sofia Romanchuk");
			index++;
		}
	}

	@Test
	public void testIndexOf()
	{
		list.setComparator(new StringComparator());
		list.setSort(false);
		
		assertTrue(list.indexOf("Ken Greer") == 5);
		list.setSort(true);
		assertTrue(list.indexOf("Roxanna Dyrved") == 5);
	}

	@Test
	public void testIndexOfSorted()
	{
		list.setComparator(new StringComparator());
		list.setSort(false);
		
		assertTrue(list.indexOf("Ken Greer") == 5);
		list.setSort(true);
		assertTrue(list.indexOfSorted("Roxanna Dyrved") == 5);
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(!list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	public void testIterator()
	{
		Iterator <String> iterator = list.iterator();
		assertNotNull(iterator != null);
		assertTrue(iterator.hasNext());
		int index = 0;
		
		for(index = 0; iterator.hasNext(); index++)
		{
			String value = iterator.next();
			assertNotNull(value);
		}
		
		assertTrue(index == 8);
	}

	@Test
	public void testLastIndexOf()
	{
		list = new UtilList<String>();
		list.add("Taft McWhorter");		
		list.add("Louis Weyrich");		
		list.add("Sofia Romanchuk");	
		list.add("Roxanna Dyrved");		
		list.add("Docia Weyrich"); 		
		list.add("Taft McWhorter");		
		list.add("Mike Atchison");		
		list.add("Ken Greer");			
		list.add("Geoff Wilder");
		
		assertTrue(list.lastIndexOf("Taft McWhorter")  == 5);
		assertTrue(list.indexOf("Taft McWhorter")  == 0);
	}

	@Test
	public void testListIterator()
	{
		ListIterator <String> iterator = list.listIterator();
		assertNotNull(iterator != null);
		assertTrue(iterator.hasNext());
		assertFalse(iterator.hasPrevious());
		int index = 0;
		
		for(index = 0; iterator.hasNext(); index++)
		{
			String value = iterator.next();
			assertNotNull(value);
			if(index > 0)
			{
				assertTrue(iterator.hasPrevious());
			}
		}
		
		assertTrue(index == 8);
	}

	@Test
	public void testListIteratorInt()
	{
		ListIterator <String> iterator = list.listIterator(4);
		assertNotNull(iterator != null);
		assertTrue(iterator.hasNext());
		assertFalse(iterator.hasPrevious());
		int index = 0;
		
		for(index = 0; iterator.hasNext(); index++)
		{
			String value = iterator.next();
			assertNotNull(value);
			if(index > 0)
			{
				assertTrue(iterator.hasPrevious());
			}
		}
		
		assertTrue(index == 3);
	}

	@Test
	public void testRemoveObject()
	{
		list.setComparator(new StringComparator());
		list.setSort(false);
		
		assertTrue(list.size() == 8);
		
		assertTrue(list.get(0) == "Louis Weyrich");
		assertTrue(list.get(1) == "Sofia Romanchuk");
		assertTrue(list.get(2) == "Roxanna Dyrved");

		assertTrue(list.remove("Sofia Romanchuk"));
		
		assertTrue(list.size() == 7);
		
		assertTrue(list.get(0) == "Louis Weyrich");
		assertTrue(list.get(1) == "Roxanna Dyrved");
		assertTrue(list.get(2) == "Docia Weyrich");
	}

	@Test
	public void testRemoveInt()
	{
		list.setComparator(new StringComparator());
		list.setSort(false);
		
		assertTrue(list.size() == 8);
		
		assertTrue(list.get(0) == "Louis Weyrich");
		assertTrue(list.get(1) == "Sofia Romanchuk");
		assertTrue(list.get(2) == "Roxanna Dyrved");

		assertTrue(list.remove(1) == "Sofia Romanchuk");
		
		assertTrue(list.size() == 7);
		
		assertTrue(list.get(0) == "Louis Weyrich");
		assertTrue(list.get(1) == "Roxanna Dyrved");
		assertTrue(list.get(2) == "Docia Weyrich");
	}

	@Test
	public void testRemoveAll()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		assertTrue(list.size() == 8);
		
		List <String> collection = new ArrayList <String> (3);
		collection.add("Sofia Romanchuk");
		collection.add("Louis Weyrich");
		collection.add("Mike Atchison");

		assertTrue(list.removeAll(collection));
		assertTrue(list.size() == 5);
		assertFalse(list.contains("Louis Weyrich"));
		assertFalse(list.contains("Sofia Romanchuk"));
		assertFalse(list.contains("Mike Atchison"));
		assertTrue(list.contains("Docia Weyrich"));
	}

	@Test
	public void testRetainAll()
	{
		list.setComparator(new StringComparator());
		list.setSort(true);
		assertTrue(list.size() == 8);
		
		List <String> collection = new ArrayList <String> (3);
		collection.add("Sofia Romanchuk");
		collection.add("Louis Weyrich");
		collection.add("Mike Atchison");

		assertTrue(list.retainAll(collection));
		assertTrue(list.size() == 3);
		assertTrue(list.contains("Louis Weyrich"));
		assertTrue(list.contains("Sofia Romanchuk"));
		assertTrue(list.contains("Mike Atchison"));
		assertFalse(list.contains("Docia Weyrich"));
		assertFalse(list.contains("Ken Greer"));
	}

	@Test
	public void testSet()
	{
		String value = list.get(5);
		assertTrue(value.equals("Ken Greer"));
		assertTrue(list.set(5, "Baby Sofia").equals(value));
		value = list.get(5);
		assertTrue(value.equals("Baby Sofia"));
	}

	@Test
	public void testSubList()
	{
		List <String> subList = list.subList(5, 7);
		assertNotNull(subList.size());
		assertTrue(subList.size() == 3);
		assertTrue(subList.get(0) == "Ken Greer");
		assertTrue(subList.get(2) == "Taft McWhorter");
		
		list.setComparator(new StringComparator());
		list.setSort(true);
		subList = list.subList(5, 7);
		assertTrue(subList.size() == 3);
		assertTrue(subList.get(0) == "Roxanna Dyrved");
		assertTrue(subList.get(2) == "Taft McWhorter");
	}

	@Test
	public void testToArray()
	{
		Object [] array = list.toArray();
		assertTrue(array.length == 8);
	}

	@Test
	public void testToArrayTArray()
	{
		String [] array = new String[0];
		array = list.toArray(array);
		assertTrue(array.length == 8);
	}

	@Test
	public void testSort()
	{
		list.setComparator(new StringComparator());
		list.sort();
		
		for(int index = 0; index < list.size(); index++)
		{
			String value = list.get(index);
			
			if(index == 0) assertTrue(value == "Docia Weyrich");
			else if(index == 2) assertTrue(value == "Ken Greer");
			else if(index == 4) assertTrue(value == "Mike Atchison");
			else if(index == 6) assertTrue(value == "Sofia Romanchuk");
		}
		
	}

}
