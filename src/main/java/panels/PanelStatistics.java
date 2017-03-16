package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classes.Patient;
import classes.PatientCity;
import database.Database;
import models.CustomComboboxModel;

public class PanelStatistics extends JPanel implements ActionListener {

	private JLabel lHighestAge;
	private JLabel lSalary;
	private JLabel lDisease;
	private JLabel lMiddleAge;
	private JLabel lPatientsAmount;

	private JTextField tfDisease;
	private JTextField tfMiddleAge;

	private JComboBox cbTheOldest;
	private JComboBox cbSalary;
	private JComboBox cbPatientsAmount;

	private CustomComboboxModel customComboSalary;
	private CustomComboboxModel customComboCities;
	private CustomComboboxModel customComboTheOldest;

	public PanelStatistics() {

		super(new GridBagLayout());

		lHighestAge = new JLabel("The oldest patient: ");
		lHighestAge.setFont(new Font("Arial", Font.PLAIN, 20));

		lSalary = new JLabel("The highest and the lowest doctor salary: ");
		lSalary.setFont(new Font("Arial", Font.PLAIN, 20));

		lDisease = new JLabel("The most frequent disease: ");
		lDisease.setFont(new Font("Arial", Font.PLAIN, 20));

		lMiddleAge = new JLabel("Avereage patient age: ");
		lMiddleAge.setFont(new Font("Arial", Font.PLAIN, 20));

		lPatientsAmount = new JLabel("Patients amount in particular cities: ");
		lPatientsAmount.setFont(new Font("Arial", Font.PLAIN, 20));

		try {
			List<String> theOldestPatientList = Database.selectTheOldestPatient();
			theOldestPatientList = theOldestPatientList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
			customComboTheOldest = new CustomComboboxModel<>(theOldestPatientList);
			cbTheOldest = new JComboBox<>(customComboTheOldest);
			cbTheOldest.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		try {
			List<String> salaryList = Database.selectMinMaxDoctorSalary();
			salaryList = salaryList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
			customComboSalary = new CustomComboboxModel<>(salaryList);
			cbSalary = new JComboBox<>(customComboSalary);
			cbSalary.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		} catch (SQLException e) {

			e.printStackTrace();
		}

		tfDisease = new JTextField(15);

		tfDisease.setHorizontalAlignment(JTextField.CENTER);
		tfDisease.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		try {
			tfDisease.setText(theMostFrequentDisease().toUpperCase());
		} catch (SQLException e) {

			e.printStackTrace();
		}
		tfDisease.setEditable(false);

		tfMiddleAge = new JTextField(15);
		tfMiddleAge.setHorizontalAlignment(JTextField.CENTER);
		tfMiddleAge.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		try {
			tfMiddleAge.setText(String.valueOf(Database.selectAveragePatientAge()));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		tfMiddleAge.setEditable(false);

		try {
			List<String> patientsInCitiesList = patientsInCities();
			patientsInCitiesList = patientsInCitiesList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
			customComboCities = new CustomComboboxModel<>(patientsInCitiesList);
			cbPatientsAmount = new JComboBox<>(customComboCities);
			cbPatientsAmount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// =======PANELS===========

		JPanel panelFields = new JPanel(new GridBagLayout());

		panelFields.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
				"Statistics", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("TimesNewRoman", Font.BOLD, 26), Color.BLUE));

		GridBagConstraints gbcFields = new GridBagConstraints();

		gbcFields.insets = new Insets(5, 10, 5, 10);

		gbcFields.anchor = GridBagConstraints.LINE_START;

		gbcFields.gridx = 0;
		gbcFields.gridy = 0;
		panelFields.add(lHighestAge, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 1;
		panelFields.add(lSalary, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 2;
		panelFields.add(lDisease, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 3;
		panelFields.add(lMiddleAge, gbcFields);

		gbcFields.gridx = 0;
		gbcFields.gridy = 4;
		panelFields.add(lPatientsAmount, gbcFields);

		gbcFields.anchor = GridBagConstraints.CENTER;
		gbcFields.gridx = 1;
		gbcFields.gridy = 0;
		panelFields.add(cbTheOldest, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 1;
		panelFields.add(cbSalary, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 2;
		panelFields.add(tfDisease, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 3;
		panelFields.add(tfMiddleAge, gbcFields);

		gbcFields.gridx = 1;
		gbcFields.gridy = 4;
		panelFields.add(cbPatientsAmount, gbcFields);

		// ===============title=============

		JPanel panelTitle = new JPanel(new GridBagLayout());
		GridBagConstraints gbcTitle = new GridBagConstraints();

		// ==========MAIN ========================

		GridBagConstraints gbcMain = new GridBagConstraints();

		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		add(panelTitle, gbcMain);

		gbcMain.gridx = 0;
		gbcMain.gridy = 1;
		add(panelFields, gbcMain);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public static String theMostFrequentDisease() throws SQLException {

		List<Patient> patientsList = new ArrayList<>(Database.selectPatient());
		Map<String, List<Patient>> groupBy =

				patientsList.stream().collect(Collectors.groupingBy(p -> p.getDisease()));

		if(!groupBy.isEmpty()){
		Entry<String, List<Patient>> mostFreq = groupBy.entrySet().stream()
				.sorted((o1, o2) -> (o2.getValue().size() - o1.getValue().size())).findFirst().get();
		
		String disease = mostFreq.getKey();
		int frequency = mostFreq.getValue().size();

		return disease + " - " + frequency;
		
		}
		
		return "";
	}

	public static List<String> patientsInCities() throws SQLException {

		List<String> statistics = new ArrayList<>();
		List<PatientCity> patientCity = new ArrayList<>(Database.selectPatientCity());
		Map<String, List<PatientCity>> groupBy = patientCity.stream().collect(Collectors.groupingBy(p -> p.getCity()));

		groupBy.entrySet().forEach(e -> {

			statistics.add(e.getKey() + " - " + e.getValue().size());

		});

		return statistics;
	}

	public void updateCb() {

		try {
			List<String> salaryList = Database.selectMinMaxDoctorSalary();
			salaryList = salaryList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
			customComboSalary.setItems(salaryList);

			List<String> patientsInCitiesList = patientsInCities();
			patientsInCitiesList = patientsInCitiesList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
			customComboCities.setItems(patientsInCitiesList);

			List<String> theOldestPatientList = Database.selectTheOldestPatient();
			theOldestPatientList = theOldestPatientList.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
			customComboTheOldest.setItems(theOldestPatientList);

			tfDisease.setText(theMostFrequentDisease().toUpperCase());
			tfMiddleAge.setText(String.valueOf(Database.selectAveragePatientAge()));

		} catch (SQLException e) {

			e.printStackTrace();
		}

		cbTheOldest.updateUI();
		cbSalary.updateUI();
		cbPatientsAmount.updateUI();
		tfDisease.updateUI();
		tfMiddleAge.updateUI();

	}

}
