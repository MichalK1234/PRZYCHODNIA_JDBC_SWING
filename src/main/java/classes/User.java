package classes;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String username;
	private String password;
	private String role;
	private String numbersToDecrypt;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNumbersToDecrypt() {
		return numbersToDecrypt;
	}

	public void setNumbersToDecrypt(String numbersToDecrypt) {
		this.numbersToDecrypt = numbersToDecrypt;
	}

	public User() {

	}

	public User(int id, String firstName, String lastName, int age, String username, String password, String role,
			String numbersToDecrypt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.username = username;
		this.password = password;
		this.role = role;
		this.numbersToDecrypt = numbersToDecrypt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", username="
				+ username + ", password=" + password + ", role=" + role + ", numbersToDecrypt=" + numbersToDecrypt
				+ "]";
	}

}
