package ppillaproj3;

public class FantasyFootBallTester {

	public static void main(String[] args) {
		
		Quarterback quarterback1 = new Quarterback(30, 25, 2, 200, "Phill Sims", "Quarter Back", "Giants", 5);
		Quarterback quarterback2 = new Quarterback(25, 20, 1, 250, "Steve Young", "Quarter Back", "49ers", 6);

		RunningBack runningBack1 = new RunningBack(17, 95, 4, "Jim Brown", "Running Back", "Browns", 5);
		RunningBack runningBack2 = new RunningBack(15, 80, 3, "Saquon Barkley", "Running Back", "Giants", 3);
		
		DefensiveBack defensiveBack1 = new DefensiveBack(6, 1, 0, "Spider Lockart", "Defensive Back", "Giants", 4);
		DefensiveBack defensiveBack2 = new DefensiveBack(5, 0, 1, "Aqib Talib", "Defensive Back", "Rams", 2);
		
		FantasyFootballTeam fantasyFootballTeam1 = new FantasyFootballTeam("UWF Argos", "Mr. Argos", 3);
		FantasyFootballTeam fantasyFootballTeam2 = new FantasyFootballTeam("Cantonment Stompers", "Ms. Cantonment", 3);
	
		fantasyFootballTeam1.addPlayer(quarterback1);
		fantasyFootballTeam1.addPlayer(runningBack1);
		fantasyFootballTeam1.addPlayer(defensiveBack1);
		
		fantasyFootballTeam2.addPlayer(quarterback2);
		fantasyFootballTeam2.addPlayer(runningBack2);
		fantasyFootballTeam2.addPlayer(defensiveBack2);
		
		fantasyFootballTeam1.toString();
		fantasyFootballTeam2.toString();
		
		
		System.out.println("Testing Finding Player by position");
		
		fantasyFootballTeam1.findPlayerByPosition("Quarter Back");
		
		System.out.println("Testing comparing two players rating");

		quarterback1.compareTo(quarterback2);
	}

}
