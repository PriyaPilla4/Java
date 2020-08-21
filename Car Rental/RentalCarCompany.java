/**
RentalCarCompany class has a default and parameterized constructors, adds reservations, finds reservations, calculates avg days, avg rate and total income from the Car class and prints out all cars information.  

@author Priya Pilla
@version 1.0

COP2253	Project #: 2
File Name: RentalCarCompany.java
*/

public class RentalCarCompany {
	
/**
Instance field comment
*/
	
	static Car[] theCars = new Car[20]; //array of car objects
	static String name = ""; // name of rental company
	static int rentCnt = 0; //how many cars have been rented
	static int NUM_CARS = 20; // size of array (max cars)
	static int i = 0; //indicates how may cars have been rented 
	
	
//default constructor
	RentalCarCompany(){
		name = "";
		rentCnt = 0;
		NUM_CARS = 20;
	}
    
//Parameterized Constructor
	RentalCarCompany(String rentalCompany){
		name = rentalCompany;
		rentCnt = 0;
		NUM_CARS = 20;
	}

/**
Mutator for car company name 
@param String companyName
@return void
 */
	
	public static void setName(String companyName) {
		name = companyName;
	}
	
/**
Accessor for name variable that represents company name
@param String renter
@return void
 */
	
	static String getName() {
		return name;
	}
	
/**
Adds args to car class's parameterized constructor for adding a car reservation 
@param String renter, int number, String model, String type, double rate, int days
@return void
 */
	
	public static void addReservation(String renter, int number, String model, String type, double rate, int days){
		
			while(i < NUM_CARS) {
				
				theCars[i]= new Car(renter, number, model, type, rate, days);
				i++;
				rentCnt = rentCnt+1;
				break;
			}
			
	}
	
/**
//calculates the total sales of all the cars in the array
@return double sales
 */
	
	static double getTotalRentalSales() { 
		
		double sales = 0;
		
		for(int i = 0; i < rentCnt; i++) {	
			sales = sales + (theCars[i].getCarRate() * theCars[i].getNumOfDays());
		}
		
		return sales;
		
	}
	
/**
calculates avg no of days rented for all cars
@return double sum/rentCnt
 */
	
	static double getAvgDays() {
		
		double sum = 0;
		
		for(int i = 0; i < rentCnt; i++) {
			sum = sum + theCars[i].getNumOfDays();
		}
		
		return sum/rentCnt;
		
	}
	
/**
calculates avg rate of all cars
@return double Math.round((sum/rentCnt) * 100.0)/100.0
 */
	
	static double getAvgRate() {
		
		double sum = 0;
		
		for(int i = 0; i < rentCnt; i++) {
			sum = sum + theCars[i].getCarRate();
		}
		
		return Math.round((sum/rentCnt) * 100.0)/100.0;	// rounds to 2 decimal places
		
	}
	
/**
Find a reservation by the carNum variable and calls to the car's toString to print out info if the car is found. If carNum is not found, returns a message. 
@param int carNum
@return void
 */
	
	public static void findReservation(int carNUM) {
		
		for(int i = 0; i < rentCnt; i++) {
			
			if (carNUM == theCars[i].getCarNum()) {
				System.out.println("Found reservation for car number: " + theCars[i].getCarNum());
				System.out.println("Car information: ");
				System.out.print(theCars[i].toString());
				break;
				
			}else if (i == rentCnt-1 && carNUM != theCars[i].getCarNum()){
				System.out.println("Could not find reservation for this car number: " + carNUM);
				
			}
			
		}
	}
	
/**
Prints out company name, all car information, avg days, avg rate and total income
@return String
 */
	
	public static  String toString(int i) {
		System.out.println("Rental Car Company: " + getName());
		
		for(i = 0; i < rentCnt; i++) {
			System.out.print(theCars[i].toString());
		}
		
		System.out.println("Average days rented out is: " + getAvgDays());
		System.out.println("Average rate is: $" + getAvgRate());
		System.out.println("Total rental income is: $" + getTotalRentalSales());
		System.out.println();
		
		return "";
	}


}
