
public class MyBST<E extends Comparable<E>>{
	//declare all variables
	private MyTreeNode<E> root;
	private int size;
	
	//check the size of the tree
	public int getSize(){
		return size;
	}
	
	void add(E s){
		root = add(root,s);
	}
	
	MyTreeNode<E> add(MyTreeNode<E> node, E s){
		if(node == null){
			node = new MyTreeNode<E>(s);
			return node;
		}
		
		if(s.compareTo(node.element) < 0 )
			node.left = add(node.left, s);
		else if(s.compareTo(node.element) > 0)
			node.right = add(node.right,s);
		
		size++;
		return node;
	}
	
	//search the tree(given code)
	public boolean find(E key){
		MyTreeNode<E> valueFound = find(root, key);
		if (valueFound == null){
			return false;
		}
		return true;
	}
	public MyTreeNode<E> find(MyTreeNode<E> root, E key){
		//base case
		if (root == null)
			return null;
		if (key.compareTo(root.element) == 0)
			return root;
		else if(key.compareTo(root.element) < 0)
			return find(root.left, key);
		else
			return find(root.right, key);
	}
	
	//find the height of any tree(needed to do a breadth first search)
	public int height(MyTreeNode<E> root){
		//if the root is finally null, return 0
		if (root == null)
			return 0;
		//otherwise keep track
		else{
			//go through the tree
			int lHeight = height(root.left);
			int rHeight = height(root.right);
			//add one to left height if it's greater than the right height
			if(lHeight > rHeight)
				return (lHeight+1);
			else
				return (rHeight+1);
		}
	}
	
	//print the tree, level by level
	public void print(){
		int height = height(root);
		
		for (int level = 0; level <= height; level++)
			BFSTraversal(root, level);
	}
	
	//traverse the tree to print it ouy
	public void BFSTraversal(MyTreeNode<E> root, int level){
		//base case if no tree
		if (root == null)
			return;
		//base case if bottom of the level is reached
		else if (level ==1){
			System.out.print(root.element + " ");
		}
		else{
			//otherwise go down LEFT side first
			BFSTraversal(root.left, level-1);
			//then go down the RIGHT side and hit lefts
			BFSTraversal(root.right, level-1);
		}
	}
	
	//delete function
	public boolean delete(E key){
		MyTreeNode<E> current= root;
		MyTreeNode<E> parent = root;
		
		//find the parent and the current nodes which contain the element
		if (root != null){
			while(current.element != key){
				parent = current;
				if(key.compareTo(current.element) < 0)
					current = current.left;
				else current = current.right;
			}
			//if left == null, case 1
			if(current.left == null){
				if(current.element == null){
					if (current == root) root = current.right;
					else{
						if(key.compareTo(parent.element) > 0)
							parent.right = current.left;
						else
							parent.left = current.left;
					}
				}
			}
			//left has a subtree then case 2
			else{
				MyTreeNode<E> parentOfRightMost = current;
				MyTreeNode<E> rightMost = current.left;
				
				while (rightMost.right != null){
					parentOfRightMost = rightMost;
					rightMost = rightMost.right;
				}
				
				current.element = rightMost.element;
				if(!current.left.equals(rightMost))
					parentOfRightMost.right = rightMost.left;
				else parentOfRightMost.left = rightMost.left;
			}
			size--;
			return true;
		}
		return false;
	}
	
	//in order search
	public void inOrder(){
		inOrder(root);
	}
	//go dow all the lefts and then print, and then go down the rights and print
	public void inOrder(MyTreeNode<E> root){
		if(root == null) return;
		inOrder(root.left);
		System.out.print(root.element + " ");
		inOrder(root.right);
	}
	
	//preorder search
	public void preOrder(){
		preOrder(root);
	}
	//print element first, then its lest child, then right
	public void preOrder(MyTreeNode<E> root){
		if(root == null) return;
		System.out.print(root.element + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	//postorder search
	public void postOrder(){
		postOrder(root);
	}
	//go down LRN until hit the bottom and start printing, root should print last
	public void postOrder(MyTreeNode<E> root){
		if(root == null) return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.element + " ");
	}

}