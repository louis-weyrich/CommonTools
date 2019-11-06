/**
 * 
 */
package com.sos.tools.threadpoolmanager.executables;

import java.net.URL;

/**
 * @author louis.weyrich
 *
 */
public class HTTPExecutable implements Runnable 
{
	
	protected URL url;

	/**
	 * 
	 */
	public HTTPExecutable() 
	{
		
	}

	/**
	 * 
	 */
	public HTTPExecutable(URL httpUrl) 
	{
		url = httpUrl;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub

	}

}
