package classes;

public class Doctor {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private double salary;
	private String specialization;
	private int HospitalId;

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

	public int getHospitalId() {
		return HospitalId;
	}

	public void setHospitalId(int hospitalId) {
		HospitalId = hospitalId;
	}

	public Doctor(int id, String firstName, String lastName, int age, double salary, String specialization,
			int hospitalId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.salary = salary;
		this.specialization = specialization;
		HospitalId = hospitalId;
	}

	public Doctor() {

	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", salary="
				+ salary + ", specialization=" + specialization + ", HospitalId=" + HospitalId + "]";
	}

}
