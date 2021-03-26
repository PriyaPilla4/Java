/*
    Name: Priya Pilla
    COP4027
    Project 4
    Class: GameService
        This is class is responsible for sending messages from the server
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class GameService implements Runnable
{

    private int playerNo;
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Clients clients;
    
   public GameService(Clients clients, int playerNo, ServerSocket server, Socket aSocket)
   {
      s = aSocket;
      this.playerNo = playerNo;
      this.clients = clients;
   }

   //run method runs the threads
   public void run()
   {
      try
      {
    	  
         try
         {
            in = new Scanner(s.getInputStream());
            out = new PrintWriter(s.getOutputStream());

            doService();
 
         }
         finally
         {
            s.close();
         }
         
      }
      catch (IOException exception)
      {
         exception.printStackTrace();
      }
   }

   /**
      Executes all commands until the QUIT command or the
      end of input.
   */
   public void doService() throws IOException
   {
       String command = "";
       
      while (true){
    	  
          if (!in.hasNext()){
        	  return;
          }
          
          command = in.next();
          
          if (command.equals("QUIT")){
              s.close();
              return;
          }else{
            
           executeCommand(command);
          
          }
         
      }
   }
   
   //prints message 
   public void printMessage(String command, int playerNumber) {
	   String move = getMove(command);
	   System.out.println("Player " + playerNumber + " has chosen " + move);
   }
   
   //sends message to client
   public void sendMessage(String command, int playerNumber) {
	   out.println("Player " + playerNumber + " has chosen " + command);
       out.flush();
   }
   
   /**
      Executes a single command.
      @param command the command to execute
   */
   public void executeCommand(String command)throws IOException{
       
      if (command.equals("Hello"))
      {
          playerNo++;
          System.out.println("Hello you are player " + playerNo);
          out.println("Hello you are player " + playerNo);
          out.flush();
          
          if(playerNo == 2) {
        	  clients.sendMessageToPlayer2();
          }
          
      }else if (command.equals("QUIT")) {
          s.close();
      }else {
          clients.addMove(command);
          clients.sendMessageToAll(command, playerNo);  
      }  
   }
   
   //convert move to coordinate
   public String getMove(String command) {
	   
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
