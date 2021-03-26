/*
 * Created by Priya Pilla
 */

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
	

	public static void main(String [] args) {

		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		MakeRace makeRace = new MakeRace();
		makeRace.createRace(stage);

	}
	
}
