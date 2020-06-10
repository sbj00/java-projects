public class MyStack<E extends Comparable<E>> {
	int top;
	MyLinkedList<E> link = new MyLinkedList<>();
	
	//set top
	public void setTop(){
		top = link.getSize();
	}
	
	//remove the top of the stack
	public void pop(){
		link.removeFirst();
	}
	
	//add things to the top of the stack
	public void push(E element){
		link.addFirst(element);
	}
	
	//pick the top of the stack and return it
	public E pick(){
		return link.getFirst();
	}
	
	//print the linked list
	public void print(){
		link.print();
	}
	
}
