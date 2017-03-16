package classes;

public class DoctorWithHospital {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private double salary;
	private String specialization;
	private String hospitalName;
	private String hospitalCity;
	private String hospitalAddress;
	private String hospitalTelephoneNumber;

	public DoctorWithHospital() {

	}

	public DoctorWithHospital(int id, String firstName, String lastName, int age, double salary, String specialization,
			String hospitalName, String hospitalCity, String hospitalAddress, String hospitalTelephoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.salary = salary;
		this.specialization = specialization;
		this.hospitalName = hospitalName;
		this.hospitalCity = hospitalCity;
		this.hospitalAddress = hospitalAddress;
		this.hospitalTelephoneNumber = hospitalTelephoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalCity() {
		return hospitalCity;
	}

	public void setHospitalCity(String hospitalCity) {
		this.hospitalCity = hospitalCity;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalTelephoneNumber() {
		return hospitalTelephoneNumber;
	}

	public void setHospitalTelephoneNumber(String hospitalTelephoneNumber) {
		this.hospitalTelephoneNumber = hospitalTelephoneNumber;
	}

	@Override
	public String toString() {
		return "DoctorWithHospital [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", salary=" + salary + ", specialization=" + specialization + ", hospitalName=" + hospitalName
				+ ", hospitalCity=" + hospitalCity + ", hospitalAddress=" + hospitalAddress
				+ ", hospitalTelephoneNumber=" + hospitalTelephoneNumber + "]";
	}

}
