package com.sos.tools.filesearch;

import com.sos.tools.filemonitor.FileFilter;
import com.sos.tools.filemonitor.FileFilters;

/**
 * 
 * @author louis.weyrich
 *
 */
public class FileSearchConfiguration
{
	
	private boolean 	deepSearch 			= false;
	private int 		limit 				= Integer.MAX_VALUE;
	private int 		depth 				= Integer.MAX_VALUE;
	private String		saveFile			= null;
	private FileFilters findFilters			= new FileFilters();
	private FileFilters directoryFilters	= new FileFilters();
	private String 		startLocation		= null;

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public FileSearchConfiguration(String [] args) throws Exception
	{
		for(int index = 0; index < args.length; index += 2)
		{
			switch(args[index])
			{
				case "-deepSearch" :
				{
					if(args.length > index+1)
						deepSearch = Boolean.parseBoolean(args[index+1]);
					
					break;
				}
				
				case "-ds" :
				{
					if(args.length > index+1)
						deepSearch = Boolean.parseBoolean(args[index+1]);
					
					break;
				}
				
				case "-depth" :
				{
					if(args.length > index+1)
						depth = Integer.parseInt(args[index+1]);
					
					break;
				}

				case "-limit" :
				{
					if(args.length > index+1)
						limit = Integer.parseInt(args[index+1]);
					
					break;
				}

				case "-find" :
				{
					if(args.length > index+1)
					{
						findFilters.add(new FileFilter("findFile", args[index+1], true));
					}
					
					break;
				}
				
				case "-f" :
				{
					if(args.length > index+1)
					{
						findFilters.add(new FileFilter("findFile", args[index+1], true));
					}
					
					break;
				}

				case "-includeDir" :
				{
					if(args.length > index+1)
					{
						directoryFilters.add(new FileFilter("includeDir", args[index+1], true));
					}
					
					break;
				}

				case "-exclude" :
				{
					if(args.length > index+1)
					{
						findFilters.add(new FileFilter("excludeFile", args[index+1], false));
					}
					
					break;
				}

				case "-excludeDir" :
				{
					if(args.length > index+1)
					{
						directoryFilters.add(new FileFilter("excludeDir", args[index+1], false));
					}
					
					break;
				}

				
				case "-e" :
				{
					if(args.length > index+1)
					{
						findFilters.add(new FileFilter("excludeFile", args[index+1], false));
					}
					
					break;
				}

				case "-start" :
				{
					if(args.length > index+1)
						startLocation = args[index+1];
					
					break;
				}
				
				case "-s" :
				{
					if(args.length > index+1)
						startLocation = args[index+1];
					
					break;
				}
				
				case "-save" :
				{
					if(args.length > index+1)
						saveFile = args[index+1];
					
					break;
				}
				
				case "-u" :
				{
					throw new Exception(usage());
				}

				case "-usage" :
				{
					throw new Exception(usage());
				}
				
				default :
				{
					throw new Exception("Argument "+args[index]+" is not valid\n\n"+usage());
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String usage()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Usage:\n");
		builder.append("example: -start C:\\projects -limit 100 -find [a-zA-Z0-9_-[.]]+.xml -exclude pom.xml -deepSearch true -depth 10 -save C:\\results\\search.txt\n\n");
		builder.append("  -deepSearch or -ds has a value of true or false.\n");
		builder.append("  -depth is how deep will you allow the search to go.\n");
		builder.append("  \tthis is a whole number value.\n");
		builder.append("  -limit will limit the number of results.\n");
		builder.append("  \tthis is a whole number value.\n");
		builder.append("  -find or -f is a regular expression of the file(s) you are searching.\n");
		builder.append("  -exclude or -e is a regular expression of the file(s) you do not want included.\n");
		builder.append("  -includeDir is a regular expression of the directories(ies) you want included in the search.\n");
		builder.append("  -excludeDir is a regular expression of the directory(ies) you do not want included in the search.\n");
		builder.append("  -start or -s is the starting directory to start the search.\n");
		builder.append("  -save will save the results to a file.\n");
		builder.append("  \twithout the -save, it will print results to the console.\n");
		builder.append("  -usage or -u will display this usage message.\n");
		
		builder.append("\nWhen adding find or exclude parameters, use only all find or exclude.  Do not mix.\n");
		builder.append("When adding includeDir or excludeDir parameters, use only all includeDir or excludeDir.  Do not mix.\n");
		
		builder.append("\nYour Settings: [");
		
		builder.append("-deepSearch "+Boolean.toString(this.deepSearch));
		
		if(this.depth != Integer.MAX_VALUE)
		{
			builder.append(" -depth "+this.depth);
		}
		
		if(this.findFilters.size() > 0)
		{
			for(FileFilter filter : this.findFilters.getFilters())
			{
				builder.append(" -").append(filter.isInclude()?"find ":"exclude ")
				.append(filter.getFilePattern());
			}
		}
		
		if(this.directoryFilters.size() > 0)
		{
			for(FileFilter filter : this.directoryFilters.getFilters())
			{
				builder.append(" -").append(filter.isInclude()?"includeDir ":"excludeDir ")
				.append(filter.getFilePattern());
			}
		}

		if(this.limit != Integer.MAX_VALUE)
		{
			builder.append(" -limit "+this.limit);
		}
		
		if(this.saveFile != null)
		{
			builder.append(" -save "+this.saveFile);
		}
		
		if(this.startLocation != null)
		{
			builder.append(" -start "+this.startLocation);
		}
		
		builder.append("]");
		
		return builder.toString();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDeepSearch()
	{
		return deepSearch;
	}

	/**
	 * 
	 * @return
	 */
	public int getLimit()
	{
		return limit;
	}

	/**
	 * 
	 * @return
	 */
	public String getStartLocation()
	{
		return startLocation;
	}

	/**
	 * 
	 * @return
	 */
	public int getDepth()
	{
		return depth;
	}

	/**
	 * 
	 * @return
	 */
	public String getSaveFile()
	{
		return saveFile;
	}

	/**
	 * 
	 * @param deepSearch
	 */
	public void setDeepSearch(boolean deepSearch)
	{
		this.deepSearch = deepSearch;
	}

	/**
	 * 
	 * @param depth
	 */
	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	/**
	 * 
	 * @param saveFile
	 */
	public void setSaveFile(String saveFile)
	{
		this.saveFile = saveFile;
	}

	/**
	 * 
	 * @param startLocation
	 */
	public void setStartLocation(String startLocation)
	{
		this.startLocation = startLocation;
	}

	/**
	 * 
	 * @param limit
	 */
	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	/**
	 * 
	 * @return
	 */
	public FileFilters getFindFilters()
	{
		return findFilters;
	}

	/**
	 * 
	 * @param findFilters
	 */
	public void setFindFilters(FileFilters findFilters)
	{
		this.findFilters = findFilters;
	}

	public FileFilters getDirectoryFilters()
	{
		return directoryFilters;
	}

	public void setDirectoryFilters(FileFilters directoryFilters)
	{
		this.directoryFilters = directoryFilters;
	}


}
