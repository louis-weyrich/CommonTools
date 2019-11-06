/*
 * File: FileChangeEvent.java
 *
 */
package com.sos.tools.event;

import java.io.File;
import java.util.EventObject;

import com.sos.tools.filemonitor.FileChangeType;
import com.sos.tools.filemonitor.FileMonitor;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class FileChangeEvent extends EventObject
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8954443792573055162L;
	private FileMonitor monitor;
    private FileChangeType type;
    
    /**
     * @param arg0
     */
    public FileChangeEvent(File src,  FileMonitor monitor, FileChangeType type)
    {
        super(src);
        this.monitor = monitor;
        this.type = type;
    }

    
    public File getSourceFile(){
        return (File)super.getSource();
    }
    
    public FileChangeType getFileChangeType(){
        return type;
    }
    
    public FileMonitor getFileMonitor(){
        return monitor;
    }
}
