package com.sos.tools.test.utilities.collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.collection.ArraySet;

public class ArraySetTest
{

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
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testArraySetAddIsEmptySizeContains()
	{
		ArraySet <String> test = new ArraySet <String> ();
		
		try
		{
			test.add("Louis");
			test.add("Alyona");
			test.add("Roxanna");
			test.add("Odin");
			test.add("Neils");
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assertTrue(!test.isEmpty());
		assertTrue(test.size() == 5);
		assertTrue(test.contains("Alyona"));
	}

	@Test
	public void testArraySetInitialSize()
	{
		ArraySet <String> test = new ArraySet <String> (3);
		
		try
		{
			test.add("Louis");
			test.add("Alyona");
			test.add("Roxanna");
			test.add("Odin");
			test.add("Neils");
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assertTrue(!test.isEmpty());
		assertTrue(test.size() == 5);
		assertTrue(test.contains("Odin"));
	}
	

	@Test
	public void testArraySetToArray()
	{
		ArraySet <String> test = new ArraySet <String> (3);
		
		try
		{
			test.add("Louis");
			test.add("Alyona");
			test.add("Roxanna");
			test.add("Odin");
			test.add("Neils");
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		Object [] arrayTest = test.toArray();
		assertTrue(arrayTest.length == 5);
		assertTrue(arrayTest[0].equals("Louis"));
	}
	

	@Test
	public void testArraySetToArrayArray()
	{
		ArraySet <String> test = new ArraySet <String> ();
		
		try
		{
			assertTrue(test.add("Louis"));
			assertTrue(test.add("Alyona"));
			assertTrue(test.add("Roxanna"));
			assertTrue(test.add("Odin"));
			assertTrue(test.add("Neils"));
			assertFalse(test.add("Neils"));
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		String [] arrayTest = new String[0];
		arrayTest = test.toArray(arrayTest);
		assertTrue(arrayTest.length == 5);
		assertTrue(arrayTest[0].equals("Louis"));
	}
	
	

	@Test
	public void testArraySetDeltas()
	{
		ArraySet <String> test = new ArraySet <String> ();
		
		try
		{
			test.add("Louis");
			test.add("Alyona");
			test.add("Roxanna");
			test.add("Odin");
			test.add("Neils");
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		String [] deltaData = {"Louis","Odin","Eugene","Docia","Alyona"};
		String [] deltaReturns = test.getDeltas(deltaData);
		
		assertTrue(deltaReturns.length == 2);
		assertTrue(deltaReturns[0].equals("Eugene"));
	}


	@Test
	public void testArraySetIterator()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		Iterator <String> iterator = test.iterator();
		int index = 0;
		while(iterator.hasNext())
		{
			assertTrue(iterator.next().equals(data[index++]));
		}
	
	}

	@Test
	public void testArraySetRemove()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assertTrue(test.remove("Neils"));
	
	}
	

	@Test
	public void testArraySetRemoveAll()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		String [] removedata = {"Roxanna","Odin","Neils"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assertTrue(test.removeAll(removedata));
		assertTrue(test.contains("Alyona"));
		assertFalse(test.contains("Roxanna"));
	}
	

	@Test
	public void testArraySetRetainAll()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		String [] retaindata = {"Louis","Alyona","Eugene"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assertTrue(test.retainAll(retaindata));
		assertTrue(test.contains("Alyona"));
		assertFalse(test.contains("Roxanna"));
	}

	@Test
	public void testArraySetContainsAll()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		String [] containsdata = {"Roxanna","Odin","Neils"};
		String [] baddata = {"Steve","Odin","Neils"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		assertTrue(test.containsAll(containsdata));
		assertFalse(test.containsAll(baddata));
	}
	

	@Test
	public void testArraySetPopFromBottom()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		String pop = test.popFromBottom();
		assertTrue(pop.equals(data[5]));
		
		pop = test.popFromBottom();
		assertTrue(pop.equals(data[4]));
	}
	

	@Test
	public void testArraySetPopFromBottomCount()
	{
		ArraySet <String> test = new ArraySet <String> ();
		String [] data = {"Louis","Alyona","Roxanna","Odin","Neils","Eugene"};
		
		try
		{
			test.add(data[0]);
			test.add(data[1]);
			test.add(data[2]);
			test.add(data[3]);
			test.add(data[4]);
			test.add(data[5]);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
		String [] pop = test.popFromBottom(3, new String[3]);
		assertTrue(pop[0].equals(data[5]));
		assertTrue(pop[1].equals(data[4]));
		assertTrue(pop[2].equals(data[3]));
	}
}
