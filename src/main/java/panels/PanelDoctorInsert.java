package panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Doctor;
import classes.Hospital;
import database.Database;
import models.CustomComboboxModel;

public class PanelDoctorInsert extends JPanel {

	// =============DOCTOR FIELDS==============

	private JLabel lDocotrFirstName;
	private JLabel lDoctorLastName;
	private JLabel lDocotrAge;
	private JLabel lDocotrSalary;
	private JLabel lDocotrSpecialization;

	private JTextField tfDoctorFirstName;
	private JTextField tfDoctorLastName;
	private JTextField tfDocotrAge;
	private JTextField tfDocotrSalary;
	private JTextField tfDocotrSpecialization;

	// =============HOSPITAL FIELDS==============

	private JLabel lhospitalName;
	private JLabel lhospitalCity;
	private JLabel lhospitalAddress;
	private JLabel lhospitalTelephoneNumber;

	private JTextField tfhospitalName;
	private JTextField tfhospitalCity;
	private JTextField tfhospitalAddress;
	private JTextField tfhospitalTelephoneNumber;

	// =============BUTTONS======================

	private JButton btnInsert;
	private JButton btnCancel;

	// ============comboBox============

	private JComboBox<String> cbHospitalId;
	private CustomComboboxModel<String> customComboboxModelHospital;

	PanelDoctor panelDoctor;

