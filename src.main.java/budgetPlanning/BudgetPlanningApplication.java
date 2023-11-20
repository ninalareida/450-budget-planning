package budgetPlanning;

/**
 * This is the main class for the Budget Planning Application
 * 
 * @version 1.0
 *
 * @author Nina Lareida
 * @author Sabrina Boccia
 * 
 */
public class BudgetPlanningApplication {

	public static void main(String[] args) {
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		
		HandleData fullData = new HandleData(myObject);
		DataContainer t = fullData.getDataContainer();
		//fullData.showFullData();	
		//fullData.getSumOfBalanceOfMonth();
		
		//fullData.getMaximumOfYear();
		
		//fullData.displayMaxMinForMonths();
		fullData.savingsPotential();
	}
}
