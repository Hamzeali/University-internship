package code;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

public class DeleteUserFrame extends JFrame {

	private JPanel contentPane;
	private static String user_id;
	private static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUserFrame frame = new DeleteUserFrame(user_id, username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteUserFrame(String user_id, String username) {
		setResizable(false);
		setTitle("Delete User");
		this.user_id = user_id;
		this.username = username;

		System.err.println("user_id : " + user_id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 111);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setIcon(new ImageIcon(DeleteUserFrame.class.getResource("/code/icons/cancel.png")));
		cancelBtn.setBounds(115, 65, 105, 29);
		panel.add(cancelBtn);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setIcon(new ImageIcon(DeleteUserFrame.class.getResource("/code/icons/delete.png")));
		deleteBtn.setBounds(233, 65, 105, 29);
		panel.add(deleteBtn);

		JLabel msgLabel1 = new JLabel("Are you sure you want to delete the User : " + username + " ");
		msgLabel1.setBounds(6, 23, 426, 16);
		panel.add(msgLabel1);

		deleteBtn.addActionListener(l -> {

			try {
				sqlConnection.sqlDeleteUser(user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
			cancelBtn.setText("Exit");
		});

		cancelBtn.addActionListener(l -> {
			dispose();
		});

	}
}