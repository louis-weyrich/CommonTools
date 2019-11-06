package com.sos.tools.utilities;

import com.sos.tools.utilities.collection.Node;

public class LinkedList {

	public static Node<Integer> reverse(Node<Integer> head)
	{
		Node <Integer> current = head;
		Node <Integer> previous = null;
		Node <Integer> next = null;
		
		while(current != null)
		{
			next = current.getNextValue();
			current.setNextValue(previous);
			previous = current;
			current = next;
		}
		
		head = previous;
		
		return head;
	}

	
	public static void main(String []args)
	{
		Node <Integer> node = new Node <Integer> (0);
		
		for(int index = 1; index < 10; index++)
		{
			node = LinkedList.insert(node, index);
		}
		
		LinkedList.printList(node);
		node = LinkedList.reverse(node);
		LinkedList.printList(node);
	}
	
	public static void printList(Node <Integer> node)
	{
		Node <Integer> current = node;
		
		while(current != null)
		{
			System.out.print(current);
			current = current.getNextValue();
		}
		
		System.out.print("\n");
	}
	
	public static Node <Integer> insert(Node <Integer> node, Integer value)
	{
		Node <Integer> current = node;
		
		while(true)
		{
			if(current.getNextValue() == null)
			{
				current.setNextValue(new Node <Integer>(new Integer(value)));
				break;
			}
			else
			{
				current = current.getNextValue();
			}
			
		}
		
		
		return node;
	}
}
