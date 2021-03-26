/*
   Name: Priya Pilla
   COP4027
   Project 4
   Class: Clients
       This is class is responsible for keeping track of the clients
*/

import java.util.ArrayList;
import java.util.Vector;

public class Clients {

    private ArrayList<GameService> clientList; //holds list of clients
    private Vector<String> moves; //holds moves
    private boolean justJoined; //check for player 2 when it just joins
    
    Clients(){
        clientList = new ArrayList<GameService>();
        moves = new Vector<String>();
        justJoined = true;
        
    }
    
    //add move into moves vector
    public void addMove(String move) {
        moves.add(move);
    }
    
    //get moves
    public Vector<String> getMoves() {
        return moves;
    }
    
    //add client to clientList
    public void addClient(GameService client) {
        clientList.add(client);
    }
    
    //get no of clients
    public int getNoOfClients() {
        return clientList.size();
    }
    
    //send message to all clients in client list
    public void sendMessageToAll(String message, int playerNo) {
        
        //prints out message 
    	if(clientList.size() >= 1 ) {
    		clientList.get(0).printMessage(message, playerNo);
    	}
    	
    	//goes through client list and sends message to clients
        for(GameService client : clientList) {
            
            if(clientList.size() == 2 && client == clientList.get(1) && justJoined == true) {
                client.sendMessage(moves.get(1), playerNo);
                client.sendMessage(moves.get(1), playerNo);
                justJoined = false;
            }else {
                client.sendMessage(message, playerNo);
            }
            
        }
        
    }
    
    public void sendMessageToPlayer2() {
    	if(clientList.size() == 2) {
    		clientList.get(1).sendMessage(moves.get(0), 1);
    	}
    }
    
}
