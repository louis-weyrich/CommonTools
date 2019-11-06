/**
 * 
 */
package com.sos.tools.threadpoolmanager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author louis.weyrich
 * 
 */
public class PausableThreadPoolExecutor extends ThreadPoolExecutor 
{

	private boolean isPaused;
	private ReentrantLock pauseLock = new ReentrantLock();
	private Condition unpaused = pauseLock.newCondition();
	
	
	/**
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 */
	public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
	long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) 
	{
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	/**
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param handler
	 */
	public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
	long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, 
	RejectedExecutionHandler handler) 
	{
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				handler);
	}

	
	/**
	 * 
	 */
	protected void beforeExecute(Thread t, Runnable r) 
	{
		super.beforeExecute(t, r);
		pauseLock.lock();
		try 
		{
			while (isPaused)
				unpaused.await();
		} 
		catch (InterruptedException ie) 
		{
			t.interrupt();
		} 
		finally 
		{
			pauseLock.unlock();
		}
	}

	
	/**
	 * 
	 */
	public void pause() 
	{
		pauseLock.lock();
		try 
		{
			isPaused = true;
		} 
		finally 
		{
			pauseLock.unlock();
		}
	}

	
	/**
	 * 
	 */
	public void resume() {
		pauseLock.lock();
		
		try 
		{
			isPaused = false;
			unpaused.signalAll();
		} 
		finally 
		{
			pauseLock.unlock();
		}
	}

}
