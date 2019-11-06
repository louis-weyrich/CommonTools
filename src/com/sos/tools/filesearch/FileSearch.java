/**
 * 
 */
package com.sos.tools.filesearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author louis.weyrich
 *
 * Example commandline args 
 * -start C:projects -find [a-zA-Z0-9_-]+.xml -exclude pom.xml -deepSearch true -depth 10 -save C:\search.txt -limit 1000
 */
public class FileSearch 
{

	private FileSearchConfiguration config;
	
	
	
	public FileSearch(FileSearchConfiguration config) throws IOException, Exception
	{
		this.config = config;
	}
	
	public List<File> find()
	{
		List <File> fileList = new ArrayList <File> ();
		
		File startLocation = new File(config.getStartLocation());
		
		if(startLocation.exists())
		{
			for(File file : startLocation.listFiles())
			{
				if(fileList.size() >= config.getLimit())
				{
					break;
				}
				
				if(file.isDirectory() && config.isDeepSearch())
				{
					if(config.getDirectoryFilters().matches(file))
					{
						fileList = find(file, fileList, 0);
					}
				}
				else
				{
					if(config.getFindFilters().matches(file))
					{
						fileList.add(file);
					}
				}
			}
		}
		
		return fileList;
	}
	
	private List <File> find(File currentDir, List <File> fileList, int depth)
	{
		
		for(File file : currentDir.listFiles())
		{
			if(fileList.size() >= config.getLimit())
			{
				break;
			}
			
			if(file.isDirectory() && config.getDepth() > depth)
			{
				if(config.getDirectoryFilters().matches(file))
				{
					fileList = find(file, fileList, depth+1);
				}
			}
			else
			{
				if(config.getFindFilters().matches(file))
				{
					fileList.add(file);
				}
			}
		}
		
		return fileList;
	}
	
	public FileSearchConfiguration getConfig()
	{
		return this.config;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try
		{
			FileSearchConfiguration config = new FileSearchConfiguration(args);

			FileSearch search = new FileSearch(config);
			List <File> files = search.find();
			
			if(config.getSaveFile() != null)
			{
				File saveFile = new File(config.getSaveFile());
				
				if(!saveFile.exists())
				{
					saveFile.createNewFile();
				}
				else
				{
					saveFile.delete();
				}
				
				FileOutputStream fos = new FileOutputStream(saveFile);
				fos.write(("Found "+files.size()+" matching files\n").getBytes());
				
				for(File file : files)
				{
					fos.write((file.getAbsolutePath()+"\n").getBytes());
				}
				fos.flush();
				fos.close();
			}
			else
			{
				System.out.println("Found "+files.size()+" matching files");
				
				for(File file : files)
				{
					System.out.println(file.getAbsolutePath());
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
