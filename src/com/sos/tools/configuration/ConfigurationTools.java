package com.sos.tools.configuration;

import java.io.File;

import com.sos.tools.exception.ConfigurationException;

public class ConfigurationTools 
{
	
	/**
	 * 
	 * @param location
	 * @throws ConfigurationException
	 */
	public static void buildConfigurationSchema(File folderPath) 
	throws ConfigurationException
	{
		if(folderPath.exists())
		{
			if(folderPath.isDirectory())
			{
				
			}
			else
			{
				
			}
		}
		else
		{
			
		}
	}
	
	/**
	 * 
	 * @param location represents an existing or new folder
	 * @throws ConfigurationException
	 */
	public static void buildConfiguration(File folderPath, String configName) 
	throws ConfigurationException
	{
		buildConfigurationSchema(folderPath);
		
		if(folderPath.exists())
		{
			if(folderPath.isDirectory())
			{
				
			}
			else
			{
				
			}
		}
		else
		{
			
		}
	}

}
