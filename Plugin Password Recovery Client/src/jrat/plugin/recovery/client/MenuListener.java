package jrat.plugin.recovery.client;

import java.util.List;

import jrat.api.Client;
import jrat.api.ui.RATMenuItemActionListener;

public class MenuListener implements RATMenuItemActionListener {

	@Override
	public void onClick(List<Client> clients) {
		try {
			if (clients.size() > 0) {
				Client c = clients.get(0);		
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
