/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;
	
	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
	
		// Each function here should test a different class.

		// CharLinkedList
		testCharLinkedList();

		// ICharLinkedListNode
		testICharLinkedListNode();

		// SuffixTreeNode
		testSuffixTreeNode();

		// CompressedTrie
		testCompressedTrie();
		
		// SuffixTree
		testSuffixTree();

		// longestRepeatedSuffixTree
		testLongestRepeatedSuffixTree();

		
		// Notifying the user that the code have passed all tests. 
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked. 
	 * In addition, if a test fails the function will print the error message.  
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;
		
		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}

	/**
	 * Checks the CharLinkedList class.
	 */
	private static void testCharLinkedList(){
		CharLinkedList list = new CharLinkedListImpl();
		test(list.size() == 0, "The size of the list should be 0");
		list.add('a');
		test(list.size() == 1, "The size of the list should be 1");
		test(list.firstChar() == 'a', "The first char should be 'a'");
		list.add('a');
		list.add('b');
		test(list.size() == 3, "The size of the list should be 4");
		test(list.firstChar() == 'a', "The first char should be 'a'");
		test(list.getLast().getChar() == 'b', "The first char should be 'c'");
		CharLinkedList list2 = new CharLinkedListImpl();
		list2.add('r');
		list2.add('e');
		list2.append(list);
		test(list2.size() == 5, "The size of the list should be 6");
		test(list2.firstChar() == 'r', "The first char should be 'r'");
		test(list2.getLast().getChar() == 'b', "The first char should be 'c'");

	}

	/**
	 * Checks the ICharLinkedListNode class.
	 */
	private static void testICharLinkedListNode(){
		CharLinkedListNodeImpl n1 = new CharLinkedListNodeImpl('3');
		CharLinkedListNodeImpl n2 = new CharLinkedListNodeImpl('6');
		n1.setNext(n2);
		test(n1.getNext() == n2, "The next node should be n2");
		test(n2.getNext() == null, "The next node should be null");
		test(n1.getNext().getChar() == '6', "The next node should be 6");
	}

	/**
	 * Checks the SuffixTree class.
	 */
	private static void testSuffixTree() {
		SuffixTree st1 = new SuffixTreeImpl("mississippi");
		test(st1.contains("pp"), "pp should be in word");
		test(!st1.contains("w"), "w should not be in word");
		test(st1.numOfOccurrences("pp") == 1, "pp should be in word 1 time");
		test(st1.numOfOccurrences("w") == 0, "w should be in word 0 time");
		test(st1.numOfOccurrences("mo") == 0, "mo should be in word 0 time");
		test(st1.numOfOccurrences("issi") == 2, "issi should be in word 2 time");
	}

	/**
	 * Checks the SuffixTreeNode class.
	 */
	private static void testSuffixTreeNode() {
		// test empty root
		SuffixTreeNode node = new SuffixTreeNodeImpl();
		test(node.getTotalWordLength() == 0, "node word length should be 0");
		test(node.getNumOfChildren() == 0, "node num of children should be 0");
		test(node.getParent() == null, "node's parent should be null");

		// test search, binary search, shiftChildren and addChild
		SuffixTreeNode child1 = new SuffixTreeNodeImpl(CharLinkedList.from("abc"), node);
		SuffixTreeNode child2 = new SuffixTreeNodeImpl(CharLinkedList.from("bcd"), node);
		SuffixTreeNode child3 = new SuffixTreeNodeImpl(CharLinkedList.from("hello"), node);
		SuffixTreeNode child5 = new SuffixTreeNodeImpl(CharLinkedList.from("o"), node);
		SuffixTreeNode child6 = new SuffixTreeNodeImpl(CharLinkedList.from("f"), node);
		node.setChildren(new SuffixTreeNode[]{child1, child2, child3, child5, child6, null, null, null}, 5);

		// binary search
		test(node.binarySearch('b', 0, 4) == child2, "search for 'b' should find child2");
		test(node.binarySearch('b', 2, 4) == null, "search for 'b' should find null");
		test(node.binarySearch('o', 0, 4) == child5, "search for 'o' should find child5");

		// search
		test(node.search('a') == child1, "search for 'a' should return child1");
		test(node.search('x') == null, "search for 'x' should fail");

		// add child
		SuffixTreeNode child7 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), node);
		node.addChild(child7);
		test(node.getChildren()[2] == child7, "3rd child should be child7");
		test(node.getChildren()[5] == child6, "6th child should be child6");
	}

	/**
	 * Checks the CompressedTrie class.
	 */
	private static void testCompressedTrie(){
		CompressedTrie t1 = new CompressedTrie();
		test(t1.getRoot() instanceof SuffixTreeNodeImpl, "chars should be null");
		test(t1.getRoot().parent == null, "parent should be null");
		t1.compressTree();
		test(t1.getRoot().chars == null, "chars should be null");
		test(t1.getRoot().parent == null, "parent should be null");
		test(t1.getRoot().getDescendantLeaves() == 1, "DescendantLeaves should be 1");

		CompressedTrie t2 = new CompressedTrie();
		String word = "combine$";
		char[] c_word = word.toCharArray();
		for (int i=0; i<word.length(); i++)
			t2.addSuffix(c_word, i);
		test(t2.getRoot().chars == null, "chars should be null");
		test(t2.getRoot().getNumOfChildren()  == 8, "NumOfChildren should be 8");
		test(t2.getRoot().getDescendantLeaves() == 0, "DescendantLeaves should be 0");
		test(t2.getRoot().getChildren()[1].getChars().firstChar() == 'b', "first child's char should be b");
		t2.compressTree();
		test(t2.getRoot().getDescendantLeaves() == 8, "DescendantLeaves should be 8");
		test(t2.getRoot().getChildren()[0].getChars().firstChar() == '$', "first child's char should be $");
		test(t2.getRoot().getChildren()[1].getChars().getFirst().getNext().getChar() == 'i', "second child's second char should be i");
	}

	/**
	 * a wrapper func. to testLongestRepeated
	 */
	private static void testLongestRepeatedSuffixTree(){
		testLongestRepeated("mississippi", "issi");
		testLongestRepeated("abc", "X");
		testLongestRepeated("abbc", "b");
		testLongestRepeated("error", "r");
		testLongestRepeated("nurture", "ur");
		testLongestRepeated("aaa", "aa");
		testLongestRepeated("zwxyglmna", "X");
		testLongestRepeated("ddaaann", "aa");
		testLongestRepeated("ddaaannn", "aa");
		testLongestRepeated("ddddaaannn", "ddd");
	}

	/**
	 * Checks the getLongestRepeatedSubstring function of longestRepeatedSuffixTreeImpl class.
	 * @param word string
	 * @param expected longest sub-string in word
	 */
	private static void testLongestRepeated(String word, String expected){
		test(new longestRepeatedSuffixTreeImpl(word).getLongestRepeatedSubstring().equals(expected), "Longest repeated substring should be " + expected);
	}

}