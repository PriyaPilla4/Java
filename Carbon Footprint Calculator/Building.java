/**
Building class holds information and calculates Carbon footprint specific to Buildings

@author Priya Pilla
@version 1.0

COP2253	Project #: 5
File Name: Building.java
*/

package ppillaproj5;

public class Building implements CarbonFootprint{

	private double amount; // $ amount paid for electricity 
	private String buildingName = "";
	
	Building(String name, double _amount){
		buildingName = name;
		amount = _amount;
	}
	
	/**
	Mutator for building name 
	@param String name
	@return void
	*/
	
	public void setBuildingName(String name) {
		buildingName = name;
	}
	
	/**
	Accessor for building name 
	@return String
	*/
	
	@Override
	public String getName() {
		return buildingName;
	}
	
	/**
	Mutator for perKWH 
	@param double setPerKWH
	@return void
	*/
	
	public void setAmount(double setAmount) {
		amount = setAmount;
	}
	
	/**
	Accessor for perKWH 
	@return double
	*/
	
	public double getAmount() {
		return amount;
	}
	
	/**
	Calculates carbon footprint by dividing total amount paid for electricity by 0.25 to get per KWH and times the by 0.7 Emissions factor
	@return double
	*/
	
	public double buildingCarbonFootprint(){
		return ((amount/0.25)*0.7);
	}
	
	/**
	Prints carbon footprint for building  
	@return double
	*/
	
	@Override
	public double getCarbonFootprint() {	
		return buildingCarbonFootprint();
	}

}
