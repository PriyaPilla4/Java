/**
RentalCarTester class verifies operations of all constructors and public methods in RentalCarCompany class. it tests addReservation() and findReservation() methods.

@author Priya Pilla
@version 1.0

COP2253	Project #: 2
File Name: RentalCarTester.java
*/

public class RentalCarTester {
	
	public static void main(String[] args) {
		
		RentalCarCompany.setName("El Cheapo"); // sets car company name
		
		int carNumber[]= new int[50]; //array for parsing string car number args to int values
		int rentedDays[] = new int[50]; //array for parsing string rent args to int values
		double rateOfCar[] = new double[50]; //array for parsing string car rent args to double values
		int i = 1; //for while loops for parsing
		int j = 5;
		int k = 4;
		
		
//changes car number string args into int, so that it can pass through the car parameterized constructor 
		while(i< args.length)
	    {
			 carNumber[i] = Integer.parseInt(args[i]);
			
			i = i+6;
	    }
		
//changes rent string args into int, so that it can pass through the car parameterized constructor 
		while(j< args.length)
	    {
			rentedDays[j] = Integer.parseInt(args[j]);
			j = j+6;
	    }
		
//changes rate string args into double, so that it can pass through the car parameterized constructor 
		while(k< args.length)
	    {
			rateOfCar[k] = Double.parseDouble(args[k]);
			k = k+6;
	    }

//passes arg values into rental car company parameterized constructor 
		for(int h = 0; h < args.length; h++) {
			
			if(args.length == (h*6)) {
				break;
			}
		RentalCarCompany.addReservation(args[h*6], carNumber[(h*6)+1], args[(h*6)+2], args[(h*6)+3], rateOfCar[(h*6)+4], rentedDays[(h*6)+5]);
		
		}
		
		RentalCarCompany.toString(0); //prints out all car info
		RentalCarCompany.findReservation(222); //finds a car reservation by car num
		RentalCarCompany.findReservation(200); //does not find a car reservations with the given car num
		
	}

}
