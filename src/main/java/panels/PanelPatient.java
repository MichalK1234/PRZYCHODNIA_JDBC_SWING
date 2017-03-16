package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classes.Patient;
import database.Database;

public class PanelPatient extends JPanel implements ActionListener {

	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;

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

	private List<Patient> patientList;
	private int idx;

	public PanelPatient() throws SQLException {

		super(new GridBagLayout());

		GridBagConstraints gbcMain = new GridBagConstraints();

		String role = Database.selectByUsername(PanelLogin.getUser());

		btnLeft = new JButton("<<");
		btnLeft.addActionListener(this);
		btnRight = new JButton(">>");
		btnRight.addActionListener(this);
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		if (role.equals("Admin")) {
			btnInsert.addActionListener(e -> {
				createInsertWindow();
			});
		} else {
			btnInsert.setEnabled(false);
		}

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);

		if (role.equals("Admin")) {
			btnUpdate.addActionListener(e -> {
				createUpdateWindow();
			});
		} else {
			btnUpdate.setEnabled(false);
		}

		btnDelete = new JButton("Delete");

		if (role.equals("Admin")) {
			btnDelete.addActionListener(e -> {
				if (!tfidx.getText().isEmpty()) {
					try {
						Database.deletePatient(Integer.parseInt(tfidx.getText()));
						patientList = Database.selectPatient();
					} catch (Exception e1) {

						e1.printStackTrace();
					}

					--idx;
					if (idx < 0) {
						idx = 0;
					}
					fillPatientPanel();
				}
			});
		} else {
			btnDelete.setEnabled(false);
		}

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
		tfidx.setBackground(Color.ORANGE);
		tfidx.setFont(new Font("Arial", Font.ITALIC, 20));
		tfidx.setHorizontalAlignment(JTextField.CENTER);
		tfidx.setForeground(Color.GRAY);

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

		JPanel panelNavi = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelNavi = new GridBagConstraints();

		gbcPanelNavi.gridx = 0;
		gbcPanelNavi.gridy = 0;
		gbcPanelNavi.insets = new Insets(0, 10, 5, 0);
		panelNavi.add(btnLeft, gbcPanelNavi);

		gbcPanelNavi.gridx = 1;
		gbcPanelNavi.gridy = 0;
		panelNavi.add(tfidx, gbcPanelNavi);

		gbcPanelNavi.gridx = 2;
		gbcPanelNavi.gridy = 0;
		gbcPanelNavi.insets = new Insets(0, 10, 5, 10);
		panelNavi.add(btnRight, gbcPanelNavi);

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

		JPanel panelOperations = new JPanel();

		panelOperations.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		GridBagConstraints gbcPanelOperations = new GridBagConstraints();

		gbcPanelOperations.insets = new Insets(5, 15, 5, 15);
		gbcPanelOperations.gridx = 0;
		gbcPanelOperations.gridy = 0;
		panelOperations.add(btnInsert, gbcPanelOperations);

		gbcPanelOperations.gridx = 1;
		gbcPanelOperations.gridy = 0;
		panelOperations.add(btnUpdate, gbcPanelOperations);

		gbcPanelOperations.gridx = 2;
		gbcPanelOperations.gridy = 0;
		panelOperations.add(btnDelete, gbcPanelOperations);

		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelNavi, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelFields, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 2;
		add(panelOperations, gbcMain);

		try {
			patientList = Database.selectPatient();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		idx = 0;
		fillPatientPanel();

	}

	public void fillPatientPanel() {
		if (patientList != null && !patientList.isEmpty()) {

			tfidx.setText(String.valueOf(patientList.get(idx).getId()));
			tfFirstName.setText(String.valueOf(patientList.get(idx).getFirstName()));
			tfLastName.setText(String.valueOf(patientList.get(idx).getLastName()));
			tfAge.setText(String.valueOf(patientList.get(idx).getAge()));
			tfDisease.setText(String.valueOf(patientList.get(idx).getDisease()));
			tfEmailAddress.setText(String.valueOf(patientList.get(idx).getEmailAddress()));
			tfMailAddress.setText(String.valueOf(patientList.get(idx).getMailAddress()));
			tfAddress.setText(String.valueOf(patientList.get(idx).getAddress()));
			tfTelephoneNumber.setText(String.valueOf(patientList.get(idx).getTelephoneNumber()));
		} else {

			tfidx.setText("");
			tfFirstName.setText("");
			tfLastName.setText("");
			tfAge.setText("");
			tfDisease.setText("");
			tfEmailAddress.setText("");
			tfMailAddress.setText("");
			tfAddress.setText("");
			tfTelephoneNumber.setText("");
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (btnLeft == e.getSource()) {
			idx--;
			if (idx < 0) {
				idx = patientList.size() - 1;

			}
			fillPatientPanel();
		} else if (btnRight == e.getSource()) {
			idx++;
			if (idx >= patientList.size()) {

				idx = 0;

			}
			fillPatientPanel();
		}

	}

	public Patient getCurrentPatient() {

		if (tfidx.getText().isEmpty() || tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty()
				|| String.valueOf(tfAge.getText()).isEmpty() || tfDisease.getText().isEmpty()
				|| tfEmailAddress.getText().isEmpty() || tfMailAddress.getText().isEmpty()
				|| tfAddress.getText().isEmpty() || tfTelephoneNumber.getText().isEmpty()) {

			return null;

		}

		return new Patient(Integer.parseInt(tfidx.getText()), tfFirstName.getText(), tfLastName.getText(),
				Integer.parseInt(tfAge.getText()), tfDisease.getText(), tfEmailAddress.getText(),
				tfMailAddress.getText(), tfAddress.getText(), tfTelephoneNumber.getText()

		);
	};

	public void createUpdateWindow() {
		if (getCurrentPatient() == null) {
			JOptionPane.showMessageDialog(null, "THERE IS NO ELEMENT TO UPDATE");
			return;
		}

		JFrame frame = new JFrame("Update existing patient");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelPatientUpdate panel = new PanelPatientUpdate(this);
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public void updateAfterUpdate() {
		try {
			patientList = Database.selectPatient();
			fillPatientPanel();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void createInsertWindow() {
		JFrame frame = new JFrame("Insert new patient");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelPatientInsert panel = new PanelPatientInsert(this);
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public void updateAfterInsert() {
		try {
			patientList = Database.selectPatient();
			idx = patientList.size() - 1;
			fillPatientPanel();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
