/**
DefensiveBack class holds information and stores statistics specific to Defensive Backs

@author Priya Pilla
@version 1.0

COP2253	Project #: 3
File Name: DefensiveBack.java
*/

package ppillaproj3;

public class DefensiveBack extends FootBallPlayer{

	private int tackles;
	private int interceptions;
	private int forcedFumbles;
	
	DefensiveBack(){
		tackles = 0;
		interceptions = 0;
		forcedFumbles = 0;
	}
	
	DefensiveBack(int theTackles, int theInterceptions, int theForcedFumbles, String superName, String superPosition, String superTeam, int superGames){
		super(superName, superPosition, superTeam, superGames);
		tackles = theTackles;
		interceptions = theInterceptions;
		forcedFumbles = theForcedFumbles;
	}
	
/**
Mutator for tackles 
@param int noOfTackles
@return void
*/
	
	public void setTackles(int noOfTackles) {
		tackles = noOfTackles;
	}
	
/**
Mutator for Interceptions 
@param int noOfInterceptions
@return void
*/
	
	public void setInterceptions(int noOfInterceptions) {
		interceptions = noOfInterceptions;
	}
	
/**
Mutator for forced fumbles
@param int noOfForcedFumbles
@return void
*/
	
	public void setForcedFumbles(int noOfForcedFumbles) {
		forcedFumbles = noOfForcedFumbles;
	}
	
/**
Accessor for tackles
@return int tackles
*/
	
	public int getTackles() {
		return tackles;
	}
	
/**
Accessor for interceptions 
@return int interceptions
*/
	
	public int getInterceptions() {
		return interceptions;
	}
	
/**
Accessor for forced fumbles 
@return int forcedFumbles
*/
	
	public int getForcedFumbles() {
		return forcedFumbles;
	}
	
/**
method for average tackles per game 
@return double tackles / game
*/
	
	public double averageTacklesPerGame() {
		return (tackles / games);
	}
	
/**
method for average interceptions per game
@return double interceptions / games
*/
	
	public double averageInterceptionsPerGame() {
		return (interceptions / games);
	}
	
/**
method for average forced fumbles per game 
@return double forcedFumbles / games
*/
	
	public double averageForcedFumblesPerGame() {
		return (forcedFumbles / games);
	}
	
/**
method for player rating
@return double  (averageTacklesPerGame() + averageInterceptionsPerGame() +  (averageForcedFumblesPerGame()/5)) * 10
*/

	@Override
	public double playerRating() {
		return ((averageTacklesPerGame() + averageInterceptionsPerGame() +  (averageForcedFumblesPerGame()/5)) * 10);
	}
	
/**
method for printing player info
@return String
*/
	
	public String toString() {
		super.toString();
		System.out.println("Tackles Per Game: " + averageTacklesPerGame() + ", " + "Interceptions Per Game: " + averageInterceptionsPerGame());
		System.out.println("Forced Fumbles Per Game: " + averageForcedFumblesPerGame() + ", " + "Player's Rating: " + playerRating());
		return "";
	}
	
}
