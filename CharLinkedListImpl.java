public class CharLinkedListImpl extends CharLinkedList {
	/**
	 * Constructs a new empty character linked list
	 */
	public CharLinkedListImpl() {
		super();
	}

	/**
	 * Constructs a new character linked list by given the letter of the first node
	 * 
	 * @param c
	 */
	public CharLinkedListImpl(char c) {// constructor
		super();
		this.first = new CharLinkedListNodeImpl(c);
		this.last = first;
	}

	public CharLinkedListImpl(String s) {// constructor
		CharLinkedList linkedList = from(s);
		this.first = linkedList.first;
		this.last = linkedList.last;
	}

	/**
	 * Adds a new character to the end of the list
	 * 
	 * @param c Character to add
	 */
	public void add(char c) {// add node to list
		CharLinkedListNodeImpl n = new CharLinkedListNodeImpl(c);
		if (first == null && last == null) {
			first = n;
			last = n;
		} else {
			last.setNext(n);
			last = n;
		}
	}

	/**
	 * Getter for the first character
	 * 
	 * @return The first character in the list
	 */
	public char firstChar() {
		return first.getChar();
	}

	/**
	 * Calculates the size of the list
	 * 
	 * @return The number of characters in the list
	 */
	public int size() {// return the size of list
		int count = 0;
		ICharLinkedListNode current = first;
		while (current != null) {
			count++;
			current = current.getNext();
		}
		return count;
	}

	/**
	 * Appends a list to the end of this list
	 * 
	 * @param toAppend The list to be appended at the end of this list
	 */
	public void append(CharLinkedList toAppend) {// connect two lists
		if (first == null) {
			first = toAppend.getFirst();
		} else {
			last.setNext(toAppend.getFirst());
		}
		last = toAppend.getLast();
	}

	/**
	 * Getter for the first node
	 * 
	 * @return First node of the list
	 */
	public ICharLinkedListNode getFirst() {
		return this.first;
	}

	/**
	 * Getter for the last node
	 * 
	 * @return Last node of the list
	 */
	public ICharLinkedListNode getLast() {
		return this.last;
	}

}
