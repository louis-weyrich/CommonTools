/**
 * 
 */
package com.sos.tools.test.utilities.btree;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.RandomUtil;
import com.sos.tools.utilities.btree.BinaryTree;

/**
 * @author louisweyrich
 *
 */
public class BinaryTreeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddUniqueRandomNumbers() {
		BinaryTree <Integer, String> tree = new BinaryTree <Integer, String> ();
		RandomUtil util = new RandomUtil(0, 20);
		
		for(int index = 0; index < 20; index++)
		{
			tree.addNode(util.generateUniqueRandom(), "");
		}
		
		assertTrue(tree.size() == 20);
	}
}
