package jrat.plugin.recovery.stub;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import jrat.api.stub.StubPlugin;

@SuppressWarnings("unused")
public class RecoveryPlugin extends StubPlugin {
	
	public static final short HEADER = 130;
	
	private DataInputStream dis;
	private DataOutputStream dos;
	
	@Override
	public void onStart() throws Exception {
		
	}

	@Override
	public void onDisconnect(Exception ex) throws Exception {
		
	}

	@Override
	public void onConnect(DataInputStream dis, DataOutputStream dos) throws Exception {
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void onPacket(short header) throws Exception {
		if (header == HEADER) {
			
		}
	}
	@Override
	public void onEnable() throws Exception {
		
	}
	
	@Override
	public String getName() {
		return "Password Recovery";
	}

}
