package ppillaproj5;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;

public class CarbonFootprintTester {

	public static void main(String[] args)throws IOException {
		
		String type = "";
		String B1 = "";
		String B2 = "";
		String A1 = "";
		String A2 = "";
		String A3 = "";
		String A4 = "";
		String A5 = "";
		String F1 = "";
		String F2 = "";
		String F3 = "";
		String F4 = "";
		String F5 = "";
		String F6 = "";
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		Building[] building = new Building[3];
		Auto[] auto = new Auto[3];
		Food[] food = new Food[3];
		
		FileInputStream fileByteStream = null; 
	    Scanner inFS = null;  
	    
	    System.out.println("Opening file File.txt");
	    fileByteStream = new FileInputStream("File.txt");
	    
	    inFS = new Scanner(fileByteStream);
	    
	    System.out.println("Reading file...");
	    System.out.println("");
	    
	    while (inFS.hasNext()){
	    	
	    type = inFS.next();
	   
	    	if (type.equals("B")) {
	    		 
	    		B1 = inFS.next();
	    		B2 = inFS.next();
	    		
	    		while(i < 3) {
	    			building[i] = new Building (B1, Double.parseDouble(B2));
	    			i++;
	    			break;
	    		}
	    	
	    	}else if (type.equals("A")) {
	    		
	    		A1 = inFS.next();
	    		A2 = inFS.next();
	    		A3 = inFS.next();
	    		A4 = inFS.next();
	    		A5 = inFS.next();
	    		
	    		while(j < 3) {		
	    			auto[j] = new Auto (A1, Double.parseDouble(A2), Double.parseDouble(A3), Double.parseDouble(A4), Double.parseDouble(A5));
	    			j++;
	    			break;
	    		}
	    	
	    	}else if (type.equals("F")) {
	    		
	    		F1 = inFS.next();
	    		F2 = inFS.next();
	    		F3 = inFS.next();
	    		F4 = inFS.next();
	    		F5 = inFS.next();
	    		F6 = inFS.next();
	    		
	    		while(k < 3) {
	    			food[k] = new Food (F1, Double.parseDouble(F2), Double.parseDouble(F3), Double.parseDouble(F4), Double.parseDouble(F5), Double.parseDouble(F6));
	    			k++;
	    			break;
	    		}
	    	
	    	}
	    
	    }
	    
	    
	    CarbonFootprintObjects carbonFootprintObject = new CarbonFootprintObjects();
	    
	    carbonFootprintObject.addObject(building[0]);
	    carbonFootprintObject.addObject(building[1]);
	    carbonFootprintObject.addObject(building[2]);
	    
	    carbonFootprintObject.addObject(auto[0]);
	    carbonFootprintObject.addObject(auto[1]);
	    carbonFootprintObject.addObject(auto[2]);
	    
	    carbonFootprintObject.addObject(food[0]);
	    carbonFootprintObject.addObject(food[1]);
	    carbonFootprintObject.addObject(food[2]);
	    
	    carbonFootprintObject.toString();
	    
	    inFS.close();

	}

}
