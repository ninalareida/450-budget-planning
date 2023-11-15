/**
 * 
 */
package budgetPlanning;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

import java.util.Arrays;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
public class HandleDataTest {

    //private Data mockData;
    
    @Mock
    private DataContainer mockDataContainer;
    
    @InjectMocks
    private HandleData handleData;

    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.initMocks(this);
    	/*
        mockData = mock(Data.class);
        mockDataContainer = mock(DataContainer.class);
        
        // Stellen Sie sicher, dass Ihre Data-Klasse diese Methode richtig unterstützt.
        when(mockData.getDataContainer()).thenReturn(mockDataContainer);
        */
    }

    @Test
    void testShowFullData() {
        List<HandleData> expectedList = Arrays.asList(
            new HandleData("Januar", 1, true, 100.0),
            new HandleData("Februar", 2, false, 200.0)
        );
        
        when(mockDataContainer.getData()).thenReturn(expectedList);
        
        HandleData handleData = new HandleData();
        List<HandleData> result = handleData.showFullData();
        
        assertEquals(expectedList, result);
        verify(mockDataContainer).getData();
    }

    @Test
    void testGetSumOfBalanceOfMonth() {
        // Mock DataContainer
        DataContainer mockDataContainer = mock(DataContainer.class);
        
        // Mock data
        List<HandleData> dataList = Arrays.asList(
            new HandleData("Januar", 1, true, 100.0),
            new HandleData("Januar", 2, false, 200.0),
            new HandleData("Februar", 1, true, 300.0),
            new HandleData("Februar", 2, false, 150.0)
        );

        // Set up the mock to return the data
        when(mockDataContainer.getData()).thenReturn(dataList);

        // Create an instance of HandleData with the mocked DataContainer
        HandleData handleData = new HandleData(mockDataContainer);

        // Capture System.out.println output
        ArgumentCaptor<String> outputCaptor = ArgumentCaptor.forClass(String.class);

        // Call the method
        handleData.getSumOfBalanceOfMonth();

        // Verify the expected output
        verifyPrintStatements(outputCaptor, "Januar", 300.0, 100.0, 200.0);
        verifyPrintStatements(outputCaptor, "Februar", 450.0, 300.0, 150.0);
    }

    private void verifyPrintStatements(ArgumentCaptor<String> outputCaptor, String month, double total, double income, double expenses) {
        // Verify the expected output for each month
        String expectedHeader = "Monat: " + month;
        String expectedIncome = "\tEinkommen: " + income;
        String expectedExpenses = "\tAusgaben: " + expenses;

        // Capture and assert the output
        List<String> outputValues = outputCaptor.getAllValues();

        assertTrue(outputValues.contains(expectedHeader));
        assertTrue(outputValues.contains(expectedIncome));
        assertTrue(outputValues.contains(expectedExpenses));
        // You can add more assertions if needed
    }

    @Test
    void testSavingsPotential() {
        List<HandleData> dataList = Arrays.asList(
            new HandleData("Januar", 1, false, 500.0),
            new HandleData("Januar", 2, true, 300.0),
            new HandleData("Februar", 1, false, 1000.0),
            new HandleData("Februar", 2, true, 200.0)
        );
        
        when(mockDataContainer.getData()).thenReturn(dataList);
        
        HandleData handleData = new HandleData();
        handleData.savingsPotential();
        
        // Wie im vorherigen Test ist auch hier die Überprüfung der Ausgabe erforderlich.
    }
}
