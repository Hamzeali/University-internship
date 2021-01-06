package code;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class AdminFrame extends JFrame {

	private JPanel panel;

	private JTable userTable3;
	private JScrollPane scrollPane3;
	private JTable userTable;
	private JTable userTable2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JButton ManageUserBtn;
	private JButton logoutBtn;
	private JButton deleteUserBtn;
	private JButton manageSection;

	private JTable sectionTable;
	private JScrollPane sectionScrollPane;
	private JButton deleteSectionBtn;
	private JLabel welcomeLabel;
	private Register register = null;

	private JButton renameSectionBtn;
	private JButton editUserBtn;
	private static String username;
	private static String user_id;
	private static String section_id;
	private static String section_type;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("serial")
	public AdminFrame(String AUsername) throws SQLException {
		this.username = AUsername;
		System.err.println(username);
		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		panel = new JPanel();
		setSize(800, 500);

		/*
		 * 
		 * For Manage User
		 */
		DefaultTableModel tableModel = new DefaultTableModel(
				new String[] { "User ID", "First Name", "Last Name", "Username", "Role" }, 0);

		userTable = new JTable(tableModel);
		userTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		userTable.getColumnModel().getColumn(3).setPreferredWidth(130);

		panel.add(new JScrollPane(userTable));

		// Set editable=false;
		userTable.setDefaultEditor(Object.class, null);

		panel.setLayout(null);

		scrollPane = new JScrollPane(userTable);
		scrollPane.setBounds(35, 98, 641, 156);
		panel.add(scrollPane);
		getContentPane().add(panel);
		userTable.setRowSelectionAllowed(true);
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable Draging Columns
		userTable.getTableHeader().setReorderingAllowed(false);

		userTable.getTableHeader().setOpaque(false);

		sqlConnection.sqlUserTable(tableModel);

		ManageUserBtn = new JButton("Manage User");
		ManageUserBtn.setFocusPainted(false);
		ManageUserBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/user.png")));
		ManageUserBtn.setBounds(177, 425, 135, 29);
		panel.add(ManageUserBtn);

		JButton addUserBtn = new JButton("Add User");
		addUserBtn.setFocusPainted(false);
		addUserBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/add-friend.png")));
		addUserBtn.setBounds(35, 266, 135, 29);
		addUserBtn.setVisible(false);
		panel.add(addUserBtn);

		deleteUserBtn = new JButton("Delete Selected User");
		deleteUserBtn.setFocusPainted(false);
		deleteUserBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/delete.png")));
		deleteUserBtn.setBounds(484, 266, 192, 29);
		deleteUserBtn.setVisible(false);
		panel.add(deleteUserBtn);

		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.setFocusPainted(false);
		refreshBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/refresh-page-option.png")));
		refreshBtn.setBounds(688, 166, 106, 29);
		refreshBtn.setVisible(false);
		panel.add(refreshBtn);

		refreshBtn.addActionListener(l -> {

			user_id = null;
			username = null;
			try {
				tableModel.setRowCount(0);
				sqlConnection.sqlUserTable(tableModel);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

//		  To get the User ID when clicking on the Row	//

		userTable.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				user_id = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
				System.out.println(user_id);
				username = userTable.getValueAt(userTable.getSelectedRow(), 3).toString();
				System.out.println(username);
			}
		});

		/*
		 * 
		 * For Manage Section
		 */

		DefaultTableModel SectionTableModel = new DefaultTableModel(
				new String[] { "Section ID", "Section", "Subsection" }, 0);

		sectionTable = new JTable(SectionTableModel);

		panel.add(new JScrollPane(sectionTable));

		// Set editable=false;
		sectionTable.setDefaultEditor(Object.class, null);

		panel.setLayout(null);

		sectionScrollPane = new JScrollPane(sectionTable);
		sectionScrollPane.setBounds(35, 98, 641, 156);
		panel.add(sectionScrollPane);
		getContentPane().add(panel);
		sectionTable.setRowSelectionAllowed(true);
		sectionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable Draging Columns
		sectionTable.getTableHeader().setReorderingAllowed(false);

		sectionTable.getTableHeader().setOpaque(false);
		sqlConnection.sqlSectionTable(SectionTableModel);

		JButton addSectionBtn = new JButton("Add Section");
		addSectionBtn.setFocusPainted(false);
		addSectionBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/tab.png")));
		addSectionBtn.setBounds(35, 266, 135, 29);
		addSectionBtn.setVisible(false);
		panel.add(addSectionBtn);

		deleteSectionBtn = new JButton("Delete Selected Section");
		deleteSectionBtn.setFocusPainted(false);
		deleteSectionBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/delete copy.png")));
		deleteSectionBtn.setBounds(484, 266, 192, 29);
		deleteSectionBtn.setVisible(false);
		panel.add(deleteSectionBtn);

		JButton refreshSectionBtn = new JButton("Refresh");
		refreshSectionBtn.setFocusPainted(false);
		refreshSectionBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/refresh-page-option.png")));
		refreshSectionBtn.setBounds(688, 166, 91, 29);
		refreshSectionBtn.setVisible(false);
		panel.add(refreshSectionBtn);

		refreshSectionBtn.addActionListener(l -> {

			section_id = null;
			section_type = null;
			try {
				SectionTableModel.setRowCount(0);
				sqlConnection.sqlSectionTable(SectionTableModel);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		sectionScrollPane.setVisible(false);

		sectionTable.setRowSelectionAllowed(true);

//		  To get the Section ID when clicking on the Row	//
		sectionTable.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				section_id = sectionTable.getValueAt(sectionTable.getSelectedRow(), 0).toString();
				System.out.println(section_id);

				section_type = sectionTable.getValueAt(sectionTable.getSelectedRow(), 1).toString();
				System.out.println(section_type);
			}
		});
		/*
		 * Close for Manage Section
		 */

		logoutBtn = new JButton("Logout");
		logoutBtn.setFocusPainted(false);
		logoutBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/exit-logout-2857.png")));
		logoutBtn.setBounds(664, 425, 117, 29);
		logoutBtn.addActionListener(l -> {

			Login login = new Login();
			dispose();
			login.setVisible(true);

		});
		panel.add(logoutBtn);

		manageSection = new JButton("Manage Section");
		manageSection.setFocusPainted(false);
		manageSection.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/sections.png")));
		manageSection.setBounds(397, 425, 155, 29);
		panel.add(manageSection);

		welcomeLabel = new JLabel("New label");
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(18, 22, 377, 46);

		welcomeLabel.setText(username + " (Admin)");
		panel.add(welcomeLabel);

		renameSectionBtn = new JButton("Rename Selected Section");
		renameSectionBtn.setFocusPainted(false);
		renameSectionBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/edit copy.png")));
		renameSectionBtn.setBounds(227, 266, 202, 29);
		renameSectionBtn.setVisible(false);
		panel.add(renameSectionBtn);

		editUserBtn = new JButton("Edit Selected User");
		editUserBtn.setFocusPainted(false);
		editUserBtn.setIcon(new ImageIcon(AdminFrame.class.getResource("/code/icons/edit.png")));
		editUserBtn.setBounds(227, 266, 202, 29);
		editUserBtn.setVisible(false);
		panel.add(editUserBtn);

		manageSection.addActionListener(l -> {
			scrollPane.setVisible(false);
			userTable.setVisible(false);
			addUserBtn.setVisible(false);
			deleteUserBtn.setVisible(false);
			refreshBtn.setVisible(false);
			editUserBtn.setVisible(false);

			sectionScrollPane.setVisible(true);
			sectionTable.setVisible(true);
			addSectionBtn.setVisible(true);
			deleteSectionBtn.setVisible(true);
			refreshSectionBtn.setVisible(true);
			renameSectionBtn.setVisible(true);

		});

		userTable.setVisible(false);
		scrollPane.setVisible(false);

		ManageUserBtn.addActionListener(l -> {

			scrollPane.setVisible(true);
			userTable.setVisible(true);
			addUserBtn.setVisible(true);
			deleteUserBtn.setVisible(true);
			refreshBtn.setVisible(true);
			editUserBtn.setVisible(true);

			sectionScrollPane.setVisible(false);
			sectionTable.setVisible(false);
			addSectionBtn.setVisible(false);
			deleteSectionBtn.setVisible(false);
			refreshSectionBtn.setVisible(false);
			renameSectionBtn.setVisible(false);

			try {
				register = new Register();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addUserBtn.addActionListener(w -> {

				register.setVisible(true);

			});

		});

		addSectionBtn.addActionListener(l -> {

			AddSectionFrame addsectionframe = null;
			try {
				addsectionframe = new AddSectionFrame();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addsectionframe.setVisible(true);

		});

		deleteSectionBtn.addActionListener(l -> {

			DeleteSectionFrame deletesectionframe = null;
			if(section_id != null && !section_id.isEmpty()) {
				deletesectionframe = new DeleteSectionFrame(section_id, section_type);
				deletesectionframe.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(panel, "Please select a section", "Warning",
				        JOptionPane.WARNING_MESSAGE);

		});

		editUserBtn.addActionListener(l -> {

			EditUserFrame edituserframe = null;
			try {
				edituserframe = new EditUserFrame(user_id);
				edituserframe.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Please select a user", "Warning",
				        JOptionPane.WARNING_MESSAGE);
			}

		});

		deleteUserBtn.addActionListener(w -> {
			DeleteUserFrame deleteuser = null;
			if(user_id != null && !user_id.isEmpty()) {
				deleteuser = new DeleteUserFrame(user_id, username);
				deleteuser.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(panel, "Please select a user", "Warning",
				        JOptionPane.WARNING_MESSAGE);
			

		});

		renameSectionBtn.addActionListener(l -> {

			EditSectionFrame editsectionframe = null;
			if(section_id != null && !section_id.isEmpty()) {
				editsectionframe = new EditSectionFrame(section_id, section_type);
				editsectionframe.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(panel, "Please select a section", "Warning",
				        JOptionPane.WARNING_MESSAGE);

		});

	}
}
