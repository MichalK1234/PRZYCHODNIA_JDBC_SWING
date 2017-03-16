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

import classes.DoctorWithHospital;
import database.Database;

public class PanelDoctor extends JPanel implements ActionListener {

	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;

	private JLabel lFirstName;
	private JLabel lLastName;
	private JLabel lAge;
	private JLabel lSalary;
	private JLabel lSpecialization;
	private JLabel lHospitalName;
	private JLabel lHospitalCity;
	private JLabel lHospitalAddress;
	private JLabel lHospitalTelephoneNumber;

	private JTextField tfId;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfAge;
	private JTextField tfSalary;
	private JTextField tfSpecialization;
	private JTextField tfHospitalName;
	private JTextField tfHospitalCity;
	private JTextField tfHospitalAddress;
	private JTextField tfHospitalTelephoneNumber;

	private List<DoctorWithHospital> doctorList;
	private int idx;

	public PanelDoctor() throws SQLException {
		super(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();

		String role = Database.selectByUsername(PanelLogin.getUser());

		btnLeft = new JButton("<<");
		btnLeft.addActionListener(this);
		btnRight = new JButton(">>");
		btnRight.addActionListener(this);
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);

		if (role.equals("Admin")) {
			btnDelete.addActionListener(e -> {
				if (!tfId.getText().isEmpty()) {
					try {
						Database.deleteDoctor(Integer.parseInt(tfId.getText()));

						try {
							doctorList = Database.selectInnerJoin();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

						--idx;

						if (idx < 0) {
							idx = 0;
						}

						fillDoctorPanel();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
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
		lSalary = new JLabel("Salary");
		lSalary.setFont(new Font("Arial", Font.PLAIN, 20));
		lSpecialization = new JLabel("Specialization");
		lSpecialization.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalName = new JLabel("Hospital Name");
		lHospitalName.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalCity = new JLabel("Hospital City");
		lHospitalCity.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalAddress = new JLabel("Hospital Adress");
		lHospitalAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalTelephoneNumber = new JLabel("Hospital Telephone Number");
		lHospitalTelephoneNumber.setFont(new Font("Arial", Font.PLAIN, 20));

		tfId = new JTextField(4);
		tfId.setFont(new Font("Arial", Font.ITALIC, 31));
		tfId.setBackground(Color.GREEN);
		tfId.setHorizontalAlignment(JTextField.CENTER);
		tfId.setFont(new Font("Courier New", Font.ITALIC, 17));
		tfId.setEditable(false);

		tfFirstName = new JTextField(20);
		tfFirstName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfFirstName.setHorizontalAlignment(JTextField.CENTER);
		tfFirstName.setEditable(false);

		tfLastName = new JTextField(20);
		tfLastName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfLastName.setHorizontalAlignment(JTextField.CENTER);
		tfLastName.setEditable(false);

		tfAge = new JTextField(20);
		tfAge.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAge.setHorizontalAlignment(JTextField.CENTER);
		tfAge.setEditable(false);

		tfSalary = new JTextField(20);
		tfSalary.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfSalary.setHorizontalAlignment(JTextField.CENTER);
		tfSalary.setEditable(false);

		tfSpecialization = new JTextField(20);
		tfSpecialization.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfSpecialization.setHorizontalAlignment(JTextField.CENTER);
		tfSpecialization.setEditable(false);

		tfHospitalName = new JTextField(20);
		tfHospitalName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalName.setHorizontalAlignment(JTextField.CENTER);
		tfHospitalName.setEditable(false);

		tfHospitalCity = new JTextField(20);
		tfHospitalCity.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalCity.setHorizontalAlignment(JTextField.CENTER);
		tfHospitalCity.setEditable(false);

		tfHospitalAddress = new JTextField(20);
		tfHospitalAddress.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalAddress.setHorizontalAlignment(JTextField.LEFT);
		tfHospitalAddress.setEditable(false);

		tfHospitalTelephoneNumber = new JTextField(20);
		tfHospitalTelephoneNumber.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalTelephoneNumber.setHorizontalAlignment(JTextField.CENTER);
		tfHospitalTelephoneNumber.setEditable(false);

		// ------------------------------ NAVIGATION PANEL
		// -----------------------------------
		JPanel panelNavigation = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelNavigation = new GridBagConstraints();

		gbcPanelNavigation.gridx = 0;
		gbcPanelNavigation.gridy = 0;
		gbcPanelNavigation.insets = new Insets(10, 0, 15, 0);
		gbcPanelNavigation.ipadx = 20;
		panelNavigation.add(btnLeft, gbcPanelNavigation);

		gbcPanelNavigation.gridx = 1;
		gbcPanelNavigation.gridy = 0;
		gbcPanelNavigation.insets = new Insets(0, 15, 5, 15);
		gbcPanelNavigation.ipadx = 0;
		panelNavigation.add(tfId, gbcPanelNavigation);

		gbcPanelNavigation.gridx = 2;
		gbcPanelNavigation.gridy = 0;
		gbcPanelNavigation.insets = new Insets(0, 0, 5, 0);
		gbcPanelNavigation.ipadx = 20;
		panelNavigation.add(btnRight, gbcPanelNavigation);
		// ----------------------------------------------------------------------------------

		// -------------------------------- FIELDS PANEL
		// ------------------------------------
		JPanel panelFields = new JPanel(new GridBagLayout());
		panelFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true),
				"Doctor's data", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 26),
				Color.PINK));
		GridBagConstraints gbcPanelFields = new GridBagConstraints();

		gbcPanelFields.insets = new Insets(5, 10, 5, 15);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 0;
		panelFields.add(lFirstName, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 0;
		panelFields.add(tfFirstName, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 1;
		panelFields.add(lLastName, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 1;
		panelFields.add(tfLastName, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 2;
		panelFields.add(lAge, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 2;
		panelFields.add(tfAge, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 3;
		panelFields.add(lSalary, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 3;
		panelFields.add(tfSalary, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 4;
		panelFields.add(lSpecialization, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 4;
		panelFields.add(tfSpecialization, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 5;
		panelFields.add(lHospitalName, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 5;
		panelFields.add(tfHospitalName, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 6;
		panelFields.add(lHospitalCity, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 6;
		panelFields.add(tfHospitalCity, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 7;
		panelFields.add(lHospitalAddress, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 7;
		panelFields.add(tfHospitalAddress, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 8;
		panelFields.add(lHospitalAddress, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 8;
		panelFields.add(tfHospitalAddress, gbcPanelFields);

		gbcPanelFields.gridx = 0;
		gbcPanelFields.gridy = 9;
		panelFields.add(lHospitalTelephoneNumber, gbcPanelFields);

		gbcPanelFields.gridx = 1;
		gbcPanelFields.gridy = 9;
		panelFields.add(tfHospitalTelephoneNumber, gbcPanelFields);

		// ----------------------------------------------------------------------------------
		JPanel panelOperations = new JPanel();
		panelOperations.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		GridBagConstraints gbcPanelOperations = new GridBagConstraints();

		gbcPanelOperations.insets = new Insets(5, 10, 5, 15);

		gbcPanelOperations.gridx = 0;
		gbcPanelOperations.gridy = 0;

		if (role.equals("Admin")) {
			btnInsert.addActionListener(e -> {
				createInsertWindow();
			});
		} else {
			btnInsert.setEnabled(false);
		}
		panelOperations.add(btnInsert, gbcPanelOperations);

		gbcPanelOperations.gridx = 1;
		gbcPanelOperations.gridy = 0;
		if (role.equals("Admin")) {
			btnUpdate.addActionListener(e -> {
				createUpdateWindow();
			});
		} else {
			btnUpdate.setEnabled(false);
		}

		panelOperations.add(btnUpdate, gbcPanelOperations);

		gbcPanelOperations.gridx = 2;
		gbcPanelOperations.gridy = 0;
		panelOperations.add(btnDelete, gbcPanelOperations);

		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelNavigation, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelFields, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 2;
		add(panelOperations, gbcMain);

		try {
			doctorList = Database.selectInnerJoin();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		idx = 0;
		fillDoctorPanel();

	}

	public void updateAfterInsert() {
		try {
			doctorList = Database.selectInnerJoin();
			idx = doctorList.size() - 1;
			fillDoctorPanel();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void updateAfterUpdate() {
		try {

			doctorList = Database.selectInnerJoin();
			fillDoctorPanel();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void fillDoctorPanel() {
		if (!doctorList.isEmpty()) {
			tfId.setText(String.valueOf(doctorList.get(idx).getId()));
			tfFirstName.setText(doctorList.get(idx).getFirstName());
			tfLastName.setText(doctorList.get(idx).getLastName());
			tfAge.setText(String.valueOf(doctorList.get(idx).getAge()));
			tfSalary.setText(String.valueOf(doctorList.get(idx).getSalary()));
			tfSpecialization.setText(doctorList.get(idx).getSpecialization());
			tfHospitalName.setText(doctorList.get(idx).getHospitalName());
			tfHospitalCity.setText(doctorList.get(idx).getHospitalCity());
			tfHospitalAddress.setText(doctorList.get(idx).getHospitalAddress());
			tfHospitalTelephoneNumber.setText(doctorList.get(idx).getHospitalTelephoneNumber());
		} else {
			tfId.setText(String.valueOf(""));
			tfFirstName.setText("");
			tfLastName.setText("");
			tfAge.setText(String.valueOf(""));
			tfSalary.setText(String.valueOf(""));
			tfSpecialization.setText("");
			tfHospitalName.setText("");
			tfHospitalCity.setText("");
			tfHospitalAddress.setText("");
			tfHospitalTelephoneNumber.setText("");
		}
	}

	public void createInsertWindow() {
		JFrame frame = new JFrame("Insert new doctor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelDoctorInsert panel = new PanelDoctorInsert(this);
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public void createUpdateWindow() {
		if (getCurrentDoctor() == null) {
			JOptionPane.showMessageDialog(null, "THERE IS NO ELEMENT TO UPDATE!");
			return;
		}

		JFrame frame = new JFrame("Update existing doctor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelDoctorUpdate panel = new PanelDoctorUpdate(this);
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (btnLeft == e.getSource()) {
			idx--;
			if (idx < 0) {
				idx = doctorList.size() - 1;
			}
			fillDoctorPanel();
		} else if (btnRight == e.getSource()) {
			idx++;
			if (idx >= doctorList.size()) {
				idx = 0;
			}
			fillDoctorPanel();
		}

	}

	public DoctorWithHospital getCurrentDoctor() {
		try {
			if (tfId.getText().isEmpty() || tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty()
					|| tfAge.getText().isEmpty() || tfSalary.getText().isEmpty() || tfSpecialization.getText().isEmpty()
					|| tfHospitalName.getText().isEmpty() || tfHospitalCity.getText().isEmpty()
					|| tfHospitalAddress.getText().isEmpty() || tfHospitalTelephoneNumber.getText().isEmpty()) {
				return null;
			}
			return new DoctorWithHospital(Integer.parseInt(tfId.getText()), tfFirstName.getText(), tfLastName.getText(),
					Integer.parseInt(tfAge.getText()), Double.parseDouble(tfSalary.getText()),
					tfSpecialization.getText(), tfHospitalName.getText(), tfHospitalCity.getText(),
					tfHospitalAddress.getText(), tfHospitalTelephoneNumber.getText());

		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
		return null;
	}
}
