public interface ICharLinkedListNode {

    /**
     * Getter for the character inside the node
     * @return Characrer stored in the node
     */
    char getChar();

    /**
     * Getter for the next node in the list
     * @return next node in the list
     */
    ICharLinkedListNode getNext(); 

    /**
     * Setter for the next node
     * @param next New next node
     */
    void setNext(ICharLinkedListNode next);
}
