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
		
		HandleData fullData = new HandleData();
		//fullData.showFullData();	
		//fullData.getSumOfBalanceOfMonth();
		//fullData.getMaxOfYear();
		
		//fullData.displayMaxMinForMonths();
		fullData.savingsPotential();
	}
}
