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
import classes.DoctorWithHospital;
import classes.Hospital;
import database.Database;
import models.CustomComboboxModel;

public class PanelDoctorUpdate extends JPanel {

	// =============DOCTOR FIELDS==============

	private JLabel lDoctorId;
	private JLabel lDocotrFirstName;
	private JLabel lDoctorLastName;
	private JLabel lDoctorAge;
	private JLabel lDoctorSalary;
	private JLabel lDoctorSpecialization;

	private JTextField tfDoctorId;
	private JTextField tfDoctorFirstName;
	private JTextField tfDoctorLastName;
	private JTextField tfDoctorAge;
	private JTextField tfDoctorSalary;
	private JTextField tfDoctorSpecialization;

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

	private JButton btnUpdate;
	private JButton btnCancel;

	// ============comboBox============

	private JComboBox<String> cbHospitalId;
	private CustomComboboxModel<String> customComboboxModelHospital;

	private PanelDoctor panelDoctor;

	public PanelDoctorUpdate(PanelDoctor panelDoctor) {

		super(new GridBagLayout());
		this.panelDoctor = panelDoctor;
		// =========DOCTOR==============

		lDoctorId = new JLabel("Id: ");
		lDoctorId.setFont(new Font("Arial", Font.PLAIN, 20));

		lDocotrFirstName = new JLabel("First Name: ");
		lDocotrFirstName.setFont(new Font("Arial", Font.PLAIN, 20));
		lDoctorLastName = new JLabel("Last Name: ");
		lDoctorLastName.setFont(new Font("Arial", Font.PLAIN, 20));
		lDoctorAge = new JLabel("Age: ");
		lDoctorAge.setFont(new Font("Arial", Font.PLAIN, 20));
		lDoctorSalary = new JLabel("Salary: ");
		lDoctorSalary.setFont(new Font("Arial", Font.PLAIN, 20));
		lDoctorSpecialization = new JLabel("Specialization: ");
		lDoctorSpecialization.setFont(new Font("Arial", Font.PLAIN, 20));

		tfDoctorId = new JTextField(10);
		tfDoctorId.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorId.setHorizontalAlignment(JTextField.CENTER);
		tfDoctorId.setEditable(false);

		tfDoctorFirstName = new JTextField(10);
		tfDoctorFirstName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorFirstName.setHorizontalAlignment(JTextField.CENTER);

		tfDoctorLastName = new JTextField(10);
		tfDoctorLastName.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorLastName.setHorizontalAlignment(JTextField.CENTER);

		tfDoctorAge = new JTextField(10);
		tfDoctorAge.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorAge.setHorizontalAlignment(JTextField.CENTER);

		tfDoctorSalary = new JTextField(10);
		tfDoctorSalary.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorSalary.setHorizontalAlignment(JTextField.CENTER);

		tfDoctorSpecialization = new JTextField(10);
		tfDoctorSpecialization.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDoctorSpecialization.setHorizontalAlignment(JTextField.CENTER);

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
		btnCancel.addActionListener(e -> {

			JFrame frame = (JFrame) getRootPane().getParent();
			frame.dispose();
		});

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(e -> {

			try {

				Database.updateDoctor(new Doctor(Integer.parseInt(tfDoctorId.getText()), tfDoctorFirstName.getText(),
						tfDoctorLastName.getText(), Integer.parseInt(tfDoctorAge.getText()),
						Double.parseDouble(tfDoctorSalary.getText()), tfDoctorSpecialization.getText(),
						Integer.parseInt(((String) cbHospitalId.getSelectedItem()).split(" ")[0])));

				panelDoctor.updateAfterUpdate();

				JFrame frame = (JFrame) getRootPane().getParent();
				frame.dispose();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		});

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
		panelFieldsDoctor.add(lDoctorId, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 0;
		panelFieldsDoctor.add(tfDoctorId, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 1;
		panelFieldsDoctor.add(lDocotrFirstName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 1;
		panelFieldsDoctor.add(tfDoctorFirstName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 2;
		panelFieldsDoctor.add(lDoctorLastName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 2;
		panelFieldsDoctor.add(tfDoctorLastName, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 3;
		panelFieldsDoctor.add(lDoctorAge, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 3;
		panelFieldsDoctor.add(tfDoctorAge, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 4;
		panelFieldsDoctor.add(lDoctorSalary, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 4;
		panelFieldsDoctor.add(tfDoctorSalary, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 0;
		gbcPanelFieldsDoctor.gridy = 5;
		panelFieldsDoctor.add(lDoctorSpecialization, gbcPanelFieldsDoctor);

		gbcPanelFieldsDoctor.gridx = 1;
		gbcPanelFieldsDoctor.gridy = 5;
		panelFieldsDoctor.add(tfDoctorSpecialization, gbcPanelFieldsDoctor);

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
		panelBtns.add(btnUpdate, gbcBtnsPanel);

		gbcBtnsPanel.gridx = 1;
		gbcBtnsPanel.gridy = 0;
		panelBtns.add(btnCancel, gbcBtnsPanel);

		GridBagConstraints gbcMain = new GridBagConstraints();

		gbcMain.anchor = GridBagConstraints.CENTER;

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

		fillAllDoctorAndHospitalFields(panelDoctor.getCurrentDoctor());
	}

	public void fillHospitalPanel(int id) throws SQLException {

		Hospital h = Database.selectHospitalByID(id);

		tfhospitalName.setText(h.getHospitalName());
		tfhospitalCity.setText(h.getHospitalCity());
		tfhospitalAddress.setText(h.getHospitalAddress());
		tfhospitalTelephoneNumber.setText(h.getHospitalTelephoneNumber());

	}

	public void fillAllDoctorAndHospitalFields(DoctorWithHospital dwh) {
		try {
			tfDoctorId.setText(String.valueOf(dwh.getId()));
			tfDoctorFirstName.setText(dwh.getFirstName());
			tfDoctorLastName.setText(dwh.getLastName());
			tfDoctorAge.setText(String.valueOf(dwh.getAge()));
			tfDoctorSalary.setText(String.valueOf(dwh.getSalary()));
			tfDoctorSpecialization.setText(dwh.getSpecialization());

			Integer id = Database.selectHospitalIdByOtherHospitalData(dwh.getHospitalName(), dwh.getHospitalCity(),
					dwh.getHospitalAddress(), dwh.getHospitalTelephoneNumber());

			cbHospitalId.setSelectedItem(id + " " + dwh.getHospitalName());
			fillHospitalPanel(id);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateNewDoctor() {
		try {
			String doctorName = tfDoctorFirstName.getText();
			String doctorLastName = tfDoctorLastName.getText();
			int doctorAge = Integer.parseInt(tfDoctorAge.getText());
			double salary = Double.parseDouble(tfDoctorSalary.getText());
			String specialization = tfDoctorSpecialization.getText();

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