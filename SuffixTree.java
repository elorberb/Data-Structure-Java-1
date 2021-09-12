public abstract class SuffixTree extends CompressedTrie {

    /**
     * Constructs a regular suffix tree containing all suffices of a single word.
     * A dollar sign is appended to the end of the word to mark it.
     * @param word The word to create a tree of all its suffices
     */
    public SuffixTree(String word) {
        super();
        word += "$";
        char[] cword = word.toCharArray();
        for (int i=0; i<word.length(); i++)
            addSuffix(cword, i); 
        compressTree();
    }

    /**
     * Returns true if and only if the tree's word contains the specified subword.
     * Examples: new SuffixTree("mississippi").contains("ssi") -> true,
     * new SuffixTree("mississippi").contains("ms") -> false
     * @param subword String to check if contained in tree's word
     * @return True if and only if the tree's word contains the specified subword
     */
    public abstract boolean contains(String subword);

    /**
     * Calculates the number of occurrences of subword in the tree's word
     * Examples: new SuffixTree("mississippi").numOfOccurrences("ssi") -> 2,
     * new SuffixTree("mississippi").numOfOccurrences("s") -> 4,
     * new SuffixTree("mississippi").numOfOccurrences("ms") -> 0
     * @param subword String to calculate the number of its occurrences in tree's word
     * @return Number of occurrences of subword in the tree's word (0 or more)
     */
    public abstract int numOfOccurrences(String subword);

}
