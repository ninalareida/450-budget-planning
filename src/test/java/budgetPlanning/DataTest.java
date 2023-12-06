package budgetPlanning;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

class DataTest {

	@Test
	public void testDataWithSchema() throws Exception {
        try {
            // JSONSchema laden
            JsonNode schemaNode = JsonLoader.fromString("/data_schema.json");
            JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);

            // JSONDatenbeispiel aus Originaldaten
            String jsonData = "{\n" +
                    "  \"data\": [\n" +
                    "    {\"month\": \"Januar\", \"day\": 1, \"expenses\": true, \"value\": 100.0},\n" +
                    "    {\"month\": \"Januar\", \"day\": 2, \"expenses\": true, \"value\": 150.0},\n" +
                    "    {\"month\": \"Februar\", \"day\": 3, \"expenses\": false, \"value\": 200.0},\n" +
                    "    {\"month\": \"Februar\", \"day\": 4, \"expenses\": true, \"value\": 75.0}\n" +
                    "  ]\n" +
                    "}";

            ProcessingReport report = schema.validate(JsonLoader.fromString(jsonData));
            assertTrue("Die JSON-Daten entsprechen nicht dem Schema: " + report.toString(), report.isSuccess());
            assertTrue("Invisible characters found", DataInvisibleTest.checkInvisibleCharacters(jsonData));

        } catch (ProcessingException e) {
            throw new RuntimeException("Fehler bei der JSON-Schema-Validierung", e);
        }
    }
}
