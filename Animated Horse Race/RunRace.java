/*
 * Created by Priya Pilla
 */

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.application.*;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.lang.System;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class RunRace {
	
	//horse counters for incrementing horse steps
	private int horse1COUNTER;
	private int horse2COUNTER;
	private int horse3COUNTER;
	private int horse4COUNTER;
	private int horse5COUNTER;
	
	//boolean for when reset is clicked while its running
	private boolean resetWhileRun;
	
	//grid instance variables
	private GridPane gridPane;
	private Group horse1Group;
	private Group horse2Group;
	private Group horse3Group;
	private Group horse4Group;
	private Group horse5Group;
	private Stage stage;
	
	//lock
	private ReentrantLock horseLock; 
	
	RunRace(GridPane gridPane, Group horse1Group, Group horse2Group, Group horse3Group, Group horse4Group, Group horse5Group, Stage stage){
		this.gridPane = gridPane;
		this.horse1Group = horse1Group;
		this.horse2Group = horse2Group;
		this.horse3Group = horse3Group;
		this.horse4Group = horse4Group;
		this.horse5Group = horse5Group;
		this.stage = stage;
		horseLock = new ReentrantLock();
        horse1COUNTER = 0;
        horse2COUNTER = 0;
        horse3COUNTER = 0;
        horse4COUNTER = 0;
        horse5COUNTER = 0;
        resetWhileRun = false;
	}

	public Button run() {
		 Button runButton = new Button("Run");
		  
		 runButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
	         public void handle(ActionEvent event) {
				 
				//when run button is clicked, reset button is not clicked 
				resetWhileRun = false;
				
				//disable run button when running
				runButton.setDisable(true);
				
				//start timer
				long startTime = System.currentTimeMillis();
				
				//start threads 
				Thread t1 = new Thread();
			    Thread t2 = new Thread();
			    Thread t3 = new Thread();
			    Thread t4 = new Thread();
			    Thread t5 = new Thread();
			    t1.start();
			    t2.start();
			    t3.start();
			    t4.start();
			    t5.start();
			      
			    new Thread(()->{ 
			    	
			    	//while race hasn't ended or reset isn't pressed
			    	while(horse1COUNTER < 50 && horse2COUNTER < 50 && horse3COUNTER < 50 && horse4COUNTER < 50 && horse5COUNTER < 50 && resetWhileRun == false) {
	
			    		//generate random steps for horses
			    		Random rand = new Random(); 
			    		
			    		int rand1 = rand.nextInt(5);
				    	int rand2 = rand.nextInt(5);
				    	int rand3 = rand.nextInt(5);
				    	int rand4 = rand.nextInt(5);
				    	int rand5 = rand.nextInt(5);
				    	
				    	//lock
				    	horseLock.lock();
				    	
				    	//add random steps to horse counters
				    	horse1COUNTER+=rand1;
				    	horse2COUNTER+=rand2;
				    	horse3COUNTER+=rand3;
				    	horse4COUNTER+=rand4;
				    	horse5COUNTER+=rand5;
				    	
				    	//unlock
				    	horseLock.unlock();
				    	
			    		Platform.runLater(() -> {
					 
			    			//if a horse crosses the finish line
			    			if(horse1COUNTER > 49 || horse2COUNTER > 49 || horse3COUNTER > 49 || horse4COUNTER > 49 || horse5COUNTER > 49) {
			    				
				    			//show positions of the horses
				    			removeAndAddHorse();
				    			
				    			//end time
				    			long finishTime = System.currentTimeMillis();
				    			
				    			//find winner, compute time and display
				    			findWinner(startTime, finishTime);
				    			
				    		}else {
			
				    			//show positions of the horses
				    			removeAndAddHorse();
				    			
				    		}
			    		});
					
			    		try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} 
					 
			    	}
			    }).start(); //start thread
	
			 }

		 });

		 return runButton;	 
	}
	
	private void removeAndAddHorse() {
		gridPane.getChildren().remove(horse1Group);
		gridPane.add(horse1Group, horse1COUNTER, 0);
		
		gridPane.getChildren().remove(horse2Group);
		gridPane.add(horse2Group, horse2COUNTER, 1);
		
		gridPane.getChildren().remove(horse3Group);
		gridPane.add(horse3Group, horse3COUNTER, 2);
		
		gridPane.getChildren().remove(horse4Group);
		gridPane.add(horse4Group, horse4COUNTER, 3);
		
		gridPane.getChildren().remove(horse5Group);
		gridPane.add(horse5Group, horse5COUNTER, 4);
	
		Parent root = gridPane;
		Scene scene = stage.getScene();

		try {
			scene.setRoot(root);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	private void findWinner(long startTime, long finishTime) {
		
		//compute time race was finished
		double timeElapsed = TimeUnit.MILLISECONDS.toSeconds(finishTime-startTime);
		
		int findWinner[] = {horse1COUNTER, horse2COUNTER, horse3COUNTER, horse4COUNTER, horse5COUNTER};
		
		int max = findWinner[0]; 
		String winner = "Horse 1";
		
        //find winner based on max distance
         for (int i = 0; i < findWinner.length; i++) { 
             if (findWinner[i] > max) {
                 max = findWinner[i];
                 winner = "Horse " + (i+1);
             } 
         }
         
		//throw interupted exception
		try {
			throw new InterruptedException();
		}catch (InterruptedException e){

			//dialog box declaring winner
			Alert alert = new Alert(AlertType.NONE, winner + " is the winner!" + "\n" + 
			"Time: " + timeElapsed + " seconds", ButtonType.OK);
            alert.showAndWait(); 
		}
	}
	
	
	public Button reset(Button runButton) {
		Button resetButton = new Button("Reset");
		
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
	         public void handle(ActionEvent event) {

				 	resetWhileRun = true;
				
				 	//throw interupted exception
	    			try {
	    				throw new InterruptedException();
	    			}catch (InterruptedException e){
	    				
	    			}

				 	runButton.setDisable(false);
				 	
				 	//move horses to the beginning
				 	horse1COUNTER = 0;
				 	horse2COUNTER = 0;
				 	horse3COUNTER = 0;
				 	horse4COUNTER = 0;
				 	horse5COUNTER = 0;
				 	
				 	//show positions of the horses
				 	removeAndAddHorse();

			 }
		 });
		 
		return resetButton;
	}
	
	public Button quit() {
		Button quitButton = new Button("Quit");
		
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
	         public void handle(ActionEvent event) {
				 System.exit(0);
			 }
		 });
		
		return quitButton;
	}
}
