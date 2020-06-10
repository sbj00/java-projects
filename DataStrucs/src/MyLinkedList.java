
public class MyLinkedList <E extends Comparable<E>>{
	private Node<E> first;
	private Node<E> last;
	private int size;
	
	//check to see it the first node is null, if so the list is empty
	public boolean isEmpty(){
		return first == null;
	}
	
	public E getLast(){
		return last.element;
	}
	
	public E getFirst(){
		return first.element;
	}
	
	public int getSize(){
		return size;
	}
	//add an element at the first index
	public void addFirst(E s){
		Node<E> newNode = new Node<E>(s);
		//if we are actually adding our first element
		if(isEmpty()){
			last = newNode;
		}
		else{
			newNode.next = first;
		}
		// sit the first pointer to the new node; add one to the list size
		first = newNode;
		size++;
	}
	
	//add an element at the last index
	public void addLast(E s){
		Node<E> newNode = new Node<E>(s);
		//if we are actually adding our first element
		if(isEmpty()){
			first = newNode;
		}
		else{
			last.next = newNode;
		}
		//point the last pointer to the new node; add one to the size
		last = newNode;
		size++;
	}
	
	//remove the first element
	public void removeFirst(){
		//store the value we are removing
		E temp = first.element;
		//if the list isn't empty
		if(!isEmpty()){
			//if only one element in the list
			if (first == last){
				first = last = null;
			}
			//otherwise move the first pointer up one in the list
			else{
				first = first.next;
			}
			//decrease size by one and output what element was removed
			size--;
			System.out.println(temp + " is removed");
		}
		else{
			System.out.println("The list is empty");
		}
	}
	
	//remove the last element
	public void removeLast(){
		//store the value being removed
		E temp = last.element;
		//if it's not empty
		if(!isEmpty()){
			//if only one element, make the lsit empty
			if(first == last){
				first = last = null;
			}
			//otherwise
			else{
				//create a new node
				Node<E> previousNode = first;
				//move to the last node
				while(previousNode.next == last){
					previousNode = previousNode.next;
				}
				///set the last node to null
				previousNode.next = null;
				last = previousNode;
			}
			//decrease size by one and output elemenet removed
			size--;
			System.out.println(temp + " is removed");
		}
		else{
			System.out.println("The list is empty");
		}
	}
	
	
	public boolean search(E key){
		//create a first pointer
		Node<E> pointer = first;
		//keep proceeding through the list, only break when you're at the end or you found the key
		while( pointer != null && !pointer.element.equals(key)){
			pointer = pointer.next;
		}
		//return true or false depending on whether the pointer is null
		return pointer != null;
	}

	public void remove(E key){
		//if you can find the key
		if(search(key)){
			Node<E> prev = first;
			Node<E> curr = first;
			//while you aren't at the end and still havent found the key
			while (curr != null && !curr.element.equals(key)){
				prev = curr;
				curr = curr.next;
			}
			//if the element is the first, remove it
			if(curr == first) removeFirst();
			//if element is the last, remove it
			else if (curr == last) removeLast();
			//other change where the pointer points, decrease size by one
			else{
				prev.next = curr.next;
				size--;
				System.out.println(key + " has been removed");
			}
		}
		else{
			System.out.println(key + " is not in the list");
			
		}
	}
	
	//print the list
	public void print(){
		Node<E> pointer = first;
		if(!isEmpty()){
			System.out.print("| ");
			while(pointer != null){
				System.out.print(pointer.element + " | ");
				pointer = pointer.next;
			}
			System.out.println();
		}
	}
	
	
	
}
