package com.sos.tools.utilities;

import java.util.ArrayList;
import java.util.List;

import com.sos.tools.model.Range;

public class PrimeNumberGenerator {

	public PrimeNumberGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Long> getSequence(int count)
	{
		List<Long> primes = new ArrayList<Long>(count);
		
		for(int i = 0; primes.size() < count; i++)
		{
			boolean isPrime = true;
			for(int prime = 2; prime <= i/2; prime++)
			{
				if(i % prime == 0)
				{
					isPrime = false;
					break;
				}
			}
			
			if(isPrime)
			{
				primes.add(new Long(i));
			}
		}
		
		return primes;
	}


	public static List<Long> generateTo(Long value)
	{
		List<Long> primes = new ArrayList<Long>(50);
		
		for(int i = 0; i < value; i++)
		{
			boolean isPrime = true;
			for(int prime = 2; prime <= i/2; prime++)
			{
				if(i % prime == 0)
				{
					isPrime = false;
					break;
				}
			}
			
			if(isPrime)
			{
				primes.add(new Long(i));
			}
		}
		
		return primes;
	}
	
	
	public static boolean isPrime(Long value)
	{
		boolean isPrime = true;
		
		for(int prime = 2; prime <= value/2; prime++)
		{
			if(value % prime == 0)
			{
				isPrime = false;
				break;
			}
		}
			
		return isPrime;
	}


	public static Range<Long> closestPrime(long value)
	{
		Long max = nextGreater(value);
		Long min = PreviousLesser(value);
				
		return new Range <Long> (min, max, value);
	}
	
	private static Long nextGreater(Long value)
	{
		boolean isPrime = false;
		long primeNumber = 0;
		
		for(long i = value; !isPrime; i++)
		{
			
			for(long prime = 2; prime <= i/2 && !isPrime; prime++)
			{
				if(i % prime == 0)
				{
					break;
				}
				else
				{
					isPrime = true;
					primeNumber = i;
				}
			}
			
		}
		
		return primeNumber;
	}
	
	private static Long PreviousLesser(Long value)
	{
		List <Long> list = generateTo(value);
		return list.get(list.size() - 1);
	}
	
	
	public static void main(String []args)
	{
		List <Long> primes = PrimeNumberGenerator.getSequence(466);
		List<Long> fibonacci = Fibonacci.getSequence(30);
		
		for(Long value : fibonacci)
		{
			System.out.print(value+" ");
		}
		
		System.out.print("\n");
		
		for(Long value : primes)
		{
			System.out.print(value+" ");
		}
		
		System.out.println("\n");
		Range <Long> range = PrimeNumberGenerator.closestPrime(246);
		System.out.println("Closest prime to 246 is "+range);
	}

}
