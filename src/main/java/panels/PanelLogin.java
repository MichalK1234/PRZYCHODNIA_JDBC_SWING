package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classes.User;
import database.Database;
import encryption.Encrypt;

public class PanelLogin extends JPanel {

	private JLabel lUsername;
	private JLabel lPassword;

	private static JTextField tfUsername;
	private JPasswordField pfPassword;

	private JButton btnLogin;
	private JButton btnRegistration;
	private JButton btnCancel;

	public PanelLogin() {
		super(new GridBagLayout());

		lUsername = new JLabel("USERNAME: ");
		lPassword = new JLabel("PASSWORD: ");

		tfUsername = new JTextField(10);
		tfUsername.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {

				tfUsername.selectAll();

			}
		});

		pfPassword = new JPasswordField(10);

		pfPassword.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {

				pfPassword.selectAll();

			}
		});

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {

			login();

		});

		btnRegistration = new JButton("Registration");
		btnRegistration.addActionListener(e -> {

			createRegistrationWindow();

		});

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {

			JFrame frameCancel = (JFrame) getRootPane().getParent();
			frameCancel.dispose();

		});

		JPanel panelFields = new JPanel(new GridBagLayout());
		panelFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true),
				"Login panel", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 15),
				Color.BLACK));

		GridBagConstraints gbcFields = new GridBagConstraints();

		gbcFields.insets = new Insets(5, 5, 5, 5);

		gbcFields.gridx = 0;
		gbcFields.gridy = 0;
		panelFields.add(lUsername, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 0;
		panelFields.add(tfUsername, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 1;
		panelFields.add(lPassword, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 1;

		pfPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		panelFields.add(pfPassword, gbcFields);

		JPanel panelBtns = new JPanel(new GridBagLayout());

		GridBagConstraints gbcBtns = new GridBagConstraints();

		gbcBtns.insets = new Insets(5, 10, 5, 10);

		gbcBtns.gridx = 0;
		gbcBtns.gridy = 0;

		panelBtns.add(btnLogin, gbcBtns);

		gbcBtns.gridx = 1;
		gbcBtns.gridy = 0;
		panelBtns.add(btnRegistration, gbcBtns);

		gbcBtns.gridx = 2;
		gbcBtns.gridy = 0;
		panelBtns.add(btnCancel, gbcBtns);

		GridBagConstraints gbcMain = new GridBagConstraints();

		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelFields, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelBtns, gbcMain);

	}

	public void createRegistrationWindow() {
		JFrame frame = new JFrame("Add new user");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelRegistration panel = new PanelRegistration();
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public static String getUser() {
		return tfUsername.getText();
	}

	public void login() {
		if (tfUsername.getText().isEmpty() || String.valueOf(pfPassword.getPassword()).isEmpty()) {

			JOptionPane.showMessageDialog(null, "Insert login and password !");
		} else {

			try {

				Optional<User> op = Database.returnUser(tfUsername.getText(),
						Encrypt.encrypt(String.valueOf(pfPassword.getPassword())));

				if (op.isPresent()) {

					showMainPanel();

				} else {
					JOptionPane.showMessageDialog(null, "Login error");
				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}
	}

	public void showMainPanel() {
		JFrame frame;
		try {
			frame = new JFrame("Panel Main" + ": " + PanelLogin.getUser() + " - "
					+ Database.selectByUsername(PanelLogin.getUser()));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			PanelMain panel = new PanelMain();
			panel.setVisible(true);
			frame.setContentPane(panel);
			frame.setJMenuBar(panel.createJMenu());
			frame.setVisible(true);
			frame.setResizable(true);
			frame.pack();
			frame.setLocationRelativeTo(null);
			JFrame frameLogin = (JFrame) getRootPane().getParent();
			frameLogin.dispose();
		} catch (HeadlessException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
