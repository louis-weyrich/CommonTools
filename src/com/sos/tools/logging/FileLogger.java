/*
 * File: Logger.java
 *
 */
package com.sos.tools.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ...
 * 
 * @author louis.weyrich
 */
public class FileLogger implements Logger 
{

	private File file;
	private FileOutputStream fos;
	private String name;
	private boolean append;
	private long maxSize;
	private LogFileSizeType sizeType;


	/**
     * 
     */
	public FileLogger(String location, boolean append) 
	throws IOException 
	{
		file = new File(location);
		this.append = append;

		if (!file.exists()) 
		{
			String stringPath = file.getAbsolutePath();
			if (stringPath.endsWith(".log")) 
			{
				int index = stringPath.lastIndexOf(File.separator);
				File path = new File(stringPath.substring(0, index));
				if (!path.exists())
					path.mkdirs();
				file.createNewFile();
			}
		} 
		else 
		{
			if (!append) 
			{
				String stringPath = file.getAbsolutePath();
				Calendar calendar = Calendar.getInstance();
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int year = calendar.get(Calendar.YEAR);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				int second = calendar.get(Calendar.SECOND);
				int index = stringPath.lastIndexOf(".");
				File logCopy = new File(stringPath.substring(0, index) + "_"
						+ month + "-" + day + "-" + year + "_" + hour + "-"
						+ minute + "-" + second+"."+stringPath.substring(index+1, stringPath.length()));
				copyLog(file, logCopy);
			}
		}

		this.name = file.getName();
	}

	protected void copyLog(File original, File copy) 
	throws IOException 
	{
		FileInputStream in = new FileInputStream(original);
		FileOutputStream out = new FileOutputStream(copy);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) 
		{
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public void openLog() 
	throws IOException 
	{
		fos = new FileOutputStream(file, append);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss");
		fos.write(new String(
				"********************************************************************************\n")
				.getBytes());
		fos.write(new String("Logger (" + name + ") is opened at "
				+ dateFormat.format(new Date()) + "\n").getBytes());
		fos.write(new String(
				"********************************************************************************\n\n")
				.getBytes());
		fos.flush();
	}

	public void logException(Throwable e) 
	throws IOException 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss");
		fos.write(("EXCEPTION --> " + e.getClass().getName() + " at "
				+ dateFormat.format(new Date()) + "\n").getBytes());
		fos.write(new String("   MESSAGE: " + e.getMessage() + "\n").getBytes());

		StackTraceElement[] stack = e.getStackTrace();
		for (StackTraceElement element : stack) 
		{
			fos.write(("   " + element.getClassName() + "."
					+ element.getMethodName() + " : line("
					+ element.getLineNumber() + ")\n").getBytes());
		}

		fos.flush();
	}

	public void log(LoggerLevel type, String message) 
	throws IOException 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss");
		fos.write((type.name() + " " + dateFormat.format(new Date()) + " : "
				+ message + "\n").getBytes());
		fos.flush();
	}

	public void closeLog() 
	throws IOException 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss");
		fos.write(new String(
				"\n\n\n********************************************************************************\n")
				.getBytes());
		fos.write(new String("Logger (" + name + ") was closed at "
				+ dateFormat.format(new Date()) + "\n").getBytes());
		fos.write(new String(
				"********************************************************************************\n\n")
				.getBytes());
		fos.flush();
		fos.close();
		fos = null;
	}
	
	public boolean checkFile(File file)
	{
		long size = file.length();
		
		if(this.sizeType != null)
		{
			if(sizeType.equals(LogFileSizeType.SIZE))
			{
				if(size >= maxSize)
				{
					
				}
			}
			else
			{
				if(size >= maxSize)
				{
					
				}
			}
		}
		
		return true;
	}
	
	public String getName()
	{
		return name;
	}

	public boolean isAppend()
	{
		return append;
	}

	public void setAppend(boolean append)
	{
		this.append = append;
	}

	public long getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(long maxSize)
	{
		this.maxSize = maxSize;
	}

	public LogFileSizeType getSizeType()
	{
		return sizeType;
	}

	public void setSizeType(LogFileSizeType sizeType)
	{
		this.sizeType = sizeType;
	}

}
