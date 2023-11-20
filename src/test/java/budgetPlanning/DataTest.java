package budgetPlanning;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataTest {

	@Test
    void testIsValidJsonWithValidData() {
        // valid JSON-String
        String validJsonString = "{\n" +
            "  \"data\": [\n" +
            "    {\"month\": \"Januar\", \"day\": 1, \"expenses\": true, \"value\": 100.0},\n" +
            "    {\"month\": \"Januar\", \"day\": 2, \"expenses\": true, \"value\": 150.0}\n" +
            "  ]\n" +
            "}";
        Data data = new Data(validJsonString);
        assertTrue(data.isValidJson(), "Der JSON-String sollte gültig sein.");
        System.out.println("Test 1: Der JSON String ist gültig");
    }
	
	
	@Test
    void testValidateDataWithValidData() {
        String validJsonString = "{\n" +
            "  \"data\": [\n" +
            "    {\"month\": \"Januar\", \"day\": 1, \"expenses\": true, \"value\": 100.0},\n" +
            "    {\"month\": \"Januar\", \"day\": 2, \"expenses\": true, \"value\": 150.0}\n" +
            "  ]\n" +
            "}";
        Data data = new Data(validJsonString);
        assertTrue(data.validateData(), "Die Daten sollten als gültig validiert werden.");
        System.out.println("Test 2: Die Daten innerhalb des JSON Strings sind valide");
    }

    @Test
    void testValidateDataWithInvalidData() {
        String invalidJsonString = "{\n" +
            "  \"data\": [\n" +
            "    {\"month\": \"Januar\", \"day\": 1, \"expenses\": true, \"value\": 100.0}\n" + // no comma
            "    {\"month\": \"Januar\", \"day\": 2, \"expenses\": true, \"value\": 150.0}\n" +
            "  ]"; // missing (
        Data data = new Data(invalidJsonString);
        assertFalse(data.validateData(), "Die Daten sollten als ungültig validiert werden.");
        System.out.println("Test 3: Die Daten sind nicht valide. Überprüfe deine Struktur.");
    }

}
