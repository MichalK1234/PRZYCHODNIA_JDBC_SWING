package panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import booleans.CustomFlags;
import database.Database;
import models.CustomComboboxModel;
import models.CustomListModel;
import models.CustomTableModel;

public class PanelFilter extends JPanel {

	private JTable jtable;
	private CustomTableModel modelTable;

	private JList<String> jListName;
	private JList<String> jListSurname;
	private JList<String> jListCity;

	private CustomListModel<String> modelListName;
	private CustomListModel<String> modelListSurname;
	private CustomListModel<String> modelListCity;

	private JCheckBox jCName;
	private JCheckBox jCSurname;
	private JCheckBox jCCity;
	private JCheckBox jCAge;
	private JCheckBox jCSalary;

	private JComboBox<Integer> cbAgeLeft;
	private JComboBox<Integer> cbAgeRight;

	private JComboBox<Double> cbSalaryLeft;
	private JComboBox<Double> cbSalaryRight;

	private CustomComboboxModel<Integer> customComboboxModelDoctorAgeFrom;
	private CustomComboboxModel<Integer> customComboboxModelDoctorAgeTo;

	private CustomComboboxModel<Double> customComboboxModelDoctorSalaryFrom;
	private CustomComboboxModel<Double> customComboboxModelDoctorSalaryTo;

	private JButton btnFilter;
	private JButton btnReset;

	private JLabel lAgeFrom;
	private JLabel lAgeTo;
	private JLabel lSalaryFrom;
	private JLabel lSalaryTo;

