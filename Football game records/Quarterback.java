/**
Quarterback class hold information and stores statistics specific to Quarterbacks

@author Priya Pilla
@version 1.0

COP2253	Project #: 3
File Name: Quarterback.java
*/
package ppillaproj3;

public class Quarterback extends FootBallPlayer {

	private int passAttempts;
	private int passCompleted;
	private int touchdownsPassing;
	private int totalYardsPassing;
	
	Quarterback(){
		passAttempts = 0;
		passCompleted = 0;
		touchdownsPassing = 0;
		totalYardsPassing = 0;
	}
	
	Quarterback(int thePassAttempts, int thePassCompleted, int theTouchdownsPassing, int theTotalYardsPassing, String superName, String superPosition, String superTeam, int superGames ){
		super(superName, superPosition, superTeam, superGames);
		passAttempts = thePassAttempts;
		passCompleted = thePassCompleted;
		touchdownsPassing = theTouchdownsPassing;
		totalYardsPassing = theTotalYardsPassing;
	}

/**
Mutator for pass attempt 
@param int passAttempt
@return void
*/
	
	public void setPassAttempts(int passAttempt) {
		passAttempts = passAttempt;
	}
	
/**
Mutator for pass completed 
@param int passComplete
@return void
*/	
	
	public void setPassCompleted(int passComplete) {
		passCompleted = passComplete;
	}

/**
Mutator for touchdownsPassing 
@param int touchdowns
@return void
*/
	
	public void setTouchdownsPassing(int touchdowns) {
		touchdownsPassing = touchdowns;
	}

/**
Mutator for Total yards passing
@param int totalYards
@return void
*/

	public void setTotalYardsPassing(int totalYards) {
		totalYardsPassing = totalYards;
	}
	
/**
Accessor for pass attempts
@return int passAtempts
*/
	
	public int getPassAttempts() {
		return passAttempts;
	}
	
/**
Accessor for pass completed
@return int passCompleted
*/
	
	public int getPassCompleted() {
		return passCompleted;
	}
	
/**
Accessor for Touchdowns passing
@return int touchdownsPassing
*/
	
	public int getTouchdownsPassing() {
		return touchdownsPassing;
	}
	
/**
Accessor for Total yards passing
@return int totalYardsPassing
*/
	
	public int getTotalYardsPassing() {
		return totalYardsPassing;
	}
	
/**
method for completion percentage
@return double passCompleted / passAttempts
*/
	
	public double completionPercentage() {
		return (passCompleted / passAttempts);
	}
	
/**
method for average passing yards per game
@return double totalYardsPassing / games
*/
	
	public double averagePassingYardsPerGame() {
		return (totalYardsPassing / games);
	}
	
/**
method for average touch downs per game
@return double touchdownsPassing / games
*/
	
	public double averageTouchDownsPerGame() {
		return (touchdownsPassing / games);
	}
	
/**
method for player rating 
@return double averageTouchDownsPerGame() + (completionPercentage()*100) + (averagePassingYardsPerGame()/5)
*/
	
	@Override
	public double playerRating() {
		return (averageTouchDownsPerGame() + (completionPercentage()*100) + (averagePassingYardsPerGame()/5));
	}
	
/**
method for printing player info
@return string
*/
	
	public String toString() {
		super.toString();
		System.out.println("Completion Percentage: " + completionPercentage() + ", " + "Average Passing Yards Per Game: " + averagePassingYardsPerGame());
		System.out.println("Average Touch Downs Per Game: " + averageTouchDownsPerGame() + ", " + "Player's Rating: " + playerRating());
		
		return "";
	}

}
