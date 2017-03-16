package panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.DoctorWithHospital;
import classes.Patient;
import classes.PatientHospitalDoctor;
import classes.Register;
import database.Database;
import models.CustomComboboxModel;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PanelRegister extends JPanel {

	private JComboBox<String> cbDoctor;
	private CustomComboboxModel<String> customComboboxModelDoctor;

	private JComboBox<String> cbPatient;
	private CustomComboboxModel<String> customComboboxModelPatient;

	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnInsert;
	private JButton btnDelete;
	private JTextField tfId;

	private List<PatientHospitalDoctor> list;
	private int idx;

	// -------------------------------------DoctorUp-------------------------------------

	private JLabel lFirstNameDoctorUp;
	private JLabel lLastNameDoctorUp;
	private JLabel lAgeDoctorUp;
	private JLabel lSalaryDoctorUp;
	private JLabel lSpecializationDoctorUp;
	private JLabel lHospitalNameDoctorUp;
	private JLabel lHospitalCityDoctorUp;
	private JLabel lHospitalAddressDoctorUp;
	private JLabel lHospitalTelephoneNumberDoctorUp;

	private JTextField tfFirstNameDoctorUp;
	private JTextField tfLastNameDoctorUp;
	private JTextField tfAgeDoctorUp;
	private JTextField tfSalaryDoctorUp;
	private JTextField tfSpecializationDoctorUp;
	private JTextField tfHospitalNameDoctorUp;
	private JTextField tfHospitalCityDoctorUp;
	private JTextField tfHospitalAddressDoctorUp;
	private JTextField tfHospitalTelephoneNumberDoctorUp;

	// --------------------------DoctorDown------------------------------------------------

	private JLabel lIdDoctorDown;
	private JLabel lFirstNameDoctorDown;
	private JLabel lLastNameDoctorDown;
	private JLabel lAgeDoctorDown;
	private JLabel lSalaryDoctorDown;
	private JLabel lSpecializationDoctorDown;
	private JLabel lHospitalNameDoctorDown;
	private JLabel lHospitalCityDoctorDown;
	private JLabel lHospitalAddressDoctorDown;
	private JLabel lHospitalTelephoneNumberDoctorDown;

	private JTextField tfFirstNameDoctorDown;
	private JTextField tfLastNameDoctorDown;
	private JTextField tfAgeDoctorDown;
	private JTextField tfSalaryDoctorDown;
	private JTextField tfSpecializationDoctorDown;
	private JTextField tfHospitalNameDoctorDown;
	private JTextField tfHospitalCityDoctorDown;
	private JTextField tfHospitalAddressDoctorDown;
	private JTextField tfHospitalTelephoneNumberDoctorDown;

	// -------------------------PatientUp-------------------------------------------------------

	private JLabel lFirstNamePatientUp;
	private JLabel lLastNamePatientUp;
	private JLabel lAgePatientUp;
	private JLabel lDiseasePatientUp;
	private JLabel lEmailAddressPatientUp;
	private JLabel lMailAddressPatientUp;
	private JLabel lAddressPatientUp;
	private JLabel lTelephoneNumberPatientUp;

	private JTextField tfFirstNamePatientUp;
	private JTextField tfLastNamePatientUp;
	private JTextField tfAgePatientUp;
	private JTextField tfDiseasePatientUp;
	private JTextField tfEmailAddressPatientUp;
	private JTextField tfMailAddressPatientUp;
	private JTextField tfAddressPatientUp;
	private JTextField tfTelephoneNumberPatientUp;

	// ---------------------------------------------------------------------------------------------

	// ------------------------------------PatientDown----------------------------------------------

	private JLabel lIdPatientDown;
	private JLabel lFirstNamePatientDown;
	private JLabel lLastNamePatientDown;
	private JLabel lAgePatientDown;
	private JLabel lDiseasePatientDown;
	private JLabel lEmailAddressPatientDown;
	private JLabel lMailAddressPatientDown;
	private JLabel lAddressPatientDown;
	private JLabel lTelephoneNumberPatientDown;

	private JTextField tfFirstNamePatientDown;
	private JTextField tfLastNamePatientDown;
	private JTextField tfAgePatientDown;
	private JTextField tfDiseasePatientDown;
	private JTextField tfEmailAddressPatientDown;
	private JTextField tfMailAddressPatientDown;
	private JTextField tfAddressPatientDown;
	private JTextField tfTelephoneNumberPatientDown;

	// ---------------------------------------------------------------------------------------------------

	private List<DoctorWithHospital> doctorList;
	private int idxDWH;

	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePicker datePicker;
	private JComboBox cbTime;
	private CustomComboboxModel<LocalTime> customTimeModel;

	private JLabel lVisitLabel;

	public PanelRegister() throws SQLException {
		super(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		String role = Database.selectByUsername(PanelLogin.getUser());

		btnLeft = new JButton("<<");
		btnLeft.addActionListener(e -> {
			leftBtnMethod();
			try {
				fillDoctorAndPatient();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		});
		btnRight = new JButton(">>");
		btnRight.addActionListener(e -> {
			rightBtnMethod();
			try {
				fillDoctorAndPatient();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnInsert = new JButton("INSERT");

		if (role.equals("Admin")) {

			btnInsert.addActionListener(e -> {
				insertDoctorPatientMethod();
			});

		} else {
			btnInsert.setEnabled(false);
		}

		btnDelete = new JButton("DELETE");

		if (role.equals("Admin")) {

			btnDelete.addActionListener(e -> {

				deleteDoctorPatientMethod();

			});

		} else {
			btnDelete.setEnabled(false);
		}

		// -------------------label DoctorUp--------------------------

		lFirstNameDoctorUp = new JLabel("First Name");
		lFirstNameDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lLastNameDoctorUp = new JLabel("Last Name");
		lLastNameDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lAgeDoctorUp = new JLabel("Age");
		lAgeDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lSalaryDoctorUp = new JLabel("Salary");
		lSalaryDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lSpecializationDoctorUp = new JLabel("Specialization");
		lSpecializationDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalNameDoctorUp = new JLabel("Hospital Name");
		lHospitalNameDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalCityDoctorUp = new JLabel("Hospital City");
		lHospitalCityDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalAddressDoctorUp = new JLabel("Hospital Adress");
		lHospitalAddressDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalTelephoneNumberDoctorUp = new JLabel("Hospital Telephone Number");
		lHospitalTelephoneNumberDoctorUp.setFont(new Font("Arial", Font.PLAIN, 20));

		// -----------------TextField DoctorUp

		tfId = new JTextField(4);
		tfId.setFont(new Font("Arial", Font.ITALIC, 31));
		tfId.setHorizontalAlignment(JTextField.CENTER);
		tfId.setFont(new Font("Courier New", Font.ITALIC, 17));

		tfFirstNameDoctorUp = new JTextField(20);
		tfFirstNameDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfFirstNameDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfLastNameDoctorUp = new JTextField(20);
		tfLastNameDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfLastNameDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfAgeDoctorUp = new JTextField(20);
		tfAgeDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAgeDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfSalaryDoctorUp = new JTextField(20);
		tfSalaryDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfSalaryDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfSpecializationDoctorUp = new JTextField(20);
		tfSpecializationDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfSpecializationDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalNameDoctorUp = new JTextField(20);
		tfHospitalNameDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalNameDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalCityDoctorUp = new JTextField(20);
		tfHospitalCityDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalCityDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalAddressDoctorUp = new JTextField(20);
		tfHospitalAddressDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalAddressDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalTelephoneNumberDoctorUp = new JTextField(20);
		tfHospitalTelephoneNumberDoctorUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalTelephoneNumberDoctorUp.setHorizontalAlignment(JTextField.CENTER);

		// ------------------------------------------------------------

		// ---------------label DoctorDown---------------------

		lIdDoctorDown = new JLabel("ID");
		lIdDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lFirstNameDoctorDown = new JLabel("First Name");
		lFirstNameDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lLastNameDoctorDown = new JLabel("Last Name");
		lLastNameDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lAgeDoctorDown = new JLabel("Age");
		lAgeDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lSalaryDoctorDown = new JLabel("Salary");
		lSalaryDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lSpecializationDoctorDown = new JLabel("Specialization");
		lSpecializationDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalNameDoctorDown = new JLabel("Hospital Name");
		lHospitalNameDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalCityDoctorDown = new JLabel("Hospital City");
		lHospitalCityDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalAddressDoctorDown = new JLabel("Hospital Adress");
		lHospitalAddressDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lHospitalTelephoneNumberDoctorDown = new JLabel("Hospital Telephone Number");
		lHospitalTelephoneNumberDoctorDown.setFont(new Font("Arial", Font.PLAIN, 20));

		// ---------------------------------------------------

		// --------------------TextField DoctorDown----------------------------

		tfId = new JTextField(4);
		tfId.setFont(new Font("Arial", Font.ITALIC, 31));
		tfId.setHorizontalAlignment(JTextField.CENTER);
		tfId.setFont(new Font("Courier New", Font.ITALIC, 17));

		tfFirstNameDoctorDown = new JTextField(20);
		tfFirstNameDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfFirstNameDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfLastNameDoctorDown = new JTextField(20);
		tfLastNameDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfLastNameDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfAgeDoctorDown = new JTextField(20);
		tfAgeDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAgeDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfSalaryDoctorDown = new JTextField(20);
		tfSalaryDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfSalaryDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfSpecializationDoctorDown = new JTextField(20);
		tfSpecializationDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfSpecializationDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalNameDoctorDown = new JTextField(20);
		tfHospitalNameDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalNameDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalCityDoctorDown = new JTextField(20);
		tfHospitalCityDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalCityDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalAddressDoctorDown = new JTextField(20);
		tfHospitalAddressDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalAddressDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		tfHospitalTelephoneNumberDoctorDown = new JTextField(20);
		tfHospitalTelephoneNumberDoctorDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfHospitalTelephoneNumberDoctorDown.setHorizontalAlignment(JTextField.CENTER);

		// ---------------------------------------------------------

		// --------------------------------label
		// PatientUp-----------------------------

		lFirstNamePatientUp = new JLabel("First Name");
		lFirstNamePatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lLastNamePatientUp = new JLabel("Last Name");
		lLastNamePatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lAgePatientUp = new JLabel("Age");
		lAgePatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lDiseasePatientUp = new JLabel("Disease");
		lDiseasePatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lEmailAddressPatientUp = new JLabel("Email Address");
		lEmailAddressPatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lMailAddressPatientUp = new JLabel("Mail Address");
		lMailAddressPatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lAddressPatientUp = new JLabel("Address");
		lAddressPatientUp.setFont(new Font("Arial", Font.PLAIN, 20));
		lTelephoneNumberPatientUp = new JLabel("Telephone Number");
		lTelephoneNumberPatientUp.setFont(new Font("Arial", Font.PLAIN, 20));

		// --------------------------------------------------------------------

		// --------------------TextField---------------------------------------

		tfFirstNamePatientUp = new JTextField(20);
		tfFirstNamePatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfFirstNamePatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfLastNamePatientUp = new JTextField(20);
		tfLastNamePatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfLastNamePatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfAgePatientUp = new JTextField(20);
		tfAgePatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAgePatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfDiseasePatientUp = new JTextField(20);
		tfDiseasePatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDiseasePatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfEmailAddressPatientUp = new JTextField(20);
		tfEmailAddressPatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfEmailAddressPatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfMailAddressPatientUp = new JTextField(20);
		tfMailAddressPatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfMailAddressPatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfAddressPatientUp = new JTextField(20);
		tfAddressPatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAddressPatientUp.setHorizontalAlignment(JTextField.CENTER);

		tfTelephoneNumberPatientUp = new JTextField(20);
		tfTelephoneNumberPatientUp.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfTelephoneNumberPatientUp.setHorizontalAlignment(JTextField.CENTER);

		// ----------------------------------------------------

		// -----------------label Patient down--------------------

		lIdPatientDown = new JLabel("ID");
		lIdPatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lFirstNamePatientDown = new JLabel("First Name");
		lFirstNamePatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lLastNamePatientDown = new JLabel("Last Name");
		lLastNamePatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lAgePatientDown = new JLabel("Age");
		lAgePatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lDiseasePatientDown = new JLabel("Disease");
		lDiseasePatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lEmailAddressPatientDown = new JLabel("Email Address");
		lEmailAddressPatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lMailAddressPatientDown = new JLabel("Mail Address");
		lMailAddressPatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lAddressPatientDown = new JLabel("Address");
		lAddressPatientDown.setFont(new Font("Arial", Font.PLAIN, 20));
		lTelephoneNumberPatientDown = new JLabel("Telephone Number");
		lTelephoneNumberPatientDown.setFont(new Font("Arial", Font.PLAIN, 20));

		// ------------------------TextField
		// PatientDown-------------------------------
		tfFirstNamePatientDown = new JTextField(20);
		tfFirstNamePatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfFirstNamePatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfLastNamePatientDown = new JTextField(20);
		tfLastNamePatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfLastNamePatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfAgePatientDown = new JTextField(20);
		tfAgePatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAgePatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfDiseasePatientDown = new JTextField(20);
		tfDiseasePatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfDiseasePatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfEmailAddressPatientDown = new JTextField(20);
		tfEmailAddressPatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfEmailAddressPatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfMailAddressPatientDown = new JTextField(20);
		tfMailAddressPatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfMailAddressPatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfAddressPatientDown = new JTextField(20);
		tfAddressPatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfAddressPatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfTelephoneNumberPatientDown = new JTextField(20);
		tfTelephoneNumberPatientDown.setFont(new Font("Courier New", Font.ITALIC, 20));
		tfTelephoneNumberPatientDown.setHorizontalAlignment(JTextField.CENTER);

		tfFirstNameDoctorUp.setEditable(false);
		tfLastNameDoctorUp.setEditable(false);
		tfAgeDoctorUp.setEditable(false);
		tfSalaryDoctorUp.setEditable(false);
		tfSpecializationDoctorUp.setEditable(false);
		tfHospitalNameDoctorUp.setEditable(false);
		tfHospitalCityDoctorUp.setEditable(false);
		tfHospitalAddressDoctorUp.setEditable(false);
		tfHospitalTelephoneNumberDoctorUp.setEditable(false);

		tfFirstNameDoctorDown.setEditable(false);
		tfLastNameDoctorDown.setEditable(false);
		tfAgeDoctorDown.setEditable(false);
		tfSalaryDoctorDown.setEditable(false);
		tfSpecializationDoctorDown.setEditable(false);
		tfHospitalNameDoctorDown.setEditable(false);
		tfHospitalCityDoctorDown.setEditable(false);
		tfHospitalAddressDoctorDown.setEditable(false);
		tfHospitalTelephoneNumberDoctorDown.setEditable(false);

		tfFirstNamePatientUp.setEditable(false);
		tfLastNamePatientUp.setEditable(false);
		tfAgePatientUp.setEditable(false);
		tfDiseasePatientUp.setEditable(false);
		tfEmailAddressPatientUp.setEditable(false);
		tfMailAddressPatientUp.setEditable(false);
		tfAddressPatientUp.setEditable(false);
		tfTelephoneNumberPatientUp.setEditable(false);

		tfFirstNamePatientDown.setEditable(false);
		tfLastNamePatientDown.setEditable(false);
		tfAgePatientDown.setEditable(false);
		tfDiseasePatientDown.setEditable(false);
		tfEmailAddressPatientDown.setEditable(false);
		tfMailAddressPatientDown.setEditable(false);
		tfAddressPatientDown.setEditable(false);
		tfTelephoneNumberPatientDown.setEditable(false);

		// ---------------------------------------------------------------------------

		customComboboxModelDoctor = new CustomComboboxModel<>(Database.selectDoctorIdFirstNameLastName());
		cbDoctor = new JComboBox<>(customComboboxModelDoctor);

		if (role.equals("Admin")) {
			cbDoctor.addActionListener(e -> {
				String text = (String) cbDoctor.getSelectedItem();
				String[] tab = text.split(" ");
				fillDoctorPanelDown(Integer.parseInt(tab[0]));

			});
		} else {
			cbDoctor.setEnabled(false);
		}

		add(cbDoctor, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;

		customComboboxModelPatient = new CustomComboboxModel<>(Database.selectPatientIdFirstNameLastName());
		cbPatient = new JComboBox<>(customComboboxModelPatient);

		if (role.equals("Admin")) {

			cbPatient.addActionListener(e -> {
				String text = (String) cbPatient.getSelectedItem();
				String[] tab = text.split(" ");
				fillPatientPanelDown(Integer.parseInt(tab[0]));
			});
		} else {
			cbPatient.setEnabled(false);
		}

		add(cbPatient, gbc);

		JPanel doctorPanelUp = new JPanel(new GridBagLayout());
		GridBagConstraints doctorUpGBC = new GridBagConstraints();

		JPanel doctorPanelDown = new JPanel(new GridBagLayout());
		GridBagConstraints doctorDownGBC = new GridBagConstraints();

		JPanel patientPanelUp = new JPanel(new GridBagLayout());
		GridBagConstraints patientPanelUpGBC = new GridBagConstraints();

		JPanel patientPanelDown = new JPanel(new GridBagLayout());
		GridBagConstraints patientPanelDownGBC = new GridBagConstraints();

		JPanel buttonsPanelUp = new JPanel(new GridBagLayout());
		GridBagConstraints buttonsPanelUpGBC = new GridBagConstraints();

		JPanel buttonInsert = new JPanel(new GridBagLayout());
		GridBagConstraints buttonInsertGBC = new GridBagConstraints();

		JPanel buttondDelete = new JPanel(new GridBagLayout());
		GridBagConstraints buttonDeleteGBC = new GridBagConstraints();

		JPanel panelTime = new JPanel(new GridBagLayout());
		GridBagConstraints gbcTime = new GridBagConstraints();

		// --------------------------DOCTOR PANEL
		// UP---------------------------------

		doctorPanelUp.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 0;
		doctorPanelUp.add(lFirstNameDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 0;
		doctorPanelUp.add(tfFirstNameDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 1;
		doctorPanelUp.add(lLastNameDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 1;
		doctorPanelUp.add(tfLastNameDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 2;
		doctorPanelUp.add(lAgeDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 2;
		doctorPanelUp.add(tfAgeDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 3;
		doctorPanelUp.add(lSalaryDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 3;
		doctorPanelUp.add(tfSalaryDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 4;
		doctorPanelUp.add(lSpecializationDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 4;
		doctorPanelUp.add(tfSpecializationDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 5;
		doctorPanelUp.add(lHospitalNameDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 5;
		doctorPanelUp.add(tfHospitalNameDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 6;
		doctorPanelUp.add(lHospitalCityDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 6;
		doctorPanelUp.add(tfHospitalCityDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 7;
		doctorPanelUp.add(lHospitalAddressDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 7;
		doctorPanelUp.add(tfHospitalAddressDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 0;
		doctorUpGBC.gridy = 8;
		doctorPanelUp.add(lHospitalTelephoneNumberDoctorUp, doctorUpGBC);

		doctorUpGBC.gridx = 1;
		doctorUpGBC.gridy = 8;
		doctorPanelUp.add(tfHospitalTelephoneNumberDoctorUp, doctorUpGBC);

		// --------------------------------------------------------------------------

		// --------------------------DOCTOR PANEL
		// Down---------------------------------

		doctorPanelDown.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 0;
		doctorPanelDown.add(lIdDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 0;
		doctorPanelDown.add(cbDoctor, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 1;
		doctorPanelDown.add(lFirstNameDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 1;
		doctorPanelDown.add(tfFirstNameDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 2;
		doctorPanelDown.add(lLastNameDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 2;
		doctorPanelDown.add(tfLastNameDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 3;
		doctorPanelDown.add(lAgeDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 3;
		doctorPanelDown.add(tfAgeDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 4;
		doctorPanelDown.add(lSalaryDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 4;
		doctorPanelDown.add(tfSalaryDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 5;
		doctorPanelDown.add(lSpecializationDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 5;
		doctorPanelDown.add(tfSpecializationDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 6;
		doctorPanelDown.add(lHospitalNameDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 6;
		doctorPanelDown.add(tfHospitalNameDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 7;
		doctorPanelDown.add(lHospitalCityDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 7;
		doctorPanelDown.add(tfHospitalCityDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 8;
		doctorPanelDown.add(lHospitalAddressDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 8;
		doctorPanelDown.add(tfHospitalAddressDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 0;
		doctorDownGBC.gridy = 9;
		doctorPanelDown.add(lHospitalTelephoneNumberDoctorDown, doctorDownGBC);

		doctorDownGBC.gridx = 1;
		doctorDownGBC.gridy = 9;
		doctorPanelDown.add(tfHospitalTelephoneNumberDoctorDown, doctorDownGBC);

		// --------------------------------------------------------------------------

		// --------------------------PATIENT PANEL
		// Up--------------------------------

		patientPanelUp.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 0;
		patientPanelUp.add(lFirstNamePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 0;
		patientPanelUp.add(tfFirstNamePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 1;
		patientPanelUp.add(lLastNamePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 1;
		patientPanelUp.add(tfLastNamePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 2;
		patientPanelUp.add(lAgePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 2;
		patientPanelUp.add(tfAgePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 3;
		patientPanelUp.add(lDiseasePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 3;
		patientPanelUp.add(tfDiseasePatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 4;
		patientPanelUp.add(lEmailAddressPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 4;
		patientPanelUp.add(tfEmailAddressPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 5;
		patientPanelUp.add(lMailAddressPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 5;
		patientPanelUp.add(tfMailAddressPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 6;
		patientPanelUp.add(lAddressPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 6;
		patientPanelUp.add(tfAddressPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 0;
		patientPanelUpGBC.gridy = 7;
		patientPanelUp.add(lTelephoneNumberPatientUp, patientPanelUpGBC);

		patientPanelUpGBC.gridx = 1;
		patientPanelUpGBC.gridy = 7;
		patientPanelUp.add(tfTelephoneNumberPatientUp, patientPanelUpGBC);

		// --------------------------------------------------------------------------

		// --------------------------PATIENT PANEL
		// DOWN--------------------------------

		patientPanelDown.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 0;
		patientPanelDown.add(lIdPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 0;
		patientPanelDown.add(cbPatient, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 1;
		patientPanelDown.add(lFirstNamePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 1;
		patientPanelDown.add(tfFirstNamePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 2;
		patientPanelDown.add(lLastNamePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 2;
		patientPanelDown.add(tfLastNamePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 3;
		patientPanelDown.add(lAgePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 3;
		patientPanelDown.add(tfAgePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 4;
		patientPanelDown.add(lDiseasePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 4;
		patientPanelDown.add(tfDiseasePatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 5;
		patientPanelDown.add(lEmailAddressPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 5;
		patientPanelDown.add(tfEmailAddressPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 6;
		patientPanelDown.add(lMailAddressPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 6;
		patientPanelDown.add(tfMailAddressPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 7;
		patientPanelDown.add(lAddressPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 7;
		patientPanelDown.add(tfAddressPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 0;
		patientPanelDownGBC.gridy = 8;
		patientPanelDown.add(lTelephoneNumberPatientDown, patientPanelDownGBC);

		patientPanelDownGBC.gridx = 1;
		patientPanelDownGBC.gridy = 8;
		patientPanelDown.add(tfTelephoneNumberPatientDown, patientPanelDownGBC);

		// --------------------------------------------------------------------------

		// -----------------------------BUTTONS PANEL
		// UP-----------------------------
		buttonsPanelUpGBC.gridx = 0;
		buttonsPanelUpGBC.gridy = 0;
		buttonsPanelUp.add(btnLeft, buttonsPanelUpGBC);

		buttonsPanelUpGBC.gridx = 1;
		buttonsPanelUpGBC.gridy = 0;
		buttonsPanelUp.add(tfId, buttonsPanelUpGBC);

		buttonsPanelUpGBC.gridx = 2;
		buttonsPanelUpGBC.gridy = 0;
		buttonsPanelUp.add(btnRight, buttonsPanelUpGBC);
		// --------------------------------------------------------------------------

		// ------------------------------BUTTON DELETE--------------------------

		buttonDeleteGBC.gridx = 0;
		buttonDeleteGBC.gridy = 0;
		buttondDelete.add(btnDelete, buttonDeleteGBC);

		// -----------------------------BUTTON INSERT----------------------

		buttonInsertGBC.gridx = 0;
		buttonInsertGBC.gridy = 0;
		buttonInsert.add(btnInsert, buttonInsertGBC);

		// -----------------------PANEL TIME-----------------------------

		model = new UtilDateModel();

		model.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth());
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);

		lVisitLabel = new JLabel("Choose visit date: ");
		lVisitLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		customTimeModel = new CustomComboboxModel<>(createHoursList());
		cbTime = new JComboBox<>(customTimeModel);

		gbcTime.gridx = 0;
		gbcTime.gridy = 0;
		panelTime.add(lVisitLabel, gbcTime);

		gbcTime.gridx = 0;
		gbcTime.gridy = 1;
		panelTime.add((Component) datePicker, gbcTime);

		gbcTime.gridx = 0;
		gbcTime.gridy = 2;
		panelTime.add(cbTime, gbcTime);

		// ---------------------------------

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(buttonsPanelUp, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(doctorPanelUp, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(patientPanelUp, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(buttondDelete, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(doctorPanelDown, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		add(patientPanelDown, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		add(buttonsPanelUp, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		add(buttondDelete, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		add(panelTime, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		add(buttonInsert, gbc);

		list = new ArrayList<>();
		idx = 0;

		fillFirstIdx();
		fillDoctorAndPatient();

	}

	public void fillDoctorAndPatient() throws SQLException {
		list = Database.selectFullInnerJoin();

		if (list != null && !list.isEmpty()) {
			PatientHospitalDoctor phd = list.get(idx);

			tfId.setText(String.valueOf(phd.getRegistrationId()));

			tfFirstNameDoctorUp.setText(phd.getDoctorFirstName());
			tfLastNameDoctorUp.setText(phd.getDoctorLastName());
			tfAgeDoctorUp.setText(String.valueOf(phd.getDoctorAge()));
			tfSalaryDoctorUp.setText(String.valueOf(phd.getDoctorSalary()));
			tfSpecializationDoctorUp.setText(phd.getSpecialization());
			tfHospitalNameDoctorUp.setText(phd.getHospitalName());
			tfHospitalCityDoctorUp.setText(phd.getHospitalCity());
			tfHospitalAddressDoctorUp.setText(phd.getHospitalAddress());
			tfHospitalTelephoneNumberDoctorUp.setText(phd.getHospitalTelephoneNumber());

			tfFirstNamePatientUp.setText(phd.getPatientFirstName());
			tfLastNamePatientUp.setText(phd.getPatientLastName());
			tfAgePatientUp.setText(String.valueOf(phd.getPatientAge()));
			tfDiseasePatientUp.setText(phd.getPatientDisease());
			tfEmailAddressPatientUp.setText(phd.getPatientEmailAddress());
			tfMailAddressPatientUp.setText(phd.getPatientMailAddress());
			tfAddressPatientUp.setText(phd.getPatientAddress());
			tfTelephoneNumberPatientUp.setText(phd.getPatientTelephoneNumber());
		} else {
			tfId.setText("");

			tfFirstNameDoctorUp.setText("");
			tfLastNameDoctorUp.setText("");
			tfAgeDoctorUp.setText("");
			tfSalaryDoctorUp.setText("");
			tfSpecializationDoctorUp.setText("");
			tfHospitalNameDoctorUp.setText("");
			tfHospitalCityDoctorUp.setText("");
			tfHospitalAddressDoctorUp.setText("");
			tfHospitalTelephoneNumberDoctorUp.setText("");

			tfFirstNamePatientUp.setText("");
			tfLastNamePatientUp.setText("");
			tfAgePatientUp.setText("");
			tfDiseasePatientUp.setText("");
			tfEmailAddressPatientUp.setText("");
			tfMailAddressPatientUp.setText("");
			tfAddressPatientUp.setText("");
			tfTelephoneNumberPatientUp.setText("");
		}

	}

	public void fillFirstIdx() throws SQLException {
		list = Database.selectFullInnerJoin();

		if (list != null && !list.isEmpty()) {
			PatientHospitalDoctor phd = list.get(0);

			tfId.setText(String.valueOf(phd.getRegistrationId()));

			tfFirstNameDoctorDown.setText(phd.getDoctorFirstName());
			tfLastNameDoctorDown.setText(phd.getDoctorLastName());
			tfAgeDoctorDown.setText(String.valueOf(phd.getDoctorAge()));
			tfSalaryDoctorDown.setText(String.valueOf(phd.getDoctorSalary()));
			tfSpecializationDoctorDown.setText(phd.getSpecialization());
			tfHospitalNameDoctorDown.setText(phd.getHospitalName());
			tfHospitalCityDoctorDown.setText(phd.getHospitalCity());
			tfHospitalAddressDoctorDown.setText(phd.getHospitalAddress());
			tfHospitalTelephoneNumberDoctorDown.setText(phd.getHospitalTelephoneNumber());

			tfFirstNamePatientDown.setText(phd.getPatientFirstName());
			tfLastNamePatientDown.setText(phd.getPatientLastName());
			tfAgePatientDown.setText(String.valueOf(phd.getPatientAge()));
			tfDiseasePatientDown.setText(phd.getPatientDisease());
			tfEmailAddressPatientDown.setText(phd.getPatientEmailAddress());
			tfMailAddressPatientDown.setText(phd.getPatientMailAddress());
			tfAddressPatientDown.setText(phd.getPatientAddress());
			tfTelephoneNumberPatientDown.setText(phd.getPatientTelephoneNumber());
		} else {
			tfId.setText("");

			tfFirstNameDoctorDown.setText("");
			tfLastNameDoctorDown.setText("");
			tfAgeDoctorDown.setText("");
			tfSalaryDoctorDown.setText("");
			tfSpecializationDoctorDown.setText("");
			tfHospitalNameDoctorDown.setText("");
			tfHospitalCityDoctorDown.setText("");
			tfHospitalAddressDoctorDown.setText("");
			tfHospitalTelephoneNumberDoctorDown.setText("");

			tfFirstNamePatientDown.setText("");
			tfLastNamePatientDown.setText("");
			tfAgePatientDown.setText("");
			tfDiseasePatientDown.setText("");
			tfEmailAddressPatientDown.setText("");
			tfMailAddressPatientDown.setText("");
			tfAddressPatientDown.setText("");
			tfTelephoneNumberPatientDown.setText("");
		}

	}

	public void leftBtnMethod() {
		--idx;
		if (idx < 0) {
			idx = list.size() - 1;
		}
	}

	public void rightBtnMethod() {
		++idx;
		if (idx >= list.size()) {
			idx = 0;
		}
	}

	public void fillDoctorPanelDown(int id) {
		try {
			DoctorWithHospital phd = Database.selectDoctorWithHospitalById(id);
			tfFirstNameDoctorDown.setText(phd.getFirstName());
			tfLastNameDoctorDown.setText(phd.getLastName());
			tfAgeDoctorDown.setText(String.valueOf(phd.getAge()));
			tfSalaryDoctorDown.setText(String.valueOf(phd.getSalary()));
			tfSpecializationDoctorDown.setText(phd.getSpecialization());
			tfHospitalNameDoctorDown.setText(phd.getHospitalName());
			tfHospitalCityDoctorDown.setText(phd.getHospitalCity());
			tfHospitalAddressDoctorDown.setText(phd.getHospitalAddress());
			tfHospitalTelephoneNumberDoctorDown.setText(phd.getHospitalTelephoneNumber());
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void fillPatientPanelDown(int id) {
		try {
			Patient phd = Database.selectPatientById(id);
			tfFirstNamePatientDown.setText(phd.getFirstName());
			tfLastNamePatientDown.setText(phd.getLastName());
			tfAgePatientDown.setText(String.valueOf(phd.getAge()));
			tfDiseasePatientDown.setText(phd.getDisease());
			tfEmailAddressPatientDown.setText(phd.getEmailAddress());
			tfMailAddressPatientDown.setText(phd.getMailAddress());
			tfAddressPatientDown.setText(phd.getAddress());
			tfTelephoneNumberPatientDown.setText(phd.getTelephoneNumber());

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void insertDoctorPatientMethod() {
		try {

			String textCbDoctor = (String) cbDoctor.getSelectedItem();
			String[] arrD = textCbDoctor.split(" ");
			int idD = Integer.parseInt(arrD[0]);

			String textCbPatient = (String) cbPatient.getSelectedItem();
			String[] arrP = textCbDoctor.split(" ");
			int idP = Integer.parseInt(arrP[0]);

			if (LocalDateTime.of(
					(LocalDate.of(datePicker.getModel().getYear(), datePicker.getModel().getMonth() + 1,
							datePicker.getModel().getDay())),
					LocalTime.from((TemporalAccessor) cbTime.getSelectedItem())).isAfter(LocalDateTime.now())

			) {
				if (!Database.checkRegistrationDate(LocalDateTime.of(
						(LocalDate.of(datePicker.getModel().getYear(), datePicker.getModel().getMonth() + 1,
								datePicker.getModel().getDay())),
						LocalTime.from((TemporalAccessor) cbTime.getSelectedItem())))) {

					Database.insertRegister(
							new Register(0, idD, idP,
									LocalDateTime.of((LocalDate.of(datePicker.getModel().getYear(),
											datePicker.getModel().getMonth() + 1, datePicker.getModel().getDay())),
											LocalTime.from((TemporalAccessor) cbTime.getSelectedItem()))));

					JOptionPane.showMessageDialog(null, "Done");
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Choose antother day, on that day someone is already singed up");
				}
			} else {

				JOptionPane.showMessageDialog(null, "Choose antother date, this date is already expired");
				datePanel.updateUI();
				cbTime.updateUI();

			}
			list = Database.selectFullInnerJoin();
			idx = list.size() - 1;
			fillDoctorAndPatient();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteDoctorPatientMethod() {
		try {
			if (!tfId.getText().isEmpty()) {
				Database.deleteRegister(Integer.parseInt(tfId.getText()));
				list = Database.selectFullInnerJoin();
				--idx;
				if (idx < 0) {
					idx = 0;
				}
				fillDoctorAndPatient();
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateCb() {
		try {
			customComboboxModelDoctor.setItems(Database.selectDoctorIdFirstNameLastName());
			customComboboxModelPatient.setItems(Database.selectPatientIdFirstNameLastName());
			cbDoctor.updateUI();
			cbPatient.updateUI();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public List<LocalTime> createHoursList() {
		List<LocalTime> time = new ArrayList<>();

		LocalTime lt = LocalTime.of(7, 0);

		do {

			time.add(lt);
			lt = lt.plusMinutes(30);

		} while (lt.isBefore(LocalTime.of(15, 00)));

		return time;
	}

}
