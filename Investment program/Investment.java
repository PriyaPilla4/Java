package ppillaproj4;

public class Investment {

		Asset asset;
		double amountInvested;
		double tenYearValue;
		
		Investment(double theAmtInvested, double _10YrVal, String _Symbol, String _AssetName){
			amountInvested = theAmtInvested;
			tenYearValue = _10YrVal;
			asset = new StableAccess(_Symbol, _AssetName);
		}
		
		public void setAmountInvested(double setAmtInvested) {
			amountInvested = setAmtInvested;
		}
		
		public void setTenYearValue(double setTenYearValue) {
			tenYearValue = setTenYearValue;
		}
		
		public double getAmountInvested() {
			return amountInvested;
		}
		
		public double getTenYearValue() {
			return tenYearValue;
		}
	

}
