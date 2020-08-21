/**
Car class has a default and parameterized constructors, accessor and mutator methods and a toString() that returns car information. 

@author Priya Pilla
@version 1.0

COP2253	Project #: 2
File Name: Car.java
*/

public class Car {
	
/**
Instance field comment
*/
	private String carRenter;
	private int carNum = 0;
	private String carName;
	private String carType;
	private double rate;
	private int days;

//Default constructor that sets all instance variables to default values.
	Car(){
		carRenter = " ";
		carNum = 0;
		carName = " ";
		carType = " ";
		rate = 0.0;
		days = 0;
	}
	
//A parameterized constructor 
	Car(String renterName, int carNumber, String modelName, String type, double rentalRate, int daysRented  ){
		carRenter = renterName;
		carNum = carNumber;
		carName = modelName;
		carType = type;
		rate = rentalRate;
		days = daysRented;
	}
	
/**
Mutator for carRenter variable 
@param String renter
@return void
 */
	
	void setCarRenter(String renter) {
		carRenter = renter;
	}
	
/**
Mutator for carNum variable 
@param int number
@return void
*/
	
	void setCarNum(int number) {
		carNum = number;
	}
	
/**
Mutator for carName variable 
@param String model
@return void
*/
	
	void setCarName(String model) {
		carName = model;
	}
	
/**
Mutator for carType variable 
@param String typeOfCar
@return void
*/
	
	void setCarType(String typeOfCar) {
		carType = typeOfCar;
	}
	
/**
Mutator for rate variable 
@param double carRate
@return void
*/
	
	void setCarRate(double carRate) {
		rate = carRate;
	}
	
/**
Mutator for days variable 
@param int numOfDays
@return void
*/
	
	void setNumOfDays(int numOfDays) {
		days = numOfDays;
	}
	
/**
Accessor for carRenter variable 
@return String carRenter
*/
	
	String getCarRenter() {
		return carRenter;
	}
	
/**
Accessor for carNum variable 
@return int carNum
*/
	
	public int getCarNum() {
		return carNum;
	}
	
/**
Accessor for carName variable 
@return String carName
*/
	
	String getCarName() {
		return carName;
	}
	
/**
Accessor for carType variable 
@return String carType
*/
	
	String getCarType() {
		return carType;
	}
	
/**
Accessor for rate variable 
@return double rate
*/
	
	public double getCarRate() {
		return rate;
	}
	
/**
Accessor for days variable 
@return int days
*/
	
	public int getNumOfDays() {
		return days;
	}

/**
Prints out info about car 
@return String carRenter, carNum, carName, carType, rate, days
*/
	
	public String toString() {
		System.out.println("Car renter's name: " + carRenter);
		System.out.println("Car number: " + carNum);
		System.out.println("Car name: " + carName);
		System.out.println("Car type: " + carType);
		System.out.println("Rate: $" + rate);
		System.out.println("Rented for: " + days + " days");
		System.out.println();
		
		return "";
	}


}
