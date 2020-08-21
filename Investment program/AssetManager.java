package ppillaproj4;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class AssetManager {

	public static void main(String[] args) throws IOException{
		
		FileInputStream fileByteStream = null; 
	    Scanner inFS = null;  
	     
	    String line1 = "";
	    String line2 = "";
	    String tickerName = "";
	    String stockName = "";
	    String _5YrReturn;
	    String _1YrReturn = "";
	    String _90DayReturn = "";
	    double _5Yr = 0.0;
	    double _1Yr = 0.0;
	    double _90Day = 0.0;
	    
	    System.out.println("Opening file assetData.csv");
	    fileByteStream = new FileInputStream("assetData.csv");
	     
	    inFS = new Scanner(fileByteStream);
	    
	    System.out.println("Reading file...");
	    
	    line1 = inFS.nextLine();
	    line2 = inFS.nextLine();
	    
	    for(int r = 0; r < 4; r++) {
	    	tickerName = inFS.next();
	    	stockName = inFS.next();
	    	_5YrReturn = inFS.next();
	    	_1YrReturn = inFS.next();
	    	_90DayReturn = inFS.next();
	    	
	    	
	    	
	    	_5Yr = Double.valueOf(_5YrReturn);
	    	
	    	_1Yr =  Double.parseDouble(_1YrReturn);
	    	 _90Day = Double.parseDouble(_90DayReturn);
	    	
	    	AvailableAsset.storeObjects(tickerName, stockName, _5Yr, _1Yr, _90Day);
	    	
	    	
	    	
	    	//AvailableAsset.storeObjects(tickerName, stockName, Double.parseDouble(_5YrReturn), Double.parseDouble(_1YrReturn), Double.parseDouble(_90DayReturn));
	    	// convert double to float 
	    	//AvailableAsset.storeObjects(tickerName, stockName, Double.parseDouble(_5YrReturn));
	    }
	    
	    

	}

}
