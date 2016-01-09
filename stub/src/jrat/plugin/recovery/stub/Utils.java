package jrat.plugin.recovery.stub;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utils {

	public static String getTagValue(String tag, Element e) {
		NodeList list = e.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) list.item(0);
		return node == null ? "" : node.getNodeValue();
	}
	
}
