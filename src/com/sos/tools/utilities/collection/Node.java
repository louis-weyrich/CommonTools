package com.sos.tools.utilities.collection;

public class Node <V>
{
	private V  value = null;
	private Node <V> previousValue = null;
	private Node <V> nextValue = null;
	
	
	public Node(V value)
	{
		this.value = value;
	}
	
	public Node(V value, Node<V> previous)
	{
		this(value);
		setPreviousValue(previous);
	}

	public Node(V value, Node<V> previous, Node<V> next)
	{
		this(value, previous);
		setNextValue(next);
	}
	
	public boolean isHead()
	{
		return (previousValue == null);
	}
	
	public boolean isTail()
	{
		return (nextValue == null);
	}

	public V getValue()
	{
		return value;
	}
	
	public void setValue(V value)
	{
		this.value = value;
	}
	
	public Node<V> getPreviousValue()
	{
		return previousValue;
	}
	
	public void setPreviousValue(Node<V> previousValue)
	{
		this.previousValue = previousValue;
	}
	
	public Node<V> getNextValue()
	{
		return nextValue;
	}
	
	public void setNextValue(Node<V> nextValue)
	{
		this.nextValue = nextValue;
	}
	
	public Node<V> findTail()
	{
		if(nextValue == null)
		{
			return this;
		}
		else
		{
			return nextValue.findTail();
		}
	}
	
	public void remove()
	{
		value = null;
		
		if(previousValue != null)
		{
			previousValue.setNextValue(nextValue);
		}
		
		if(nextValue != null)
		{
			nextValue.setPreviousValue(previousValue);
		}
	}
	
	public Node <V> insert(V insertedValue)
	{
		return insert(insertedValue, true);
	}
	
	public Node <V> insert(V insertedValue, boolean insertBefore)
	{
		if(insertBefore)
		{
			Node <V> previous = getPreviousValue();
			Node <V> newNode = new Node <V> (insertedValue);
			previous.setNextValue(newNode);
			newNode.setNextValue(this);
			return this;
		}
		else
		{
			Node <V> next = getNextValue();
			Node <V> newNode = new Node <V> (insertedValue);
			setNextValue(newNode);
			newNode.setNextValue(next);
			return next;
		}
	}
	
	public void replace(V replaceValue)
	{
		setValue(replaceValue);
	}
	
	@Override
	public String toString()
	{
		return value.toString()+" ";
	}
}
