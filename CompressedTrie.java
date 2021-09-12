public class CompressedTrie {

    protected SuffixTreeNode root;

    /**
     * Constructs a simple empty suffix tree
     */
    public CompressedTrie() { 
        this.root = new SuffixTreeNodeImpl();
    }

    /**
     * Compress the tree using the following rule:
     * For each node, if it has only 1 child - merge it with his (only) child and concatenate their chars;
     * For graphic examples, see the PDF
     */
    public void compressTree(){
        this.root.compress();
    }

    /**
     * Adds the suffix word[from:] to the tree
     * Since this method is called before the compression method, we can assume all tree's nodes contain only one character each
     * @param word The tree's full word
     * @param from Suffix index
     */
    public void addSuffix(char[] word, int from){
        this.root.addSuffix(word, from);
    }

    /**
     * Getter for the tree's root
     * @return The tree's root node
     */
    public SuffixTreeNode getRoot() {
        return this.root;
    }
}
