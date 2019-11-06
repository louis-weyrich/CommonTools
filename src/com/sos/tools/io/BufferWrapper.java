package com.sos.tools.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BufferWrapper
{
	
	protected StringBuilder buffer;

	public BufferWrapper()
	{
		buffer = new StringBuilder();
	}

	public BufferWrapper(String initializeString)
	{
		buffer = new StringBuilder(initializeString);
	}

	public BufferWrapper append(String value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(int value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(boolean value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(char value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(char [] value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(char[] value, int offset, int len)
	{
		buffer = buffer.append(value, offset, len);
		return this;
	}

	public BufferWrapper append(float value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(double value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(long value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(byte value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(byte [] byteArray)
	{
		for(byte value : byteArray)
		{
			buffer = buffer.append((char)value);
		}
		return this;
	}

	public BufferWrapper append(Object value)
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(CharSequence value) 
	{
		buffer = buffer.append(value);
		return this;
	}

	public BufferWrapper append(CharSequence value, int start, int end) 
	{
		buffer = buffer.append(value, start, end);
		return this;
	}

	public BufferWrapper append(StringBuffer value) 
	{
		buffer = buffer.append(value);
		return this;
	}

	public char charAt(int index) 
	{
		return buffer.charAt(index);
	}

	public BufferWrapper delete(int start, int end) 
	{
		buffer = buffer.delete(start, end);
		return this;
	}

	public BufferWrapper deleteCharAt(int index) 
	{
		buffer = buffer.deleteCharAt(index);
		return this;
	}

	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) 
	{
		buffer.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	public int indexOf(String value) 
	{
		return buffer.indexOf(value);
	}

	public int indexOf(String value, int fromIndex)
	{
		return buffer.indexOf(value, fromIndex);
	}

	public BufferWrapper insert(int offset, boolean b)
	{
		buffer.insert(offset, b);
		return this;
	}

	public BufferWrapper insert(int offset, char value) 
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int offset, char[] value) 
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int index, char[] value, int offset, int len) 
	{
		buffer.insert(index, value, offset, len);
		return this;
	}

	public BufferWrapper insert(int dstOffset, CharSequence value, int start, int end) 
	{
		buffer.insert(dstOffset, value, start, end);
		return this;
	}

	public BufferWrapper insert(int offset, double value)
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int offset, float value)
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int offset, int value) 
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int offset, long value) 
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int offset, Object value) 
	{
		buffer.insert(offset, value);
		return this;
	}

	public BufferWrapper insert(int offset, String value) 
	{
		buffer.insert(offset, value);
		return this;
	}

	public int lastIndexOf(String value) 
	{
		return buffer.lastIndexOf(value);
	}

	public int lastIndexOf(String value, int fromIndex) 
	{
		return buffer.lastIndexOf(value, fromIndex);
	}

	public int length()
	{
		return buffer.length();
	}

	public BufferWrapper replace(int start, int end, String value) 
	{
		buffer = buffer.replace(start, end, value);
		return this;
	}

	public BufferWrapper reverse()
	{
		buffer = buffer.reverse();
		return this;
	}

	public BufferWrapper setCharAt(int index, char value) 
	{
		buffer.setCharAt(index, value);
		return this;
	}

	public void setLength(int newLength)
	{
		buffer.setLength(newLength);
	}

	public CharSequence subSequence(int start, int end) 
	{
		return buffer.subSequence(start, end);
	}

	public String substring(int start)
	{
		return buffer.substring(start);
	}

	public String substring(int start, int end)
	{
		return buffer.substring(start, end);
	}

	public String toString() 
	{
		return buffer.toString();
	}

	public BufferWrapper trimToSize()
	{
		buffer.trimToSize();
		return this;
	}

	public boolean endsWith(char value)
	{
		return( buffer.charAt(buffer.length() - 1) == value);
	}

	public boolean endsWith(String value)
	{
		String  check = buffer.substring(buffer.length() - (value.length()), buffer.length());
		return( check.equals(value));
	}

	public BufferWrapper clear()
	{
		buffer.delete(0, buffer.length() - 1);
		return this;
	}
	
	public StringBuilder getBuffer()
	{
		return buffer;
	}

	public int countChars(char [] charArray)
	{
		int count = 0;
		int endIndex = buffer.length();
		
		for(int index = 0; index < endIndex; index++)
		{
			char charValue = buffer.charAt(index);
			for(char c : charArray)
			{
				if(c == charValue) ++count;
			}
		}
		return count;
	}

	public int countChars(char [] charArray, int startIndex)
	{
		int count = 0;
		int endIndex = buffer.length();
		
		for(int index = startIndex; index < endIndex; index++)
		{
			char charValue = buffer.charAt(index);
			for(char c : charArray)
			{
				if(c == charValue) ++count;
			}
		}
		return count;
	}

	public int countChars(char [] charArray, int startIndex, int endIndex)
	{
		int count = 0;
		endIndex = (endIndex < buffer.length())?endIndex: buffer.length();
		
		for(int index = startIndex; index < endIndex; index++)
		{
			char charValue = buffer.charAt(index);
			for(char c : charArray)
			{
				if(c == charValue) ++count;
			}
		}
		return count;
	}

	public int countChars(char charValue, int startIndex)
	{
		int count = 0;
		int endIndex = buffer.length();
		
		for(int index = startIndex; index < endIndex; index++)
		{
			if(buffer.charAt(index) == charValue) ++count;
		}
		
		return count;
	}

	public int countChars(char charValue, int startIndex, int endIndex)
	{
		int count = 0;
		endIndex = (endIndex < buffer.length())?endIndex: buffer.length();
		
		for(int index = startIndex; index < endIndex; index++)
		{
			if(buffer.charAt(index) == charValue) ++count;
		}
		
		return count;
	}

	public int countChars(char charValue)
	{
		int count = 0;
		int endIndex = buffer.length();
		
		for(int index = 0; index < endIndex; index++)
		{
			if(buffer.charAt(index) == charValue) ++count;
		}
		
		return count;
	}
	
	public Map <Character, BufferAnalysis> analizeBuffer()
	{
		Map <Character, BufferAnalysis> map = new HashMap <Character, BufferAnalysis> ();
		
		for(int index = 0; index < buffer.length(); index++)
		{
			char charValue = buffer.charAt(index);
			
			if(!map.containsKey(charValue))
			{
				map.put(charValue, new BufferAnalysis(index));
			}
			else
			{
				BufferAnalysis ba = map.get(charValue);
				ba.incrementCount();
				ba.addIndex(index);
			}
		}
		
		return map;
	}
	
	protected class BufferAnalysis
	{
		private int count;
		private List <Integer> indexer;
		
		public BufferAnalysis(int index)
		{
			addIndex(index);
			incrementCount();
		}
		
		
		public int getCount()
		{
			return count;
		}
		
		public int incrementCount()
		{
			return ++count;
		}
		
		public void setCount(int count)
		{
			this.count = count;
		}
		
		public List<Integer> getIndexer()
		{
			if(indexer == null)
			{
				indexer = new ArrayList <Integer> ();
			}
			
			return indexer;
		}
		
		public void setIndexer(List<Integer> indexer)
		{
			this.indexer = indexer;
		}
		
		public void addIndex(int index)
		{
			getIndexer().add(index);
		}
	}

}
