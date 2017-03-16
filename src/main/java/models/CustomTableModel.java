package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import classes.PatientHospitalDoctor;

public class CustomTableModel extends AbstractTableModel {
	List<PatientHospitalDoctor> rows;
	List<String> columns = new ArrayList<>(
			Arrays.asList(new String[] { "Id", "Doctor first name", "Doctor last name", "Doctor age", "Doctor salary",
					"Patient first name", "Patient last name", "Patient age", "Hospital city" }));

	public CustomTableModel(List<PatientHospitalDoctor> rows) {
		this.rows = rows;
	}

	public void update(List<PatientHospitalDoctor> rows) {
		this.rows = rows;
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PatientHospitalDoctor row = rows.get(rowIndex);
		if (columnIndex == 0) {
			return row.getRegistrationId();
		} else if (columnIndex == 1) {

			return row.getDoctorFirstName();
		} else if (columnIndex == 2) {

			return row.getDoctorLastName();
		} else if (columnIndex == 3) {

			return row.getDoctorAge();
		} else if (columnIndex == 4) {

			return row.getDoctorSalary();
		} else if (columnIndex == 5) {

			return row.getSpecialization();
		} else if (columnIndex == 6) {

			return row.getHospitalName();
		} else if (columnIndex == 7) {

			return row.getHospitalCity();
		} else if (columnIndex == 8) {

			return row.getHospitalAddress();
		} else if (columnIndex == 9) {

			return row.getHospitalTelephoneNumber();
		} else if (columnIndex == 10) {

			return row.getPatientFirstName();
		} else if (columnIndex == 11) {

			return row.getPatientLastName();
		} else if (columnIndex == 12) {

			return row.getPatientAge();
		} else if (columnIndex == 13) {

			return row.getPatientDisease();
		} else if (columnIndex == 14) {

			return row.getPatientAddress();
		} else if (columnIndex == 15) {

			return row.getPatientEmailAddress();
		} else if (columnIndex == 16) {

			return row.getPatientMailAddress();
		} else if (columnIndex == 17) {

			return row.getPatientAddress();
		} else {
			return row.getPatientTelephoneNumber();
		}
	}

	@Override
	public String getColumnName(int column) {
		return columns.get(column);
	}

}
