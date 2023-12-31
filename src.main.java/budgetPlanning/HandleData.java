package budgetPlanning;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the data
 * 
 * @author Nina Lareida
 * @author Sabrina Boccia
 */
public class HandleData {

	private String month;
	private int day;
	private Boolean expenses;
	private double value;

	public HandleData() {

	}

	public HandleData(String month, int day, Boolean expenses, double value) {
		super();
		this.month = month;
		this.day = day;
		this.expenses = expenses;
		this.value = value;
	}

	/**
	 * Get the month
	 * 
	 * @return String with Name of Month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month
	 * 
	 * @param month as String
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Gets the wanted number of a day within a month
	 * 
	 * @return day as a number (integer)
	 */
	public int getDay() {
		return day;
	}

	/**
	 * sets the day
	 * 
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Gets the all the expenses of every month
	 * 
	 * @return expenses as an boolean value. If expenses are false, then its
	 *         displayed as income
	 */
	public Boolean getExpenses() {
		return expenses;
	}

	/**
	 * sets expenses as true or false
	 * 
	 * @param expenses
	 */
	public void setExpenses(Boolean expenses) {
		this.expenses = expenses;
	}

	/**
	 * gets the value of data object
	 * 
	 * @return value as an double
	 */
	public double getValue() {
		return value;
	}

