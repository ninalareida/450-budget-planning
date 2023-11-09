/**
 * 
 */
package budgetPlanning;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HandleDataTest {

    private Data mockData;
    private DataContainer mockDataContainer;
    
    @BeforeEach
    void setUp() {
        mockData = mock(Data.class);
        mockDataContainer = mock(DataContainer.class);
        
        // Stellen Sie sicher, dass Ihre Data-Klasse diese Methode richtig unterstützt.
        when(mockData.getDataContainer()).thenReturn(mockDataContainer);
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
        List<HandleData> dataList = Arrays.asList(
            new HandleData("Januar", 1, true, 100.0),
            new HandleData("Januar", 2, false, 200.0),
            new HandleData("Februar", 1, true, 300.0),
            new HandleData("Februar", 2, false, 150.0)
        );
        
        when(mockDataContainer.getData()).thenReturn(dataList);
        
        HandleData handleData = new HandleData();
        handleData.getSumOfBalanceOfMonth();
        
        // Hier müssten Sie die Ausgabe überprüfen, was in einem echten Test nicht trivial ist. 
        // Sie könnten einen Ansatz mit System.out verfolgen oder die Methode umgestalten, um testbare Ergebnisse zurückzugeben.
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
