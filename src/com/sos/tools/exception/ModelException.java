/*
 * File: ModelException.java
 *
 */
package com.sos.tools.exception;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class ModelException extends RuntimeException
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2322230127877794572L;

	/**
     * 
     */
    public ModelException()
    {
        // do nothing
    }

    /**
     * @param arg0
     */
    public ModelException(String arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     */
    public ModelException(Throwable arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ModelException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        // do nothing
    }

}
