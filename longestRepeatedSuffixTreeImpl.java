
public class longestRepeatedSuffixTreeImpl extends longestRepeatedSuffixTree {

	public longestRepeatedSuffixTreeImpl(String word) {// constructor
		super(word);
	}

	/**
	 * Calculates and returns the longest repeated substring in the tree's word. In
	 * case of multiple longest Repeated Substring we choose the longest Repeated
	 * Substring which comes 1st lexicographically. This function preforms placement
	 * to the maxLength and substringStartNode members.
	 */
	@Override
	public void createLongestRepeatedSubstring() {// creates the longest repeated substring by calculating substring
													// starting node and max length of the sub string

		this.substringStartNode = findMaxLength(getRoot().children[0]);
		SuffixTreeNode temp = null;
		for (int i = 1; i < getRoot().numOfChildren; i++) {// check longest substring between all root's children
			temp = findMaxLength(getRoot().children[i]);
			if (substringStartNode.numOfChildren == 0 && temp.numOfChildren > 1) {
				substringStartNode = temp;
			}
			if (temp.numOfChildren > 1 && temp.getTotalWordLength() > this.substringStartNode.getTotalWordLength()) {
				substringStartNode = temp;
			}
			char tempdChr = temp.getChars().getFirst().getChar();
			char sbStrChr = substringStartNode.getChars().getFirst().getChar();
			boolean childChrSmaller = temp.getTotalWordLength() == substringStartNode.getTotalWordLength()
					&& tempdChr < sbStrChr;
			if (childChrSmaller) {
				substringStartNode = temp;
			}
		}
		this.maxLength = this.substringStartNode.getTotalWordLength();

	}

	/**
	 * a wrapper function for createLRS
	 * 
	 * @param curr  SuffixTreeNode
	 * @param mxlen maximum length of a substring found in the suffix tree
	 */
	private SuffixTreeNode findMaxLength(SuffixTreeNode curr) {// finds the longest repeated sub string
		SuffixTreeNode maxLenNode = curr;
		for (int i = 0; i < curr.getNumOfChildren(); i++) {
			SuffixTreeNode child = curr.getChildren()[i];
			if (child.getNumOfChildren() == 0) { // child is leaf
				continue;
			}
			boolean childWordLonger = child.getTotalWordLength() > curr.getTotalWordLength();
			char childChr = child.getChars().getFirst().getChar();
			char currChr = curr.getChars().getFirst().getChar();
			boolean childChrSmaller = child.getTotalWordLength() == curr.getTotalWordLength() && childChr < currChr;
			if (childWordLonger || childChrSmaller) {
				maxLenNode = child;
			}
			SuffixTreeNode temp = findMaxLength(child);
			if (temp.getTotalWordLength() > maxLenNode.getTotalWordLength()) {
				maxLenNode = temp;
			}
		}
		return maxLenNode;
	}

	/**
	 * Getter for the longest repeated substring You should use the
	 * substringStartNode you already found in createLongestRepeatedSubstring
	 * function.
	 * 
	 * @return the longest repeated substring
	 */
	public String getLongestRepeatedSubstring() {
		if (this.getRoot().getDescendantLeaves() == this.getRoot().getNumOfChildren()) {
			return "X";
		}
		return buildSubString(this.substringStartNode);

	}

	public String buildSubString(SuffixTreeNode curr) {// build longest repeated substring
		if (curr.totalWordLength == 0) {
			return "";
		}
		String ans = "";
		ICharLinkedListNode n = curr.getChars().getFirst();
		while (n != null) {
			ans += n.getChar();
			n = n.getNext();
		}

		return buildSubString(curr.getParent()) + ans;
	}

}
