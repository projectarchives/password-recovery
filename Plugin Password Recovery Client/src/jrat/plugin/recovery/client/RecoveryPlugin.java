package jrat.plugin.recovery.client;

import jrat.api.Icons;
import jrat.api.Plugin;
import jrat.api.ui.RATMenuItem;

public class RecoveryPlugin extends Plugin {
	
	public RecoveryPlugin() {
		super("Password Recovery", "1.0", "Recover Passwords", "jRAT", Icons.getIcon("Password Recovery", "/icons/icon.png"));
	
		RATMenuItem entry = new RATMenuItem(new MenuListener(), "Password Recovery", icon);
		RATMenuItem.addItem(entry);
	}

}
