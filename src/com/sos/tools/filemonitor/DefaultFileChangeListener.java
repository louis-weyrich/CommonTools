/**
 * 
 */
package com.sos.tools.filemonitor;

import com.sos.tools.event.FileChangeEvent;
import com.sos.tools.event.FileChangeListener;

/**
 * @author louis.weyrich
 *
 */
public class DefaultFileChangeListener implements FileChangeListener 
{

	/**
	 * 
	 */
	public DefaultFileChangeListener() 
	{
		// do nothing
	}

	/**
	 * @see com.sos.tools.event.FileChangeListener#fileChanged(com.sos.tools.event.FileChangeEvent)
	 */
	public void fileChanged(FileChangeEvent event) 
	{
		System.out.println(event.getSourceFile().getName()+" has been "+event.getFileChangeType()+"D");
	}

}
