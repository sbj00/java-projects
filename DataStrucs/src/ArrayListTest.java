public class ArrayListTest {
	//test the methods in MyArrayList
	public static void main(String[] args) {
		MyArrayList<String> myArrayList = new MyArrayList<>();
		myArrayList.print();
		myArrayList.add("a"); 
		myArrayList.add("b", 0);
		myArrayList.print();
	    myArrayList.add("c");
	    myArrayList.add("d", 1);
	    myArrayList.print();
	    
	    
		MyArrayList<Integer> intArrayList = new MyArrayList<>();
		intArrayList.print();
		intArrayList.add(1); 
		intArrayList.add(2, 0);
		intArrayList.print();
		intArrayList.add(3);
		intArrayList.add(4, 1);
		intArrayList.print();
	}
}