public class MyQueue<E extends Comparable<E>>{
	MyLinkedList<E> link = new MyLinkedList<>();
	//remove the last in the queue which is first in the list
	public E dequeue(){
		E temp = link.getFirst();
		link.removeFirst();
		return temp;
	}
	
	//add first to queue which adds last to list
	public void enqueue(E element){
		link.addLast(element);
	}
	
	public void print(){
		link.print();
	}
}
