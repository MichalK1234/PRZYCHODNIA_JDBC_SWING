package przychodnia;

import java.sql.SQLException;

import javax.swing.JFrame;

import database.Database;
import panels.PanelLogin;

public class App {
	
	//PROGRAM WYKONANY W RAMACH SZKOLENIA KM-PROGRAMS
	//http://km-programs.pl/

	public static void createWindow() throws SQLException {

		JFrame frame = new JFrame("Doctor's surgery ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelLogin panel = new PanelLogin();
		// PanelInsert panel = new PanelInsert();
		// PanelUpdate panel = new PanelUpdate();
		// PanelMain panel = new PanelMain();
		// PanelRegister panel = new PanelRegister();
		// PanelStatistics panel = new PanelStatistics();
		// PanelFilter panel = new PanelFilter();
		// PanelDoctor panel = new PanelDoctor();
		panel.setVisible(true);
		frame.setContentPane(panel);
		// frame.setJMenuBar(panel.createJMenu());
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		/*
		 * Database.connect(); Database.createTables();
		 * 
		 * Database.selectCities().forEach(System.out::println);
		 * Database.selectNames().forEach(System.out::println);
		 * Database.selectSurnames().forEach(System.out::println);
		 */

		Database.connect();
		Database.createTables();

		/*
		 * Database.insertHospital(new Hospital(0, "XXX", "SZCZECIN", "aaa AAA",
		 * "1 333 444")); Database.insertHospital(new Hospital(1, "YYY",
		 * "WARSZAWA", "bbb AAA", "2 333 444")); Database.insertHospital(new
		 * Hospital(2, "ZZZ", "GDANSK", "ccc AAA", "3 333 444"));
		 * Database.insertHospital(new Hospital(3, "UUU", "POZNAN", "ddd AAA",
		 * "4 333 444")); Database.insertHospital(new Hospital(4, "QQQ",
		 * "KRAKOW", "eee AAA", "5 333 444"));
		 * 
		 * //Database.updateHospital(new Hospital(1, "XYZ2", "SZCZECIN2",
		 * "ASW WAZ DEW", "222 333 444")); //Database.deleteHospital(2);
		 * 
		 * Database.insertDoctor(new Doctor(0, "JAN", "KOWALSKI", 40, 4500,
		 * "AAA",2)); Database.insertDoctor(new Doctor(1, "ANDRZEJ",
		 * "ZWIERCIADLO", 54, 5600, "BBB",2)); Database.insertDoctor(new
		 * Doctor(2, "JUREK", "OGOREK", 34, 2300, "CCC",1));
		 * Database.insertDoctor(new Doctor(3, "JANEK", "DZBANEK", 42, 9200,
		 * "DDD",3)); Database.insertDoctor(new Doctor(4, "ANNA", "ZWIERZ", 54,
		 * 25600, "RRR",2)); Database.insertDoctor(new Doctor(5, "BEATA", "OSA",
		 * 34, 12300, "EEE",4));
		 * 
		 * 
		 * //Database.updateLekarz(new Lekarz(1, "JAN2", "KOWALSKI2", 40, 2300,
		 * "XXX")); //Database.insertLekarz(new Lekarz(0, "JAN3", "KOWALSKI3",
		 * 40, 2300, "XXX")); //Database.deleteLekarz(1);
		 * 
		 * Database.insertPatient(new Patient(1, "JUREK", "OGOREK", 24, "katar",
		 * "jerzy@wp.pl", "aaa", "AAA", "11 11 11 111"));
		 * Database.insertPatient(new Patient(2, "JAN", "WAZA", 21, "bol glowy",
		 * "janek.dzbanek@wp.pl", "bbb", "BBB", "22 22 22 222"));
		 * Database.insertPatient(new Patient(3, "ANDRZEJ", "BATMAN", 42,
		 * "katar", "andrju@yahoo.pl", "ccc", "CCC", "33 33 33 333"));
		 * Database.insertPatient(new Patient(4, "ANNA", "SKOWRONSKA", 32,
		 * "zapalenie gardla", "anna@onet.pl", "ddd", "DDD", "44 44 44 444"));
		 * Database.insertPatient(new Patient(5, "KRZYSZTOF", "SOBIESKI", 12,
		 * "biegunka", "kris56k@onet.pl", "eee", "EEE", "55 55 55 555"));
		 * Database.insertPatient(new Patient(6, "HANNA", "MARZANNA", 60,
		 * "stluczone kolano", "hania123@hotmail.pl", "fff", "FFF",
		 * "66 66 66 666")); Database.insertPatient(new Patient(7, "BARBARA",
		 * "BARTNIK", 76, "bol gardla", "jurek@ogorek.pl", "rrr", "RRR",
		 * "77 77 77 777"));
		 * 
		 * 
		 * System.out.println(Database.selectDoctor());
		 * 
		 * //Database.updatePacjent(new Pacjent(4, "Jurek2", "Ogorek2", 24,
		 * "katar", "jurek@ogorek.pl", "xyz", "xyz", "123 456 789"));
		 * //Database.deletePacjent(4);
		 * 
		 * Database.insertRegister(new Register(2, 2,
		 * 2,LocalDateTime.of(LocalDate.of(2018, 2, 2), LocalTime.of(7, 0))));
		 * Database.insertRegister(new Register(1, 1, 1,
		 * LocalDateTime.of(LocalDate.of(2017, 3, 1), LocalTime.of(11, 0))));
		 * Database.insertRegister(new Register(3, 3, 2,
		 * LocalDateTime.of(LocalDate.of(2019, 9, 6), LocalTime.of(13, 0))));
		 * Database.insertRegister(new Register(3, 3, 2,
		 * LocalDateTime.of(LocalDate.of(2018, 4, 7), LocalTime.of(12, 0))));
		 * Database.insertRegister(new Register(1, 1, 1,
		 * LocalDateTime.of(LocalDate.of(2019, 5, 12), LocalTime.of(14, 30))));
		 * Database.insertRegister(new Register(5, 5, 5,
		 * LocalDateTime.of(LocalDate.of(2017, 6, 4), LocalTime.of(8, 30))));
		 */
		/*
		 * boolean ifFilterFirstName = true; boolean ifFilterLastName= true;
		 * boolean ifFilterAge = true; boolean ifFilterCCity = true; boolean
		 * ifFilterSalary = true; List<String> names = new
		 * ArrayList<>(Arrays.asList(new String[]{"ania","Kasia"}));
		 * List<String> lastnames = new ArrayList<>(Arrays.asList(new
		 * String[]{"kowalska","kubacka"})); List<String> cities = new
		 * ArrayList<>(Arrays.asList(new String[]{"krk","wwa"})); int ageFrom =
		 * 13; int ageTo = 99; double salaryFrom = 100; double salaryTo = 9999;
		 * 
		 * Database.selectInnerJoinFilter(ifFilterFirstNameDoctor,
		 * ifFilterLastNameDoctor, ifFilterAgeDoctor, ifFilterFirstNamePatient,
		 * ifFilterLastNamePatient, ifFilterAgePatient, ifFilterCCity,
		 * ifFilterDoctorSalary, doctorsNames, doctorsLastnames, patientsNames,
		 * patientsLastnames, cities, doctorAgeFrom, doctorAgeTo,
		 * doctorDalaryFrom, doctorSalaryTo)
		 * .stream().forEach(System.out::print);
		 */

		// Database.insertUser(new User(1, "Krzys", "M",30, "Ksys123", "ala123",
		// "Admin"));

		// Database.insertUser(new User(1, "Ania", "M",30, "Ania123", "ala123",
		// "User"));

		/*
		 * Optional<User> op = Database.returnUser("Ksys123", "ala123");
		 * if(op.isPresent()) { System.out.println(op.get()); } else{
		 * System.out.println("nie ma"); }
		 */
		// Database.insertUser(new User(0, "Jurek", "Ogorek", 32, "jerzy123",
		// "jerzy123", "user"));
		// Database.insertPatient(new Patient(8, "Basia", "BARTNIK", 76, "bol
		// gardla", "jurek@ogorek.pl", "rrr", "RRR", "77 77 77 777"));
		// Database.insertPatient(new Patient(8, "Kasia", "BARTNIK", 76, "bol
		// gardla", "jurek@ogorek.pl", "rrr", "RRR", "77 77 77 777"));
		// Database.insertDoctor(new Doctor(4, "Janinna", "ZWIERZ", 54, 25600,
		// "RRR",2));
		// Database.insertDoctor(new Doctor(4, "Krystyna", "ZWIERZ", 54, 25600,
		// "RRR",2));

		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				createWindow();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});

	}
}
