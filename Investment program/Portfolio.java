package ppillaproj4;

public class Portfolio {

	Investment[] investment;
	int i = 0;
	
	Portfolio(){
		investment = new Investment[10];
	}
	
	public void addInvestment(Asset asset, double amtInvested, double futureVal, String Symbol_ , String asset_Name){
		Investment investmentObj = new Investment(amtInvested, futureVal, Symbol_, asset_Name);
		
		while(i < 10) {
			investment[i] = investmentObj;
			i++;
			break;
		}
		
	}
	
	public String toString() {
		System.out.println("+--------------+-----------------+--------------------+");
		System.out.println("| ASSET SYMBOL | AMOUNT INVESTED | VALUE IN TEN YEARS |");
		System.out.println("+==============+=================+====================+");
		for(int i = 0; i < 10; i++) {
			System.out.println(investment[i]);
		}
		System.out.println("+--------------+-----------------+--------------------+");
		System.out.println("+--------------+-----------------+--------------------+");
		return "";
	}
	
}
