/**
 * 
 */
package com.sos.tools.exception;

/**
 * @author louis.weyrich
 *
 */
public class CloneCopyException extends RuntimeException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8368613993386284044L;

	/**
	 * 
	 */
	public CloneCopyException() 
	{
		// do nothing
	}

	/**
	 * @param message
	 */
	public CloneCopyException(String message) 
	{
		super(message);
		// do nothing
	}

	/**
	 * @param cause
	 */
	public CloneCopyException(Throwable cause) 
	{
		super(cause);
		// do nothing
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CloneCopyException(String message, Throwable cause) 
	{
		super(message, cause);
		// do nothing
	}

}
