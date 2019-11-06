package com.sos.tools.model;

public class Range <T extends Number>
{
	
	private T minimumRange;
	private T maximumRange;
	private T meanRange;

	public Range() {
		// TODO Auto-generated constructor stub
	}
	
	public Range(T min, T max, T mean) {
		this.meanRange = mean;
		this.maximumRange = max;
		this.minimumRange = min;
	}

	public T getMinimumRange() {
		return minimumRange;
	}

	public void setMinimumRange(T minimumRange) {
		this.minimumRange = minimumRange;
	}

	public T getMaximumRange() {
		return maximumRange;
	}

	public void setMaximumRange(T maximumRange) {
		this.maximumRange = maximumRange;
	}

	public T getMeanRange() {
		return meanRange;
	}

	public void setMeanRange(T meanRange) {
		this.meanRange = meanRange;
	}

	@Override
	public String toString()
	{
		return "[Max="+this.maximumRange+"][Min="+this.minimumRange+"][Mean="+this.meanRange+"]";
	}
}
