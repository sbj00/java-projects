import java.util.Scanner;
/*
 * STEPHANIE JOSEPH; CS 2336.006
Analysis:
	- SAME AS IN DRIVER MAIN
Design:
	- create 9 boards and 2 players array, the two indexes of the players
	- constructor to play the game
	- rules where the player decides what version of game to play
	- play game method
		while the game isn't over make a move
		then print the board
	- make a move (2 types)
		a move that involves a users row and col (regularMove)
		or user picks a row col and board (specialMove)
		all moves involve getting a space
			process and read in two numbers and then zero index them
		special move involves getting a board number
	- game over
		happens if someone has won three board in one of 4 directions
			-Row
			-Col
			-DiagL
			-DiagR
		or all the boards are full
	- print moves will print all moves for a given board
	- print all moves prints all moves for all boards
Test:
	- Heres where most of my testing happens
	- I test for invalid input, every single time the user inputs it
		- my code can handle any wrong input and will keep taking in data until it's correct
		- this includes when they input out of bounds spaces
	- computer to make sure he keeps trying to pick a spot until it reaches one
	- every type of win (row, col, diagl, diagr, cats game) to make sure my code was right
	- i tested to make sure the user can pick what version of the game they want to play and it handles it right
	- i tested to make sure if the user plays against AI they can pick their mark and they get to play that mark(this took a while)
*/

public class UltGame {
	//create the 9 tic-tac-toe boards
	private Board[] game = new Board[9];
	//two players and their marks
	private Player[] players = new Player[2];
	private String[] marks = {"X", "O"};
	//there are two indexes (real players or AI)
	private int currentIndex, indexOfAI;
	//you need 3 points to win
	private int scoreToWin = 3;
	
	//create the ultimate game
	public UltGame(){
		//initialize the game
		init();
		//see what version and set that game
		int version = rules();
		setPlayers(version);
		//play game, at a non valid board number to know it's the first move
		playGame(-1);
	}
	
	//initialize the 9 board objects with a 3x3 tic tac toe board
	private void init(){
		for(int i= 0; i < 9; i++){
			game[i] = new Board();
		}	
	}
	
	//print the rules of the game and let them pick a version
	private int rules(){
		Scanner s = new Scanner(System.in);
		String version;
		char versionCh;
		int x;
		System.out.println("Ultimate Tic-Tac-Toe(TTT) involves 9 TTT Games in 1!");
		System.out.println("Input will only be accepted if the FIRST NUMBER in a line is a digit");
		System.out.println("Press ENTER after each number that you input");
		do{
			System.out.println("Enter (1) 1-player version, (2) 2-player version");
			version = s.next();
			versionCh = version.charAt(0);
			while(!Character.isDigit(versionCh)){
				version = s.next();
				versionCh = version.charAt(0);
			}
			x = versionCh - '0';
		}while(x != 1 && x != 2);
		return x;
	}
	
	//initialize the players
	private void setPlayers(int version) {
		//declare all variables
		Scanner s = new Scanner(System.in);
		String mark;
		char markCh;
		int index = 0;
		
		//1-PLAYER: input validation, decide which player the computer is
		if (version == 1){
			//have the players pick a mark until valid
			do{
				System.out.println("Pick your mark: 0 for 'X' or 1 for 'O'");
				mark = s.next();
				markCh = mark.charAt(0);
				while(!Character.isDigit(markCh)){
					mark = s.next();
					markCh = mark.charAt(0);
				}
				index = markCh - '0';
				indexOfAI = (index+1)%2;
			}while (index != 0 && index != 1);
			
			//initialize the player names and their marks
			for(int i= 0; i < players.length; i++){
				Player p = new Player("Player " + (i+1), marks[i]);
				players[i] = p;
			}
		}
		
		//2-PLAYER: set the input like normal
		else{
			for(int i= 0; i < players.length; i++){
				Player p = new Player("Player " + (i+1), marks[i]);
				players[i] = p;
			}
			currentIndex= 0;
			indexOfAI = -1;
		}
	}
	
	//play the game
	private void playGame(int board) {
		//as long as the game isn't over
		while(!gameOver()){
			//print board
			print();
			//if it's the first move
			if(board == -1){
				board = specialMove();
			}
			//if the player get's to pick the board
			else if(game[board].isFull()){
				//dont print out moves for AI's turn
				if(currentIndex != indexOfAI)
					printAllMoves();
				board = specialMove();
			}
			//if the player's board is chosen for him
			else{
				//dont print out moves for AI's turn
				if(currentIndex != indexOfAI)
					printMoves(board);
				board = regularMove(board);
			}
		}
		//print the final move that was made
		if(isFull())
			System.out.println("No Winner! Game Over");
		else
			System.out.println(players[(currentIndex + 1)%2].getName() + " with mark " + players[(currentIndex + 1)%2].getMark() + " has won!");
		print();
	}
	
	//play a move where you take in the row, col AND BOARD #
	private int specialMove(){
		//declare all variables
		int board, row, col;
		boolean available = false;
		Scanner s = new Scanner(System.in);
		
		//let the user pick a place to start
		do{
			System.out.println("Enter a valid board #, row #, col #:");
			board = getBoard();
			row = getSpace();
			col = getSpace();
		}while((board > 8 || board < 0) || (row > 2 ||row < 0 ) || (col > 2 || col < 0));

		//make the move, until valid
		available = game[board].makeMove(players[currentIndex].getMark(), row, col);
		while(!available){
			System.out.println("Please input an open board and space");
			board = getBoard();
			row = getSpace();
			col = getSpace();
			available = game[board].makeMove(players[currentIndex].getMark(), row, col);
		}
		
		//switch the player
		currentIndex = (currentIndex + 1) % 2;
		
		//return the new board
		return (3*row) + col;
	}
	
