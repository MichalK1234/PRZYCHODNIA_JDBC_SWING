package classes;

public class Patient {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String disease;
	private String emailAddress;
	private String mailAddress;
	private String address;
	private String telephoneNumber;

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

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Patient(int id, String firstName, String lastName, int age, String disease, String emailAddress,
			String mailAddress, String address, String telephoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.disease = disease;
		this.emailAddress = emailAddress;
		this.mailAddress = mailAddress;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
	}

	public Patient() {

	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", disease=" + disease + ", emailAddress=" + emailAddress + ", mailAddress=" + mailAddress
				+ ", address=" + address + ", telephoneNumber=" + telephoneNumber + "]";
	}

}
