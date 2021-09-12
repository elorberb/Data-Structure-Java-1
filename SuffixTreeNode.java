public abstract class SuffixTreeNode {

    public static final int ALPHABET_LENGTH = 26 + 2;

    protected CharLinkedList chars;
    protected SuffixTreeNode[] children;
    protected int numOfChildren;
    protected int descendantLeaves;

    protected int totalWordLength;
    protected SuffixTreeNode parent;

    /**
     * Constructs an empty tree node
     * (useful only for the root node and to safely initialize the fields)
     */
    public SuffixTreeNode() {
        this.chars = null;
        this.children = new SuffixTreeNode[ALPHABET_LENGTH];
        this.numOfChildren = 0;

        this.descendantLeaves = 0; 
        this.parent = null;
        this.totalWordLength = 0;
    }

    public SuffixTreeNode(CharLinkedList chars, SuffixTreeNode parent) {
        this();
        this.chars = chars;
        this.descendantLeaves = 0;
        this.parent = parent;
        this.totalWordLength = parent.totalWordLength + 1;
    }

    /**
     * Wrapper for the binary search method
     * @param c Character to search for
     * @return A child node with c as his first character
     */
    public abstract SuffixTreeNode search(char c);

    /**
     * Finds and returns the node's child with target as his first character, using the binary search operation.
     * @param target Character to search for
     * @param left Left boundary index for searching in the children array
     * @param right Right boundary index for searching in the children array
     * @return A child node with c as his first character, or null if no such child exists
     */
    public abstract SuffixTreeNode binarySearch(char target, int left, int right);

    /**
     * Shifts all elements one cell to the right, until the "until" index, including.
     * Assume the array is big enough even after the shifting
     * @param until Left boundary index of shifting
     */
    public abstract void shiftChildren(int until);

    /**
     * Add a new node as a child to this node.
     * To preserve the lexicographic order, shifting some of the elements in the array might be needed.
     * Note: To compare two siblings in this tree, you need to compare only their first character, as they are surely different
     * @param node node to add
     */
    public abstract void addChild(SuffixTreeNode node);

    /**
     * Adds the suffix word[from:] to the node and recursively to its children.
     * Since this method is called before the compression method, we can assume all nodes contain only one character each
     * @param word The tree's full word
     * @param from Suffix index
     */
    public abstract void addSuffix(char[] word, int from);

    /**
     * Compress the node and its descendents using the following rule:
     * For each node, if it has only 1 child - merge it with his (only) child and concatenate their chars;
     * For graphic examples, see the PDF
     */
    public abstract void compress();

    /**
     * Calculates the number of occurrences of subword[from:] in the tree's word
     * Examples: new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s', 's', 'i'}, 0) -> 2,
     * new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s', 's', 'i'}, 3) -> 1,
     * new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s'}, 0) -> 4,
     * new SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'m', 's'}, 0) -> 0,
     * @param subword Char array representing string to calculate the number of its occurrences in tree's word
     * @return Number of occurrences of subword in the tree's word (0 or more)
     */
    public abstract int numOfOccurrences(char[] subword, int from);

    /**
     * Restores the string written in the path from the root of the tree to this node
     * @return the string as specified
     */
    public String restoreStringAlongPath(){
        if (this.parent == null)
            return "";
        return this.parent.restoreStringAlongPath() + this.chars.toString();
    }

    /**
     * Getter for the character linked list of this node
     * @return node's chars
     */
    public CharLinkedList getChars() {
        return this.chars;
    }

    /**
     * Getter for the children array
     * @return Children array
     */
    public SuffixTreeNode[] getChildren() {
        return this.children;
    }

    /**
     * Getter for the actual number of children of this node
     * @return num of children
     */
    public int getNumOfChildren() {
        return this.numOfChildren;
    }

    /**
     * Getter for the size of the sub-tree with this node as its root.
     * @return the sub-tree size
     */
    public int getDescendantLeaves() {
        return this.descendantLeaves;
    }

    /**
     * Getter for the total word length derived from this node
     * @return total word length
     */
    public int getTotalWordLength() {
        return this.totalWordLength;
    }

    /**
     * Getter for the node's parent
     * @return Node's parent
     */
    public SuffixTreeNode getParent() {
        return this.parent;
    }

    /**
     * Setter for the node's children, for testing purposes
     * @return Node's new children array
     */
    public void setChildren(SuffixTreeNode[] children, int numOfChildren) {
        this.children = children;
        this.numOfChildren = numOfChildren;
    }

    /**
     * Convenient string for debug purposes
     * @return String representing the tree node
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("value: ").append(this.chars).append(", children:");
        for (int i = 0; i < this.numOfChildren; i++)
            sb.append(" ").append(this.children[i].chars).append(",");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
