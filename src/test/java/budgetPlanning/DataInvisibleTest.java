package budgetPlanning;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataInvisibleTest {

	@Test
	public void testInvisibleCharacters() {
		String jsonData = "{\"data\": [{\"month\": \"Januar\", \"day\": 1, \"expenses\": true, \"value\": 100.0}, {\"month\": \"Januar\", \"day\": 2, \"expenses\": true, \"value\": 150.0}, {\"month\": \"Februar\", \"day\": 3, \"expenses\": false, \"value\": 200.0}, {\"month\": \"Februar\", \"day\": 4, \"expenses\": true, \"value\": 75.0}]}";


        assertTrue("Invisible characters found", checkInvisibleCharacters(jsonData));
    }

    public static boolean checkInvisibleCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int asciiValue = (int) c;

            if (asciiValue < 32 || asciiValue == 127) {
                System.out.println("Invisible character found: " + c + ", ASCII Value: " + asciiValue);
                return true; // Invisible character found
            }
        }
        System.out.println("no invisible characters found");
        return false; // No invisible characters found
    }

}
