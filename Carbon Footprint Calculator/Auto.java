/**
Auto class holds information and calculates Carbon footprint specific to Vehicles

@author Priya Pilla
@version 1.0

COP2253	Project #: 5
File Name: Auto.java
*/

package ppillaproj5;

public class Auto implements CarbonFootprint{

	private double distanceDriven; // total miles driven per year
	private double MPG; // miles per gallon
	private double constFootprint; // construction footprint in lbs
	private double expLifeTimeMileage; // expected life time mileage in miles
	private String autoName; 
	
	Auto(String name, double distDriven, double theMPG, double constFP, double expLTM ){
		distanceDriven = distDriven;
		MPG = theMPG;
		constFootprint = constFP;
		expLifeTimeMileage = expLTM;
		autoName = name;
	}
	
	/**
	Mutator for Auto name 
	@param String setAutoName
	@return void
	*/
	
	public void setAutoName(String setAutoName) {
		autoName = setAutoName;
	}
	
	/**
	Accessor for Auto name 
	@return String
	*/
	
	@Override
	public String getName() {
		return autoName;	
	}
	
	/**
	Mutator for distance driven 
	@param double setDistanceDriven
	@return void
	*/
	
	public void setDistanceDriven(double setDistanceDriven) {
		distanceDriven = setDistanceDriven;
	}
	
	/**
	Mutator for MPG 
	@param double setMPG
	@return void
	*/
	
	public void setMPG (double setMPG) {
		MPG = setMPG;
	}
	
	/**
	Mutator for constFootprint
	@param double setConstFootprint
	@return void
	*/
	
	public void setConstFootprint(double setConstFootprint) {
		constFootprint = setConstFootprint;
	}
	
	/**
	Mutator for expLifeTimeMileage
	@param double setExpLifeTimeMileage
	@return void
	*/
	
	public void setExpLifeTimeMileage(double setExpLifeTimeMileage) {
		expLifeTimeMileage = setExpLifeTimeMileage;
	}
	
	/**
	Accessor for distanceDriven 
	@return double
	*/
	
	public double getDistanceDriven() {
		return distanceDriven;	
	}
	
	/**
	Accessor for MPG
	@return double
	*/
	
	public double getMPG() {
		return MPG;	
	}
	
	/**
	Accessor for constFootprint 
	@return double
	*/
	
	public double getConstFootprint() {
		return constFootprint;	
	}
	
	/**
	Accessor for expLifeTimeMileage
	@return double
	*/
	
	public double getExpLifeTimeMileage() {
		return expLifeTimeMileage;
	}
	
	/**
	Calculates direct emissions by dividing CO2 pounds by miles per gallon
	@return double
	*/
	
	public double directEM() {
		return (19.36/MPG);
	}
	
	/**
	Calculates indirect emissions by dividing CO2 pounds by miles per gallon
	@return double
	*/
	
	public double indirectEM() {
		return (3.42/MPG);
	}
	
	/**
	Calculates manufacturing emissions by dividing construction footprint by expected life time mileage
	@return double
	*/
	
	public double manufacturingEM() {
		return (getConstFootprint()/getExpLifeTimeMileage());
	}
	
	/**
	Calculates carbon foot print for vehicle by adding direct emissions, indirect emissions, and manufacturing emissions and times them by total distance traveled per year
	@return double
	*/
	
	@Override
	public double getCarbonFootprint() {
		return ((directEM()+indirectEM()+manufacturingEM())*distanceDriven);
	}

}
