/*
 * Created by Priya Pilla
 */

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.canvas.*; 
import javafx.scene.Group;
import javafx.scene.layout.*; 

public class MakeRace {

	public void createRace(Stage stage) {
		 //Creating a Grid Pane 
	      GridPane gridPane = new GridPane(); 
	      
	      //Setting size for the pane  
	      gridPane.setMinSize(300, 200); 
	      gridPane.setMaxSize(600, 400);
	     	       
	      //Setting the padding  
	      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	      
	      //Setting the vertical and horizontal gaps between the columns 
	      gridPane.setVgap(1); 
	      gridPane.setHgap(1); 
	      
	      //Create columns
	      createColumns(gridPane);
	
	      //Create horses
	      Group horse1Group = makeHorse(Color.BROWN); 
	      Group horse2Group = makeHorse(Color.BLACK); 
	      Group horse3Group = makeHorse(Color.DARKBLUE); 
	      Group horse4Group = makeHorse(Color.DARKGREEN);
	      Group horse5Group = makeHorse(Color.GREY);
	
          //RunRace class object
	      RunRace runRace = new RunRace(gridPane, horse1Group, horse2Group, horse3Group, horse4Group, horse5Group, stage);
        
	      //create 3 buttons
	      Button runButton = runRace.run(); 
	      Button resetButton = runRace.reset(runButton);
	      Button quitButton = runRace.quit();  
	      FlowPane buttons = new FlowPane(10.0, 10.0, runButton, resetButton, quitButton);
	        
	      //add horses objects and buttons to grid pane
	      gridPane.add(horse1Group, 0,0);
	      gridPane.add(horse2Group, 0,1);
	      gridPane.add(horse3Group, 0,2);
	      gridPane.add(horse4Group, 0,3);
	      gridPane.add(horse5Group, 0,4);
	      gridPane.add(buttons, 0, 5);
	              
	      //Creating a scene object 
	      Scene scene = new Scene(gridPane);  
	      
	      //Setting title to the Stage 
	      stage.setTitle("Horse Race"); 
	         
	      //Adding scene to the stage 
	      stage.setScene(scene); 
	         
	      //Displaying the contents of the stage 
	      stage.show(); 
	}
	
	//creating columns
	private void createColumns(GridPane gridPane) {
		
	    final int numCols = 60;
	    
	    for (int i = 0; i < numCols; i++) {
	        ColumnConstraints colConst = new ColumnConstraints();
	        colConst.setPercentWidth(5.0);
	        gridPane.getColumnConstraints().add(colConst);
	     }
		
	}
	
	private Group makeHorse(Color color) {
		 
      	Canvas horseCanvas = new Canvas(60.0, 50.0); 
      
        // graphics context 
        GraphicsContext horse =  horseCanvas.getGraphicsContext2D(); 
  	       
        // set fill for horse body 
        horse.setFill(color); 
        horse.fillRect(20, 20, 30, 18); 

        // set fill for horse legs 
        horse.setFill(color); //right leg
        horse.fillRect(40, 35, 10, 11.5);
        
        horse.setFill(color); //left leg
        horse.fillRect(20, 35, 10, 11.5);
        
        //set fill for horse head
        horse.setFill(color); 
        horse.fillRect(40, 10, 15, 11.5); 
        
        // create a Group 
        Group horseGroup = new Group(horseCanvas); 
        
        return horseGroup;
	}
}
