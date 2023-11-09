package budgetPlanning;

import java.util.List;

/**
 * Container for storing the data
 * 
 * DataContainer for GSON data
 * 
 * @see Data
 * @see HandleData
 * 
 * @author Nina Lareida
 * @author Sabrina Boccia
 */

public class DataContainer {
	private List<HandleData> data;

	/**
	 * Get the data
	 * 
	 * @return data
	 */
    public List<HandleData> getData() {
        return data;
    }
}
