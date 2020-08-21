package ppillaproj4;


public class AvailableAsset {

	static Asset[] assetArray = new Asset[100];
	static int j = 0;
	static int k = 0;
	
	public static void storeObjects(String theSymbol, String theAssetName, double d, double e, double f) {
		//Stock stock = new Stock(theSymbol, theAssetName, _5Year, _1Year, _90Day);
		System.out.println("------check------");
		while(j < 100) {
			assetArray[j] = new Stock(theSymbol, theAssetName, d, e, f);
			//assetArray[j]= stock;
			j++;
			k++;
			break;
		}
	}
	
	public static void storeObjects(String theSymbol, String theAssetName, double theExpAnnualReturn) {
		StableAccess stableAccess = new StableAccess(theSymbol, theAssetName, theExpAnnualReturn);
	
		while(k < 100) {
			assetArray[k] = stableAccess;
			k++;
			j++;
			break;
		}
	}
	
	public boolean exceptionHandling(String investment) {
	
		try {
			double investment1 = Double.parseDouble(investment);
				
					throw new ImproperAssetException(investment1);
				
			
		}catch(ImproperAssetException ex) {	
			System.out.println(ex.getMessage);
			//ex.MalformedEmail();
			return false;
		}
	
	}
	
	public String toString() {
		System.out.println("Available assets for investment");
		System.out.println("-------------------------------");
		for(int p = 0; p < 100; p++) {
			System.out.println(assetArray[p]);
		}
		
		return "";
		
	}

	
	
}
