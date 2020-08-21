/**
FootBallPlayer class hold information related to all players. Compare to method that compares player's rating and toString that prints out info

@author Priya Pilla
@version 1.0

COP2253	Project #: 3
File Name: FootBallPlayer.java
*/
package ppillaproj3;

abstract public class FootBallPlayer {

	 private String name; //player name
	 private String position; //quarter back, running back or defensive back
	 private String team; //NFL team
	 int games; // no. of games played this season
	
	FootBallPlayer(){
		name = "";
		position = "";
		team = "";
		games = 0;
	}
	
	FootBallPlayer(String theName, String thePosition, String theTeam, int theGames){
		name = theName;
		position = thePosition;
		team = theTeam;
		games = theGames;
	}
	
/**
Mutator for player name 
@param String nameOfPlayer
@return void
 */
	
	public void setName(String nameOfPlayer) {
		name = nameOfPlayer;
	}
	
/**
 Mutator for player position 
@param String positionOfPlayer
@return void
 */
	
	public void setPosition(String positionOfPlayer) {
		position = positionOfPlayer;
	}
	
/**
 Mutator for player team 
@param String teamOfPlayer
@return void
 */
	
	public void setTeam(String teamOfPlayer) {
		team = teamOfPlayer;
	}

/**
 Mutator for player games 
@param int gamesOfPlayer
@return void
 */
	
	public void setGames(int gamesOfPlayer) {
		games = gamesOfPlayer;
	}
	
/**
Accessor for player name
@return String name
 */
	
	public String getName() {
		return name;
	}
	
/**
Accessor for player position
@return String position
 */
	
	public String getPosition() {
		return position;
	}
	
/**
Accessor for player team
@return String team
*/
	
	public String getTeam() {
		return team;
	}
	
/**
Accessor for player games
@return int games
 */
	
	public int getGames() {
		return games;
	}

/**
Abstract method for player rating
@return double
 */
	
	abstract public double playerRating();
	
/**
Method to compare player's rating. Returns a positive int if the calling player’s rating is higher than the parameter’s player’s rating. Returns a negative int if the calling player’s rating is lower than the parameter’s player’s rating. Return zero if their ratings are equal.  
@param FootBallPlayer callingPlayer
@return int -1 , 1, 0
 */
	
	public int compareTo(FootBallPlayer callingPlayer) {
		System.out.println("Comparing " + name + " with " + callingPlayer.getName());
		
		if (playerRating() > callingPlayer.playerRating()) {
			System.out.println(name + " had a higher rating");
			return -1;
		}
		if (callingPlayer.playerRating() > playerRating()) {
			System.out.println(callingPlayer.getName() + " has a higher rating");
			return 1;
		}
		if(callingPlayer.playerRating() == playerRating()) {
			System.out.println(name + " and "+ callingPlayer.getName() + " have equal rating");
			return 0;
		}
		return 0;
		
	}
	
/**
method to print player info
@return String
 */
	
	public String toString() {
		System.out.println("Name: " + getName() + ", " + "Position: " + getPosition() + ", " + "NFL Team: " + getTeam());
		return "";
	}

}
