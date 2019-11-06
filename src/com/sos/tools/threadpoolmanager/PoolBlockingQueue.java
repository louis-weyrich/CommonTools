/**
 * 
 */
package com.sos.tools.threadpoolmanager;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.sos.tools.utilities.collection.UtilQueue;

/**
 * @author louis.weyrich
 *
 */
public class PoolBlockingQueue 
extends UtilQueue <Runnable> 
implements BlockingQueue<Runnable> 
{

	/**
	 * 
	 */
	public PoolBlockingQueue(int growthSize) 
	{
		super(growthSize);
	}
	
	public PoolBlockingQueue(int growthSize, int maxSize) 
	{
		super(growthSize, maxSize);
	}


	public void put(Runnable e) 
	throws InterruptedException 
	{
		super.add(e);
	}

	public boolean offer(Runnable e, long timeout, TimeUnit unit)
	throws InterruptedException 
	{
		
		return false;
	}

	public Runnable take() throws InterruptedException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Runnable poll(long timeout, TimeUnit unit)
	throws InterruptedException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int remainingCapacity() 
	{
		return maxSize - size;
	}

	public int drainTo(Collection<? super Runnable> c) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public int drainTo(Collection<? super Runnable> c, int maxElements) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
}
