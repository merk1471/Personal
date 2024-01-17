/*This file contains the generic OrderedLinkedList class which extends the generic Comparable class. The class has 4 methods; a constructor, insert, get, and getSize. It is used
 * to create, retrive data from, and manage a singly linked list that has it's Nodes ordered from biggest to smallest degree of monomials. This is done in order to create a valid 
 * representation of a polynomial. 
*/

public class OrderedLinkedList<T extends Comparable<T>> 
{
	private Node<T> head;
	private int size;
	
	//OrderedLinkedList constructor, initializes a new ordered linked list by setting the generic Node variable "head" to null indicating the list is empty and sets the size to 0. 
	public OrderedLinkedList()
	{
		head = null;
		size = 0;
	}
	
	//Void insert method, inserts a generic value into the singly linked list at the correct position. 
	public void insert(T val) //@param a generic value to insert. 
	{
		//Create new node containing the desired value. 
		Node<T> newNode = new Node<>(val, null);

	    if (head == null || head.getData().compareTo(val) < 0) 
	    {
	        // If the list is empty or the value is the greatest, insert at the beginning of the list or into an empty list then set the head to be the new Node. 
	        newNode.setNext(head);
	        head = newNode;
	    }
	    
	    else 
	    {
	    	//Keep track of 2 Nodes to find the correct position. 
	        Node<T> current = head; 
	        Node<T> previous = null;
	
	        //while the current Node exists and is greater than the value, keep track of the 2 Nodes while traversing the linked list. 
	        while (current != null && current.getData().compareTo(val) > 0) 
	        {
	            previous = current;
	            current = current.getNext();
	        }
	
	        // Once position is found, insert between previous and current Nodes. 
	        previous.setNext(newNode);
	        newNode.setNext(current);
	    }

	    size++; // Update the size of the list each time 
	}
	
	//get method, returns a generic value and throws an IndexOutOfBoundsException. Used to get the Node at a certain index in the ordered linked list. 
	public T get(int i) throws IndexOutOfBoundsException  //@param an int signifying the desired index. 
	{
		//If the index given is less than 0 or greater than the size of the ordered linked list raise an IndexOutOfBoundsException. 
	    if (i < 0 || i >= size) 
	    {
	        throw new IndexOutOfBoundsException();
	    }
	    
	    Node<T> current = head;
	    
	    //iterate through the linked list until you reach the given index.
	    for (int j = 0; j < i; j++) 
	    {
	        current = current.getNext();
	    }
	    
	    return current.getData(); //@return the Node reached after iterating to the specified index. 
	}
	
	//getSize method, returns an int, used to get the size of the ordered linked list. 
	public int getSize() 
	{
			return size; //@return size; 
	}
}
//End of class. 
