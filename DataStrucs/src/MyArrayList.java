public class MyArrayList<E extends Comparable<E>> {
	//declare all variables
	private E[] list;
	private int capacity = 4;
	private int size;
	
	//default constructor
	public MyArrayList(){
		this.list = (E[]) new Object[this.capacity];
	}
	//overloaded constructor
	public MyArrayList(int capacity){
		if(capacity > 4)
			this.capacity = capacity;
		this.list = (E[]) new Object[capacity];
	}
	
	//get size of string
	public int getSize(){
		return this.size;
	}
	
	//return element at certain index
	public E getElement(int index){
		if (index > 0 && index < size){
			return list[index];
		}
		return null;
	}
	
	//check if empty
	public boolean isEmpty(){
		return size ==0;
	}
	
	//add a string to the end
	public void add(E s){
		if(size >= capacity)
			resize(this.capacity*2);
		list[size] = s;
		size++;
	}
	
	
	//overloaded add method
	public void add (E s, int index){
		if (index >= 0 && index < size){
			if(size >= capacity)
				resize(this.capacity*2);
			for (int k = size - 1; k>= index; k--){
				list[k+1] = list[k];
			}
			list[index] = s;
			size++;
		}
		else {
			System.out.println("index " + index + "is out of range!"); 
		}
	}
	
	
	//search for element and return the index that it's at
	public int search(E key){
		for(int i = 0; i < size; i++){
			if (list[i].equals(key))
				return i;
		}
		return -1;
	}
	
	//remove a string
	public void remove(E s){
		int index = search(s);
		if (index != -1){
			E temp = list[index];
			for(int k = index+1; k<= size; k++){
				list[k-1] = list[k];
			}
			size--;
			if(size != 0 && capacity/size >= 4) resize(capacity/2);
			System.out.println(temp + " removed!");
		}
		else{
			System.out.println(s + " is not in the list");
		}
	}
	
	//remove an element at a certain index i
	public void remove(int i){
		if(!isEmpty() && i >= 0 && i < size)
			remove(list[i]);
	}
	
	//basic remove method to remove elements
	public void remove(){
		if (size != 0){
			size--;
			E temp = list[size];
			list[size] = null;
			if(size != 0 && capacity/size >= 4)
				resize(capacity/2);
			System.out.println(temp + " removed!");
		}
		else{
			System.out.println("The list is empty");
		}
	}
	
	//resize the array to a certain capacity
	private void resize(int capacity){
		this.capacity = capacity;
		E[] temp = list;
		list = (E[]) new Object[this.capacity];
		for(int i = 0; i < size; i++){
			list[i] =temp[i];
		}
	}
	
	public void print(){
		System.out.print("|");
		for (int i = 0; i < capacity; i++){
			System.out.print(" " + list[i] +" | ");
		}
		System.out.println();
	}
}
