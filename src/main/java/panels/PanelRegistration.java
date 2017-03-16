package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import models.CustomComboboxModel;

public class PanelRegistration extends JPanel {

	private JLabel lFirstName;
	private JLabel lLastName;
	private JLabel lAge;
	private JLabel lUsername;
	private JLabel lPassword;
	private JLabel lPasswordConfirmation;
	private JLabel lRole;

	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfAge;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JPasswordField pfPasswordConfirmation;

	private JComboBox<String> cbRole;
	private CustomComboboxModel<String> customModel;

	private JButton btnAdd;
	private JButton btnCancel;

	public PanelRegistration() {
		super(new GridBagLayout());

		lFirstName = new JLabel("First name : ");
		lLastName = new JLabel("Last name : ");
		lAge = new JLabel("Age : ");
		lUsername = new JLabel("Username : ");
		lPassword = new JLabel("Password : ");
		lPasswordConfirmation = new JLabel("Confirm password : ");
		lRole = new JLabel("Role : ");

		tfFirstName = new JTextField(10);
		tfLastName = new JTextField(10);
		tfAge = new JTextField(10);
		tfUsername = new JTextField(10);
		pfPassword = new JPasswordField(10);
		pfPasswordConfirmation = new JPasswordField(10);

		List<String> role = new ArrayList<>();
		role.addAll(Arrays.asList("User", "Admin"));
		customModel = new CustomComboboxModel<>(role);
		cbRole = new JComboBox<>(customModel);

		btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(e -> {

			JFrame frame = (JFrame) getRootPane().getParent();
			frame.dispose();
		});

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> {

			try {
				addUser();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		});

		JPanel panelFields = new JPanel(new GridBagLayout());
		panelFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true),
				"Add user", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 15),
				Color.BLACK));

		GridBagConstraints gbcFields = new GridBagConstraints();

		gbcFields.insets = new Insets(5, 5, 5, 5);

		gbcFields.gridx = 0;
		gbcFields.gridy = 0;
		panelFields.add(lFirstName, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 0;
		panelFields.add(tfFirstName, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 1;
		panelFields.add(lLastName, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 1;
		panelFields.add(tfLastName, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 2;
		panelFields.add(lAge, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 2;
		panelFields.add(tfAge, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 3;
		panelFields.add(lUsername, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 3;
		panelFields.add(tfUsername, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 4;
		panelFields.add(lPassword, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 4;
		panelFields.add(pfPassword, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 5;
		panelFields.add(lPasswordConfirmation, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 5;
		panelFields.add(pfPasswordConfirmation, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 6;
		panelFields.add(lRole, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 6;
		panelFields.add(cbRole, gbcFields);

		JPanel panelBtns = new JPanel(new GridBagLayout());

		GridBagConstraints gbcBtns = new GridBagConstraints();

		gbcBtns.insets = new Insets(5, 10, 5, 10);

		gbcBtns.gridx = 0;
		gbcBtns.gridy = 0;
		panelBtns.add(btnAdd, gbcBtns);

		gbcBtns.gridx = 1;
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

	public void addUser() throws SQLException {

		if (String.valueOf(pfPassword.getPassword()).isEmpty() || tfFirstName.getText().isEmpty()
				|| tfLastName.getText().isEmpty() || tfAge.getText().isEmpty() || tfUsername.getText().isEmpty()
				|| String.valueOf(pfPassword.getPassword()).isEmpty()

		) {

			JOptionPane.showMessageDialog(null, "There is no elements to add");

		} else {

			username();

		}

	}

	public void username() throws SQLException {

		if (Database.checkUsername(tfUsername.getText())) {

			tfUsername.setText(
					JOptionPane.showInputDialog("This username already exists, please insert another username."));

		} else {

			password();

		}
	}

	public void password() {

		if (String.valueOf(pfPassword.getPassword()).equals(String.valueOf(pfPasswordConfirmation.getPassword()))) {

			try {
				String firstName = tfFirstName.getText();
				String lastName = tfLastName.getText();
				int age = Integer.parseInt(tfAge.getText());
				String username = tfUsername.getText();
				String password = String.valueOf(pfPassword.getPassword());
				String role = String.valueOf(cbRole.getSelectedItem());

				Database.insertUser(new User(1, firstName, lastName, age, username,
						Encrypt.encrypt(String.valueOf(pfPassword.getPassword())), role,
						Encrypt.getNumbersToDecrypt()));

				JOptionPane.showMessageDialog(null, "User added");
				JFrame frame = (JFrame) getRootPane().getParent();
				frame.dispose();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		} else {

			JOptionPane.showMessageDialog(null, "Password is incorrect");

		}
	}

}
