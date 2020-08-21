/**
Food class holds information and calculates Carbon footprint specific to Food

@author Priya Pilla
@version 1.0

COP2253	Project #: 5
File Name: Food.java
*/

package ppillaproj5;

public class Food implements CarbonFootprint{

	private String category; // category of food
	private double consumption; // consumption of the food kCal per day
	private double EF; //emissions factor
	private double avgCGEmissions; // avg Cradle to Gate emissions lbs CO2 / lbs
	private double avgEC; // avg energy content kCal per lb
	private double retailLossPtg; // retail loss percentage
	private double consumerLossPtg; // consumer loss percentage
	
	Food(String cat, double consump, double avgCGE, double aEC, double RLP, double CLP){
		category = cat;
		consumption = consump;
		avgCGEmissions = avgCGE;
		avgEC = aEC;
		retailLossPtg = RLP;
		consumerLossPtg = CLP;
	}
	
	/**
	Mutator for category
	@param String setCategory
	@return void
	*/
	
	public void setCategory(String setCategory) {
		category = setCategory;
	}
	
	/**
	Mutator for consumption
	@param double setConsumption
	@return void
	*/
	
	public void setConsumption(double setConsumption) {
		consumption = setConsumption;
	}
	
	/**
	Mutator for EF
	@param double setEF
	@return void
	*/
	
	public void setEF(double setEF) {
		EF = setEF;
	}
	
	/**
	Mutator for avgCGEmissions
	@param double setAvgCGEmissions
	@return void
	*/
	
	
	public void setAvgCGEmissions(double setAvgCGEmissions) {
		avgCGEmissions = setAvgCGEmissions;
	}
	
	/**
	Mutator for avgEC
	@param double setAvgEC
	@return void
	*/
	
	public void setAvgEC(double setAvgEC) {
		avgEC = setAvgEC;
	}
	
	/**
	Mutator for retailLossPtg
	@param double setRetailLossPtg
	@return void
	*/
	
	public void setRetailLossPtg(double setRetailLossPtg ) {
		retailLossPtg = setRetailLossPtg;
	}
	
	/**
	Mutator for consumerLossPtg
	@param double setConsumerLossPtg
	@return void
	*/
	
	public void setConsumerLossPtg(double setConsumerLossPtg) {
		consumerLossPtg = setConsumerLossPtg;
	}
	
	/**
	Accessor for category 
	@return String
	*/
	
	@Override
	public String getName() {
		return category;
	}
	
	/**
	Accessor for consumption
	@return double
	*/
	
	public double getConsumption() {
		return consumption;
	}
	
	/**
	Accessor for EF
	@return double
	*/
	
	public double getEF() {
		return EF;
	}
	
	/**
	Accessor for avgCGEmissions
	@return double
	*/
	
	public double getAvgCGEmissions() {
		return avgCGEmissions;
	}
	
	/**
	Accessor for avgEC
	@return double
	*/
	
	public double getAvgEC() {
		return avgEC;
	}
	
	/**
	Accessor for retailLossPtg
	@return double
	*/
	
	public double getRetailLossPtg() {
		return retailLossPtg;
	}
	
	/**
	Accessor for consumerLossPtg
	@return double
	*/
	
	public double getConsumerLossPtg() {
		return consumerLossPtg;
	}
	
	/**
	Calculates Emissions factor
	@return double
	*/
	
	public double emissionsFactor() {
		return ((getAvgCGEmissions() / getAvgEC())/ ((1 - getRetailLossPtg())+(1 - getConsumerLossPtg())));
	}
	
	/**
	Calculates carbon footprint for food
	@return double
	*/
	
	@Override
	public double getCarbonFootprint() {
		return (Math.round((consumption * 365 * emissionsFactor())*100.0)/100.0);
	}

}
