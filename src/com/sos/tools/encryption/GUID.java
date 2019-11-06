package com.sos.tools.encryption;

import java.util.Calendar;
import java.util.Random;

/**
 * 
 * 	ALPHA = '@';
 *	ALPHA_FIRST = '+';
 *	ALPHA_LOWER = '<';
 *	ALPHA_LOWER_FIRST = '*';
 *	ALPHA_UPPER = '>';
 *	ALPHA_UPPER_FIRST = '%';
 *	NUMERIC = '#';
 *	NUMERIC_FIRST = '.';
 *	SYMBOL = '$';
 *  SYMBOL_FIRST = '='
 *	ALPHA_NUMERIC = '!';
 *	ALPHA_NUMERIC_FIRST = '&';
 *	ANY = '?';
 *	"!%%!.-#=#-%..@..";
 * 
 * @author louis.weyrich
 *
 */

public class GUID
{
	private final char [] characters = {'0','1','2','3','4','5','6','7','8','9',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
			'r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H',
			'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
			'Z','!','@','#','$','%','^','&','*','|','?'};

	private static final int ALPHA_START = 10;
	private static final int ALPHA_MAX = 51;
	
	private static final int ALPHA_UPPER_START = 36;
	private static final int ALPHA_UPPER_MAX = 25;
	
	private static final int ALPHA_LOWER_START = 10;
	private static final int ALPHA_LOWER_MAX = 25;
	
	private static final int ALPHA_NUMERIC_START = 0;
	private static final int ALPHA_NUMERIC_MAX = 61;
	
	private static final int NUMERIC_START = 0;
	private static final int NUMERIC_MAX = 9;
	
	private static final int SYMBOL_START = 62;
	private static final int SYMBOL_MAX = 9;

	private static final int ANY_START = 0;
	private static final int ANY_MAX = 71;

	private static final char ALPHA = '@';
	private static final char ALPHA_FIRST = '+';
	private static final char ALPHA_LOWER = '<';
	private static final char ALPHA_LOWER_FIRST = '*';
	private static final char ALPHA_UPPER = '>';
	private static final char ALPHA_UPPER_FIRST = '%';
	private static final char NUMERIC = '#';
	private static final char NUMERIC_FIRST = '.';
	private static final char SYMBOL = '$';
	private static final char SYMBOL_FIRST = '=';
	private static final char ALPHA_NUMERIC = '!';
	private static final char ALPHA_NUMERIC_FIRST = '&';
	private static final char ANY = '?';
	private static final String DEFAULT_GENCODE = "!%%!.-#=#-%..@..";
	
	private char [] gencode = null;
	private Random random = null;
	private String currentGenCode = null;
	
	public GUID(String genCode)
	{
		this.gencode = genCode.toCharArray();
		random = new Random(Calendar.getInstance().getTimeInMillis());
	}
	
	public GUID()
	{
		this.gencode = DEFAULT_GENCODE.toCharArray();
		random = new Random(Calendar.getInstance().getTimeInMillis());
	}
	
	public void restSeed()
	{
		random = new Random(Calendar.getInstance().getTimeInMillis());
	}
	
	public void setGenCode(String genCode)
	{
		this.gencode = genCode.toCharArray();
	}
	
	public String generateGUID() {
		StringBuffer buffer = new StringBuffer();
		
		if(gencode.length > 0)
		{
			for( char c : gencode)
			{
				switch(c)
				{
					
					case NUMERIC :
					{
						buffer.append(characters[getRandom(NUMERIC_START, NUMERIC_MAX)]);
						break;
					}
					
					case NUMERIC_FIRST :
					{
						buffer.append('0');
						break;
					}

					case ALPHA :
					{
						buffer.append(characters[getRandom(ALPHA_START, ALPHA_MAX)]);
						break;
					}

					case ALPHA_FIRST :
					{
						buffer.append('a');
						break;
					}

					case ALPHA_LOWER :
					{
						buffer.append(characters[getRandom(ALPHA_LOWER_START, ALPHA_LOWER_MAX)]);
						break;
					}

					case ALPHA_LOWER_FIRST :
					{
						buffer.append('a');
						break;
					}

					case ALPHA_UPPER :
					{
						buffer.append(characters[getRandom(ALPHA_UPPER_START, ALPHA_UPPER_MAX)]);
						break;
					}

					case ALPHA_UPPER_FIRST :
					{
						buffer.append('A');
						break;
					}

					case ALPHA_NUMERIC :
					{
						buffer.append(characters[getRandom(ALPHA_NUMERIC_START, ALPHA_NUMERIC_MAX)]);
						break;
					}

					case ALPHA_NUMERIC_FIRST :
					{
						buffer.append('0');
						break;
					}

					case SYMBOL :
					{
						buffer.append(characters[getRandom(SYMBOL_START, SYMBOL_MAX)]);
						break;
					}
					
					case SYMBOL_FIRST :
					{
						buffer.append('!');
						break;
					}
					
					case ANY :
					{
						buffer.append(characters[getRandom(ANY_START, ANY_MAX)]);
						break;
					}
					
					default :
					{
						buffer.append(c);
					}
				}
				
				
			}
		}
		
		this.currentGenCode = buffer.toString();
		return currentGenCode;
	}
	
