/**
 * 
 */
package com.sos.tools.test.utilities;

import org.junit.Test;

import com.sos.tools.utilities.collection.UtilIterator;
/**
 * @author louis.weyrich
 *
 */
public class UtilIteratorTest {

	@Test
	public void testUtilIterator()
	{
		String startValue = new String("system user address zip");
		String [] values = startValue.split(" ");
		UtilIterator<String> iterator = new UtilIterator<String>(values);
		
		while(iterator.hasNext())
		{
			String value = iterator.next();
			System.out.println(value);
			iterator.remove();
		}
	}

}
