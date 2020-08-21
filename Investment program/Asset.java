package ppillaproj4;

public abstract class Asset {
	private String symbol;
	private String assetName;
	
	Asset(String theSymbol, String theAssetName){
		symbol = theSymbol;
		assetName = theAssetName;
	}
	
	public abstract double getExpectedReturn();
	
	public double investmentReturm(double amount, double noOfYears) {
		
		return (amount * Math.pow((1.0 + getExpectedReturn()),noOfYears));
		
	}
	
	public void setSymbol(String setSymbol) {
		symbol = setSymbol;
	}
	
	public void setAssestName(String setAssetName) {
		assetName = setAssetName;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getAssetName() {
		return assetName;
	}
	
	public String toString() {
		getSymbol();
		getAssetName();
		
		return "";
		
	}
}
