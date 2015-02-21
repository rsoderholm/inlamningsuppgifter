package inlamningsuppgift2;

/**
 * A dynamic list that holds objects of any type
 * 
 * @author Jonathan Böcker 2015-02-20
 *
 * @param <E>
 *            The type of Object the class will hold
 */
public class DoublyLinkedList<E> {
	private ObjectNode<E> first = null;

	/*
	 * Finds and returns the ObjectNode at index
	 */
	private ObjectNode<E> locate(int index) {
		// A temporary reference holder used to traverse
		ObjectNode<E> node = first;

		if (node != null) {
			// Traverse through the objects to find the wanted object
			for (int i = 0; i < index; i++)
				node = node.getNext();
		}
		return node;
	}

	/*
	 * Throws an IndexOutOfBoundsException if provided index is smaller than
	 * zero or larger than list size
	 */
	private void rangeCheck(int index) {
		if ((index < 0) || (index > size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index="
					+ index);
	}

	/**
	 * @return Returns the number of elements in the list
	 */
	public int size() {
		// A counter
		int n = 0;

		// A temporary reference holder used to traverse
		ObjectNode<E> node = first;

		// Traverse through all the objects and increase
		// counter for every object
		while (node != null) {
			node = node.getNext();
			n++;
		}
		return n;
	}

	/**
	 * Insert an element at a given index
	 * 
	 * @param index
	 *            Index where element will be inserted
	 * @param data
	 *            Element to be stored in the list. Can be null.
	 * @throws IndexOutOfBoundsException
	 *             If index is lesser than zero or larger than list size
	 */
	public void add(int index, E data) {
		rangeCheck(index);

		// If to be inserted first in the list
		if (index == 0) {

			// The first will now be second
			ObjectNode<E> previousFirst = first;

			// A new Object will be first
			first = new ObjectNode<E>(data, null, previousFirst);

			// The second Object needs a reference to the first
			if (previousFirst != null)
				previousFirst.setPrevious(first);

			// If to be inserted last in the list
		} else if (index == (size())) {
			// Locate the last element
			ObjectNode<E> previousLast = locate(size() - 1);

			// A new object will be last
			ObjectNode<E> last = new ObjectNode<E>(data, previousLast, null);

			// The previous last needs to be updated
			previousLast.setNext(last);

		} else {
			// Find the node to be after the new one
			ObjectNode<E> nextNode = locate(index);

			// Get the node to be before the new one
			ObjectNode<E> previousNode = nextNode.getPrevious();

			// Create a new node and give it references to previous and next
			ObjectNode<E> newNode = new ObjectNode<E>(data, previousNode,
					nextNode);

			// Update the previous and the next node
			previousNode.setNext(newNode);
			nextNode.setPrevious(newNode);
		}
	}

	/**
	 * Places an element first in the list
	 * 
	 * @param data
	 *            The element to be placed first in the list. Can be null.
	 */
	public void addFirst(E data) {
		add(0, data);
	}

	/**
	 * Places an element last in the list
	 * 
	 * @param data
	 *            The element to be placed last in the list. Can be null.
	 */
	public void addLast(E data) {
		add((size()), data);
	}

	/**
	 * Removes an element at given index
	 * 
	 * @param index
	 *            Index where element will be removed
	 * @return Returns the removed element
	 * @throws IndexOutOfBoundsException
	 *             If index is lesser than zero or larger than list size
	 */
	public E remove(int index) {
		if (first != null) {
			rangeCheck(index);
			ObjectNode<E> throwAway;

			// If index is 0, first is to be removed
			if (index == 0) {

				// Store the removed node reference
				throwAway = first;
				first = first.getNext();

				if (first != null)
					first.setPrevious(null);
			} else if (index == size() - 1) {

				// Store the removed node reference
				throwAway = locate(index);

				// Set previous node's next to null
				throwAway.getPrevious().setNext(null);
			} else {

				// Store the removed node reference
				throwAway = locate(index);

				// Find the node before and after the removed node
				ObjectNode<E> previousNode = throwAway.getPrevious();
				ObjectNode<E> nextNode = throwAway.getNext();

				// If there is a previous node, update references
				if (previousNode != null)
					previousNode.setNext(nextNode); // Can be null!

				// If there is a node after, update references
				if (nextNode != null)
					nextNode.setPrevious(previousNode); // Can be null!

				// Dereference the removed element for safety
				throwAway.setNext(null);
				throwAway.setPrevious(null);
			}

			// Return the element in the removed node
			return throwAway.getData();
		} else {
			return null;
		}
	}

	/**
	 * Remove the first element in the list
	 * 
	 * @return The removed element
	 */
	public E removeFirst() {
		return remove(0);
	}

	/**
	 * Remove the last element in the list
	 * 
	 * @return The removed element
	 */
	public E removeLast() {
		return remove(size() - 1);
	}

	/**
	 * Retrieves the element at given index without removing it
	 * 
	 * @param index
	 *            The index of the wanted element
	 * @return The element wanted
	 * @throws IndexOutOfBoundsException
	 *             If index is lesser than zero or larger than list size
	 */
	public E get(int index) {
		rangeCheck(index);

		if (first != null)
			return locate(index).getData();
		else
			return null;
	}

	/**
	 * @return The first element of the list without removing it
	 */
	public E getFirst() {
			return get(0);
	}

	/**
	 * @return The last element of the list without removing it
	 */
	public E getLast() {
			return get(size() - 1);
	}
}
