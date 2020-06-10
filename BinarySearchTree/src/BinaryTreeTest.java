
public class BinaryTreeTest {
	public static void main(String [] args){
		//make a tree of integers
		MyBST<Integer> intTree = new MyBST<>();
		intTree.add(10);
		intTree.add(3);
		intTree.add(5);
//		intTree.insert(7);
//		intTree.insert(6);
//		intTree.insert(2);
//		intTree.insert(9);
		
		//BFS print
		intTree.print();
		System.out.println(intTree.find(3));
//		//delete items, say true if they were deleted
//		System.out.println(intTree.delete(10));
//		System.out.println(intTree.delete(7));
//		System.out.println(intTree.delete(9));
//		System.out.println(intTree.delete(5));
//		//BFS print after deletions
//		intTree.print();
//		
//		//print in order, pre-order and post-order
		System.out.println();
		intTree.inOrder();
//		System.out.println();
		intTree.preOrder();
//		System.out.println();
//		intTree.postOrder();
	}
}