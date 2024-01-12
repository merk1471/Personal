/* This is the node class. It defines a generic node object with 4 methods; the constructor, getNext, getData, and setNext. The node object is used to create Nodes to 
 * form a singly linked list. 
 * @Author Mark Samwaiel
 * CS1027 Assignment #2
 */ 
public class Node<T>
{
	private T data;
	private Node<T> next;
	
	//Constructor method, creates a new Node object with it's data and a pointer to another node.
	public Node(T data, Node<T> next) //@param generic value for the Node's data and a generic Node object to set as the next Node in the singly linked list. 
	{
		this.next = next;
		this.data = data; 
	}
	
	//getNext method, returns a generic Node object, used to get the next Node in the singly linked list. 
	public Node<T> getNext()
	{
		return this.next; //@return the next Node. 
	}
	
	//getData method, returns a generic value, used to get the data contained in the Node object. 
	public T getData() 
	{
		return this.data; //@return the data. 
	}
	
	//Void setNext method, used to set or mutate the Node's pointer to make it point to a new Node in the singly linked list. 
	public void setNext(Node<T> newNode) 
	{
		this.next = newNode;
	}
}

//End of class. 