public class ListTest {
	//test the methods in MyArrayList
	public static void main(String[] args) {
		MyLinkedList<String> myLinkedList = new MyLinkedList<>();
		myLinkedList.addFirst("A");
		myLinkedList.addFirst("B");
		myLinkedList.addFirst("C");
		myLinkedList.addFirst("D");
		myLinkedList.print();
		
		MyLinkedList<Integer> myLinkedList2 = new MyLinkedList<>();
		myLinkedList2.addFirst(1);
		myLinkedList2.addFirst(2);
		myLinkedList2.addFirst(3);
		myLinkedList2.addFirst(4);
		myLinkedList2.print();
		
		MyStack<String> stack = new MyStack<>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.print();
		System.out.println();

		MyStack<Integer> intStack = new MyStack<>();
		intStack.push(1);
		intStack.push(2);
		intStack.push(3);
		intStack.push(4);
		intStack.push(5);
		intStack.print();
		System.out.println();
	
		MyQueue<String> queue = new MyQueue<>();
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		queue.print();
		queue.dequeue();
		queue.print();
		
		MyQueue<Integer> intQueue = new MyQueue<>();
		intQueue.enqueue(1);
		intQueue.enqueue(2);
		intQueue.enqueue(3);
		intQueue.print();
		intQueue.dequeue();
		intQueue.print();
	}
}