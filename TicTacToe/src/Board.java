/*
 * STEPHANIE JOSEPH; CS 2336.006
Analysis:
	- SAME AS IN DRIVER MAIN
Design:
	- all board are 3x3, contain 9 boxes and have the special property of a first winner
	- board constructor initializes an array of boxes
	- in a board you can
		get the mark at any space
		make a move in a specific place w a specific mark
		check to see if a board is full
		
		print all available spaces
		print a row in the board
		print the whole board (not useful in this game, but could be in later versions)
		
		check to see if someone is the firstWinner of a board
			-true if they contain property firstWinner for the board
			-true if they just won a board w no winner
			-false otherwise
Test:
	- the main thing to test here was that every board CORRECTLY held the property of first winner
	- other than that all this code was saved from my inclass so i knew all the winning checks worked
*/

public class Board{
	//declare all variables
	private final int ROW_NUM = 3;
	private final int COL_NUM = 3;
	private Box boxes[];
	//all boards have a property of the first person to win that board
	private String firstWinner = "-";
	
	//default constructor
	public Board(){
		boxes = new Box[ROW_NUM*COL_NUM];
		init();
	}
	
	//see what mark is at a certain box
	public String getMark(int row, int col){
		return boxes[row * COL_NUM + col].getPlaceHolder();
	}
	
	//create all cells of the board and initialize them
	public void init(){
		for(int i = 0; i< boxes.length; i++){
			Box b = new Box( i / COL_NUM ,i % COL_NUM);
			boxes[i] =b;
		}
	}
	
	//method will mark a box with a players mark IF the 
	public boolean makeMove(String s, int row, int col){
		return boxes[row * COL_NUM + col].setPlaceHolder(s);	
	}
	
	//go through the whole board and check to see if at least one space is available
	public boolean isFull(){
		for(Box b: boxes){
			if(b.isAvailable())
				return false;
		}
		return true;
	}
	
	/* ALL PRINT FUNCTIONS OF A BOARD*/
	//print out all available spaces on a board
	public void printAvailable(){
		int index;
		for(int row = 0; row < 3; row++){
			for(int col = 0; col < 3; col++){
				index = (3*row) + col;
				if (boxes[index].isAvailable())
					System.out.println((row + 1) + "," + (col + 1));
			}
		}
	}
	
	//print out the board with whatever is in the placeholder
//	public void print(){
//		for(int i = 0; i< boxes.length; i++){
//			boxes[i].print(COL_NUM);
//		}
//	}
	
	//print a given row on a board
	public void print(int numRow){
		int count = 0;
		while(count < 3){
			System.out.print(boxes[(numRow*3)+count].getPlaceHolder() + " ");
			count++;
		}
	}
	/* END ALL PRINT FUNCTIONS*/
	
	
	/*CHECK THE WINNER IN ALL DIRECTIONS*/
	public boolean isWinner(Player currentPlayer){
		//person can only win a board...
		boolean winner = false;
		
		//if they have already won it previously
		if(firstWinner == currentPlayer.getMark())
			winner = true;
		//or nobody has won it yet
		else if(firstWinner == "-"){
			if(checkRowWin(currentPlayer))
				winner = true;
			else if (checkColWin(currentPlayer))
				winner = true;
			else if (checkDiagRWin(currentPlayer))
				winner = true;
			else if(checkDiagLWin(currentPlayer))
				winner = true;
			if(winner)
				firstWinner = currentPlayer.getMark();
		}
		//return if the currentPlayer is the winner
		return winner;
	}
	//check if a row won
	private boolean checkRowWin(Player currentPlayer){
		//declare all variables
		int count = 0;
		//nested for loop to go through each row
		for (int row = 0; row < ROW_NUM; row++){
			count = 0;
			for (int col = 0; col < COL_NUM; col++){
				//if the mark at a given index is the players mark, add one to their count
				if(getMark(row, col).equals(currentPlayer.getMark()))
					count++;
			}
			//after going through each row, see if the player got enough marks to win
			if(count == COL_NUM){
				return true;
			}
		}
		//if the whole board is gone through, with no row winner, return false
		return false;
	}
	
	//check if the diagonal right won
	private boolean checkDiagRWin(Player currentPlayer){
		//check all squares that have the same index for row and column
		for(int i = 0; i < COL_NUM; i++){
			if(getMark(i, i).equals(currentPlayer.getMark()));
			else return false;
		}
		return true;
	}
	
	//check if the diagonal left won
	private boolean checkDiagLWin(Player currentPlayer){
		//check all squares that have a row and column that add up to the total number columns allowed
		for(int i = 0; i < COL_NUM; i++){
			if(getMark(i, this.COL_NUM-1-i).equals(currentPlayer.getMark()));
			else return false;
		}
		return true;
	}
	
	//check if a column won
	private boolean checkColWin(Player currentPlayer){
		//declare all variables
		int count = 0;
		
		//go through each column and add one to the players score if they have a mark
		for (int col = 0; col < COL_NUM; col++){
			count = 0;
			for (int row = 0; row < ROW_NUM; row++){
				if(getMark(row, col).equals(currentPlayer.getMark()))
					count++;
			}
			//if they have enough marks in any given colun, return that they won
			if(count == COL_NUM)
				return true;
		}
		return false;
		
	}
}


