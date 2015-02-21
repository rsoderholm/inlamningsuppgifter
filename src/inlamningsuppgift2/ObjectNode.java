package inlamningsuppgift2;

/**
 * A class used for holding an object in a {@link DoublyLinkedList}
 * <p>
 * It also holds a reference to the previous and the next {@code ObjectNode} in the
 * list.
 * 
 * @author Jonathan Böcker 2015-02-20
 *
 * @param <E>
 *            The type of Object the class will hold
 */
public class ObjectNode<E> {
	private ObjectNode<E> previous;
	private ObjectNode<E> next;
	private E data;

	/**
	 * Only data provided will set this objects next and previous {@code ObjectNode}s to
	 * null.
	 * 
	 * @param data
	 *            The data to be held
	 */
	public ObjectNode(E data) {
		this(data, null, null);
	}

	/**
	 * Will set data and references for the ObjectNode
	 * 
	 * @param data
	 *            The data to be held
	 * @param previous
	 *            The ObjectNode that is before this one in the list
	 * @param next
	 *            The ObjectNode that is after this one in the list
	 */
	public ObjectNode(E data, ObjectNode<E> previous, ObjectNode<E> next) {
		this.data = data;
		this.previous = previous;
		this.next = next;
	}

	/**
	 * @return Returns the {@code ObjectNode} that is before this one in the list. Can
	 *         be null.
	 */
	public ObjectNode<E> getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            The {@code ObjectNode} before this one in the list. Can be set to
	 *            null.
	 */
	public void setPrevious(ObjectNode<E> previous) {
		this.previous = previous;
	}

	/**
	 * @return Returns the {@code ObjectNode} that is after this one in the list. Can be
	 *         null.
	 */
	public ObjectNode<E> getNext() {
		return next;
	}

	/**
	 * 
	 * @param next
	 *            The {@code ObjectNode} after this one in the list. Can be set to null
	 */
	public void setNext(ObjectNode<E> next) {
		this.next = next;
	}

	/**
	 * 
	 * @return Returns the Object which is held by the {@code ObjectNode}. Can be null.
	 */
	public E getData() {
		return data;
	}

}
