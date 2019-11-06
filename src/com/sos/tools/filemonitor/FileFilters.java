package com.sos.tools.filemonitor;

import java.io.File;
import java.util.Iterator;

public class FileFilters
{
	
	private static final FileFilter [] EMPTY_FILTERS = new FileFilter[0];
	
	protected FileFilter [] filters = EMPTY_FILTERS;
	protected int size = 0;

	public FileFilters()
	{
		
	}
	
	public void add(FileFilter fileFilter)
	{
		FileFilter [] temp = new FileFilter[size+1];
		System.arraycopy(filters, 0, temp, 0, size);
		temp[size++] = fileFilter;
		filters = temp;
	}
	
	public boolean matches(File file)
	{
		boolean found = true;
		
		Iterator <FileFilter> iterator = iterator();
		while(iterator.hasNext() && found)
		{
			FileFilter filter = iterator.next();
			found = (filter.accept(file, file.getName()));
		}
		
		return found;
	}

	public int size()
	{
		return size;
	}
	
	public FileFilter [] getFilters()
	{
		return filters;
	}
	
	public Iterator <FileFilter> iterator()
	{
		return new Iterator <FileFilter> ()
		{
			
			private int currentIndex = 0;

			@Override
			public boolean hasNext()
			{
				return currentIndex < size;
			}

			@Override
			public FileFilter next()
			{
				return filters[currentIndex++];
			}
	
		};
	}
}
