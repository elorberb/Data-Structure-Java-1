import java.util.Iterator;

public abstract class CharLinkedList implements Iterable<Character>{

    protected ICharLinkedListNode first;
    protected ICharLinkedListNode last;

    /**
     * Constructs a new empty character linked list
     */
    public CharLinkedList() { 
        this.first = null;
        this.last = null;
    }

    /**
     * Adds a new character to the end of the list
     * @param c Character to add
     */
    public abstract void add(char c);

    /**
     * Getter for the first character
     * @return The first character in the list
     */
    public abstract char firstChar();

    /**
     * Calculates the size of the list
     * @return The number of characters in the list
     */
    public abstract int size();

    /**
     * Appends a list to the end of this list
     * @param toAppend The list to be appended at the end of this list
     */
    public abstract void append(CharLinkedList toAppend);

    /**
     * Static method to simply create a list with one character
     * @param c The single character of the new list
     * @return A new list containing the single character c
     */
    public static CharLinkedList from(char c){
        CharLinkedList linkedList = new CharLinkedListImpl();
        linkedList.add(c);
        return linkedList;
    }

    /**
     * Static method to simply create a list containing the string s
     * @param s The string to be presented by the new list
     * @return A new list containing all characters of s
     */
    public static CharLinkedList from(String s){
        CharLinkedList linkedList = new CharLinkedListImpl();
        for (char c : s.toCharArray())
            linkedList.add(c);
        return linkedList;
    }

    /**
     * Getter for the first node
     * @return First node of the list
     */
    public ICharLinkedListNode getFirst() {
        return this.first;
    }

    /**
     * Getter for the last node
     * @return Last node of the list
     */
    public ICharLinkedListNode getLast() {
        return this.last;
    }

    /**
     * Allows the user to use foreach loop on this e.g. "for (char c : list)".
	 * Don't call this function implicitly
     * @return An iterator for the list
     */
    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            ICharLinkedListNode current = first;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public Character next() {
                char c = this.current.getChar();
                this.current = this.current.getNext();
                return c;
            }
        };
    }

    /**
     * Convenient string for debug purposes
     * @return String representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char c : this)
            builder.append(c);
        return builder.toString();
    }
}
