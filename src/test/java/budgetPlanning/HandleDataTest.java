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
import java.util.ArrayList;
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
				new HandleData("Februar", 2, false, 3000.0), 
				new HandleData("März", 3, true, 200.0),
				new HandleData("April", 4, false, 8000.0)
		);

		DataContainer testDataContainer = new DataContainer();
		testDataContainer.setData(testDataList);
		
		handleData.setDataContainer(testDataContainer);

		// Mock the fromJson method
		when(mockGson.fromJson(anyString(), eq(DataContainer.class))).thenReturn(testDataContainer);
	}
	
	/**
	 * Rechenfunktionalität von MaximumOfYear
	 */

	 @Test
	    public void testGetMaximumOfYear() {
	        double result = handleData.getMaximumOfYear();
	        double expected = 8000.0; 
	        assertEquals(expected, result, 0.01);
	        
	    }
	 
	 
	 /**
	  * Konsistenz des Outputs
	  */
	 @Test
	 public void testSavingsPotentialOutputFormatting() {
	     // Umleitung der Standardausgabe
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));

	     // Ausführen der Methode
	     handleData.savingsPotential();

	     // Überprüfen der Ausgabe
	     String output = outContent.toString();
	     assertTrue(output.contains("Januar"));
	     assertTrue(output.contains("Februar"));
	     assertTrue(output.contains("März"));
	     assertTrue(output.contains("April"));
	    

	     // Standardausgabe zurücksetzen
	     System.setOut(System.out);
	 }
	 
	 
	 /**
	  * gesamthafte Funktionalität (mit Hilfsmethode)
	  */
	 @Test
	 public void testSavingsPotentialCalculations() {
	     
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));

	     handleData.savingsPotential();

	     // prüfen
	     String output = outContent.toString();
	     assertTrue(output.contains("Einkommen: 3000.0")); 
	     assertTrue(output.contains("Ausgaben: 200.0"));
	     assertTrue(output.contains("Differenz:"));
	     assertTrue(output.contains("Hohes Sparpotenzial"));
	     
	   

	     // Standardausgabe zurücksetzen
	     System.setOut(System.out);
	     
	     System.out.println(output);
	 }
	 
	 
	/**
	 * Prüfen ob die Ausgabe komplett ist.
	 */
	 
	 @Test
	 public void testSavingsPotentialCompleteness() {
	     // Umleitung der Standardausgabe
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));

	     handleData.savingsPotential();

	     // prüfen
	     String output = outContent.toString();
	     String[] months = {"Januar", "Februar", "März", "April"};
	     for (String month : months) {
	         assertTrue(output.contains("Monat: " + month));
	     }

	     System.setOut(System.out);
	 }
	 

	 /**
	  * Mutationstest von den Daten
	  * + values werden um 100 verändert, dies erkennt der Test dann.
	  */
	 
	 @Test
	 public void testMutationInData() {
		    // Erstellen Testdaten + Kopie
		    List<HandleData> originalDataList = Arrays.asList(
		        new HandleData("Januar", 1, true, 100.0),
		        new HandleData("Februar", 2, false, 3000.0)
		    );
		    List<HandleData> testDataList = new ArrayList<>();
		    for (HandleData item : originalDataList) {
		        testDataList.add(new HandleData(item.getMonth(), item.getDay(), item.getExpenses(), item.getValue()));
		    }

		    DataContainer testDataContainer = new DataContainer();
		    testDataContainer.setData(testDataList);
		    handleData.setDataContainer(testDataContainer);

		    // Änderung
		    for (HandleData item : testDataList) {       
		        item.setValue(item.getValue() + 100);
		    }

		    // vergleichen
		    assertNotEquals(originalDataList, testDataContainer.getData());
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
	 
 
	 //testdriven Um das Time-Freezing integrieren zu können wird noch ein Usecase
	 //hinzugefügt: der Nutzer soll den aktuellen Monat ausgeben können.
	 //Damit wird der Time-Aspekt integriert. Dies geschieht als Punkt 4 des
	 //Test-Driven-Developments.
	 
	 /*@Test
	    public void getCurrentMonth() {
	        double result = handleData.getSumOfBalanceOfMonth();
	        double expected = 8000.0; 
	        assertEquals(expected, result, 0.01);	        
	    }*/
	 
	 

	
}