	/**
	 * sets the value of data object
	 * 
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * shows all Data from the List from the JSON String used GSON Library Container
	 * for Data see DataContainer-Class
	 * 
	 * @return dataList, a List of all the Data
	 */
	public List<HandleData> showFullData() {
		System.out.println("------------------------------------------");
		System.out.println("------------------ DATEN -----------------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();
		for (HandleData item : dataList) {
			System.out.println("------------------------------------------");
			System.out.println("Monat: " + item.getMonth());
			System.out.println("------------------------------------------");
			System.out.println("\tTag: " + item.getDay());
			System.out.println("\tAusgaben: " + item.getExpenses());
			System.out.println("\tWert: " + item.getValue());
			System.out.println();
		}
		return dataList;
	}

	/**
	 * Shows the sum of income and expenses of each each month
	 */
	public void getSumOfBalanceOfMonth() {
		System.out.println("------------------------------------------");
		System.out.println("----- EINKOMMEN & AUSGABEN PRO MONAT -----");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		String currentMonth = "";
		double totalIncome = 0;
		double totalExpenses = 0;

		for (HandleData item : dataList) {
			String month = item.getMonth();
			double value = item.getValue();
			boolean isExpense = item.getExpenses();

			if (!month.equals(currentMonth)) {
				if (!currentMonth.isEmpty()) {
					// Print the totals for the previous month
					System.out.println("------------------------------------------");
					System.out.println("Monat: " + currentMonth);
					System.out.println("------------------------------------------");
					System.out.println("\tEinkommen: " + totalIncome);
					System.out.println("\tAusgaben: " + totalExpenses);
					System.out.println("");
				}

				// Reset totals for the new month
				currentMonth = month;
				totalIncome = 0;
				totalExpenses = 0;
			}

			if (isExpense) {
				totalExpenses += value;
			} else {
				totalIncome += value;
			}
		}

		// Print the totals for the last month
		System.out.println("------------------------------------------");
		System.out.println("Monat: " + currentMonth);
		System.out.println("------------------------------------------");
		System.out.println("\tEinkommen: " + totalIncome);
		System.out.println("\tAusgaben: " + totalExpenses);
	}

	/**
	 * Gets the highest value of the year - either income or expense.
	 * 
	 * @return dataList
	 */
	public List<HandleData> getMaxOfYear() {
		System.out.println("------------------------------------------");
		System.out.println("-------------- JAHR MAXIMUM --------------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();
		double value = 0;
		double maxValue = 0;

		for (HandleData item : dataList) {
			value = item.getValue();

			if (value > maxValue) {
				maxValue = value;
			}
		}

		System.out.println("Das ist der maximum Wert: " + maxValue);

		return dataList;
	}

	/**
	 * Shows the maximum of income and maximum of expenses for every month within
	 * one year
	 * 
	 */
	public void displayMaxMinForMonths() {
		System.out.println("------------------------------------------");
		System.out.println("------- MAXIMUM & MINIMUM PRO MONAT ------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		String[] months = { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September",
				"Oktober", "November", "Dezember" };

		// Initialize arrays to store max and min values for each month
		double[] maxIncomeByMonth = new double[12];
		double[] minIncomeByMonth = new double[12];
		double[] maxExpenseByMonth = new double[12];
		double[] minExpenseByMonth = new double[12];

		// Initialize minIncomeByMonth and minExpenseByMonth with high initial values
		Arrays.fill(minIncomeByMonth, Double.MAX_VALUE);
		Arrays.fill(minExpenseByMonth, Double.MAX_VALUE);

		for (HandleData item : dataList) {
			String month = item.getMonth();
			int monthIndex = Arrays.asList(months).indexOf(month);
			boolean isExpense = item.getExpenses();

			double value = item.getValue();

			// Update max and min values for the specific month
			if (!isExpense) {
				if (value > maxIncomeByMonth[monthIndex]) {
					maxIncomeByMonth[monthIndex] = value;
				}
				if (value < minIncomeByMonth[monthIndex]) {
					minIncomeByMonth[monthIndex] = value;

				}
			} else {
				if (value > maxExpenseByMonth[monthIndex]) {
					maxExpenseByMonth[monthIndex] = value;
				}
				if (value < minExpenseByMonth[monthIndex]) {
					minExpenseByMonth[monthIndex] = value;
				}
			}
		}

		// Output the results for each month
		for (int i = 0; i < 12; i++) {
			String month = months[i];
			System.out.println("------------------------------------------");
			System.out.println(month);
			System.out.println("------------------------------------------");
			System.out.println("\tEinkommen");
			System.out.println("\t \tmaximum: " + maxIncomeByMonth[i]);
			System.out.println("\t \tminimum: " + minIncomeByMonth[i]);
			System.out.println("\tAusgaben");
			System.out.println("\t \tmaximum: " + maxExpenseByMonth[i]);
			System.out.println("\t \tminimum: " + minExpenseByMonth[i]);
			System.out.println("");
		}
	}

	/**
	 * Shows the potential of saving money for every month as String: < 10 "no
	 * potential" < 200 "little potential" bigger than 200 is great potential
	 */
	public void savingsPotential() {
		System.out.println("------------------------------------------");
		System.out.println("-------------- SPARPOTENZIAL -------------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		String currentMonth = "";
		double totalIncome = 0;
		double totalExpenses = 0;
		double total = 0;

		for (HandleData item : dataList) {
			String month = item.getMonth();
			double value = item.getValue();
			boolean isExpense = item.getExpenses();

			if (!month.equals(currentMonth)) {
				if (!currentMonth.isEmpty()) {
					// Print the totals for the previous month
					System.out.println("------------------------------------------");
					System.out.println("Monat: " + currentMonth);
					System.out.println("------------------------------------------");
					System.out.println("\tEinkommen: " + totalIncome);
					System.out.println("\tAusgaben: " + totalExpenses);

					total = totalIncome - totalExpenses;
					System.out.println("\tDifferenz: " + total);

					if (total < 10) {
						System.out.println("\t \t --> Kein Sparpotenzial");
					} else if (total < 200) {
						System.out.println("\t \t --> Mittel Sparpotenzial");
					} else {
						System.out.println("\t \t --> Hohes Sparpotenzial");
					}

				}

				// Reset totals for the new month
				currentMonth = month;
				totalIncome = 0;
				totalExpenses = 0;
			}

			if (isExpense) {
				totalExpenses += value;
			} else {
				totalIncome += value;
			}
		}

		// Print the totals for the last month
		System.out.println("------------------------------------------");
		System.out.println("Monat: " + currentMonth);
		System.out.println("------------------------------------------");
		System.out.println("\tEinkommen: " + totalIncome);
		System.out.println("\tAusgaben: " + totalExpenses);
		total = totalIncome - totalExpenses;
		System.out.println("\tDifferenz: " + total);
		if (total < 10) {
			System.out.println("\t \t --> Kein Sparpotenzial");
		} else if (total < 200) {
			System.out.println("\t \t --> Mittel Sparpotenzial");
		} else {
			System.out.println("\t \t --> Hohes Sparpotenzial");
		}
		System.out.println("");
	}
}
