package jrat.plugin.recovery.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jrat.api.Client;

@SuppressWarnings("serial")
public class FrameRecovery extends JFrame {
	
	public static FrameRecovery INSTANCE;

	private List<Client> clients;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JPopupMenu popupMenu;

	public FrameRecovery(List<Client> c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				INSTANCE = null;
			}
		});
		INSTANCE = this;
		this.clients = c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel();
		model.addColumn("Client");
		model.addColumn("");
		model.addColumn("");
		model.addColumn("");

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		
		JMenuItem mntmReload = new JMenuItem("Reload");
		mntmReload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Client c : clients) {
					try {
						c.addToSendQueue(new Packet130GetEntries(c));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		popupMenu.add(mntmReload);
		
		addPopup(scrollPane, popupMenu);
		addPopup(table, popupMenu);
	}
	
	public void addEntry(Client client, String[] data) {
		String[] data1 = new String[data.length + 1];
		
		data1[0] = client.getIP();
		
		for (int i = 0; i < data.length; i++) {
			data1[i + 1] = data[i];
		}
		
		model.addRow(data1);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
