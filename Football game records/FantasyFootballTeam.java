/**
FantasyFootballTeam class stores a collection (Array) of FootBallPlayer objects and performs basic search and toString operations

@author Priya Pilla
@version 1.0

COP2253	Project #: 3
File Name: FantasyFootballTeam.java
*/

package ppillaproj3;

public class FantasyFootballTeam {

	private String teamName;
	private String teamOwner;
	FootBallPlayer footballPlayer[];
	private int noOfElements;
	private int i = 0;
	
	FantasyFootballTeam(String theTeamName, String theTeamOwner, int sizeOfArray) {
		teamName = theTeamName;
		teamOwner = theTeamOwner;
		footballPlayer = new FootBallPlayer[sizeOfArray];
		noOfElements = sizeOfArray;
	}
	
/**
Method takes a FootBallPlayer objects and add it to the Array. Ensures the parameter is not null and the array is not full. (Produces Error message to console )
@param FootBallPlayer player
@return void
*/
	
	public void addPlayer(FootBallPlayer player) {
		
		while(i <= noOfElements) {
			if(i == noOfElements) {
				System.out.println("Error: Array is full");
				break;
			}
			if(player == null) {
				System.out.println("Error: Parameter is null");
				break;
			}
			
			footballPlayer[i] = player;
			i++;
			break;
		}
		
	}
	
/**
Method searches the array for all players at a particular position (ignores case). Returns a string of all the player at that position or a string stating no players at that position.
@param String findPosition
@return void
*/
	
	public void findPlayerByPosition(String findPosition) {
		int check = 0;
		for(int j = 0; j < noOfElements; j++) {
			
			if(footballPlayer[j].getPosition() == findPosition) {
				System.out.println(footballPlayer[j].getName() + "\n");
				check = 1;
			}
			if((j == (noOfElements-1))&&(check == 0)){
				System.out.println("No player at " + findPosition + " found.");
			}
		}
	}
	
/**
Method to print team and player info
@return String
*/
	
	public String toString() {
		System.out.println("Team Name: " + teamName + ", " + "Owner: " + teamOwner + "\n");
		for (int k = 0; k < noOfElements; k++) {
			System.out.println(footballPlayer[k].toString() + "\n");
		}
		
		return "";
	}
	

}