	public PanelFilter() throws SQLException {

		super(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// -------------------JTable-------------------------

		modelTable = new CustomTableModel(Database.selectFullInnerJoin());
		jtable = new JTable(modelTable) {
			public Class getColumnClass(int column) {
				return getValueAt(modelTable.getRowCount() - 1, column).getClass();
			}
		};

		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				if (CustomFlags.flag1) {

					CustomFlags.flag1 = false;
				}

			}
		});

		jtable.setPreferredScrollableViewportSize(jtable.getPreferredSize());
		adjustSize();

		// -------------------CHCEK BOXES----------------------

		jCName = new JCheckBox("NAME");
		jCSurname = new JCheckBox("SURNAME");
		jCCity = new JCheckBox("CITY");
		jCAge = new JCheckBox("DOCTOR AGE");
		jCSalary = new JCheckBox("DOCTOR SALARY");

		// -----------------LISTS---------------------------

		modelListCity = new CustomListModel<>(Database.selectCities());
		modelListName = new CustomListModel<>(Database.selectNames());
		modelListSurname = new CustomListModel<>(Database.selectSurnames());
		jListName = new JList<>(modelListName);
		jListSurname = new JList<>(modelListSurname);
		jListCity = new JList<>(modelListCity);

		// ---------------COMBO BOXES--------------

		customComboboxModelDoctorAgeFrom = new CustomComboboxModel<>(Database.selectFullInnerJoinDoctorAge());
		cbAgeLeft = new JComboBox<>(customComboboxModelDoctorAgeFrom);

		cbAgeLeft.addActionListener(e -> {
			try {
				int leftAge = (int) cbAgeLeft.getSelectedItem();
				List<Integer> ageList = Database.selectFullInnerJoinGreaterEqualDoctorAge(leftAge);
				customComboboxModelDoctorAgeTo.setItems(ageList);
				cbAgeRight.updateUI();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});

		customComboboxModelDoctorAgeTo = new CustomComboboxModel<>(Database.selectFullInnerJoinDoctorAge());
		cbAgeRight = new JComboBox<>(customComboboxModelDoctorAgeTo);

		cbAgeRight.addActionListener(e -> {
			try {
				int rightAge = (int) cbAgeRight.getSelectedItem();
				List<Integer> ageList = Database.selectFullInnerJoinLowerEqualDocotrAge(rightAge);
				customComboboxModelDoctorAgeFrom.setItems(ageList);
				cbAgeLeft.updateUI();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});

		customComboboxModelDoctorSalaryFrom = new CustomComboboxModel<>(Database.selectFullInnerJoinDoctorSalary());
		cbSalaryLeft = new JComboBox<>(customComboboxModelDoctorSalaryFrom);

		cbSalaryLeft.addActionListener(e -> {
			try {

				double leftSalary = (double) cbSalaryLeft.getSelectedItem();
				List<Double> salaryList = Database.selectFullInnerJoinGreaterEqualDoctorSalary(leftSalary);
				customComboboxModelDoctorSalaryTo.setItems(salaryList);
				cbSalaryRight.updateUI();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});

		customComboboxModelDoctorSalaryTo = new CustomComboboxModel<>(Database.selectFullInnerJoinDoctorSalary());
		cbSalaryRight = new JComboBox<>(customComboboxModelDoctorSalaryTo);

		cbSalaryRight.addActionListener(e -> {
			try {
				double rightSalary = (double) cbSalaryRight.getSelectedItem();
				List<Double> salaryList = Database.selectFullInnerJoinLowerEqualDocotrSalary(rightSalary);
				customComboboxModelDoctorSalaryFrom.setItems(salaryList);
				cbSalaryLeft.updateUI();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		// -----------------LABELS--------------------------------

		lAgeFrom = new JLabel("FROM");

		lAgeTo = new JLabel("TO");

		lSalaryFrom = new JLabel("FROM");

		lSalaryTo = new JLabel("TO");

		// ----------------------BUTTONS------------------------

		btnFilter = new JButton("FILTER");
		btnFilter.addActionListener(e -> {

			try {
				boolean ifFilterFirstNameDoctor = jCName.isSelected();
				boolean ifFilterLastNameDoctor = jCSurname.isSelected();
				boolean ifFilterAgeDoctor = jCAge.isSelected();
				boolean ifFilterFirstNamePatient = false;
				boolean ifFilterLastNamePatient = false;
				boolean ifFilterAgePatient = false;
				boolean ifFilterCCity = jCCity.isSelected();
				boolean ifFilterDoctorSalary = jCSalary.isSelected();
				List<String> doctorsNames = jListName.getSelectedValuesList();
				List<String> doctorsLastnames = jListSurname.getSelectedValuesList();
				List<String> patientsNames = null;
				List<String> patientsLastnames = null;
				List<String> cities = jListCity.getSelectedValuesList();
				int doctorAgeFrom = (int) cbAgeLeft.getSelectedItem();
				int doctorAgeTo = (int) cbAgeRight.getSelectedItem();
				double doctorSalaryFrom = (double) cbSalaryLeft.getSelectedItem();
				double doctorSalaryTo = (double) cbSalaryRight.getSelectedItem();
				modelTable.update(Database.selectInnerJoinFilter(ifFilterFirstNameDoctor, ifFilterLastNameDoctor,
						ifFilterAgeDoctor, ifFilterFirstNamePatient, ifFilterLastNamePatient, ifFilterAgePatient,
						ifFilterCCity, ifFilterDoctorSalary, doctorsNames, doctorsLastnames, patientsNames,
						patientsLastnames, cities, doctorAgeFrom, doctorAgeTo, doctorSalaryFrom, doctorSalaryTo));
				jtable.updateUI();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		btnReset = new JButton("RESET");
		btnReset.addActionListener(e -> {
			try {
				modelTable.update(Database.selectFullInnerJoin());
				customComboboxModelDoctorAgeFrom.setItems(Database.selectFullInnerJoinDoctorAge());
				customComboboxModelDoctorAgeTo.setItems(Database.selectFullInnerJoinDoctorAge());
				cbAgeLeft.updateUI();
				cbAgeRight.updateUI();
				customComboboxModelDoctorSalaryFrom.setItems(Database.selectFullInnerJoinDoctorSalary());
				customComboboxModelDoctorSalaryTo.setItems(Database.selectFullInnerJoinDoctorSalary());
				;
				cbSalaryLeft.updateUI();
				cbSalaryRight.updateUI();
				jCAge.setSelected(false);
				jCCity.setSelected(false);
				jCSurname.setSelected(false);
				jCName.setSelected(false);
				jCSalary.setSelected(false);
				jtable.updateUI();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});

		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints buttonsGBC = new GridBagConstraints();

		buttonsGBC.insets = new Insets(0, 0, 0, 15);

		buttonsGBC.gridx = 0;
		buttonsGBC.gridy = 0;
		buttonsPanel.add(btnFilter, buttonsGBC);

		buttonsGBC.insets = new Insets(0, 10, 0, 0);

		buttonsGBC.gridx = 1;
		buttonsGBC.gridy = 0;
		buttonsPanel.add(btnReset, buttonsGBC);

		// ------------------FILTER PANEL------------------

		JPanel filterPanel = new JPanel(new GridBagLayout());
		GridBagConstraints filterGBC = new GridBagConstraints();

		// ---------first column------

		filterGBC.anchor = GridBagConstraints.LINE_START;
		filterGBC.insets = new Insets(10, 117, 10, 25);
		filterGBC.gridx = 0;
		filterGBC.gridy = 0;
		filterPanel.add(jCName, filterGBC);

		filterGBC.ipadx = 35;
		filterGBC.ipady = 30;

		filterGBC.gridx = 0;
		filterGBC.gridy = 1;
		filterPanel.add(new JScrollPane(jListName), filterGBC);

		filterGBC.ipadx = 0;
		filterGBC.ipady = 0;

		filterGBC.gridx = 0;
		filterGBC.gridy = 2;
		filterPanel.add(jCAge, filterGBC);

		filterGBC.insets = new Insets(10, 10, 10, 10);

		filterGBC.gridx = 1;
		filterGBC.gridy = 2;
		filterPanel.add(lAgeFrom, filterGBC);

		filterGBC.insets = new Insets(5, 117, 5, 25);

		filterGBC.gridx = 0;
		filterGBC.gridy = 3;
		filterPanel.add(jCSalary, filterGBC);

		filterGBC.insets = new Insets(5, 10, 5, 10);
		filterGBC.gridx = 1;
		filterGBC.gridy = 3;
		filterPanel.add(lSalaryFrom, filterGBC);

		// ---------second column----

		filterGBC.gridx = 2;
		filterGBC.gridy = 0;
		filterPanel.add(jCSurname, filterGBC);

		filterGBC.insets = new Insets(10, 15, 10, 15);

		filterGBC.ipadx = 50;
		filterGBC.ipady = 30;

		filterGBC.gridx = 2;
		filterGBC.gridy = 1;
		filterPanel.add(new JScrollPane(jListSurname), filterGBC);

		filterGBC.ipadx = 0;
		filterGBC.ipady = 0;

		filterGBC.insets = new Insets(10, 10, 10, 10);

		filterGBC.gridx = 2;
		filterGBC.gridy = 2;
		filterPanel.add(cbAgeLeft, filterGBC);

		filterGBC.gridx = 2;
		filterGBC.gridy = 3;
		filterPanel.add(cbSalaryLeft, filterGBC);

		// ------------third column--------------------

		filterGBC.insets = new Insets(10, 70, 10, 10);

		filterGBC.gridx = 3;
		filterGBC.gridy = 0;
		filterPanel.add(jCCity, filterGBC);

		filterGBC.insets = new Insets(10, 20, 10, 30);

		filterGBC.ipadx = 18;
		filterGBC.ipady = 30;

		filterGBC.gridx = 3;
		filterGBC.gridy = 1;
		filterPanel.add(new JScrollPane(jListCity), filterGBC);

		filterGBC.ipadx = 0;
		filterGBC.ipady = 0;

		filterGBC.insets = new Insets(0, 0, 0, 0);

		filterGBC.gridx = 3;
		filterGBC.gridy = 2;
		filterPanel.add(lAgeTo, filterGBC);

		filterGBC.gridx = 3;
		filterGBC.gridy = 3;
		filterPanel.add(lSalaryTo, filterGBC);

		// ?

		filterGBC.gridx = 4;
		filterGBC.gridy = 2;
		filterPanel.add(cbAgeRight, filterGBC);

		filterGBC.gridx = 4;
		filterGBC.gridy = 3;
		filterPanel.add(cbSalaryRight, filterGBC);

		// --------------------------------gbc Main-----------------

		JPanel tablePanel = new JPanel(new GridBagLayout());

		jtable.setSelectionMode(JTable.AUTO_RESIZE_OFF);

		add(new JScrollPane(jtable), BorderLayout.LINE_START);

		add(filterPanel, BorderLayout.CENTER);

		add(buttonsPanel, BorderLayout.PAGE_END);

	}

	public void adjustSize() {
		for (int row = 0; row < jtable.getRowCount(); row++) {
			int rowHeight = jtable.getRowHeight();

			for (int column = 0; column < jtable.getColumnCount(); column++) {
				Component comp = jtable.prepareRenderer(jtable.getCellRenderer(row, column), row, column);
				rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
			}

			jtable.setRowHeight(row, rowHeight);
		}
		jtable.updateUI();
	}

	public void updateModels() {
		try {
			modelListCity.update(Database.selectCities());
			modelListName.update(Database.selectNames());
			modelListSurname.update(Database.selectSurnames());
			customComboboxModelDoctorAgeFrom.setItems(Database.selectFullInnerJoinDoctorAge());
			customComboboxModelDoctorAgeTo.setItems(Database.selectFullInnerJoinDoctorAge());
			customComboboxModelDoctorSalaryFrom.setItems(Database.selectFullInnerJoinDoctorSalary());
			customComboboxModelDoctorSalaryTo.setItems(Database.selectFullInnerJoinDoctorSalary());
			jListCity.updateUI();
			jListName.updateUI();
			jListSurname.updateUI();
			cbAgeLeft.updateUI();
			cbAgeRight.updateUI();
			cbSalaryLeft.updateUI();
			cbSalaryRight.updateUI();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
