package com.sos.tools.test.utilities;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sos.tools.utilities.Context;


public class ContextTest
{
	
	private Context context;

	@Before
	public void setUp() throws Exception
	{
		context = Context.getInstance();
	}

	@After
	public void tearDown() throws Exception
	{
		context.removeAll();
		context = null;
	}

	@Test
	public void testAddToContextPath()
	{
//		context.addToContextPath("context", "application-context", "Application Context");
//		context.addToContextPath("context", "session-context", "Session Context");
//		context.addToContextPath("setup.database", "database-connection", "jdbc:oracle:thin:@localhost:1521:mkyong");
//		context.addToContextPath("setup.database", "database-username", "username");
//		context.addToContextPath("setup.database", "database-password", "password");
//		context.addToContextPath("context.application-context", "context-name", "context name");
//		context.addToContextPath("setup.database", "pool-count", new Integer(10));
//		context.addToContextPath("setup.product", "tax", new Float(.0875));
//		
//		Integer oldPoolCount = context.addToContextPath("setup.database", "pool-count", new Integer(12));
//		Integer newPoolCount = context.getContext("setup.database.pool-count");
//		
//		assertTrue(oldPoolCount.intValue() == 10);
//		assertTrue(newPoolCount.intValue() == 12);
	}

	@Test
	public void testHasContextPath()
	{
//		testAddToContextPath();
		
//		assertTrue(context.hasContextPath("setup.database"));
//		assertTrue(context.hasContextPath("setup.database.database-connection"));
//		assertTrue(context.hasContextPath("setup.database.pool-count"));
//		assertTrue(context.hasContextPath("context.application-context"));
//		assertTrue(context.hasContextPath("context.application-context.context-name"));
	}

	@Test
	public void testGetContext()
	{
//		testAddToContextPath();
//		
//		String connection = context.getContext("setup.database.database-connection");
//		assertTrue(connection != null);
//		assertTrue(connection.equals("jdbc:oracle:thin:@localhost:1521:mkyong"));
//		assertTrue(context.getContext("setup.database.pool-count") instanceof Integer);
//		Integer poolCount = context.getContext("setup.database.pool-count");
//		assertTrue(poolCount.intValue() == 12);
//		Float tax = context.getContext("setup.product.tax");
//		assertTrue(tax.floatValue() == .0875f);
	}

	@Test
	public void testRemoveContext()
	{
//		testAddToContextPath();
//		
//		Integer poolCount = context.removeContext("setup.database", "pool-count");
//		assertTrue(poolCount.intValue() == 12);
//		assertFalse(context.hasContextPath("setup.database.pool-count"));
	}

}
