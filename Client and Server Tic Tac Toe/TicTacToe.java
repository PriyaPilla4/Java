/*
    Name: Priya Pilla
    COP4027
    Project 4
    Class: TicTacToe
        This is class is responsible for creating and updating the GUI
 */

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

public class TicTacToe {

    private int randMove;
    private Random rand;
    private HashSet<Integer> randomMoves; //hashset of random moves
    private Vector<Rectangle> rects; //squares in tictactoe game
    private Vector<Color> colors; //colors of the squares - red and blue
    private HashSet<Integer> moveOnGUI;
    
    
    TicTacToe(){
        randMove = 0;
        rand = new Random();
        randomMoves = new HashSet<Integer>();
        moveOnGUI = new HashSet<Integer>();
        rects = new Vector<Rectangle>(9);
        colors = new Vector<Color>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
    }
    
    
    public void createTicTacToe(Stage stage) {
       
        //Creating a Grid Pane
          GridPane gridPane = new GridPane();
          
          //Setting size for the pane
          gridPane.setMinSize(300, 300);
          gridPane.setMaxSize(300, 300);
               
          //setting grid lines
          gridPane.setGridLinesVisible(true);
          
          //Setting the padding
          gridPane.setPadding(new Insets(10, 10, 10, 10));
          
          //Setting the vertical and horizontal gaps between the columns
          gridPane.setVgap(1);
          gridPane.setHgap(1);
          
          //Create columns
          createColumnsAndRows(gridPane);
         
          //create squares
          for(int i = 0; i < 9; i++) {
              rects.add(createRectangle());
          }
 
          //add elements: col, row
        gridPane.add(rects.get(0), 0, 0);
        gridPane.add(rects.get(1), 1, 0);
        gridPane.add(rects.get(2), 2, 0);
        
        gridPane.add(rects.get(3), 0, 1);
        gridPane.add(rects.get(4), 1, 1);
        gridPane.add(rects.get(5), 2, 1);
        
        gridPane.add(rects.get(6), 0, 2);
        gridPane.add(rects.get(7), 1, 2);
        gridPane.add(rects.get(8), 2, 2);
          //Creating a scene object
          Scene scene = new Scene(gridPane);
          
          //Setting title to the Stage
          stage.setTitle("TicTacToe");
             
          //Adding scene to the stage
          stage.setScene(scene);

          //Displaying the contents of the stage
          stage.show();
    }
    
        //creating columns and rows
        private void createColumnsAndRows(GridPane gridPane) {
            
           final int numCols = 3;
           final int numRows = 3;
            
            for (int i = 0; i < numCols; i++) {
                ColumnConstraints colConst = new ColumnConstraints();
                colConst.setPercentWidth(35.0);
                gridPane.getColumnConstraints().add(colConst);
             }
            for (int i = 0; i < numRows; i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setPercentHeight(35.0);
                gridPane.getRowConstraints().add(rowConst);
             }
            
        }
        
        //create squares of the tictactoe
        private Rectangle createRectangle(){
           
            Rectangle rect = new Rectangle(90, 90);
            rect.setFill(Color.WHITE);
            return rect;
        }
        
        //get a random move
       public int generateRandomMove() {
               
            randMove = rand.nextInt(10);  //    get a move

            while(randMove == 0 || randomMoves.contains(randMove)) {  // while it has already been generated
                randMove = rand.nextInt(10); // get another move
            }
            
            randomMoves.add(randMove); // new move here, add it to the vector
            return randMove;
        }
        
       //add move to randomMoves vector
        public void addMove(int move) {
            randomMoves.add(move);
        }
        
        //make a move by changing the color of the square
        public void makeMove(int move, int player) {
            
            if(player == 1) {
                 rects.get(move-1).setFill(Color.RED);
            }
            if(player == 2) {
                 rects.get(move-1).setFill(Color.BLUE);
            }
           
            moveOnGUI.add(move);
        }
        
        //checks if move is updated on GUI
        public boolean moveIsOnGUI(int move) {
            return moveOnGUI.contains(move);
        }
        
        //check for winner
        public String checkWinner() {
            
            int row1 = 0;
            int row2 = 1;
            int row3 = 2;
            
            int col1 = 0;
            int col2 = 3;
            int col3 = 6;
  
            for(Color color : colors) {
                //winner by row
                while(row1 <= 6) {
                    if(rects.get(row1).getFill() == color && rects.get(row2).getFill() == color && rects.get(row3).getFill() == color) {
                        return colorToString(color);
                    }
                 
                    row1 = row1+3;
                    row2 = row2+3;
                    row3 = row3+3;
                }
            
                //winner by column
                while(col1 <= 2) {
                        if(rects.get(col1).getFill() == color && rects.get(col2).getFill() == color && rects.get(col3).getFill() == color) {
                            return colorToString(color);
                        }
                
                        col1 = row1+1;
                        col2 = row2+1;
                        col3 = row3+1;
                }
            
                //winner by diagnol
                if(rects.get(0).getFill() == color && rects.get(4).getFill() == color && rects.get(8).getFill() == color) {
                    return colorToString(color);
                }
                if(rects.get(2).getFill() == color && rects.get(4).getFill() == color && rects.get(6).getFill() == color) {
                    return colorToString(color);
                }
            }
            
            return "GameNotOver";
        }
        
        //convert from color to string
        public String colorToString(Color color) {
            if(color == Color.RED) {
                return "Player 1 (RED) ";
            }else {
                return "PLayer 2 (BLUE) ";
            }
        }
        
        //get no of total moves made
        public int getMoves() {
            return randomMoves.size();
        }
        
        //covert from number of square to coordinate
        public String getCoordinate(String command) {
            
            if(command.equals("1")) {
                return "(0 , 0)";
            }else if(command.equals("2")) {
                return "(0 , 1)";
            }else if (command.equals("3")) {
                return "(0 , 2)";
            }else if (command.equals("4")) {
                return "(1 , 0)";
            }else if (command.equals("5")) {
                return "(1 , 1)";
            }else if (command.equals("6")) {
                return "(1 , 2)";
            }else if (command.equals("7")) {
                return "(2 , 0)";
            }else if (command.equals("8")) {
                return "(2 , 1)";
            }else if (command.equals("9")) {
                return "(2 , 2)";
            }else {
                return "";
            }
        }
}
