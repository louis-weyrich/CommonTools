package com.sos.tools.test.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sos.tools.model.TreeDataModel;
import com.sos.tools.model.TreeObject;


public class TreeDataModelTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		TreeDataModel model = new TreeDataModel();
		model.addNode("context.setup.db", "db-connection", "jdbc:connecion");
		model.addNode("context.users", "current-user", "LWeyrich");
		model.addNode("context.setup.db", "connection-pool-count", new Integer(8));
		
		TreeObject <Integer> count = model.getTreeNode("context.setup.db.connection-pool-count", false);
		assertTrue(count.getObject().intValue() == 8);
		assertTrue(model.getTreeNode("context.users.current-user", false).getObject().equals("LWeyrich"));
		Integer object = model.removeNode("context.setup.db", "connection-pool-count");
		assertTrue(model.getTreeNode("context.setup.db.connection-pool-count", false) == null);
		assertTrue(object.intValue() == 8);
	}

}
