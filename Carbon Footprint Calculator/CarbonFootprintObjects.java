/**
CarbonFootprintObjects class stores a collection (Array) of CarbonFootprint objects and performs a toString operation

@author Priya Pilla
@version 1.0

COP2253	Project #: 5
File Name: CarbonFootprintObjects.java
*/

package ppillaproj5;

public class CarbonFootprintObjects {

	CarbonFootprint carbonFootprint[] = new CarbonFootprint[9]; 
	private int p = 0;
	
	/**
	Method takes a CarbonFootprint objects and add it to the Array.
	@param CarbonFootprint CFP
	@return void
	*/
	
	public void addObject(CarbonFootprint CFP) {
		
		while(p <= 9) {
			carbonFootprint[p] = CFP;
			p++;
			break;
		}
		
	}
	
	/**
	Method to print Objects in Array
	@return String
	*/
	
	public String toString() {
		
		for (int k = 0; k < 9; k++) {
			System.out.println(carbonFootprint[k].getName() + "'s carbon footprint is " + carbonFootprint[k].getCarbonFootprint() + " lbs per year");
		}
		
		return "";
	}
	
	
}