	public void setCurrentCode(String start)
	{
		if(this.gencode.length == start.length())
			this.currentGenCode = start;
		
	}
	
	
	public void setCurrentCode(String start, String gencode)
	{
		this.gencode = gencode.toCharArray();
		
		if(this.gencode.length == start.length())
			this.currentGenCode = start;
		else if(this.gencode.length < start.length())
			this.currentGenCode = start.substring(0, this.gencode.length);
		else
			this.currentGenCode = generateGUID();
	}

	public String nextGUID()
	{
		StringBuffer buffer = new StringBuffer();
		
		if(this.currentGenCode == null)
		{
			this.currentGenCode = generateGUID();
			return this.currentGenCode;
		}
		
		boolean increment = true;
		
		char [] genArray = currentGenCode.toCharArray();
		
		for(int index = genArray.length -1; index >= 0; index--)
		{
			char c = currentGenCode.charAt(index);
			
			if(increment)
			{
				switch(gencode[index])
				{
					
					case NUMERIC :
					{
						int charIndex = getCharacterIndex(NUMERIC_START, c);
						
						if((charIndex + 1) > (NUMERIC_START + NUMERIC_MAX))
						{
							charIndex = NUMERIC_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}
					
					case NUMERIC_FIRST :
					{
						int charIndex = getCharacterIndex(NUMERIC_START, c);
						
						if((charIndex + 1) > (NUMERIC_START + NUMERIC_MAX))
						{
							charIndex = NUMERIC_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA :
					{
						int charIndex = getCharacterIndex(ALPHA_START, c);
						
						if((charIndex + 1) > (ALPHA_START + ALPHA_MAX))
						{
							charIndex = ALPHA_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_FIRST :
					{
						int charIndex = getCharacterIndex(ALPHA_START, c);
						
						if((charIndex + 1) > (ALPHA_START + ALPHA_MAX))
						{
							charIndex = ALPHA_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_LOWER :
					{
						int charIndex = getCharacterIndex(ALPHA_LOWER_START, c);
						
						if((charIndex + 1) > (ALPHA_LOWER_START + ALPHA_LOWER_MAX))
						{
							charIndex = ALPHA_LOWER_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_LOWER_FIRST :
					{
						int charIndex = getCharacterIndex(ALPHA_LOWER_START, c);
						
						if((charIndex + 1) > (ALPHA_LOWER_START + ALPHA_LOWER_MAX))
						{
							charIndex = ALPHA_LOWER_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_UPPER :
					{
						int charIndex = getCharacterIndex(ALPHA_UPPER_START, c);
						
						if((charIndex + 1) > (ALPHA_UPPER_START + ALPHA_UPPER_MAX))
						{
							charIndex = ALPHA_UPPER_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_UPPER_FIRST :
					{
						int charIndex = getCharacterIndex(ALPHA_UPPER_START, c);
						
						if((charIndex + 1) > (ALPHA_UPPER_START + ALPHA_UPPER_MAX))
						{
							charIndex = ALPHA_UPPER_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_NUMERIC :
					{
						int charIndex = getCharacterIndex(ALPHA_NUMERIC_START, c);
						
						if((charIndex + 1) > (ALPHA_NUMERIC_START + ALPHA_NUMERIC_MAX))
						{
							charIndex = ALPHA_NUMERIC_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case ALPHA_NUMERIC_FIRST :
					{
						int charIndex = getCharacterIndex(ALPHA_NUMERIC_START, c);
						
						if((charIndex + 1) > (ALPHA_NUMERIC_START + ALPHA_NUMERIC_MAX))
						{
							charIndex = ALPHA_NUMERIC_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}

					case SYMBOL :
					{
						int charIndex = getCharacterIndex(SYMBOL_START, c);
						
						if((charIndex + 1) > (SYMBOL_START + SYMBOL_MAX))
						{
							charIndex = SYMBOL_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}
					
					case SYMBOL_FIRST :
					{
						int charIndex = getCharacterIndex(SYMBOL_START, c);
						
						if((charIndex + 1) > (SYMBOL_START + SYMBOL_MAX))
						{
							charIndex = SYMBOL_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}
					
					case ANY :
					{
						int charIndex = getCharacterIndex(ANY_START, c);
						
						if((charIndex + 1) > (ANY_START + ANY_MAX))
						{
							charIndex = ANY_START;
							buffer.append(characters[charIndex]);
						}
						else
						{
							buffer.append(characters[charIndex+1]);
							increment = false;
						}
						
						break;
					}
					
					default :
					{
						buffer.append(genArray[index]);
					}
				}
			}
			else
			{
				buffer.append(genArray[index]);
			}
		}

		this.currentGenCode = buffer.reverse().toString();
		return this.currentGenCode;
	}
	
	private int getRandom(int start, int max)
	{
		int value = (random.nextInt(max) + start);
		
		return value;
	}
	
	private int getCharacterIndex(int start, char c)
	{
		for(int index = start; index <= 71; index++)
		{
			if(characters[index ] == c)
			{
				return index;
			}
		}
		
		return 0;
	}
	
}