	public PanelDoctorInsert(PanelDoctor panelDoctor) {

		super(new GridBagLayout());
		this.panelDoctor = panelDoctor;

		// =========DOCTOR==============

		lDocotrFirstName = new JLabel("First Name: ");
		lDocotrFirstName.setFont(new Font("Arial", Font.PLAIN, 20));
		lDoctorLastName = new JLabel("Last Name: ");
		lDoctorLastName.setFont(new Font("Arial", Font.PLAIN, 20));
		lDocotrAge = new JLabel("Age: ");
		lDocotrAge.setFont(new Font("Arial", Font.PLAIN, 20));
		lDocotrSalary = new JLabel("Salary: ");
		lDocotrSalary.setFont(new Font("Arial", Font.PLAIN, 20));
		lDocotrSpecialization = new JLabel("Specialization: ");
		lDocotrSpecialization.setFont(new Font("Arial", Font.PLAIN, 20));

		tfDoctorFirstName = new JTextField(10);
		tfDoctorFirstName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorFirstName.setHorizontalAlignment(JTextField.CENTER);

		tfDoctorLastName = new JTextField(10);
		tfDoctorLastName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorLastName.setHorizontalAlignment(JTextField.CENTER);

		tfDocotrAge = new JTextField(10);
		tfDocotrAge.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDocotrAge.setHorizontalAlignment(JTextField.CENTER);

		tfDocotrSalary = new JTextField(10);
		tfDocotrSalary.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDocotrSalary.setHorizontalAlignment(JTextField.CENTER);

		tfDocotrSpecialization = new JTextField(10);
		tfDocotrSpecialization.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDocotrSpecialization.setHorizontalAlignment(JTextField.CENTER);

		// =========HOSPITAL==============

		lhospitalName = new JLabel("Hospital name: ");
		lhospitalName.setFont(new Font("Arial", Font.PLAIN, 20));
		lhospitalCity = new JLabel("Hospital city: ");
		lhospitalCity.setFont(new Font("Arial", Font.PLAIN, 20));
		lhospitalAddress = new JLabel("Hospitalt address: ");
		lhospitalAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		lhospitalTelephoneNumber = new JLabel("Hospital telephone number: ");
		lhospitalTelephoneNumber.setFont(new Font("Arial", Font.PLAIN, 20));

		tfhospitalName = new JTextField(10);
		tfhospitalName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfhospitalName.setHorizontalAlignment(JTextField.CENTER);
		tfhospitalName.setEditable(false);

		tfhospitalCity = new JTextField(10);
		tfhospitalCity.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfhospitalCity.setHorizontalAlignment(JTextField.CENTER);
		tfhospitalCity.setEditable(false);

		tfhospitalAddress = new JTextField(10);
		tfhospitalAddress.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfhospitalAddress.setHorizontalAlignment(JTextField.CENTER);
		tfhospitalAddress.setEditable(false);

		tfhospitalTelephoneNumber = new JTextField(10);
		tfhospitalTelephoneNumber.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfhospitalTelephoneNumber.setHorizontalAlignment(JTextField.CENTER);
		tfhospitalTelephoneNumber.setEditable(false);

		// ========btns==========

		btnCancel = new JButton("Cancel");

		btnInsert = new JButton("Insert");

		try {
			customComboboxModelHospital = new CustomComboboxModel<>(Database.selectHospitalId());
			cbHospitalId = new JComboBox<>(customComboboxModelHospital);
			cbHospitalId.addActionListener(e -> {

				String txt = (String) cbHospitalId.getSelectedItem();
				String[] tab = txt.split(" ");
				try {
					fillHospitalPanel(Integer.parseInt(tab[0]));
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			});
		} catch (SQLException e) {

			e.printStackTrace();
		}

		JPanel panelFieldsDoctor = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelFieldsDoctor = new GridBagConstraints();

		gbcPanelFieldsDoctor.insets = new Insets(5, 10, 5, 10);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 0;
		panelFieldsDoctor.add(lDocotrFirstName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 0;
		panelFieldsDoctor.add(tfDoctorFirstName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 1;
		panelFieldsDoctor.add(lDoctorLastName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 1;
		panelFieldsDoctor.add(tfDoctorLastName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 2;
		panelFieldsDoctor.add(lDocotrAge, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 2;
		panelFieldsDoctor.add(tfDocotrAge, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 3;
		panelFieldsDoctor.add(lDocotrSalary, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 3;
		panelFieldsDoctor.add(tfDocotrSalary, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 4;
		panelFieldsDoctor.add(lDocotrSpecialization, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 4;
		panelFieldsDoctor.add(tfDocotrSpecialization, gbcPanelFieldsDoctor);

		// ==============

		JPanel panelFieldsHospital = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanelFieldsHospital = new GridBagConstraints();

		gbcPanelFieldsHospital.insets = new Insets(10, 5, 10, 5);

		gbcPanelFieldsHospital.gridx = 0;
		gbcPanelFieldsHospital.gridy = 0;
		panelFieldsHospital.add(lhospitalName, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 1;
		gbcPanelFieldsHospital.gridy = 0;
		panelFieldsHospital.add(tfhospitalName, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 0;
		gbcPanelFieldsHospital.gridy = 1;
		panelFieldsHospital.add(lhospitalCity, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 1;
		gbcPanelFieldsHospital.gridy = 1;
		panelFieldsHospital.add(tfhospitalCity, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 0;
		gbcPanelFieldsHospital.gridy = 2;
		panelFieldsHospital.add(lhospitalAddress, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 1;
		gbcPanelFieldsHospital.gridy = 2;
		panelFieldsHospital.add(tfhospitalAddress, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 0;
		gbcPanelFieldsHospital.gridy = 3;
		panelFieldsHospital.add(lhospitalTelephoneNumber, gbcPanelFieldsHospital);

		gbcPanelFieldsHospital.gridx = 1;
		gbcPanelFieldsHospital.gridy = 3;
		panelFieldsHospital.add(tfhospitalTelephoneNumber, gbcPanelFieldsHospital);

		JPanel panelBtns = new JPanel(new GridBagLayout());
		GridBagConstraints gbcBtnsPanel = new GridBagConstraints();

		gbcBtnsPanel.gridx = 0;
		gbcBtnsPanel.gridy = 0;
		btnInsert.addActionListener(e -> {

			insertNewDoctor();

			JFrame frame = (JFrame) getRootPane().getParent();
			frame.dispose();

		});
		panelBtns.add(btnInsert, gbcBtnsPanel);

		gbcBtnsPanel.gridx = 1;
		gbcBtnsPanel.gridy = 0;
		btnCancel.addActionListener(e -> {

			JFrame frame = (JFrame) getRootPane().getParent();
			frame.dispose();
		});
		panelBtns.add(btnCancel, gbcBtnsPanel);

		GridBagConstraints gbcMain = new GridBagConstraints();

		gbcMain.anchor = GridBagConstraints.PAGE_END;

		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelFieldsDoctor, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(cbHospitalId, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 2;
		add(panelFieldsHospital, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 3;
		add(panelBtns, gbcMain);

	}

	public void fillHospitalPanel(int id) throws SQLException {

		Hospital h = Database.selectHospitalByID(id);

		tfhospitalName.setText(h.getHospitalName());
		tfhospitalCity.setText(h.getHospitalCity());
		tfhospitalAddress.setText(h.getHospitalAddress());
		tfhospitalTelephoneNumber.setText(h.getHospitalTelephoneNumber());

	}

	public void insertNewDoctor() {
		try {
			String doctorName = tfDoctorFirstName.getText();
			String doctorLastName = tfDoctorLastName.getText();
			int doctorAge = Integer.parseInt(tfDocotrAge.getText());
			double salary = Double.parseDouble(tfDocotrSalary.getText());
			String specialization = tfDocotrSpecialization.getText();

			String[] tab = ((String) cbHospitalId.getSelectedItem()).split(" ");
			Database.insertDoctor(new Doctor(0, doctorName, doctorLastName, doctorAge, salary, specialization,
					Integer.parseInt(tab[0])));
			panelDoctor.updateAfterInsert();
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
