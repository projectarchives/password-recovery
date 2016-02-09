package jrat.plugin.recovery.client;

import jrat.api.Icons;
import jrat.api.Plugin;
import jrat.api.net.Packet;
import jrat.api.ui.RATMenuItem;

import javax.swing.ImageIcon;

public class RecoveryPlugin extends Plugin {
	
	public static final short HEADER = 130;
	
	public RecoveryPlugin() {
		super("Password Recovery", "1.0", "Recover Passwords", "jRAT");

		try {
			icon = new ImageIcon(getResource("icons/icon.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		RATMenuItem entry = new RATMenuItem(new MenuListener(), "Password Recovery", icon);
		RATMenuItem.addItem(entry);
		
		Packet.registerIncoming(HEADER, new EntryPacketListener());
	}

}
