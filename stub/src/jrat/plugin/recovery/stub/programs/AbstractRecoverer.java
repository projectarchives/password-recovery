package jrat.plugin.recovery.stub.programs;

import java.util.List;

public abstract class AbstractRecoverer {
	
	public abstract List<String[]> recover() throws Exception;
	
	public abstract String getName();

}
