/*
    Name: Priya Pilla
    COP4027
    Project 4
    Class: GameServer
        This is class is responsible for creating the server and receiving clients
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
   A server that executes the TicTacToe Game.
*/
public class GameServer
{
   public static void main(String[] args) throws IOException
   {
	  
	  GameService service = null; //GameService 
      Clients clients = new Clients(); //Clients
      
      int playerNo = -1;
      int noOfClients = 0;
      
      final int PORT = 8888;
      ServerSocket server = new ServerSocket(PORT); //start server
      System.out.println("Waiting for player to connect...");
      
      
      while (true)
      {
          if(noOfClients <= 2) {
              
              noOfClients++;
              Socket s = server.accept(); //accept players
 
              System.out.println("Player connected.");
              playerNo++;
              
              service = new GameService(clients, playerNo, server, s); 

              clients.addClient(service); //add player to clients

              Thread t = new Thread(service);
              t.start(); //start thread
             
              //server closes after 2 players have joined
              if(noOfClients == 2){        
                  server.close();
                  break;
              }
             
          }else {
              System.out.println("Can not accept more than 2 players");
              break;
          }
         
      }

   }
}







