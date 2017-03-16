package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.sqlite.SQLiteConfig;

import classes.Doctor;
import classes.DoctorWithHospital;
import classes.Hospital;
import classes.Patient;
import classes.PatientCity;
import classes.PatientHospitalDoctor;
import classes.Register;
import classes.User;

public class Database {

	private Database() {

	}

	private static final String DRIVER = "org.sqlite.JDBC";

	private static final String DATABASE = "jdbc:sqlite:Przychodnia.db";

	private static Connection conn;
	private static Statement stat;

	public static void connect() throws ClassNotFoundException, SQLException {

		Class.forName(DRIVER);
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);

		conn = DriverManager.getConnection(DATABASE, config.toProperties());

		stat = conn.createStatement();

	}

	public static void createTables() throws SQLException {

		String HospitalSQL = "CREATE TABLE IF NOT EXISTS Hospital ( " + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "hospitalName VARCHAR(50) NOT NULL, " + "hospitalCity VARCHAR(50) NOT NULL, "
				+ "hospitalAddress VARCHAR(50) NOT NULL, " + "hospitalTelephoneNumber VARCHAR(50) NOT NULL " + " );";

		String DoctorSQL = "CREATE TABLE IF NOT EXISTS Doctor ( " + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " firstName VARCHAR(50) NOT NULL, " + "LastName VARCHAR(50) NOT NULL, " + "Age INTEGER NOT NULL, "
				+ "salary DOUBLE NOT NULL, " + "specialization VARCHAR(50) NOT NULL, " + "hospitalId INTEGER NOT NULL, "
				+ "FOREIGN KEY (HospitalId) REFERENCES Hospital(id) ON DELETE CASCADE ON UPDATE CASCADE" + ");";

		String PatientSQL = "CREATE TABLE IF NOT EXISTS Patient ( " + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " firstName VARCHAR(50) NOT NULL, " + "lastName VARCHAR(50) NOT NULL, " + "age INTEGER NOT NULL, "
				+ "disease VARCHAR(50) NOT NULL," + "emailAddress VARCHAR(50) NOT NULL,"
				+ "mailAddress VARCHAR(50) NOT NULL," + "address VARCHAR(50) NOT NULL,"
				+ "telephoneNumber VARCHAR(50) NOT NULL " + " );";

		String RegisterSQL = "CREATE TABLE IF NOT EXISTS Register ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "idDoctor INTEGER NOT NULL, " + "idPatient INTEGER NOT NULL," + "visitDate TIMESTAMP NOT NULL, "
				+ "FOREIGN KEY (idDoctor) REFERENCES Doctor(id) ON DELETE CASCADE ON UPDATE CASCADE, "
				+ "FOREIGN KEY (idPatient) REFERENCES Patient(id) ON DELETE CASCADE ON UPDATE CASCADE " + " );";

		String UserSQL = "CREATE TABLE IF NOT EXISTS User( " + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "firstName VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age INTEGER NOT NULL,"
				+ "username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, role VARCHAR(50) NOT NULL, numbersToDecrypt VARCHAR(50) NOT NULL "
				+ " ); ";

		stat.execute(HospitalSQL);
		stat.execute(DoctorSQL);
		stat.execute(PatientSQL);
		stat.execute(RegisterSQL);
		stat.execute(UserSQL);

	}

	public static void insertHospital(Hospital h) throws SQLException {
		String sqlInsert = "INSERT INTO Hospital (hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber) VALUES (?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setString(1, h.getHospitalName());
		ps.setString(2, h.getHospitalCity());
		ps.setString(3, h.getHospitalAddress());
		ps.setString(4, h.getHospitalTelephoneNumber());
		ps.execute();

	}

	public static void insertDoctor(Doctor d) throws SQLException {

		String sqlInsert = "INSERT INTO Doctor (firstName, lastName, age, salary, specialization, hospitalId) VALUES (?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setString(1, d.getFirstName());
		ps.setString(2, d.getLastName());
		ps.setInt(3, d.getAge());
		ps.setDouble(4, d.getSalary());
		ps.setString(5, d.getSpecialization());
		ps.setInt(6, d.getHospitalId());
		ps.execute();
	}

	public static void insertPatient(Patient p) throws SQLException {

		String sqlInsert = "INSERT INTO Patient (firstName, lastName, age, disease, emailAddress,  mailAddress, "
				+ " address, telephoneNumber) VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		ps.setInt(3, p.getAge());
		ps.setString(4, p.getDisease());
		ps.setString(5, p.getEmailAddress());
		ps.setString(6, p.getMailAddress());
		ps.setString(7, p.getAddress());
		ps.setString(8, p.getTelephoneNumber());
		ps.execute();
	}

	public static void insertUser(User u) throws SQLException {

		String sqlInsert = "INSERT INTO User (firstName, lastName, age, username, password, role, numbersToDecrypt "
				+ ") VALUES (?,?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setString(1, u.getFirstName());
		ps.setString(2, u.getLastName());
		ps.setInt(3, u.getAge());
		ps.setString(4, u.getUsername());
		ps.setString(5, u.getPassword());
		ps.setString(6, u.getRole());
		ps.setString(7, u.getNumbersToDecrypt());
		ps.execute();
	}

	public static void insertRegister(Register r) throws SQLException {

		String sqlInsert = "INSERT INTO Register (idDoctor, idPatient, visitDate) VALUES (?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sqlInsert);
		ps.setInt(1, r.getIdDoctor());
		ps.setInt(2, r.getIdPatient());
		ps.setTimestamp(3, Timestamp.valueOf(r.getVisitDate()));
		ps.execute();
	}

	public static void updateDoctor(Doctor l) throws SQLException {

		String sqlUpdate = "UPDATE Doctor SET id=?, firstName = ?, lastName = ?, age = ?, salary = ?, specialization = ?, hospitalId =?"
				+ " WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlUpdate);
		ps.setInt(1, l.getId());
		ps.setString(2, l.getFirstName());
		ps.setString(3, l.getLastName());
		ps.setInt(4, l.getAge());
		ps.setDouble(5, l.getSalary());
		ps.setString(6, l.getSpecialization());
		ps.setInt(7, l.getHospitalId());
		ps.setInt(8, l.getId());
		ps.execute();

	}

	public static void updatePatient(Patient p) throws SQLException {

		String sqlUpdate = "UPDATE Patient SET firstName = ?, lastName = ?, age = ?, disease = ?, emailAddress = ?,  mailAddress = ?, "
				+ " address = ?, telephoneNumber = ? WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlUpdate);
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		ps.setInt(3, p.getAge());
		ps.setString(4, p.getDisease());
		ps.setString(5, p.getEmailAddress());
		ps.setString(6, p.getMailAddress());
		ps.setString(7, p.getAddress());
		ps.setString(8, p.getTelephoneNumber());
		ps.setInt(9, p.getId());
		ps.execute();
	}

	public static void updateHospital(Hospital h) throws SQLException {

		String sqlUpdate = "UPDATE Hospital SET hospitalName = ?, hospitalCity = ?, hospitalAddress = ?, hospitalTelephoneNumber = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlUpdate);
		ps.setString(1, h.getHospitalName());
		ps.setString(2, h.getHospitalCity());
		ps.setString(3, h.getHospitalAddress());
		ps.setString(4, h.getHospitalTelephoneNumber());
		ps.setInt(5, h.getId());
		ps.execute();
	}

	public static void updateUser(User u) throws SQLException {

		String sqlUpdate = "UPDATE User SET firstName = ?, lastName = ?, age = ?, username = ?, password = ?, role = ?, numbersToDecrypt = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlUpdate);
		ps.setString(1, u.getFirstName());
		ps.setString(2, u.getLastName());
		ps.setInt(3, u.getAge());
		ps.setString(4, u.getUsername());
		ps.setString(5, u.getPassword());
		ps.setString(6, u.getUsername());
		ps.setString(7, u.getNumbersToDecrypt());
		ps.execute();
	}

	public static void updateRegister(Register r) throws SQLException {

		String sqlUpdate = "UPDATE Register SET idDoctor = ?, idPatient = ?, visitDate = ?, WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlUpdate);
		ps.setInt(1, r.getIdDoctor());
		ps.setInt(2, r.getIdPatient());
		ps.setTimestamp(3, Timestamp.valueOf(r.getVisitDate()));
		ps.execute();
	}

	public static void deleteDoctor(int id) throws SQLException {

		String sqlDelete = "DELETE FROM Doctor WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sqlDelete);
		ps.setInt(1, id);
		ps.execute();
	}

	public static void deletePatient(int id) throws SQLException {

		String sqlDelete = "DELETE FROM Patient WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sqlDelete);
		ps.setInt(1, id);
		ps.execute();
	}

	public static void deleteUser(int id) throws SQLException {

		String sqlDelete = "DELETE FROM User WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sqlDelete);
		ps.setInt(1, id);
		ps.execute();
	}

	public static void deleteHospital(int id) throws SQLException {

		String sqlDelete = "DELETE FROM Hospital WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sqlDelete);
		ps.setInt(1, id);
		ps.execute();
	}

	public static void deleteRegister(int id) throws SQLException {

		String sqlDelete = "DELETE FROM Register WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlDelete);
		ps.setInt(1, id);
		ps.execute();

	}

	public static List<Doctor> selectDoctor() throws SQLException {

		String sqlSelect = "SELECT * FROM Doctor";

		ResultSet rs = stat.executeQuery(sqlSelect);

		int id, age, hospitalId;
		String firstName, lastName, specialization;
		double salary;

		List<Doctor> doctors = new ArrayList<>();

		while (rs.next()) {

			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			age = rs.getInt(4);
			salary = rs.getDouble(5);
			specialization = rs.getString(6);
			hospitalId = rs.getInt(7);

			doctors.add(new Doctor(id, firstName, lastName, age, salary, specialization, hospitalId));
		}

		return doctors;

	}

	public static Doctor selectDoctorsById(int idD) throws SQLException {

		String sqlSelect = "SELECT * FROM Doctor WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);
		ps.setInt(1, idD);
		ResultSet rs = ps.executeQuery();

		int id, age, hospitalId;
		String firstName, lastName, specialization;
		double salary;

		if (rs.next()) {

			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			age = rs.getInt(4);
			salary = rs.getDouble(5);
			specialization = rs.getString(6);
			hospitalId = rs.getInt(7);

			return new Doctor(id, firstName, lastName, age, salary, specialization, hospitalId);
		}
		return null;
	}

	public static List<Patient> selectPatient() throws SQLException {

		String sqlSelect = "SELECT * FROM Patient";

		ResultSet rs = stat.executeQuery(sqlSelect);

		int id, age;
		String firstName, lastName, disease, emailAddress, mailAddress, address, telephoneNumber;
		List<Patient> patientList = new ArrayList<>();
		while (rs.next()) {

			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			age = rs.getInt(4);
			disease = rs.getString(5);
			emailAddress = rs.getString(6);
			mailAddress = rs.getString(7);
			address = rs.getString(8);
			telephoneNumber = rs.getString(9);

			patientList.add(new Patient(id, firstName, lastName, age, disease, emailAddress, mailAddress, address,
					telephoneNumber));
		}

		return patientList;
	}

	public static Patient selectPatientById(int idD) throws SQLException {

		String sqlSelect = "SELECT * FROM Patient WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);
		ps.setInt(1, idD);
		ResultSet rs = ps.executeQuery();

		int id, age;
		String firstName, lastName, disease, emailAddress, mailAddress, address, telephoneNumber;

		if (rs.next()) {

			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			age = rs.getInt(4);
			disease = rs.getString(5);
			emailAddress = rs.getString(6);
			mailAddress = rs.getString(7);
			address = rs.getString(8);
			telephoneNumber = rs.getString(9);

			return new Patient(id, firstName, lastName, age, disease, emailAddress, mailAddress, address,
					telephoneNumber);
		}
		return null;

	}

	public static List<Hospital> selectHospital() throws SQLException {

		String sqlSelect = "SELECT * FROM Hospital";

		ResultSet rs = stat.executeQuery(sqlSelect);

		int id;
		String hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber;

		List<Hospital> hospitals = new ArrayList<>();

		while (rs.next()) {

			id = rs.getInt(1);
			hospitalName = rs.getString(2);
			hospitalCity = rs.getString(3);
			hospitalAddress = rs.getString(4);
			hospitalTelephoneNumber = rs.getString(5);

			hospitals.add(new Hospital(id, hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber));

		}

		return hospitals;
	}

	public static List<Register> selectRegister() throws SQLException {

		String sqlSeletc = "SELECT * FROM Register";

		ResultSet rs = stat.executeQuery(sqlSeletc);

		int id, idDoctor, idPatient;
		LocalDateTime visitDate;

		List<Register> registers = new ArrayList<>();

		while (rs.next()) {

			id = rs.getInt(1);
			idDoctor = rs.getInt(2);
			idPatient = rs.getInt(3);
			visitDate = rs.getTimestamp(4).toLocalDateTime();

			registers.add(new Register(id, idDoctor, idPatient, visitDate));
		}

		return registers;
	}

	public static List<String> selectCities() throws SQLException {
		String sqlInnerJoinCities = "SELECT DISTINCT H.hospitalCity FROM " + "Patient P INNER JOIN Register R "
				+ "ON P.id = R.idPatient " + "INNER JOIN Doctor D ON D.id = R.idDoctor "
				+ "INNER JOIN Hospital H ON D.hospitalId = H.id;";

		ResultSet rs = stat.executeQuery(sqlInnerJoinCities);

		List<String> citiesList = new LinkedList<>();

		while (rs.next()) {

			citiesList.add(rs.getString(1));

		}
		return citiesList;
	}

	public static List<String> selectNames() throws SQLException {
		String sqlInnerJoinCities = "SELECT DISTINCT D.firstName FROM " + "Patient P INNER JOIN Register R "
				+ "ON P.id = R.idPatient " + "INNER JOIN Doctor D ON D.id = R.idDoctor "
				+ "INNER JOIN Hospital H ON D.hospitalId = H.id;";

		ResultSet rs = stat.executeQuery(sqlInnerJoinCities);

		List<String> namesList = new LinkedList<>();

		while (rs.next()) {

			namesList.add(rs.getString(1));

		}
		return namesList;
	}

	public static List<String> selectSurnames() throws SQLException {
		String sqlInnerJoinCities = "SELECT DISTINCT P.lastName FROM " + "Patient P INNER JOIN Register R "
				+ "ON P.id = R.idPatient " + "INNER JOIN Doctor D ON D.id = R.idDoctor "
				+ "INNER JOIN Hospital H ON D.hospitalId = H.id;";

		ResultSet rs = stat.executeQuery(sqlInnerJoinCities);

		List<String> lastNameList = new LinkedList<>();

		while (rs.next()) {

			lastNameList.add(rs.getString(1));

		}
		return lastNameList;
	}

	public static List<DoctorWithHospital> selectInnerJoin() throws SQLException {

		String sqlInnerJoin = "SELECT D.id, D.firstName, D.lastName, D.age, D.salary, D.specialization, "
				+ "H.hospitalName, H.hospitalCity, H.hospitalAddress, H.hospitalTelephoneNumber "
				+ "FROM Doctor D INNER JOIN Hospital H ON D.HospitalId = H.id;";

		List<DoctorWithHospital> doctorHospital = new ArrayList<>();

		ResultSet rs = stat.executeQuery(sqlInnerJoin);

		int id, age;
		String firstName, lastName, specialization, hospitalName, hospitalCity, hospitalAddress,
				hospitalTelephoneNumber;
		double salary;

		while (rs.next()) {

			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			age = rs.getInt(4);
			salary = rs.getDouble(5);
			specialization = rs.getString(6);
			hospitalName = rs.getString(7);
			hospitalCity = rs.getString(8);
			hospitalAddress = rs.getString(9);
			hospitalTelephoneNumber = rs.getString(10);
			doctorHospital.add(new DoctorWithHospital(id, firstName, lastName, age, salary, specialization,
					hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber));

		}

		return doctorHospital;
	}

	public static List<String> selectDoctorIdFirstNameLastName() throws SQLException {

		String sqlSelect = "SELECT id, firstName, lastName FROM Doctor";

		ResultSet rs = stat.executeQuery(sqlSelect);

		int id;
		String firstName, lastName;

		List<String> doctorList = new ArrayList<>();

		while (rs.next()) {
			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			doctorList.add(id + " " + firstName + " " + lastName);
		}

		return doctorList;
	}

	public static DoctorWithHospital selectDoctorWithHospitalById(int idx) throws SQLException {

		String sqlSelectWithInnerJoin = " SELECT D.id, D.firstName, D.lastName, D.age, D.salary, "
				+ " D.specialization, H.hospitalName, H.hospitalCity, H.hospitalAddress,"
				+ " H. hospitalTelephoneNumber FROM Doctor D INNER JOIN Hospital H "
				+ "ON D.HospitalId = H.id WHERE D.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sqlSelectWithInnerJoin);
		ps.setInt(1, idx);
		ResultSet rs = ps.executeQuery();

		int id, age;
		double salary;
		String firstName, lastName, hospitalName, hospitalCity, specialization, hospitalAddress,
				hospitalTelephoneNumber;

		if (rs.next()) {

			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			age = rs.getInt(4);
			salary = rs.getDouble(5);
			specialization = rs.getString(6);
			hospitalName = rs.getString(7);
			hospitalCity = rs.getString(8);
			hospitalAddress = rs.getString(9);
			hospitalTelephoneNumber = rs.getString(10);

			return new DoctorWithHospital(id, firstName, lastName, age, salary, specialization, hospitalName,
					hospitalCity, hospitalAddress, hospitalTelephoneNumber);

		}
		return null;
	}

	public static List<String> selectPatientIdFirstNameLastName() throws SQLException {

		String sqlSelect = "SELECT id, firstName, lastName FROM Patient";

		ResultSet rs = stat.executeQuery(sqlSelect);

		int id;
		String firstName, lastName;

		List<String> patientList = new ArrayList<>();

		while (rs.next()) {
			id = rs.getInt(1);
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			patientList.add(id + " " + firstName + " " + lastName);
		}

		return patientList;
	}

	public static List<Integer> selectDoctorAge() throws SQLException {

		String selectSQL = "SELECT Age FROM Doctor";

		ResultSet rs = stat.executeQuery(selectSQL);

		List<Integer> doctorAgesList = new ArrayList<>();

		while (rs.next()) {

			doctorAgesList.add(rs.getInt(1));

		}

		Collections.sort(doctorAgesList);

		return doctorAgesList;
	}

	public static List<Double> selectDoctorSalary() throws SQLException {

		String selectSQL = "SELECT salary FROM Doctor";

		ResultSet rs = stat.executeQuery(selectSQL);

		List<Double> doctorSalariesList = new ArrayList<>();

		while (rs.next()) {

			doctorSalariesList.add(rs.getDouble(1));

		}

		Collections.sort(doctorSalariesList);

		return doctorSalariesList;
	}

	public static List<PatientHospitalDoctor> selectInnerJoinFilter(boolean ifFilterFirstNameDoctor,
			boolean ifFilterLastNameDoctor, boolean ifFilterAgeDoctor, boolean ifFilterFirstNamePatient,
			boolean ifFilterLastNamePatient, boolean ifFilterAgePatient, boolean ifFilterCCity,
			boolean ifFilterDoctorSalary, List<String> doctorsNames, List<String> doctorsLastnames,
			List<String> patientsNames, List<String> patientsLastnames, List<String> cities, int doctorAgeFrom,
			int doctorAgeTo, double doctorSalaryFrom, double doctorSalaryTo) throws SQLException {

		String sqlInnerJoin = "SELECT R.id, D.firstName, D.lastName, D.Age, D.salary, D.specialization, "
				+ "H.hospitalName, H.hospitalCity, H.hospitalAddress, H.hospitalTelephoneNumber, "
				+ "P.firstName, P.lastName, P.age, P.disease, P.emailAddress, P.mailAddress,P.address, P.telephoneNumber "
				+ "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ "WHERE 1 = 1 ";

		if (ifFilterFirstNameDoctor && doctorsNames != null && !doctorsNames.isEmpty()) {

			String nameCond = " AND D.firstName IN ('" + String.join("' , '", doctorsNames) + "')";
			sqlInnerJoin += nameCond;
		}
		if (ifFilterFirstNamePatient && patientsNames != null && !patientsNames.isEmpty()) {

			String nameCond = " AND P.firstName IN ('" + String.join("' , '", patientsNames) + "')";
			sqlInnerJoin += nameCond;
		}
		if (ifFilterLastNameDoctor && doctorsLastnames != null && !doctorsLastnames.isEmpty()) {

			String lastNameCond = " AND D.lastName IN ('" + String.join("' , '", doctorsLastnames) + "')";
			sqlInnerJoin += lastNameCond;
		}
		if (ifFilterLastNamePatient && patientsLastnames != null && !patientsLastnames.isEmpty()) {

			String lastNameCond = " AND P.lastName IN ('" + String.join("' , '", patientsLastnames) + "')";
			sqlInnerJoin += lastNameCond;
		}
		if (ifFilterCCity && cities != null && !cities.isEmpty()) {

			String cityCond = " AND H.hospitalCity IN ('" + String.join("' , '", cities) + "')";
			sqlInnerJoin += cityCond;
		}
		if (ifFilterAgeDoctor) {

			String ageCond = " AND D.Age BETWEEN " + doctorAgeFrom + " AND " + doctorAgeTo;
			sqlInnerJoin += ageCond;
		}

		if (ifFilterDoctorSalary) {

			String salaryCond = " AND D.salary BETWEEN " + doctorSalaryFrom + " AND " + doctorSalaryTo;
			sqlInnerJoin += salaryCond;
		}

		int registrationId;
		String doctorFirstName;
		String doctorLastName;
		int doctorAge;
		double doctorSalary;
		String specialization;
		String hospitalName;
		String hospitalCity;
		String hospitalAddress;
		String hospitalTelephoneNumber;
		String patientFirstName;
		String patientLastName;
		int patientAge;
		String patientDisease;
		String patientEmailAddress;
		String patientMailAddress;
		String patientAddress;
		String patientTelephoneNumber;

		List<PatientHospitalDoctor> filter = new ArrayList<>();

		ResultSet rs = stat.executeQuery(sqlInnerJoin);
		while (rs.next()) {

			registrationId = rs.getInt(1);
			;
			doctorFirstName = rs.getString(2);
			doctorLastName = rs.getString(3);
			doctorAge = rs.getInt(4);
			doctorSalary = rs.getDouble(5);
			specialization = rs.getString(6);
			hospitalName = rs.getString(7);
			hospitalCity = rs.getString(8);
			hospitalAddress = rs.getString(9);
			hospitalTelephoneNumber = rs.getString(10);
			patientFirstName = rs.getString(11);
			patientLastName = rs.getString(12);
			patientAge = rs.getInt(13);
			patientDisease = rs.getString(14);
			patientEmailAddress = rs.getString(15);
			patientMailAddress = rs.getString(16);
			patientAddress = rs.getString(17);
			patientTelephoneNumber = rs.getString(18);

			filter.add(new PatientHospitalDoctor(registrationId, doctorFirstName, doctorLastName, doctorAge,
					doctorSalary, specialization, hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber,
					patientFirstName, patientLastName, patientAge, patientDisease, patientEmailAddress,
					patientMailAddress, patientAddress, patientTelephoneNumber));
		}

		return filter;
	}

	public static List<PatientHospitalDoctor> selectFullInnerJoin() throws SQLException {

		String sqlInnerJoin = "SELECT R.id, D.firstName, D.lastName, D.Age, D.salary, D.specialization, "
				+ "H.hospitalName, H.hospitalCity, H.hospitalAddress, H.hospitalTelephoneNumber, "
				+ "P.firstName, P.lastName, P.age, P.disease, P.emailAddress, P.mailAddress, P.address, "
				+ "P.telephoneNumber " + "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id;";

		int registrationId;
		String doctorFirstName;
		String doctorLastName;
		int doctorAge;
		double doctorSalary;
		String specialization;
		String hospitalName;
		String hospitalCity;
		String hospitalAddress;
		String hospitalTelephoneNumber;
		String patientFirstName;
		String patientLastName;
		int patientAge;
		String patientDisease;
		String patientEmailAddress;
		String patientMailAddress;
		String patientAddress;
		String patientTelephoneNumber;

		List<PatientHospitalDoctor> filter = new ArrayList<>();

		ResultSet rs = stat.executeQuery(sqlInnerJoin);
		while (rs.next()) {

			registrationId = rs.getInt(1);
			doctorFirstName = rs.getString(2);
			doctorLastName = rs.getString(3);
			doctorAge = rs.getInt(4);
			doctorSalary = rs.getDouble(5);
			specialization = rs.getString(6);
			hospitalName = rs.getString(7);
			hospitalCity = rs.getString(8);
			hospitalAddress = rs.getString(9);
			hospitalTelephoneNumber = rs.getString(10);
			patientFirstName = rs.getString(11);
			patientLastName = rs.getString(12);
			patientAge = rs.getInt(13);
			patientDisease = rs.getString(14);
			patientEmailAddress = rs.getString(15);
			patientMailAddress = rs.getString(16);
			patientAddress = rs.getString(17);
			patientTelephoneNumber = rs.getString(18);

			filter.add(new PatientHospitalDoctor(registrationId, doctorFirstName, doctorLastName, doctorAge,
					doctorSalary, specialization, hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber,
					patientFirstName, patientLastName, patientAge, patientDisease, patientEmailAddress,
					patientMailAddress, patientAddress, patientTelephoneNumber));
		}

		return filter;
	}

	public static Hospital selectHospitalByID(int ID) throws SQLException {

		String sqlSelect = "SELECT * FROM Hospital WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);
		ps.setInt(1, ID);
		ResultSet rs = ps.executeQuery();

		int id;
		String hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber;

		if (rs.next()) {

			id = rs.getInt(1);
			hospitalName = rs.getString(2);
			hospitalCity = rs.getString(3);
			hospitalAddress = rs.getString(4);
			hospitalTelephoneNumber = rs.getString(5);

			return new Hospital(id, hospitalName, hospitalCity, hospitalAddress, hospitalTelephoneNumber);
		}
		return null;
	}

	public static List<String> selectHospitalId() throws SQLException {

		String sqlSelect = "SELECT id, hospitalName FROM Hospital";

		ResultSet rs = stat.executeQuery(sqlSelect);

		int id;
		String hospitalName;

		List<String> listIds = new ArrayList<>();

		while (rs.next()) {

			id = rs.getInt(1);
			hospitalName = rs.getString(2);
			listIds.add(id + " " + hospitalName);
		}

		return listIds;
	}

	public static Integer selectHospitalIdByOtherHospitalData(String hospitalName, String hospitalCity,
			String hospitalAddress, String hospitalTelephoneNumber

	) throws SQLException {
		String sqlSelect = "SELECT id FROM Hospital WHERE hospitalName =? AND hospitalCity = ?"
				+ " AND hospitalAddress = ? AND hospitalTelephoneNumber = ?";

		PreparedStatement prep = conn.prepareStatement(sqlSelect);
		prep.setString(1, hospitalName);
		prep.setString(2, hospitalCity);
		prep.setString(3, hospitalAddress);
		prep.setString(4, hospitalTelephoneNumber);

		ResultSet rs = prep.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return null;
		}
	}

	public static List<Integer> selectFullInnerJoinLowerEqualDocotrAge(int age) throws SQLException {

		String sqlInnerJoin = "SELECT DISTINCT D.age " + "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ "WHERE D.age <= ?  ORDER BY D.age;";

		List<Integer> ageList = new ArrayList<>();

		PreparedStatement ps = conn.prepareStatement(sqlInnerJoin);
		ps.setInt(1, age);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ageList.add(rs.getInt(1));
		}
		return ageList;
	}

	public static List<Double> selectFullInnerJoinLowerEqualDocotrSalary(double salary) throws SQLException {

		String sqlInnerJoin = "SELECT DISTINCT D.salary "
				+ "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ "WHERE D.salary <= ?  ORDER BY D.salary;";

		List<Double> salaryList = new ArrayList<>();

		PreparedStatement ps = conn.prepareStatement(sqlInnerJoin);
		ps.setDouble(1, salary);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			salaryList.add(rs.getDouble(1));
		}
		return salaryList;
	}

	public static List<Integer> selectFullInnerJoinDoctorAge() throws SQLException {

		String sqlInnerJoin = "SELECT DISTINCT D.age " + "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ " ORDER BY D.age;";

		List<Integer> ageList = new ArrayList<>();

		ResultSet rs = stat.executeQuery(sqlInnerJoin);
		while (rs.next()) {
			ageList.add(rs.getInt(1));
		}
		return ageList;
	}

	public static List<Double> selectFullInnerJoinDoctorSalary() throws SQLException {

		String sqlInnerJoin = "SELECT DISTINCT D.salary "
				+ "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ " ORDER BY D.salary;";

		List<Double> salaryList = new ArrayList<>();

		ResultSet rs = stat.executeQuery(sqlInnerJoin);
		while (rs.next()) {
			salaryList.add(rs.getDouble(1));
		}
		return salaryList;
	}

	public static List<Integer> selectFullInnerJoinGreaterEqualDoctorAge(int age) throws SQLException {

		String sqlInnerJoin = "SELECT DISTINCT D.age " + "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ "WHERE D.age >= ? ORDER BY D.age;";

		List<Integer> ageList = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement(sqlInnerJoin);
		ps.setInt(1, age);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ageList.add(rs.getInt(1));
		}
		return ageList;
	}

	public static List<Double> selectFullInnerJoinGreaterEqualDoctorSalary(double salary) throws SQLException {

		String sqlInnerJoin = "SELECT DISTINCT D.salary "
				+ "FROM Patient P INNER JOIN Register R ON R.idPatient = P.id "
				+ "INNER JOIN Doctor D ON D.id = R.idDoctor " + "INNER JOIN Hospital H ON D.hospitalId = H.id "
				+ "WHERE D.salary >= ? ORDER BY D.salary;";

		List<Double> salaryList = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement(sqlInnerJoin);
		ps.setDouble(1, salary);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			salaryList.add(rs.getDouble(1));
		}
		return salaryList;
	}

	public static List<String> selectTheOldestPatient() throws SQLException {

		int ageMax = 0;

		String sqlSelectMax = "SELECT MAX(age) FROM Patient";
		ResultSet rs = stat.executeQuery(sqlSelectMax);

		while (rs.next()) {

			ageMax = rs.getInt(1);

		}

		String selectOldestPatients = "SELECT firstName, lastName FROM Patient WHERE age = ? ";

		PreparedStatement ps = conn.prepareStatement(selectOldestPatients);
		ps.setInt(1, ageMax);

		ResultSet rs2 = ps.executeQuery();
		List<String> list = new ArrayList<>();

		while (rs2.next()) {

			list.add(rs2.getString(1) + " " + rs2.getString(2) + " - " + ageMax);

		}

		return list;
	}

	public static List<String> selectMinMaxDoctorSalary() throws SQLException {
		String sqlSelectMin = "Select Min(Salary), firstName, lastName from Doctor";

		String sqlSelectMax = "Select Max(Salary), firstName, lastName from Doctor";

		List<String> minMax = new ArrayList<>();
		double min = 0, max = 0;

		ResultSet rs1 = stat.executeQuery(sqlSelectMin);

		String sqlSelect = "SELECT firstName, lastName, Salary FROM Doctor WHERE Salary = ?";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);

		while (rs1.next()) {

			min = rs1.getDouble(1);

		}
		ps.setDouble(1, min);

		ResultSet rs3 = ps.executeQuery();

		while (rs3.next()) {

			minMax.add(rs3.getString(1) + " " + rs3.getString(2) + " - " + min);

		}

		ResultSet rs2 = stat.executeQuery(sqlSelectMax);

		while (rs2.next()) {

			max = rs2.getDouble(1);

		}

		ps.setDouble(1, max);
		ResultSet rs4 = ps.executeQuery();

		while (rs3.next()) {

			minMax.add(rs4.getString(1) + " " + rs4.getString(2) + " - " + max);

		}

		return minMax;
	}

	public static String selectAveragePatientAge() throws SQLException {

		String sqlSelect = "SELECT AVG(Age) FROM Patient";

		double avgAge = 0;

		ResultSet rs = stat.executeQuery(sqlSelect);

		while (rs.next()) {

			avgAge = rs.getDouble(1);
		}

		return String.format("%.2f", avgAge);
	}

	public static List<PatientCity> selectPatientCity() throws SQLException {
		List<PatientCity> patientCity = new ArrayList<>();

		String sqlSelectWithInnerJoin = "SELECT P.firstName, P.lastName, " + "H.hospitalCity "
				+ " FROM Patient P INNER JOIN Register R " + "ON R.id = P.id INNER JOIN Doctor D ON R.id = H.id "
				+ "INNER JOIN Hospital H ON H.id = D.id";

		ResultSet rs = stat.executeQuery(sqlSelectWithInnerJoin);

		String firstName;
		String lastName;
		String city;

		while (rs.next()) {
			firstName = rs.getString(1);
			lastName = rs.getString(2);
			city = rs.getString(3);

			patientCity.add(new PatientCity(firstName, lastName, city));

		}
		return patientCity;

	}

	public static String selectByUsername(String username) throws SQLException {
		String sqlSelect = "SELECT role from User WHERE username = ?";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getString(1);
		}

		return null;
	}

	public static boolean checkRegistrationDate(LocalDateTime date) throws SQLException {// ?

		String sqlSelect = "SELECT idDoctor, visitDate FROM Register WHERE visitDate = ?";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);
		ps.setTimestamp(1, Timestamp.valueOf(date));

		ResultSet rs = ps.executeQuery();

		return rs.isBeforeFirst();

	}

	public static Optional<User> returnUser(String username, String password) throws SQLException, IOException {

		String selectSQl = "SELECT * FROM User WHERE username = ? AND password = ?";
		PreparedStatement ps = conn.prepareStatement(selectSQl);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		Optional<User> op = Optional.ofNullable(null);
		if (rs.next()) {
			User u = new User();
			u.setId(rs.getInt(1));
			u.setFirstName(rs.getString(2));
			u.setLastName(rs.getString(3));
			u.setAge(rs.getInt(4));
			u.setUsername(rs.getString(5));
			u.setPassword(String.valueOf(rs.getString(6)));
			u.setRole(rs.getString(7));
			u.setNumbersToDecrypt(rs.getString(8));

			op = Optional.of(u);
			return op;
		}
		return op;

	}

	public static String returnUsrPassword(String username) throws SQLException {

		String selectSQl = "SELECT password FROM User WHERE username = ? ";
		PreparedStatement ps = conn.prepareStatement(selectSQl);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		return rs.getString(1);

	}

	public static String returnUsrNumbers(String username) throws SQLException {

		String selectSQl = "SELECT numbersToDecrypt FROM User WHERE username = ?";
		PreparedStatement ps = conn.prepareStatement(selectSQl);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		return rs.getString(1);

	}

	public static boolean checkUsername(String username) throws SQLException {

		String sqlSelect = "SELECT username FROM User WHERE username = ? ";

		PreparedStatement ps = conn.prepareStatement(sqlSelect);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		return rs.isBeforeFirst();
	}
}
