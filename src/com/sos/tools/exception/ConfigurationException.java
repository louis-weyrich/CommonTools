/*
 * File: ConfigurationException.java
 *
 */
package com.sos.tools.exception;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class ConfigurationException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3758668093768513131L;

	/**
     * 
     */
    public ConfigurationException()
    {
        // do nothing
    }

    /**
     * @param arg0
     */
    public ConfigurationException(String arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     */
    public ConfigurationException(Throwable arg0)
    {
        super(arg0);
        // do nothing
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ConfigurationException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        // do nothing
    }

}
