
public class SuffixTreeNodeImpl extends SuffixTreeNode {

	public SuffixTreeNodeImpl(CharLinkedList from, SuffixTreeNode node) {
		super(from, node);
	}

	public SuffixTreeNodeImpl() {// constructor
		super();
	}

	@Override
	public SuffixTreeNode search(char c) {// constructor
		if (numOfChildren == 0) {
			return null;
		}
		return binarySearch(c, 0, numOfChildren - 1);
	}

	@Override
	public SuffixTreeNode binarySearch(char target, int left, int right) {// binary search for char
		int l = left;
		int r = right;
		int m = (l + r) / 2;
		while (r >= l) {
			if (this.children[m].chars.firstChar() == target) {
				return this.children[m];
			} else if (this.children[m].chars.firstChar() < target) {
				return binarySearch(target, m + 1, right);
			} else if (this.children[m].chars.firstChar() > target) {
				return this.binarySearch(target, left, m - 1);
			}

		}
		return null;
	}

	@Override
	public void shiftChildren(int until) {// move all children to the right by 1 place
		for (int i = getNumOfChildren() - 1; i >= until; i--) {
			children[i + 1] = children[i];// moving all items 1 step to the right
		}
		children[until] = null; // clear the place of the item
	}

	@Override
	public void addChild(SuffixTreeNode node) {// add child to children array
		int numOfChilds = numOfChildren;
		// if this is a leaf
		if (this.numOfChildren == 0) {
			children[0] = node;
			// first descent leaf
		}
		// if node's char is bigger than last child first char
		else if (children[numOfChilds - 1].getChars().firstChar() <= node.getChars().firstChar()) {
			children[numOfChilds] = node;
		} else { // searching for node place in child array
			for (int i = 0; i < numOfChilds; i++) {
				char c1 = children[i].getChars().firstChar();
				char c2 = node.getChars().firstChar();
				if (c2 < c1) {
					shiftChildren(i);
					node.totalWordLength++;
					if (this.chars == null) {// if node is son of the root because we start from +1 when we use
												// constructor with father
						node.totalWordLength--;
					}
					children[i] = node;
					break;
				}
			}
		}

		this.numOfChildren++;

	}

	@Override
	public void addSuffix(char[] word, int from) {// add suffix node to trie
		if (from == word.length) { // if from == word.length we return
			return;
		} else {// if from is smaller
			char c = word[from];
			SuffixTreeNode s1 = this.search(c);
			if (s1 == null) { // ther is no son with this char
				CharLinkedList l = new CharLinkedListImpl(c);
				SuffixTreeNodeImpl s2 = new SuffixTreeNodeImpl(l, this);
				this.addChild(s2);
				s2.addSuffix(word, from + 1);

			} else {
				s1.addSuffix(word, from + 1);
			}

		}

	}

	public void SetDescendantLeaves(SuffixTreeNodeImpl n) {// set the descendent leaves to all fathers above
		n.descendantLeaves++;
		SuffixTreeNode parent = n.getParent();
		while (parent != null) { // set descendantLeaves for all parents above
			parent.descendantLeaves++;
			parent = parent.getParent();
		}

	}

	public void compress() {// compress trie

		if (this.getNumOfChildren() == 0) {
			SetDescendantLeaves(this);
		}
		if (numOfChildren > 1 || this.chars == null) { // more then 1 child or we compress root
			for (int i = 0; i < numOfChildren; i++) {
				children[i].compress();
			}
		} else if (numOfChildren == 1 && this.chars != null) { // only 1 child
			SuffixTreeNode s1 = getChildren()[0];
			chars.append(s1.getChars());
			numOfChildren = s1.getNumOfChildren();
			children = s1.getChildren();
			totalWordLength += s1.getChars().size();
			s1.children = null;// disconnect s1 pointer to children array
			for (int i = 0; i < numOfChildren; i++) {
				children[i].parent = this;
			}
			this.compress();
		}

	}

	@Override
	public int numOfOccurrences(char[] subword, int from) {// count num of occurences of a sub string build by char
															// array
		// if we reached end of string we return the num of leafs
		if (from >= subword.length) {
			return this.getDescendantLeaves();
		}
		char currChar = subword[from];
		// if first char not found no sub strings in the tree
		SuffixTreeNode currNode = search(currChar);
		if (currNode == null) {
			return 0;
		}

		ICharLinkedListNode currNodeChars = currNode.chars.getFirst();
		while (currNodeChars.getNext() != null && from < subword.length) {
			// dropped out of current node
			if (currNodeChars.getChar() != subword[from]) {
				return 0;
			}
			currNodeChars = currNodeChars.getNext();
			from++;

		}
		if (from < subword.length) {
			// end of chars in node so we go to his child
			return currNode.numOfOccurrences(subword, from + 1);
		} else {
			return currNode.getDescendantLeaves();
		}

	}

}
