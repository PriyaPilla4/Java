/*
    Name: Priya Pilla
    COP4027
    Project 4
    Class: GameClient
        This is class is responsible for the client side of the game
 */

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
   This program tests the Game server.
*/
public class GameClient extends Application
{
    private boolean openGUIcheck = false; //check if tictactoe GUI is started or not
    private boolean afterFirstMove = false;//check if players have made their first move
    private int secondPlayerFirstTime = -1;//check if 2nd player made 1st move
    private boolean gameOver = false; //check if game is over
    private TicTacToe tictactoe = new TicTacToe();  //GUI
    private int player = 0;
    private String response = "";
    private Scanner in;
    private PrintWriter out;
    private int receivedMove = 0;
    private int receivedPlayer = 0;
    
    
   public static void main(String[] args) throws IOException
   {
      Application.launch(args);
   }
   
   @Override
   public void start(Stage stage) throws IOException, InvocationTargetException, ConnectException{
     
          //connect to server
          final int SBAP_PORT = 8888;
          Socket s  = new Socket("localhost", SBAP_PORT);
          InputStream instream = s.getInputStream();
          OutputStream outstream = s.getOutputStream();
          in = new Scanner(instream);
          out = new PrintWriter(outstream);
          
          //say hello to server
          String command = "Hello\n";
          System.out.println("Hello");
          out.print(command);
          out.flush();
          response = in.nextLine();
          //get player no.
          player = Integer.parseInt(String.valueOf(response.charAt(21)));
       
        //make move if thread is is player 1
          player1Move();
       
        //make move if thread is is player 2
          player2Move();
  
           //start client thread
            new Thread(() -> {
            
                //while game is not over
                while(gameOver == false) {
   
                    secondPlayerFirstTime++;

                    //get response
                    getResponse();
     
                    //sends moves to server after first time and tries to alternate threads
                    sendMove();
                 
                    //Update GUI
                    Platform.runLater(() -> {

                        //if GUI is not yet staarted
                        if(getOpenGUI() == false) {
                            
                            //update GUI
                            tictactoe.createTicTacToe(stage);
                            tictactoe.makeMove(receivedMove, receivedPlayer);
                            
                            //check for winner
                            checkForWinner();
                            
                            setOpenGUI(true);
         
                        }else { //if GUI is open
                            
                            //update gui
                            tictactoe.makeMove(receivedMove, receivedPlayer);
                            
                            //check for winner
                            checkForWinner();
         
                        }
                         
                    });
                    
                    //make thread sleep for  1 second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                    
                }
            
            }).start(); //client thread start
       
   }
   
   public void sendMove() {
       //sends moves to server after first time and tries to alternate threads
       if(response.equals("Hello you are player 1") && getAfterFirstMove() == true || getSecondPlayerFirstTime() == 2) {
           
           int move = tictactoe.generateRandomMove();
           tictactoe.addMove(move);
           System.out.println("move " + player + " " + tictactoe.getCoordinate(String.valueOf(move)));
           out.print(move+"\n");
           out.flush();
           setAfterFirstMove(true);

       }
   }
   
   public void getResponse() {
     //get response
       final String response1 = in.nextLine();
       receivedMove = Integer.parseInt(String.valueOf(response1.charAt(20)));
       receivedPlayer = Integer.parseInt(String.valueOf(response1.charAt(7)));
       //add move
       tictactoe.addMove(receivedMove);
   }
   
   //make move if thread is player 1
   public void player1Move() {
       if(response.equals("Hello you are player 1") && getAfterFirstMove() == false) {

           int move = tictactoe.generateRandomMove();
           tictactoe.addMove(move);
           System.out.println("move " + player + " " + tictactoe.getCoordinate(String.valueOf(move)));
           out.print(move+"\n");
           out.flush();
       }
   }
   
 //make move if thread is player 2
   public void player2Move() {
       if(response.equals("Hello you are player 2")){
           
           int move = tictactoe.generateRandomMove();
           tictactoe.addMove(move);
           System.out.println("move " + player + " " + tictactoe.getCoordinate(String.valueOf(move)));
           out.print(move+"\n");
           out.flush();
           setAfterFirstMove(true);
           
       }
   }
   
   public void checkForWinner() {
     //check for winner
       if (!tictactoe.checkWinner().equals("GameNotOver")) {
           
           gameOver = true;
           
           String winner = tictactoe.checkWinner();
           
           //alret box
           Alert alert = new Alert(AlertType.NONE, winner + " is the winner!" + "\n" , ButtonType.OK);
           alert.showAndWait();
           
       }
       
       if (tictactoe.checkWinner().equals("GameNotOver") && tictactoe.getMoves() == 8){
           gameOver = true;
           
           Alert alert = new Alert(AlertType.NONE, "It is a Draw!" + "\n" , ButtonType.OK);
           alert.showAndWait();
       }
   }
   
   public void setAfterFirstMove(boolean status) {
       afterFirstMove = status;
   }
   public boolean getAfterFirstMove() {
       return afterFirstMove;
   }
   
   public void setOpenGUI(boolean status) {
       openGUIcheck = status;
   }
   public boolean getOpenGUI() {
       return openGUIcheck;
   }
    
    
    public void setSecondPlayerFirstTime(int status) {
        secondPlayerFirstTime = status;
    }
    public int getSecondPlayerFirstTime() {
        return secondPlayerFirstTime;
    }
  
}
