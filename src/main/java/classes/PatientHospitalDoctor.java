package classes;

public class PatientHospitalDoctor {
	private int registrationId;
	private String doctorFirstName;
	private String doctorLastName;
	private int doctorAge;
	private double doctorSalary;
	private String specialization;
	private String hospitalName;
	private String hospitalCity;
	private String hospitalAddress;
	private String hospitalTelephoneNumber;
	private String patientFirstName;
	private String patientLastName;
	private int patientAge;
	private String patientDisease;
	private String patientEmailAddress;
	private String patientMailAddress;
	private String patientAddress;
	private String patientTelephoneNumber;
	
	public PatientHospitalDoctor(int registrationId, String doctorFirstName, String doctorLastName, int doctorAge,
			double doctorSalary, String specialization, String hospitalName, String hospitalCity,
			String hospitalAddress, String hospitalTelephoneNumber, String patientFirstName, String patientLastName,
			int patientAge, String patientDisease, String patientEmailAddress, String patientMailAddress,
			String patientAddress, String patientTelephoneNumber) {
		super();
		this.registrationId = registrationId;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorAge = doctorAge;
		this.doctorSalary = doctorSalary;
		this.specialization = specialization;
		this.hospitalName = hospitalName;
		this.hospitalCity = hospitalCity;
		this.hospitalAddress = hospitalAddress;
		this.hospitalTelephoneNumber = hospitalTelephoneNumber;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.patientAge = patientAge;
		this.patientDisease = patientDisease;
		this.patientEmailAddress = patientEmailAddress;
		this.patientMailAddress = patientMailAddress;
		this.patientAddress = patientAddress;
		this.patientTelephoneNumber = patientTelephoneNumber;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}

	public int getDoctorAge() {
		return doctorAge;
	}

	public void setDoctorAge(int doctorAge) {
		this.doctorAge = doctorAge;
	}

	public double getDoctorSalary() {
		return doctorSalary;
	}

	public void setDoctorSalary(double doctorSalary) {
		this.doctorSalary = doctorSalary;
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

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientDisease() {
		return patientDisease;
	}

	public void setPatientDisease(String patientDisease) {
		this.patientDisease = patientDisease;
	}

	public String getPatientEmailAddress() {
		return patientEmailAddress;
	}

	public void setPatientEmailAddress(String patientEmailAddress) {
		this.patientEmailAddress = patientEmailAddress;
	}

	public String getPatientMailAddress() {
		return patientMailAddress;
	}

	public void setPatientMailAddress(String patientMailAddress) {
		this.patientMailAddress = patientMailAddress;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientTelephoneNumber() {
		return patientTelephoneNumber;
	}

	public void setPatientTelephoneNumber(String patientTelephoneNumber) {
		this.patientTelephoneNumber = patientTelephoneNumber;
	}

	@Override
	public String toString() {
		return "PatientHospitalDoctor [registrationId=" + registrationId + ", doctorFirstName="
				+ doctorFirstName + ", doctorLastName=" + doctorLastName + ", doctorAge=" + doctorAge
				+ ", doctorSalary=" + doctorSalary + ", specialization=" + specialization + ", hospitalName="
				+ hospitalName + ", hospitalCity=" + hospitalCity + ", hospitalAddress=" + hospitalAddress
				+ ", hospitalTelephoneNumber=" + hospitalTelephoneNumber + ", patientFirstName=" + patientFirstName
				+ ", patientLastName=" + patientLastName + ", patientAge=" + patientAge + ", patientDisease="
				+ patientDisease + ", patientEmailAddress=" + patientEmailAddress + ", patientMailAddress="
				+ patientMailAddress + ", patientAddress=" + patientAddress + ", patientTelephoneNumber="
				+ patientTelephoneNumber + "]";
	}
	
	
	
	
}
