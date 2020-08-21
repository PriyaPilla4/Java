/**
CarbonFootprint class is the interface for Building, Auto and Food classes. It has the abstract methods getCarbonFootprint and getName

@author Priya Pilla
@version 1.0

COP2253	Project #: 5
File Name: CarbonFootprint.java
*/

package ppillaproj5;

public interface CarbonFootprint {
	
	/**
	Abstract method for getCarbonFootprint
	@return double
	 */
	
	abstract public double getCarbonFootprint();
	
	/**
	Abstract method for getName
	@return String
	 */
	
	abstract public String getName();

}
