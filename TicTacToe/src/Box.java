/*
 * STEPHANIE JOSEPH; CS 2336.006
Analysis:
	- SAME AS IN DRIVER MAIN
Design:
	- all boxes are at a specific row and col on a board and have a mark inside them
	- with boxes you can
		see whats inside it
		know if available
		set the mark inside it
		or print out what is inside the box
Test:
	- all the code came from inClass so i knew all 'edge cases' worked and so did the logic
*/

class Box{
	
	//declare all variables as private
	private int row;
	private int col;
	private final static String DASH = "-";
	private String placeholder = DASH;
	
	//when creating a box instance, set the rows and cols with values
	public Box(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	//return what mark is in a box ('X', 'O', or '-')
	public String getPlaceHolder(){
		return placeholder;
	}
	
	//check to see if the box is available
	public boolean isAvailable(){
		//return true if the placeholder is still a dash, false otherwise
		return this.placeholder.equals(Box.DASH);
	}
	
	//place a mark in a box or 'set the place holder'
	public boolean setPlaceHolder(String placeholder){
		//if the box is available
		if(isAvailable()){
			//mark the box and return true (indicating that the mark was placed)
			this.placeholder = placeholder;
			return true;
		}
		else
			return false;
	}

	//method to print the real board
	public void print(int COL_NUM){
		//output whatever's stored in the placeholder
		System.out.print(placeholder);
		//if you are at the max number of allotted columns, output a new line
		if (col == COL_NUM -1){
			System.out.println();
		}
	}
}
