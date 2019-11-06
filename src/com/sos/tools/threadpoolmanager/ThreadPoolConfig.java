/**
 * 
 */
package com.sos.tools.threadpoolmanager;

/**
 * @author louis.weyrich
 *
 */
public class ThreadPoolConfig 
{
	
	private Integer threadCount;
	private Integer minimumThreadCount;
	private Integer maximumThreadCount;
	private Long maxWiatTime;
	private String threadPoolName;
	private String timeUnit;
	private Boolean allowTimeout;
	private boolean modified = false;
	
	

	/**
	 * 
	 */
	public ThreadPoolConfig() 
	{
		// do nothing
	}

	public ThreadPoolConfig(String threadPoolName, Integer threadCount, 
	Integer minimumThreadCount, Integer maximumThreadCount, Long maxWiatTime, 
	String timeUnit) 
	{
		setThreadPoolName(threadPoolName);
		setThreadCount(threadCount);
		setMinimumThreadCount(minimumThreadCount);
		setMaximumThreadCount(maximumThreadCount);
		setMaxWiatTime(maxWiatTime);
		setTimeUnit(timeUnit);
	}

	
	public Integer getThreadCount() 
	{
		return threadCount;
	}


	public void setThreadCount(Integer threadCount) 
	{
		this.threadCount = threadCount;
		modified = true;
	}


	public String getThreadPoolName() 
	{
		return threadPoolName;
	}


	public void setThreadPoolName(String threadPoolName) 
	{
		this.threadPoolName = threadPoolName;
		modified = true;
	}


	public Integer getMinimumThreadCount() 
	{
		return minimumThreadCount;
	}


	public void setMinimumThreadCount(Integer minimumThreadCount) 
	{
		this.minimumThreadCount = minimumThreadCount;
		modified = true;
	}


	public Integer getMaximumThreadCount() 
	{
		return maximumThreadCount;
	}


	public void setMaximumThreadCount(Integer maximumThreadCount) 
	{
		this.maximumThreadCount = maximumThreadCount;
		modified = true;
	}


	public Long getMaxWiatTime() 
	{
		return maxWiatTime;
	}


	public void setMaxWiatTime(Long maxWiatTime) 
	{
		this.maxWiatTime = maxWiatTime;
		modified = true;
	}


	public String getTimeUnit() {
		return timeUnit;
	}


	public void setTimeUnit(String timeUnit) 
	{
		this.timeUnit = timeUnit;
		modified = true;
	}
	
	public Boolean getAllowTimeout() 
	{
		return allowTimeout;
	}

	public void setAllowTimeout(Boolean allowTimeout) 
	{
		this.allowTimeout = allowTimeout;
	}

	public ThreadPoolConfig getThreadPoolConfig()
	{
		if(modified)
		{
			return this;
		}
		else
		{
			return new ThreadPoolConfig("DefaultThreadPool", new Integer(4), 
				new Integer(2), new Integer(8), new Long(12), "SECONDS");
		}
	}

}