package com.sos.tools.test.utilities;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.io.CharacterBuffer;

public class TestCharacterBuffer
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
	public void testCharacterBufferInt()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCharacterBufferIntIntInt()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCharacterBufferIntIntIntBoolean()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testClear()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testGetBufferSize()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckAvailablilty()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAppendCharArrayInt()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testAppendString()
	{
		String s1 = 
			"My name is Louis Weryrich.  I am the Myth!  You may not know who I am," //70
			+ " but I know who you are.  I stalk you when you are asleep and watch you " //72
			+ "while you dream.  Don't worry, I am harmless."; //45
		
		CharacterBuffer buffer = new CharacterBuffer(20);
		try
		{
			buffer.append(s1);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s2 = buffer.toString();
		
		System.out.println(s2);
		
		assertTrue(buffer.getBufferSize() == 187);
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testSubset()
	{
		//fail("Not yet implemented");
	}

	@Test
	public void testChatAt()
	{
		//fail("Not yet implemented");
	}

}