	//play a move where you take in the row and col num
	private int regularMove(int board){
		//declare all variables
		int row, col;
		boolean available = false;
		Scanner s = new Scanner(System.in);
		
		//take a move until valid
		do{
			System.out.println("Player " + players[currentIndex].getMark() + " choose from one of the above spaces (press enter after each number): ");
			row = getSpace();
			col = getSpace();
		}while((row > 2 ||row < 0 ) || (col > 2 || col < 0));
		
		//make the move, until valid
		available = game[board].makeMove(players[currentIndex].getMark(), row, col);
		while(!available){
			if(currentIndex == 0)
				System.out.println("the box you selected on board " + (board+1) + " is already full");
			row = getSpace();
			col = getSpace();
			available = game[board].makeMove(players[currentIndex].getMark(), row, col);
		}
		
		//switch the player
		currentIndex = (currentIndex+1) % 2;
		
		//return the new board
		return (3*row)+col;
	}
	
	//get a space number
	private int getSpace(){
		Scanner s = new Scanner(System.in);
		String numSt;
		char ans;
		int num;
		
		if(currentIndex == indexOfAI)
			num = players[currentIndex].randomNumber(3);
		//input validation for the users input
		else{
			numSt = s.next();
			ans = numSt.charAt(0);
			while(!Character.isDigit(ans)){
				System.out.println("Enter a number");
				numSt = s.next();
				ans = numSt.charAt(0); 
			}
			num = ans - '0';
		}
		//0 index the num
		return (num -1);

	}
	
	//get a board number
	private int getBoard(){
		Scanner s = new Scanner(System.in);
		int board;
		String boardSt;
		char ans;
		if(currentIndex == indexOfAI)
			board = players[currentIndex].randomNumber(9);
		//input validation for board number
		else{
			boardSt = s.next();
			ans = boardSt.charAt(0);
			while(!Character.isDigit(ans)){
				System.out.println("Enter a number");
				boardSt = s.next();
				ans = boardSt.charAt(0); 
			}
			board = ans - '0';
		}
		//0 index the board
		return board - 1;		
	}
	
	//print all the moves for each board
	private void printAllMoves(){
		int board = 0;
		for(Board g: game){
			System.out.println("board " + (board + 1) +  " possible moves:");
			g.printAvailable();
			board++;
		}
	}
	
	//print out all possible moves on a given board
	private void printMoves(int board){
		System.out.println("board " + (board+1) + " possible moves:");
		game[board].printAvailable();
	}
	
	//print the ultimate board
	private void print(){
		int countNumRows = 0;
		//go through the 9 boards top left to bottom right
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				//print out the boards row by row
				game[(i*3) + j].print(countNumRows);
				//if your not at an end board, print out a space between the boards
				if(((i*3) + j)%3 != 2)
					System.out.print("| ");
			}
			//add one to the number of rows outputted
			countNumRows++;
			//don't change boards if we haven't outputted all the rows
			if(countNumRows < 3)
				i--;
			//otherwise
			else{
				//if we aren't at the bottom row of boards
				//print a line
				if(i < 2){
					System.out.println();
					System.out.print(".....................");
				}
				//reset the number of rows on the output
				countNumRows = 0;
			}
			System.out.println();
		}
	}
	
	//check to see if the game is over
	private boolean gameOver() {
		//return true if there is a winner OR the board is full
		if(isWinner(players[(currentIndex+1)%2])|| isFull())
			return true;
		else return false;
	}	
	
	//check to see if the ult game is full
	private boolean isFull() {
		for(Board g : game){
			if(!g.isFull()) return false;
		}
		return true;
	}
	
	//check to see if there is a winner
	private boolean isWinner(Player player){
		if(checkRowWin(player))
			return true;
		else if (checkColWin(player))
			return true;
		else if (checkDiagRWin(player))
			return true;
		else if(checkDiagLWin(player))
			return true;
		return false;
	}
	
	//check if a row won
	private boolean checkRowWin(Player player){
		//declare all variables
		int count = 0;
		//nested for loop to go through each row
		for (int row = 0; row < 3; row++){
			count = 0;
			for (int col = 0; col < 3; col ++){
				//if the mark at a given index is the players mark, add one to their count
				if(game[(row*3) + col].isWinner(player)){
					count++;
				}
			}
			//after going through each row, see if the player got enough marks to win
			if(count == scoreToWin){
				return true;
			}
		}
		//if the whole board is gone through, with no row winner, return false
		return false;
	}
	
	//check if the diagonal right won
	private boolean checkDiagRWin(Player player){
		//check all squares that have the same index for row and column
		for(int i = 0; i < 3; i++){
			if(game[(i*3) + i].isWinner(player));
			else return false;
		}
		return true;
	}
	
	//check if the diagonal left won
	private boolean checkDiagLWin(Player player){
		//check all squares that have a row and column that add up to the total number columns allowed
		for(int i = 0; i < 3; i++){
			if(game[(i*3) + i].isWinner(player));
			else return false;
		}
		return true;
	}
	
	//check if a column won
	private boolean checkColWin(Player player){
		//declare all variables
		int count = 0;
		
		//go through each column and add one to the players score if they have a mark
		for (int col = 0; col < 3; col++){
			count = 0;
			for (int row = 0; row < 3; row++){
				if(game[(row*3) + col].isWinner(player)){
					count++;
				}
			}
			//if they have enough marks in any given colun, return that they won
			if(count == scoreToWin)
				return true;
		}
		return false;
	}
}

