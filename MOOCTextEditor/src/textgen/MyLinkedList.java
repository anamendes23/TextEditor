package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException("Linked List object cannot store null pointers.");

		LLNode<E> newNode = new LLNode<E>(element);
		newNode.setPrev(tail.getPrev());
		newNode.setNext(tail);
		tail.getPrev().setNext(newNode);
		tail.setPrev(newNode);

		size++;

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> lookupNode = getNodeAtIndex(index);

		return lookupNode.getData();
	}

	/**
	 * Add an element to the list at the specified index
	 * @param //the index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		LLNode<E> atIndex = getNodeAtIndex(index);
		LLNode<E> newNode = new LLNode<E>(element);

		newNode.setPrev(atIndex.getPrev());
		newNode.setNext(atIndex);
		atIndex.getPrev().setNext(newNode);
		atIndex.setPrev(newNode);

		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		LLNode<E> atIndex = getNodeAtIndex(index);

		atIndex.getPrev().setNext(atIndex.getNext());
		atIndex.getNext().setPrev(atIndex.getPrev());

		size--;

		return atIndex.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> atIndex = getNodeAtIndex(index);
		E oldElement = atIndex.getData();
		atIndex.setData(element);

		return oldElement;
	}

	private LLNode<E> getNodeAtIndex(int index) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Enter index between 0 and " + (size - 1));

		LLNode<E> lookupNode = null;
		LLNode<E> startNode = head;

		for(int i = 0; i <= index; i ++) {
			lookupNode = startNode.getNext();
			startNode = startNode.getNext();
		}

		return lookupNode;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public void setNext(LLNode<E> nextNode) {
		this.next = nextNode;
	}

	public void setPrev(LLNode<E> prevNode) {
		this.prev = prevNode;
	}

	public void setData(E data) {
		this.data = data;
	}

	public LLNode<E> getNext() { return this.next; }
	public LLNode<E> getPrev() { return this.prev; }
	public E getData() { return this.data; }
}
