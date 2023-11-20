/**
 * 
 */
package budgetPlanning;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
public class HandleDataTest {

	@Mock
	private Data mockData;

	@InjectMocks
	private HandleData handleData;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Mock the Gson object inside the Data class
		Gson mockGson = mock(Gson.class);
		mockData.gson = mockGson;

		// Create test data
		List<HandleData> testDataList = Arrays.asList(new HandleData("Januar", 1, true, 100.0),
				new HandleData("Februar", 2, false, 3000.0), new HandleData("März", 3, true, 200.0),
				new HandleData("April", 4, false, 8000.0));

		DataContainer testDataContainer = new DataContainer();
		testDataContainer.setData(testDataList);

		handleData.setDataContainer(testDataContainer);

		// Mock the fromJson method
		when(mockGson.fromJson(anyString(), eq(DataContainer.class))).thenReturn(testDataContainer);
	}

	@Test
	public void testGetMaximumOfYear() {
		double result = handleData.getMaximumOfYear();
		double expected = 8000.0;
		assertEquals(expected, result, 0.01);

	}

	@Test
	public void testSavingsPotential() {
		double result = handleData.savingsPotential();
		double expected = 8000.0;
		assertEquals(expected, result, 0.01);

	}

	@Test
	public void getSumOfBalanceOfMonth() {
		double result = handleData.getSumOfBalanceOfMonth();
		double expected = 8000.0;
		assertEquals(expected, result, 0.01);

	}

	// testdriven Um das Time-Freezing integrieren zu können wird noch ein Usecase
	// hinzugefügt: der Nutzer soll den aktuellen Monat ausgeben können.
	// Damit wird der Time-Aspekt integriert. Dies geschieht als Punkt 4 des
	// Test-Driven-Developments.

	@Test
	public void getCurrentMonth() {
		double result = handleData.getSumOfBalanceOfMonth();
		double expected = 8000.0;
		assertEquals(expected, result, 0.01);

	}

	@Test
	public void testPrintDataForMonth() {
		// Redirect System.out.println to capture output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Test the method
		//HandleData.printDataForMonth(handleData.getDataContainer().getData(), "Januar");
		handleData.printDataForMonth("Januar");
		
		// Verify the printed output
		String expectedOutput = "Month: Januar, Day: 1, Expenses: true, Value: 100.0";
		assertEquals(expectedOutput, outContent.toString().trim());
	}

	/*
	 * @Test void testShowFullData() { List<HandleData> expectedList =
	 * Arrays.asList(new HandleData("Januar", 1, true, 100.0), new
	 * HandleData("Februar", 2, false, 200.0));
	 * 
	 * when(mockDataContainer.getData()).thenReturn(expectedList);
	 * 
	 * HandleData handleData = new HandleData(); List<HandleData> result =
	 * handleData.showFullData();
	 * 
	 * assertEquals(expectedList, result); verify(mockDataContainer).getData(); }
	 * 
	 * @Test void testIsValidJson() { Data data = new Data("Gültiger JSON-String");
	 * assertTrue(data.isValidJson()); }
	 * 
	 * @Test void testCreateNewDataInstance() { HandleData handleData = new
	 * HandleData(); Data data =
	 * handleData.createNewDataInstance("Gültiger JSON-String");
	 * assertNotNull(data); }
	 * 
	 * @Test void testValidateData() { Data validData = new
	 * Data("Gültiger JSON-String"); assertTrue(validData.validateData());
	 * 
	 * Data invalidData = new Data("Ungültiger JSON-String");
	 * assertFalse(invalidData.validateData()); }
	 * 
	 * @Test void testGetSumOfBalanceOfMonth() { // Mock DataContainer DataContainer
	 * mockDataContainer = mock(DataContainer.class);
	 * 
	 * // Mock data List<HandleData> dataList = Arrays.asList(new
	 * HandleData("Januar", 1, true, 100.0), new HandleData("Januar", 2, false,
	 * 200.0), new HandleData("Februar", 1, true, 300.0), new HandleData("Februar",
	 * 2, false, 150.0));
	 * 
	 * // Set up the mock to return the data
	 * when(mockDataContainer.getData()).thenReturn(dataList);
	 * 
	 * // Create an instance of HandleData with the mocked DataContainer HandleData
	 * handleData = new HandleData(mockDataContainer);
	 * 
	 * // Capture System.out.println output ArgumentCaptor<String> outputCaptor =
	 * ArgumentCaptor.forClass(String.class);
	 * 
	 * // Call the method handleData.getSumOfBalanceOfMonth();
	 * 
	 * // Verify the expected output verifyPrintStatements(outputCaptor, "Januar",
	 * 300.0, 100.0, 200.0); verifyPrintStatements(outputCaptor, "Februar", 450.0,
	 * 300.0, 150.0); }
	 * 
	 * private void verifyPrintStatements(ArgumentCaptor<String> outputCaptor,
	 * String month, double total, double income, double expenses) { // Verify the
	 * expected output for each month String expectedHeader = "Monat: " + month;
	 * String expectedIncome = "\tEinkommen: " + income; String expectedExpenses =
	 * "\tAusgaben: " + expenses;
	 * 
	 * // Capture and assert the output List<String> outputValues =
	 * outputCaptor.getAllValues();
	 * 
	 * assertTrue(outputValues.contains(expectedHeader));
	 * assertTrue(outputValues.contains(expectedIncome));
	 * assertTrue(outputValues.contains(expectedExpenses)); // You can add more
	 * assertions if needed }
	 * 
	 * @Test void testSavingsPotential() { List<HandleData> dataList =
	 * Arrays.asList(new HandleData("Januar", 1, false, 500.0), new
	 * HandleData("Januar", 2, true, 300.0), new HandleData("Februar", 1, false,
	 * 1000.0), new HandleData("Februar", 2, true, 200.0));
	 * 
	 * when(mockDataContainer.getData()).thenReturn(dataList);
	 * 
	 * HandleData handleData = new HandleData(); handleData.savingsPotential();
	 * 
	 * // Wie im vorherigen Test ist auch hier die Überprüfung der Ausgabe //
	 * erforderlich. }
	 */
}
