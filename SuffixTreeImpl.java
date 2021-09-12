
public class SuffixTreeImpl extends SuffixTree {

	public SuffixTreeImpl(String word) {//constructor
		super(word);
	}

	@Override
	public boolean contains(String subword) {//checks if subs string is in the trie
		return this.numOfOccurrences(subword) != 0;
	}

	@Override
	public int numOfOccurrences(String subword) {/// checks the number of occurences of sub string in the trie
		return getRoot().numOfOccurrences(subword.toCharArray(), 0);
	}

}
