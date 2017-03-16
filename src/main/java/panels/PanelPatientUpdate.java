package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classes.Patient;
import database.Database;

public class PanelPatientUpdate extends JPanel {

	private JLabel lFirstName;
	private JLabel lLastName;
	private JLabel lAge;
	private JLabel lDisease;
	private JLabel lEmailAddress;
	private JLabel lMailAddress;
	private JLabel lAddress;
	private JLabel lTelephoneNumber;

	private JTextField tfidx;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfAge;
	private JTextField tfDisease;
	private JTextField tfEmailAddress;
	private JTextField tfMailAddress;
	private JTextField tfAddress;
	private JTextField tfTelephoneNumber;

	private JButton btnUpdate;
	private JButton btnCancel;

	private PanelPatient panelPatient;

	public PanelPatientUpdate(PanelPatient panelPatient) {
		super(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();

		this.panelPatient = panelPatient;

		lFirstName = new JLabel("First Name");
		lFirstName.setFont(new Font("Arial", Font.PLAIN, 20));
		lLastName = new JLabel("Last Name");
		lLastName.setFont(new Font("Arial", Font.PLAIN, 20));
		lAge = new JLabel("Age");
		lAge.setFont(new Font("Arial", Font.PLAIN, 20));
		lDisease = new JLabel("Disease");
		lDisease.setFont(new Font("Arial", Font.PLAIN, 20));
		lEmailAddress = new JLabel("Email Address");
		lEmailAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		lMailAddress = new JLabel("Mail Address");
		lMailAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		lAddress = new JLabel("Address");
		lAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		lTelephoneNumber = new JLabel("Telephone Number");
		lTelephoneNumber.setFont(new Font("Arial", Font.PLAIN, 20));

		tfidx = new JTextField(4);
		tfidx.setFont(new Font("Arial", Font.ITALIC, 20));
		tfidx.setHorizontalAlignment(JTextField.CENTER);
		tfidx.setEditable(false);

		tfFirstName = new JTextField(20);
		tfFirstName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfFirstName.setHorizontalAlignment(JTextField.CENTER);

		tfLastName = new JTextField(20);
		tfLastName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfLastName.setHorizontalAlignment(JTextField.CENTER);

		tfAge = new JTextField(20);
		tfAge.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAge.setHorizontalAlignment(JTextField.CENTER);

		tfDisease = new JTextField(20);
		tfDisease.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDisease.setHorizontalAlignment(JTextField.CENTER);

		tfEmailAddress = new JTextField(20);
		tfEmailAddress.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfEmailAddress.setHorizontalAlignment(JTextField.CENTER);

		tfMailAddress = new JTextField(20);
		tfMailAddress.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfMailAddress.setHorizontalAlignment(JTextField.CENTER);

		tfAddress = new JTextField(20);
		tfAddress.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAddress.setHorizontalAlignment(JTextField.CENTER);

		tfTelephoneNumber = new JTextField(20);
		tfTelephoneNumber.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfTelephoneNumber.setHorizontalAlignment(JTextField.CENTER);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {

			JFrame frame = (JFrame) getRootPane().getParent();
			frame.dispose();

		});

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(e -> {

			try {
				Database.updatePatient(
						new Patient(Integer.parseInt(tfidx.getText()), tfFirstName.getText(), tfLastName.getText(),
								Integer.parseInt(tfAge.getText()), tfDisease.getText(), tfEmailAddress.getText(),
								tfMailAddress.getText(), tfAddress.getText(), tfTelephoneNumber.getText()));
				panelPatient.updateAfterUpdate();

				JFrame frame = (JFrame) getRootPane().getParent();
				frame.dispose();

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		});

		JPanel panelFields = new JPanel(new GridBagLayout());

		panelFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
				"Patient's data ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("TimesNewRoman", Font.BOLD, 26), Color.BLUE));
		GridBagConstraints gbcPanelFields = new GridBagConstraints();

		gbcPanelFields.insets = new Insets(10, 10, 10, 15);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 1;

		panelFields.add(lFirstName, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 1;
		panelFields.add(tfFirstName, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 2;
		panelFields.add(lLastName, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 2;
		panelFields.add(tfLastName, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 3;
		panelFields.add(lAge, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 3;
		panelFields.add(tfAge, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 4;
		panelFields.add(lDisease, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 4;
		panelFields.add(tfDisease, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 5;
		panelFields.add(lEmailAddress, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 5;
		panelFields.add(tfEmailAddress, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 6;
		panelFields.add(lMailAddress, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 6;
		panelFields.add(tfMailAddress, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 7;
		panelFields.add(lAddress, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 7;
		panelFields.add(tfAddress, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 8;
		panelFields.add(lTelephoneNumber, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 8;
		panelFields.add(tfTelephoneNumber, gbcPanelFields);

		JPanel panelButtons = new JPanel();

		panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		GridBagConstraints gbcPanelOperations = new GridBagConstraints();

		gbcPanelOperations.insets = new Insets(5, 15, 5, 15);
		gbcPanelOperations.gridx = 0;
		gbcPanelOperations.gridy = 0;
		panelButtons.add(btnUpdate, gbcPanelOperations);

		gbcPanelOperations.gridx = 1;
		gbcPanelOperations.gridy = 0;
		panelButtons.add(btnCancel, gbcPanelOperations);

		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelFields, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelButtons, gbcMain);

		fillPatientFileds(panelPatient.getCurrentPatient());

	}

	public void fillPatientFileds(Patient p) {

		tfidx.setText(String.valueOf(p.getId()));
		tfFirstName.setText(p.getFirstName());
		tfLastName.setText(p.getLastName());
		tfAge.setText(String.valueOf(p.getAge()));
		tfDisease.setText(p.getDisease());
		tfEmailAddress.setText(p.getEmailAddress());
		tfMailAddress.setText(p.getMailAddress());
		tfAddress.setText(p.getAddress());
		tfTelephoneNumber.setText(p.getTelephoneNumber());

	}

	public void updateNewPatient() {

		try {

			String firstName = tfFirstName.getText();
			String lastName = tfLastName.getText();
			int age = Integer.parseInt(tfAge.getText());
			String disease = tfDisease.getText();
			String emailAddress = tfEmailAddress.getText();
			String mailAddress = tfMailAddress.getText();
			String address = tfAddress.getText();
			String telephoneNumber = tfTelephoneNumber.getText();

			Database.insertPatient(new Patient(0, firstName, lastName, age, disease, emailAddress, mailAddress, address,
					telephoneNumber));
			panelPatient.updateAfterUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
