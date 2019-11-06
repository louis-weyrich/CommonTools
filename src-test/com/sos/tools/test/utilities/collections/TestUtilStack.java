package com.sos.tools.test.utilities.collections;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.collection.UtilStack;

public class TestUtilStack
{
	
	private static UtilStack <String> stack;

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
		stack = new UtilStack <String> ();
		stack.push("Odin");
		stack.push("Roxanna");
		stack.push("Louis");
		stack.push("Docia");
		stack.push("Herman");
		stack.push("Steve");
		stack.push("Selma");
		stack.push("Grandpa");
	}

	@After
	public void tearDown() throws Exception
	{
//		stack.clear();
//		stack = null;
	}

	@Test
	public void testClear()
	{
		assertTrue("Testing size after clear", true);
		stack.clear();
		assertTrue("", stack.size() == 0);
	}

	@Test
	public void testSize()
	{
		assertTrue("Testing size: "+stack.size(), true);
		assertTrue("", stack.size() > 0);
	}
	

	@Test
	public void testPush()
	{
		assertTrue("Testing Push before: "+stack.size(), stack.size() == 8);
		stack.push("Alyona");
		assertTrue("Testing Push before: "+stack.size(), stack.size() == 9);
	}

	@Test
	public void testPop()
	{
//		assertTrue("Testing Pop before: "+stack.size(), stack.size() == 8);
//		String grandpa = stack.pop();
//		assertTrue("Pop from stack: "+grandpa, grandpa.equals("Grandpa"));
//		assertTrue("Testing Push before: "+stack.size(), stack.size() == 7);
	}

	@Test
	public void testSearch()
	{
		int dociaIndex = stack.search("Docia");
		assertTrue("Search for Docia: "+dociaIndex, dociaIndex == 3);
	}

}
