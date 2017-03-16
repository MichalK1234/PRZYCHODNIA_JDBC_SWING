package classes;

public class Hospital {

	private int id;
	private String hospitalName;
	private String hospitalCity;
	private String hospitalAddress;
	private String hospitalTelephoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Hospital(int id, String hospitalName, String hospitalCity, String hospitalAddress,
			String hospitalTelephoneNumber) {
		super();
		this.id = id;
		this.hospitalName = hospitalName;
		this.hospitalCity = hospitalCity;
		this.hospitalAddress = hospitalAddress;
		this.hospitalTelephoneNumber = hospitalTelephoneNumber;
	}

	public Hospital() {

	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", hospitalName=" + hospitalName + ", hospitalCity=" + hospitalCity
				+ ", hospitalAddress=" + hospitalAddress + ", hospitalTelephoneNumber=" + hospitalTelephoneNumber + "]";
	}

}
