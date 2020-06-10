//my tree node is a generic type(
public class MyTreeNode<E>{
	//if something goes in that isn't string then COMPILE time error happens, not RUN time
	E element;
	MyTreeNode<E> left;
	MyTreeNode<E> right;
	
	MyTreeNode(E element){
		this.element = element;
	}
}