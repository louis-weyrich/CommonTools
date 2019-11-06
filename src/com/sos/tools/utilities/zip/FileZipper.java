/*
 * File: FileZipper.java
 *
 */
package com.sos.tools.utilities.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * ...
 * 
 * @author louis.weyrich
 */
public class FileZipper
{
    
    protected boolean deleteOld         = false;
    protected boolean deleteCurrent     = false;
   

    /**
     * 
     */
    public FileZipper()
    {
        
    }

    /**
     * 
     */
    public FileZipper(File file) throws IOException
    {
        zipFile(file);
    }

    /**
     * 
     */
    public FileZipper(boolean deleteCurrent, boolean deleteOld)
    {
        this.deleteCurrent = deleteCurrent;
        this.deleteOld = deleteOld;
    }

    /**
     * 
     */
    public FileZipper(boolean deleteCurrent, boolean deleteOld, File file) throws IOException
    {
        this.deleteCurrent = deleteCurrent;
        this.deleteOld = deleteOld;
        zipFile(file);
    }

    public void zipFile(File file) throws IOException
    {
        if(file.isDirectory())
            throw new IOException(file.getAbsolutePath()+" is a directory.");
        

        String absolutePath = file.getAbsolutePath();
        int slashindex = absolutePath.lastIndexOf(File.separator);
        
        String fileName = absolutePath.substring(slashindex+1, absolutePath.length());
        String path = absolutePath.substring(0, slashindex);

        int dotindex= fileName.lastIndexOf(".");
        File zipFile = new File(path+File.separator+fileName.substring(0, dotindex)+".zip");
        if(zipFile.exists())
        {
            if(!zipFile.delete())
            {
                throw new IOException("Could not delete old "+zipFile.getAbsolutePath());
            }
        }
        else
        {
            if(!zipFile.createNewFile())
            {
                throw new IOException("Could not create new "+zipFile.getAbsolutePath());
            }
        }

        
        byte[] buf = new byte[1024];

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        FileInputStream in = new FileInputStream(file);
        
        // Add ZIP entry to output stream.
        out.putNextEntry(new ZipEntry(fileName));
       
        // Transfer bytes from the file to the ZIP file
        int len;
        while ((len = in.read(buf)) > 0) 
        {
            out.write(buf, 0, len);
        }

        // Complete the entry
        out.closeEntry();
        in.close();
   

        // Complete the ZIP file
        out.close();
                
        if(deleteCurrent)
        {
            if(!file.delete())
            {
                throw new IOException("Could not dlete old "+file.getAbsolutePath());
            }
        }
    }

    @SuppressWarnings("unused")
	public void unzipFile(File file) throws IOException
    {
        if(file.isDirectory())
            throw new IOException(file.getAbsolutePath()+" is a directory.  File must be a compressed file.");

        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry zip = null;
        
        while((zip = zis.getNextEntry()) != null)
        {
            String fileName = zip.getName();
            if(zip.isDirectory())
            {
                
            }
            else
            {
                
            }
            
        }
        
        zis.close();
    }
    
    public boolean isDeleteOld()
    {
        return this.deleteOld;
    }

    
    public void setDeleteOld(boolean deleteOld)
    {
        this.deleteOld = deleteOld;
    }

    
    public boolean isDeleteCurrent()
    {
        return this.deleteCurrent;
    }

    
    public void setDeleteCurrent(boolean deleteCurrent)
    {
        this.deleteCurrent = deleteCurrent;
    }

}
