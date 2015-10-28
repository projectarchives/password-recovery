package jrat.plugin.recovery.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import jrat.api.Client;
import jrat.api.net.PacketBuilder;

public class Packet130GetEntries extends PacketBuilder {
	
	public Packet130GetEntries(Client rat) {
		super(RecoveryPlugin.HEADER, rat);
	}

	@Override
	public void write(Client rat, DataOutputStream dos, DataInputStream dis) throws Exception {

	}

}
