/**
RunningBack class hold information and	stores statistics specific to Running Backs

@author Priya Pilla
@version 1.0

COP2253	Project #: 3
File Name: RunningBack.java
*/

package ppillaproj3;

public class RunningBack extends FootBallPlayer {

	private int runningAttempts;
	private int totalRunningYards;
	private int touchdowns;
	
	RunningBack(){
		runningAttempts = 0;
		totalRunningYards = 0;
		touchdowns = 0;
	}
	
	RunningBack(int theRunningAttempts, int theTotalRunningYards, int theTouchdowns, String superName, String superPosition, String superTeam, int superGames ){
		super(superName, superPosition, superTeam, superGames);
		runningAttempts = theRunningAttempts;
		totalRunningYards = theTotalRunningYards;
		touchdowns = theTouchdowns;
	}
	
/**
Mutator for running attempt 
@param int runAttempts
@return void
*/
	
	public void setRunningAttempts(int runAttempts) {
		runningAttempts = runAttempts;
	}
	
/**
Mutator for total running yards 
@param int totRunningYards
@return void
*/
	
	public void setTotalRunningYards(int totRunningYards) {
		totalRunningYards = totRunningYards;
	}
	
/**
Mutator for  touch downs
@param int touchDowns
@return void
*/
	
	public void setTouchdowns(int touchDowns) {
		touchdowns = touchDowns;
	}
	
/**
Accessor for running attempt 
@return int runningAttempts
*/
	
	public int getRunningAttempts() {
		return runningAttempts;
	}
	
/**
Accessor for total running yards 
@return int totalRunningYards
*/
	
	public int getTotalRunningYards() {
		return totalRunningYards;
	}
	
/**
Accessor for touch downs 
@return int touchdowns
*/
	
	public int getTouchdowns() {
		return touchdowns;
	}
	
/**
Method for average yards per game 
@return double totalRunningYards / games
*/
	
	public double averageYardsPerGame() {
		return (totalRunningYards / games);
	}
	
/**
Method for average yards per attempt 
@return double totalRunningYards / runningAttempts
*/
	
	public double averageYardsPerAttempt() {
		return (totalRunningYards / runningAttempts);
	}
	
/**
Method for average touchdowns per game 
@return double touchdowns / games
*/
	
	public double averageTouchDownsPerGame() {
		return (touchdowns / games);
	}
	
/**
Method for player rating 
@return double averageTouchDownsPerGame() + averageYardsPerAttempt()+ (averageYardsPerGame()/5)
*/

	@Override
	public double playerRating() {
	return(averageTouchDownsPerGame() + averageYardsPerAttempt()+ (averageYardsPerGame()/5));	
	}
	
/**
Method for printing player info
@return string
*/
	
	public String toString() {
		super.toString();
		System.out.println("Running Yards Per Game: " + averageYardsPerGame() + ", " + "Running Yards Per Attempts: " + averageYardsPerAttempt());
		System.out.println("Average Touch Downs Per Game: " + averageTouchDownsPerGame() + ", " + "Player's Rating: " + playerRating());
		return "";
	}
}
