package code;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class UserFrame extends JFrame {

	private JButton ManageBlogBtn;
	private JButton logoutBtn;
	private JPanel panel;
	private JTable blogTable;
	private JScrollPane blogScrollPane;
	private JButton deleteBlogBtn;
	private JLabel welcomeLabel;
	private AddBlogFrame addBlogFrame = null;
	private static String username;
	private static String blog_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("serial")
	public UserFrame(String AUsername) throws SQLException {
		this.username = AUsername;
		System.err.println(username);
		setTitle("User Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(800, 500);

		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);

		DefaultTableModel blogTableModel = new DefaultTableModel(new String[] { "Blog ID", "Blog Title", "From", "To",
				"Section ID", "SubSection", "Website", "Terminal", "Approved"}, 0);

		blogTable = new JTable(blogTableModel);
//		blogTable.getColumnModel().getColumn(0).setPreferredWidth(25);
//		blogTable.getColumnModel().getColumn(3).setPreferredWidth(130);

		panel.add(new JScrollPane(blogTable));

		// Set editable=false;
		blogTable.setDefaultEditor(Object.class, null);

		panel.setLayout(null);

		blogScrollPane = new JScrollPane(blogTable);
		blogScrollPane.setBounds(35, 98, 641, 156);
		panel.add(blogScrollPane);
		getContentPane().add(panel);
		blogTable.setRowSelectionAllowed(true);
		blogTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable Draging Columns
		blogTable.getTableHeader().setReorderingAllowed(false);

		blogTable.getTableHeader().setOpaque(false);

		sqlConnection.sqlBlogTable(username, blogTableModel);

		JButton addBlogBtn = new JButton("Add Blog");
		addBlogBtn.setFocusPainted(false);
		addBlogBtn.setIcon(new ImageIcon(UserFrame.class.getResource("/code/icons/blog.png")));
		addBlogBtn.setBounds(219, 266, 117, 29);
		addBlogBtn.setVisible(false);
		panel.add(addBlogBtn);

		JButton editBLogBtn = new JButton("Edit Blog");
		editBLogBtn.setFocusPainted(false);
		editBLogBtn.setIcon(new ImageIcon(UserFrame.class.getResource("/code/icons/blogging.png")));
		editBLogBtn.setBounds(366, 266, 112, 29);
		editBLogBtn.setVisible(false);
		panel.add(editBLogBtn);

		deleteBlogBtn = new JButton("Delete Selected Blog");
		deleteBlogBtn.setFocusPainted(false);
		deleteBlogBtn.setIcon(new ImageIcon(UserFrame.class.getResource("/code/icons/website.png")));
		deleteBlogBtn.setBounds(504, 266, 172, 29);
		deleteBlogBtn.setVisible(false);
		panel.add(deleteBlogBtn);

		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.setFocusPainted(false);
		refreshBtn.setIcon(new ImageIcon(UserFrame.class.getResource("/code/icons/refresh-page-option.png")));
		refreshBtn.setBounds(688, 166, 106, 29);
		refreshBtn.setVisible(false);
		panel.add(refreshBtn);

		blogTable.setRowSelectionAllowed(true);

		logoutBtn = new JButton("Logout");
		logoutBtn.setFocusPainted(false);
		logoutBtn.setIcon(new ImageIcon(UserFrame.class.getResource("/code/icons/exit-logout-2857.png")));
		logoutBtn.setBounds(664, 425, 117, 29);
		logoutBtn.addActionListener(l -> {

			Login login = new Login();
			dispose();
			login.setVisible(true);

		});
		panel.add(logoutBtn);

		welcomeLabel = new JLabel("New label");
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(18, 22, 377, 46);
		welcomeLabel.setText(username + " (User)");
		panel.add(welcomeLabel);

		blogTable.setVisible(false);
		blogScrollPane.setVisible(false);

		ManageBlogBtn = new JButton("Manage Blog");
		ManageBlogBtn.setFocusPainted(false);
		ManageBlogBtn.setIcon(new ImageIcon(UserFrame.class.getResource("/code/icons/blog copy.png")));
		ManageBlogBtn.setBounds(328, 425, 150, 29);
		panel.add(ManageBlogBtn);
		ManageBlogBtn.addActionListener(l -> {

			blogScrollPane.setVisible(true);
			blogTable.setVisible(true);
			addBlogBtn.setVisible(true);
			deleteBlogBtn.setVisible(true);
			refreshBtn.setVisible(true);
			editBLogBtn.setVisible(true);

		});

		/*
		 * 
		 * To get the Blog ID when clicking on the Row
		 */
		blogTable.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				blog_id = blogTable.getValueAt(blogTable.getSelectedRow(), 0).toString();
				System.out.println(blog_id);
			}
		});

		addBlogFrame = new AddBlogFrame(username);

		addBlogBtn.addActionListener(l -> {
			addBlogFrame.setTitleField("");
			addBlogFrame.setContentEditor("");
			addBlogFrame.setSectionComboBox(-1);
			addBlogFrame.setWebsiteCheckbox(false);
			addBlogFrame.setTerminalCheckBox(false);
			addBlogFrame.setImageField("");
			addBlogFrame.setVideoField("");
			addBlogFrame.setFromField("");
			addBlogFrame.setToField("");
			addBlogFrame.setVisible(true);
		});

		editBLogBtn.addActionListener(l -> {

			EditBlogFrame editBLogFrame = null;
			try {
				editBLogFrame = new EditBlogFrame(username, blog_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Please select a blog", "Warning",
				        JOptionPane.WARNING_MESSAGE);
			}
			try {
				editBLogFrame.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Please select a blog", "Warning",
				        JOptionPane.WARNING_MESSAGE);
			}
		});

		deleteBlogBtn.addActionListener(l -> {

			try {
				sqlConnection.sqlDeleteBlog(blog_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		refreshBtn.addActionListener(l -> {

			blog_id = null;
			try {
				blogTableModel.setRowCount(0);
				sqlConnection.sqlBlogTable(username, blogTableModel);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}
}