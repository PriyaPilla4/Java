package ppillaproj4;

public class Stock extends Asset{
	
	double fiveYear;
	double oneYear;
	double ninetyDay;

	public Stock(String theSymbol, String theAssetName, double d, double e, double f) {
		super(theSymbol, theAssetName);
		fiveYear = d;
		oneYear = e;
		ninetyDay = f;
	}

	@Override
	public double getExpectedReturn() {
		return (0.6 * fiveYear + 0.2 * oneYear + 0.2 * ninetyDay);
	}
	
	public String toString() {
		super.toString();
		getExpectedReturn();
		
		return "";
	}

}
