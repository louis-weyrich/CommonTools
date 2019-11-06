/*
 * File: FileFilter.java
 *
 */
package com.sos.tools.filemonitor;

import java.io.File;
import java.io.FilenameFilter;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class FileFilter implements FilenameFilter
{

    private String name             = null;
    private String filePattern      = null;
    private boolean include         = true;
    
    
    public FileFilter(){
        // Do nothing
    }
    
    
    /**
     * 
     */
    public FileFilter(String name, String filePattern, boolean include)
    {
       this.name = name;
       this.filePattern = filePattern;
       this.include = include;
    }

    /**
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    public boolean accept(File dir, String name)
    {
        
        boolean returnValue = true;
                
        if(name != null && filePattern != null){
            returnValue = (name.matches(filePattern));
        }
        
        if(include){
            returnValue = (returnValue && include);
        }else{
            returnValue = (!returnValue && !include);
        }
        
        return returnValue;
    }

    public String getName(){
        return name;
    }
    
    public String getFilePattern()
    {
        return this.filePattern;
    }

    
    public void setFilePattern(String filePattern)
    {
        this.filePattern = filePattern;
    }

    
    public boolean isInclude()
    {
        return this.include;
    }

    
    public void setInclude(boolean include)
    {
        this.include = include;
    }

    
    public void setName(String name)
    {
        this.name = name;
    }

}
