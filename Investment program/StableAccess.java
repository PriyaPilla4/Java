package ppillaproj4;

public class StableAccess extends Asset{
	
	double expectedAnnualReturn;
	
	StableAccess(String theSymbol, String theAssetName) {
		super(theSymbol, theAssetName);
	}
	
	StableAccess(String theSymbol, String theAssetName, double theExpAnnualReturn) {
		super(theSymbol, theAssetName);
		expectedAnnualReturn = theExpAnnualReturn;
	}

	@Override
	public double getExpectedReturn() {
		return expectedAnnualReturn;
	}
	
	public void setExpAnnReturn(double setExpAnnReturn) {
		expectedAnnualReturn = setExpAnnReturn;
	}
	
	public String toString() {
		super.toString();
		getExpectedReturn();
		
		return "";
	}
}
