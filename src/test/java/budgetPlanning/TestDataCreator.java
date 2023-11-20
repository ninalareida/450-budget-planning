package budgetPlanning;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestDataCreator {

	public static DataContainer createTestData() {
        DataContainer testData = new DataContainer();
        
        // Holen Sie die Datenliste und fügen Sie Testdaten hinzu
        List<HandleData> dataList = testData.getData();
        dataList.add(new HandleData("Januar", 1, true, 100.0));
        dataList.add(new HandleData("Februar", 2, false, 3000.0));
        dataList.add(new HandleData("März", 3, true, 200.0));
        dataList.add(new HandleData("April", 4, false, 5000.0));

        return testData;
    }
}