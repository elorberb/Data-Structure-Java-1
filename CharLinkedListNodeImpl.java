public class CharLinkedListNodeImpl implements ICharLinkedListNode {

	private char c;
	private ICharLinkedListNode nextNode;

	public CharLinkedListNodeImpl(char c) {// constructor
		this.c = c;
		this.nextNode = null;
	}

	/**
	 * Getter for the character inside the node
	 * 
	 * @return Character stored in the node
	 */
	public char getChar() {
		return this.c;
	}

	/**
	 * Getter for the next node in the list
	 * 
	 * @return next node in the list
	 */
	public ICharLinkedListNode getNext() {
		return this.nextNode;
	}

	/**
	 * Setter for the next node
	 * 
	 * @param next New next node
	 */
	public void setNext(ICharLinkedListNode next) {
		this.nextNode = next;
	}

}
