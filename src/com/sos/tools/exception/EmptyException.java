/**
 * 
 */
package com.sos.tools.exception;

/**
 * @author lweyrich
 *
 */
public class EmptyException extends RuntimeException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3205443005638985141L;

	/**
	 * 
	 */
	public EmptyException() 
	{
		// do nothing
	}

	/**
	 * @param message
	 */
	public EmptyException(String message) 
	{
		super(message);
		// do nothing
	}

	/**
	 * @param cause
	 */
	public EmptyException(Throwable cause) 
	{
		super(cause);
		// do nothing
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EmptyException(String message, Throwable cause) 
	{
		super(message, cause);
		// do nothing
	}

}
