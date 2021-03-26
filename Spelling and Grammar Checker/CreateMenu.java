import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.*;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import java.nio.file.*;

public class CreateMenu {

	private FileChooser fileChooser; 
    private File fileOpened;
    private Spelling spelling;
    
    CreateMenu(Spelling spelling){
    	fileChooser = new FileChooser();
    	fileOpened = null;
    	setSpelling(spelling);
    }
    
    //setters and getters for instance variables
    public void setFileChooser(FileChooser fileChooser) {
    	this.fileChooser = fileChooser;
    }
    
    public void setFileOpened(File fileOpened) {
    	this.fileOpened = fileOpened;
    }
    
    public FileChooser getFileChooser() {
    	return fileChooser;
    }
    
    public File getFileOpened() {
    	return fileOpened;
    }
    
    public void setSpelling(Spelling spelling) {
    	this.spelling = spelling;
    }
    
    public Spelling getSpelling() {
    	return spelling;
    }
    
    //make the menu
	public void makeMenu(Stage primaryStage) throws Exception{
			
	    	TextArea textArea = new TextArea();
	    	 
	   	   // Create MenuBar
	       MenuBar menuBar = new MenuBar();

	       // Create menus
	       Menu fileMenu = new Menu("File");
	       Menu editMenu = new Menu("Edit");
	       
	       // Create MenuItems   
	       MenuItem openFileItem = OpenFile(primaryStage, textArea); 
	       MenuItem saveItem = Save(primaryStage, textArea);
	       MenuItem exitItem = Exit();   
	       MenuItem spellCheckItem = SpellCheck(textArea);
	 
	       // Add menuItems to the Menus
	       fileMenu.getItems().addAll(openFileItem, saveItem, exitItem);
	       editMenu.getItems().addAll(spellCheckItem);

	       // Add Menus to the MenuBar
	       menuBar.getMenus().addAll(fileMenu, editMenu);

	       BorderPane root = new BorderPane();
	       root.setTop(menuBar);
	       root.setCenter(textArea);
	   	
	       Scene scene = new Scene(root, 300, 250);

	       primaryStage.setTitle("Speller!");
	       primaryStage.setScene(scene);
	       primaryStage.show();
	 }
	 
	//add functionality to OpenFile button
	 private MenuItem OpenFile (Stage stage, TextArea textArea) throws IOException{
    	 
	        MenuItem openFileItem = new MenuItem("Open");
	        textArea.setWrapText(true);

	        openFileItem.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	//set initial directory to directory of the project
	            	getFileChooser().setInitialDirectory(new File(System.getProperty("user.dir")));
	            	
	            	//get selected file
	            	File selectedFile = getFileChooser().showOpenDialog(stage);
	            	setFileOpened(selectedFile); //assign to global variable to use later
	                
	                BufferedReader br = null;
	                try {
						br = new BufferedReader(new FileReader(selectedFile));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
	  
	                String line;
	                
	                //put text in textarea
	                textArea.clear();
	       	 		try { 
						while ((line = br.readLine()) != null) { 
							textArea.appendText(line+"\n");
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					} 
	            }
	        });       
	        return openFileItem;
	 }
	 
	//add functionality to save button
	 private MenuItem Save(Stage stage, TextArea textArea){
         MenuItem saveItem = new MenuItem("Save");
         
         saveItem.setOnAction(new EventHandler<ActionEvent>() {
  
             @Override
             public void handle(ActionEvent event) {
      
                 if(getFileOpened() == null) { //no file opened so ask user to save file as
                	//get the file selected 
                     File file = getFileChooser().showSaveDialog(stage); 
                     String filePath = file.getAbsolutePath();

                     //write text in text area to new file
                     try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                         writer.write(textArea.getText());
                     } catch (IOException e) {
    					e.printStackTrace();
                     }
                     
                     Alert alert = new Alert(AlertType.NONE,"File Created and Saved!", ButtonType.OK);
                     alert.showAndWait(); 
                     
                 }else{ //file opened so saves it to that file
                	 String filePath = getFileOpened().getAbsolutePath();

                     //write text in text area to opened file
                     try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                         writer.write(textArea.getText());
                     } catch (IOException e) {
    					e.printStackTrace();
                     }
                     
                     Alert alert = new Alert(AlertType.NONE,"File Saved!", ButtonType.OK);
                     alert.showAndWait(); 	
                 }    
             }
         });     
         return saveItem;
      }
	 
	 //add functionality to exit button
	 private MenuItem Exit(){
		 
	     MenuItem exitItem = new MenuItem("Exit");
	     // Set Accelerator for Exit MenuItem.
	     exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
	     // When user click on the Exit item
	     exitItem.setOnAction(new EventHandler<ActionEvent>() {
	 
	         @Override
	         public void handle(ActionEvent event) {
	             System.exit(0);
	         }
	     });
	     return exitItem;
	 }
	 
	 //add functionality to SpellCheck button
	 private MenuItem SpellCheck(TextArea textArea) {
    	 
    	 MenuItem spellCheckItem = new MenuItem("Spell Check");
         
         spellCheckItem.setOnAction(new EventHandler<ActionEvent>() {
  
             @Override
             public void handle(ActionEvent event) {
            	 
            	 //get suggestions
            	 
            	 spelling.generateSuggestions(textArea); 
                 
            	//put suggestions in dialog boxes and display
                 if(!(spelling.getSuggestions().isEmpty())) {  
                	 for(int i = 0; i < spelling.getSuggestions().size(); i++) {
                    	 Alert alert = new Alert(AlertType.NONE,"default", ButtonType.OK);
                         alert.setTitle("Misspelled Words");
                         alert.setHeaderText(spelling.getIncorrectWords().get(i));
                         
                         if((spelling.getSuggestions().get(i)).isBlank()) {
                        	 alert.setContentText("Suggestions:" + "\n" + "\n" + "no suggestions");
                         }else {                    
                        	 alert.setContentText("Suggestions:" + "\n" + "\n" + spelling.getSuggestions().get(i));
                         }
                         
                         alert.showAndWait(); 
                     }
                 }else {
                	 Alert alert = new Alert(AlertType.NONE,"default", ButtonType.OK);
                     alert.setTitle("Misspelled Words");
                     alert.setHeaderText("Spell Check Complete");
                     alert.setContentText("No Misspelled Words!");
                     alert.showAndWait(); 	 
                 }                  	
             }
         });     
         return spellCheckItem; 
     }
}
