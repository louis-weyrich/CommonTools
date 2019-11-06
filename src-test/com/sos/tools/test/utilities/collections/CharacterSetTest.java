/**
 * 
 */
package com.sos.tools.test.utilities.collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.tools.utilities.collection.CharacterSet;

/**
 * @author louis.weyrich
 *
 */
public class CharacterSetTest
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
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#CharacterSet()}.
	 */
	@Test
	public void testCharacterSet()
	{
		CharacterSet charSet = new CharacterSet();
		assertTrue(charSet.getLoadFactor() == CharacterSet.DEFAULT_INITIAL_SIZE);
		assertTrue(charSet.size() == 0);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#CharacterSet(int)}.
	 */
	@Test
	public void testCharacterSetInt()
	{
		CharacterSet charSet = new CharacterSet(CharacterSet.DEFAULT_INITIAL_SIZE);
		assertTrue(charSet.getLoadFactor() == CharacterSet.DEFAULT_INITIAL_SIZE);
		assertTrue(charSet.size() == 0);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#indexOfSorted(java.lang.Character)}.
	 */
	@Test
	public void testIndexOfSorted()
	{
		CharacterSet charSet = new CharacterSet(10);
		
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		assertTrue(charSet.indexOfSorted(new Character('\n')) == 0);
		assertTrue(charSet.indexOfSorted(new Character(' ')) == 1);
		assertTrue(charSet.indexOfSorted(new Character('+')) == 2);
		assertTrue(charSet.indexOfSorted(new Character('0')) == 3);
		assertTrue(charSet.indexOfSorted(new Character('7')) == 4);
		assertTrue(charSet.indexOfSorted(new Character('A')) == 5);
		assertTrue(charSet.indexOfSorted(new Character('a')) == 6);
		assertTrue(charSet.indexOfSorted(new Character('n')) == 7);
		assertTrue(charSet.indexOfSorted(new Character('{')) == 8);
		assertTrue(charSet.indexOfSorted(new Character('}')) == 9);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#size()}.
	 */
	@Test
	public void testSize()
	{
		CharacterSet charSet = new CharacterSet(10);
		
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		assertTrue(charSet.size() == 10);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#isEmpty()}.
	 */
	@Test
	public void testIsEmpty()
	{
		CharacterSet charSet = new CharacterSet(10);
		
		assertTrue(charSet.isEmpty() == true);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		assertTrue(charSet.isEmpty() == false);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains()
	{
		CharacterSet charSet = new CharacterSet(10);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		assertTrue(charSet.contains(new Character('7')));
		assertTrue(charSet.contains(new Character('\n')));
		assertTrue(charSet.contains(new Character(' ')));
		assertTrue(charSet.contains(new Character('}')));
		assertFalse(charSet.contains(new Character('[')));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#iterator()}.
	 */
	@Test
	public void testIterator()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		Iterator <Character> iterator = charSet.iterator();
		int index = 0;
		while(iterator.hasNext())
		{
			Character ch = iterator.next();
			if(index == 0)
			{
				assertTrue(ch.charValue() == '\n');
			}
			else if(index == 1)
			{
				assertTrue(ch.charValue() == ' ');
			}
			else if(index == 2)
			{
				assertTrue(ch.charValue() == '+');
			}
			else if(index == 3)
			{
				assertTrue(ch.charValue() == '0');
			}
			else if(index == 4)
			{
				assertTrue(ch.charValue() == '7');
			}
			else if(index == 5)
			{
				assertTrue(ch.charValue() == 'A');
			}
			else if(index == 6)
			{
				assertTrue(ch.charValue() == 'a');
			}
			else if(index == 7)
			{
				assertTrue(ch.charValue() == 'n');
			}
			else if(index == 8)
			{
				assertTrue(ch.charValue() == '{');
			}
			else if(index == 9)
			{
				assertTrue(ch.charValue() == '}');
			}
			
			index++;
		}
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#toArray()}.
	 */
	@Test
	public void testToArray()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		Character [] charArray = charSet.toArray();
		int index = 0;
		for(Character ch : charArray)
		{
			if(index == 0)
			{
				assertTrue(ch.charValue() == '\n');
			}
			else if(index == 1)
			{
				assertTrue(ch.charValue() == ' ');
			}
			else if(index == 2)
			{
				assertTrue(ch.charValue() == '+');
			}
			else if(index == 3)
			{
				assertTrue(ch.charValue() == '0');
			}
			else if(index == 4)
			{
				assertTrue(ch.charValue() == '7');
			}
			else if(index == 5)
			{
				assertTrue(ch.charValue() == 'A');
			}
			else if(index == 6)
			{
				assertTrue(ch.charValue() == 'a');
			}
			else if(index == 7)
			{
				assertTrue(ch.charValue() == 'n');
			}
			else if(index == 8)
			{
				assertTrue(ch.charValue() == '{');
			}
			else if(index == 9)
			{
				assertTrue(ch.charValue() == '}');
			}
			
			index++;
		}
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#add(java.lang.Character)}.
	 */
	@Test
	public void testAddCharacter()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(new Character(' '))); // 32   -  1
		assertTrue(charSet.add(new Character('n'))); // 110  -  7
		assertTrue(charSet.add(new Character('0'))); // 48   -  3
		assertTrue(charSet.add(new Character('A'))); // 65   -  5
		assertTrue(charSet.add(new Character('a'))); // 97   -  6
		assertTrue(charSet.add(new Character('}'))); // 125  -  9
		assertTrue(charSet.add(new Character('7'))); // 55   -  4
		assertTrue(charSet.add(new Character('+'))); // 43   -  2
		assertTrue(charSet.add(new Character('{'))); // 123  -  8
		assertTrue(charSet.add(new Character('\n'))); // 10  -  0
		assertFalse(charSet.add(new Character(' ')));
		assertFalse(charSet.add(new Character('\n')));
		assertFalse(charSet.add(new Character('}')));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#add(char)}.
	 */
	@Test
	public void testAddChar()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		assertFalse(charSet.add(new Character(' ')));
		assertFalse(charSet.add('\n'));
		assertFalse(charSet.add('}'));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveObject()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		assertTrue(charSet.remove(new Character('a')));
		assertTrue(charSet.remove(new Character('\n')));
		assertFalse(charSet.remove(new Character('4')));
		assertFalse(charSet.remove(new Character('\r')));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#remove(int)}.
	 */
	@Test
	public void testRemoveInt()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		assertTrue(charSet.remove(4) == '7');
		assertTrue(charSet.remove(8) == '}');
		assertTrue(charSet.remove(0) == '\n');
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#containsAll(java.util.Collection)}.
	 */
	@Test
	public void testContainsAll()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		List <Character> arrayList = new ArrayList <Character> (5);
		arrayList.add(new Character('0'));
		arrayList.add(new Character('a'));
		arrayList.add(new Character('A'));
		arrayList.add(new Character('7'));
		arrayList.add(new Character('\n'));
		
		assertTrue(charSet.containsAll(arrayList));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#addAll(java.util.Collection)}.
	 */
	@Test
	public void testAddAll()
	{
		CharacterSet charSet = new CharacterSet(5);
		
		List <Character> arrayList = new ArrayList <Character> (5);
		arrayList.add(new Character('b'));
		arrayList.add(new Character('4'));
		arrayList.add(new Character('_'));
		arrayList.add(new Character('!'));
		arrayList.add(new Character('-'));
		
		assertTrue(charSet.addAll(arrayList));
		
		assertTrue(charSet.add(' ')); // 32   -  
		assertTrue(charSet.add('n')); // 110  -  
		assertTrue(charSet.add('0')); // 48   -  
		assertTrue(charSet.add('A')); // 65   -  
		assertTrue(charSet.add('a')); // 97   -  
		assertTrue(charSet.add('}')); // 125  -  
		assertTrue(charSet.add('7')); // 55   -  
		assertTrue(charSet.add('+')); // 43   -  
		assertTrue(charSet.add('{')); // 123  -  
		assertTrue(charSet.add('\n')); // 10  -  
		
		arrayList.clear();
		arrayList.add(new Character('b'));
		arrayList.add(new Character('r'));
		arrayList.add(new Character('_'));
		arrayList.add(new Character('7'));
		arrayList.add(new Character('2'));
		
		assertFalse(charSet.addAll(arrayList));
		
		assertTrue(charSet.size() == 17);
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#retainAll(java.util.Collection)}.
	 */
	@Test
	public void testRetainAll()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		List <Character> arrayList = new ArrayList <Character> (5);
		arrayList.add(new Character('0'));
		arrayList.add(new Character('a'));
		arrayList.add(new Character('A'));
		arrayList.add(new Character('7'));
		arrayList.add(new Character('\n'));
		
		assertTrue(charSet.retainAll(arrayList));
		assertTrue(charSet.size() == 5);
		assertTrue(charSet.contains('7'));
		assertFalse(charSet.contains('}'));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#removeAll(java.util.Collection)}.
	 */
	@Test
	public void testRemoveAll()
	{
		CharacterSet charSet = new CharacterSet(5);
		assertTrue(charSet.add(' ')); // 32   -  1
		assertTrue(charSet.add('n')); // 110  -  7
		assertTrue(charSet.add('0')); // 48   -  3
		assertTrue(charSet.add('A')); // 65   -  5
		assertTrue(charSet.add('a')); // 97   -  6
		assertTrue(charSet.add('}')); // 125  -  9
		assertTrue(charSet.add('7')); // 55   -  4
		assertTrue(charSet.add('+')); // 43   -  2
		assertTrue(charSet.add('{')); // 123  -  8
		assertTrue(charSet.add('\n')); // 10  -  0
		
		List <Character> arrayList = new ArrayList <Character> (5);
		arrayList.add(new Character('0'));
		arrayList.add(new Character('a'));
		arrayList.add(new Character('A'));
		arrayList.add(new Character('7'));
		arrayList.add(new Character('\n'));
		
		assertTrue(charSet.removeAll(arrayList));
		assertTrue(charSet.size() == 5);
		assertTrue(charSet.contains('n'));
		assertFalse(charSet.contains('7'));
	}

	/**
	 * Test method for {@link com.sos.tools.utilities.collection.CharacterSet#clear()}.
	 */
	@Test
	public void testClear()
	{
		CharacterSet charSet = new CharacterSet(5);
		
		assertTrue(charSet.isEmpty() == true);
		assertTrue(charSet.add(' '));
		assertTrue(charSet.add('{'));
		assertTrue(charSet.add('}'));
		assertTrue(charSet.add('['));
		assertTrue(charSet.add(']'));
		assertTrue(charSet.isEmpty() == false);
		charSet.clear();
		assertTrue(charSet.isEmpty() == true);
		assertTrue(charSet.size() == 0);
	}

}
