package code;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	public static JTextField usernameTextField;
	private JButton loginBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	@SuppressWarnings("deprecation")
	public Login() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 266);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setIcon(new ImageIcon(Login.class.getResource("/code/icons/id-card.png")));
		usernameLabel.setBounds(23, 90, 91, 16);
		panel.add(usernameLabel);
		

		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setIcon(new ImageIcon(Login.class.getResource("/code/icons/key-4363.png")));
		passwordLabel.setBounds(23, 132, 91, 16);
		panel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(126, 132, 130, 21);
		panel.add(passwordField);
		
		usernameTextField = new JTextField();
		usernameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameTextField.setBounds(126, 90, 130, 26);
		panel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(197, 181, 79, 29);
		loginBtn.setIcon(new ImageIcon(Login.class.getResource("/code/icons/user-275.png")));
		loginBtn.setBounds(197, 181, 91, 29);
		panel.add(loginBtn);

		loginBtn.addActionListener(l -> {

			try {

				if (sqlConnection.sqlLogin(usernameTextField.getText(), passwordField.getText()) == 1) {
					AdminFrame adminframe = new AdminFrame(usernameTextField.getText());
					System.out.println("Found and Loged in as Admin");
					adminframe.setVisible(true);
					dispose();
				} else if (sqlConnection.sqlLogin(usernameTextField.getText(), passwordField.getText()) == 2) {
					EditorFrame editorframe = new EditorFrame(usernameTextField.getText());
					System.out.println("Found and Loged in as Editor");
					editorframe.setVisible(true);
					dispose();
				} else if (sqlConnection.sqlLogin(usernameTextField.getText(), passwordField.getText()) == 3) {
					UserFrame userframe = new UserFrame(usernameTextField.getText());
					System.out.println("Found and Loged in as User");
					userframe.setVisible(true);
					dispose();
				} else
					JOptionPane.showMessageDialog(null, "Username or password is wrong !", "Error",
							JOptionPane.ERROR_MESSAGE);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		});
		

		JButton exitBtn = new JButton("Exit");
		exitBtn.setIcon(new ImageIcon(Login.class.getResource("/code/icons/cancel.png")));
		exitBtn.setBounds(100, 181, 79, 29);
		panel.add(exitBtn);

		exitBtn.addActionListener(l -> {
			dispose();
			// System.exit(0);
		});
		

		JCheckBox showPasswordBox = new JCheckBox("Show Password");
		showPasswordBox.setBounds(280, 128, 128, 23);
		panel.add(showPasswordBox);

		final JLabel projektTitle = new JLabel("Content Manegment System");
		projektTitle.setBackground(new Color(0, 0, 0));
		projektTitle.setVerticalAlignment(SwingConstants.TOP);
		projektTitle.setEnabled(false);
		projektTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		projektTitle.setHorizontalAlignment(SwingConstants.CENTER);
		projektTitle.setBounds(53, 32, 324, 35);
		panel.add(projektTitle);

		showPasswordBox.addActionListener(l -> {

			if (showPasswordBox.isSelected())
				passwordField.setEchoChar((char) 0);
			else
				passwordField.setEchoChar('\u2022');

		});

	}
}