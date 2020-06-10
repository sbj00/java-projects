
public class NameList {
	private static Node head;
	private static Node tail;
	
	//add - Adds a new name.  Names must be at least 2 characters long.  Adds the letter node if not already present.
	public void add(String name){
		if (name.length() < 2){
			System.out.println("INVALID NAME LENGTH");
			return;
		}
		
		String firstLetter = Character.toString(name.charAt(0)).toUpperCase();
		Node newNode = find(firstLetter);
		//if there are no other names that start with this letter
		if (newNode == tail){
			//add in the letter then add in the name
			addTwoNodes(firstLetter, name);
		}
		//start at the character node and traverse until you find the spot to add
		else{
			addNode(name);
		}
	}
	
	public void addNode(String name){
		//traverse the list
		Node previous = head;
		Node current = head.next;
		while (current != null){
			//when we find where to add it, do so
			if(name.compareTo(current.data) < 0){
				Node newNameNode = new Node(name, current);
				previous.next = newNameNode;
				return;
			}
			else{
				previous = current;
				current = current.next;
			}
		}
		//if we made it to the end of the list (out of the while loop), then this is where we add the node
		Node newNameNode = new Node(name, current);
		previous.next = newNameNode;
		return;
	}
	
	public void addTwoNodes(String letter, String name){
		//if adding to beginning
		if( head == null || letter.compareTo(head.data) < 0){
			Node newNameNode = new Node(name, null);
			Node newHeaderNode = new Node(letter, newNameNode);
			head = newHeaderNode;
		}
		//otherwise find where to add it
		else{
			Node previous = head;
			Node current = head.next;
			while (current != null){
				//once the place is found add it
				if(letter.compareTo(current.data) < 0){
					Node newNameNode = new Node(name, current);
					Node newHeaderNode = new Node(letter, newNameNode);
					previous.next = newHeaderNode;
					return;
				}
				else{
					previous = current;
					current = current.next;
				}
			}
			//if we made it to the end of the list (out of the while loop), then this is where we add the node
			Node newNameNode = new Node(name, current);
			Node newHeaderNode = new Node(letter, newNameNode);
			previous.next = newHeaderNode;
			return;
		}
	}
	
	//remove - Removes a name.  If the name is the last one for a letter, the letter node should also be removed.
	public void remove(String name){
		//don't call this method if there's nothing in the list
		if (head == null)
			return;
		
		//traverse the lsit
		Node previous = head;
		Node current = head.next;
		while(current != null){
			//if we are at the name
			if(current.data.compareTo(name) == 0){
				//remove it
				previous.next = current.next;
				//if this was the only name in list, make the head letter null
				if (current.next == null)
					head = null;
				//if this was the only name of this letter in list, remove the header
				else if (previous.data.length() == 1 && current.next.data.substring(0,1).compareTo(name.substring(0,1)) != 0){
					remove(name.substring(0,1));
				}
				return;
			}
			//edge case for if removing the first node
			else if (previous.data.compareTo(name) == 0){
				head = previous.next;
				return;
			}
			previous = current;
			current = current.next;
		}
		
		System.out.println("Name \"" + name + "\" was not in list");
	}
	
	//removeLetter - Removes a letter and all names for that letter.
	public void removeLetter(String letter){
		//dont call this method with an empty list
		if (head == null)
			return;
		
		//normalize the letter
		letter = letter.toUpperCase();

		//traverse the list
		Node current = head;
		while(current != null){
			//looking for the first name that starts with the letter
			if(current.data.substring(0,1).compareTo(letter) == 0 && current.data.length() != 1){
				//remove it and find the next one until all gone
				remove(current.data);
			}
			current = current.next;
		}
		
		//double check the edge case
		Node previous = head;
		Node current2 = head.next;
		while(current2.next != null){
			previous = current2;
			current2 = current2.next;
		}
		
		if (current2.data.substring(0,1).toUpperCase().compareTo(letter) == 0){
			remove(current2.data);
		}

	}
	
	//find - Finds a name by traversing the nodes.
	public static Node find(String data){
		//start at the beginning
		Node n = head;
		if (head == null){
			return tail;
		}
		//until you reach the end
		while(n != null){
			//look for the word and return if found
			if(n.data.compareTo(data) == 0)
				return n;
			//otherwise keep moving
			n = n.next;
		}
		//the letter could never be the tail node
		if(data.length() == 1)
			return tail;
		//the name could never be the head node
		else
			return head;
	}
	
	// toString - Returns a string of the list formatted as shown below
	public String toString() {
	  if(head == null){
		  System.out.println("EMPTY LIST");
		  return null;
	  }
	  
	  Node current = head;
	  while(current != null){
		  	if(current.data.length() == 1)
		  		System.out.println(current.data);
		  	else{
		  		System.out.println(" "+current.data);
		  	}
		  	current = current.next;
	  }
	  
	  System.out.println();
	  return null;
	}
	
	//the node class
	private static class Node{
		//that contains a name/letter and a pointer
		String data;
		Node next;
		
		//constructor
		Node(String d, Node n){
			data = d;
			next = n;
		}
	}
	
	public static void main(String [] args){
		//create a NameList
		NameList example = new NameList();
		System.out.println("TESTING ADD FUNCTION");
		//add names in unsorted order
		example.add("Charles");
		example.add("Abbie");
		example.add("Amy");
		example.add("Darwin");
		example.add("Ali");
		example.add("Stephanie");
		example.toString();
		
		System.out.println("TESTING REMOVE FUNCTION");
		//remove Darwin and Ali by name
		example.remove("Darwin");
		example.remove("Ali");
		example.toString();
		
		System.out.println("TESTING REMOVELETTER FUNCTION");
		//remove all the remaining A names (edge case)
		example.removeLetter("A");
		example.toString();
		
		System.out.println("TESTING FIND FUNCTION");
		//find a node method is used throughout all the other methods, but to test it
		Node testFind = find("Stephanie");
		System.out.println(testFind.data);
		
	}
}
