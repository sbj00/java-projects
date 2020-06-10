/*
 * STEPHANIE JOSEPH; CS 2336.006
Analysis:
	- Make a TTT Game with 9 Boards, 2 Players
	- The person plays in a corresponding board of the last move
	- If the board is already one, they must play but their mark doesn't count
	- If the board is full, the next player gets to pick the board they want to play in
	- You must win 3 TTTGames in a row,col or diag to win the ultimate game
Design:
	- UltGame -> 9 Boards -> 9 Boxes
	- UltGame -> 2 players
 * */

public class DriverMain {
	public static void main(String args[]){
		//create and play a new ultimate ttt game
		UltGame game = new UltGame();
	}
}
