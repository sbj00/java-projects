/*
 * STEPHANIE JOSEPH; CS 2336.006
Analysis:
	- SAME AS IN DRIVER MAIN
Design:
	- all players have a name and a mark
		all getters and setters
		randomNumber is for the AI player and will return a random space for the AI to play in
Test:
	- all the code came from inClass so i knew all 'edge cases' worked and so did the logic
*/

public class Player {
	//players have these two attributes
	private String name;
	private String mark;
	
	//constructor that takes both variables as parameters
	public Player(String name, String mark){
		this.name = name;
		this.mark = mark;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	//create a random number to be either the players row or column index
	public int randomNumber(int range){
		int rand = (int) (Math.random() * range);
		return rand+1;
	}
}
