/**
 * 
 */
package com.sos.tools.threadpoolmanager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.xml.sax.SAXException;

import com.sos.tools.configuration.Configuration;
import com.sos.tools.logging.LoggerSession;

/**
 * @author louis.weyrich
 *
 */
public class ThreadPool implements RejectedExecutionHandler
{
	
    
	protected ThreadPoolConfig threadPoolConfig;
	private Configuration config;
	private PausableThreadPoolExecutor executor;
	private LoggerSession logging;

	/**
	 * @throws Exception 
	 * @throws SAXException 
	 * @throws IOException 
	 * 
	 */
	public ThreadPool(String configurationLocation, String configName) 
	throws IOException, SAXException, Exception 
	{
		config = new Configuration(configurationLocation);
		
		logging = LoggerSession.instance();
		if(!logging.isOpened())  logging.openSession();

		threadPoolConfig = config.populateData(configName, 
			new ThreadPoolConfig()).getThreadPoolConfig();
		
		executor = new PausableThreadPoolExecutor(
			threadPoolConfig.getThreadCount(), //corePoolSize
			threadPoolConfig.getMaximumThreadCount(), //maximumPoolSize 
			threadPoolConfig.getMaxWiatTime(), //keepAliveTime 
			TimeUnit.valueOf(threadPoolConfig.getTimeUnit()), //Time unit
			new PoolBlockingQueue(4, threadPoolConfig.getMaximumThreadCount()), 
			this);
		
		executor.allowCoreThreadTimeOut(threadPoolConfig.getAllowTimeout());
	}
	
	public void execute(Runnable runnable)
	{
		executor.execute(runnable);
	}
	
	public int getThreadPoolCount()
	{
		return executor.getPoolSize();
	}
	
	public long getCompletedTaskCount()
	{
		return executor.getCompletedTaskCount();
	}
	
	public int getActiveThreadCount()
	{
		return executor.getActiveCount();
	}
	
	public int start()
	{
		if(!executor.isShutdown())
			return executor.prestartAllCoreThreads();
		else
			return 0;
	}
	
	public void pause()
	{
		executor.pause();
	}
	
	public void resume()
	{
		executor.resume();
	}
	
	public void shutdown()
	{
		executor.shutdown();
	}
	
	public List<Runnable> shutdownNow()
	{
		return executor.shutdownNow();
	}

	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("rejected");
		
	}
	

	public static void main(String [] args)
	{
		
		try 
		{
			ThreadPool threadpool = new ThreadPool("config/threadpool-config.xml", "ThreadPoolConfig.ThreadPoolConfig1");
			threadpool.start();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
