package classes;

import java.time.LocalDateTime;

public class Register {

	private int id;
	private int idDoctor;
	private int idPatient;
	private LocalDateTime visitDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public LocalDateTime getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDateTime visitDate) {
		this.visitDate = visitDate;
	}

	public Register(int id, int idDoctor, int idPatient, LocalDateTime visitDate) {
		super();
		this.id = id;
		this.idDoctor = idDoctor;
		this.idPatient = idPatient;
		this.visitDate = visitDate;
	}

	public Register() {

	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", idDoctor=" + idDoctor + ", idPatient=" + idPatient + ", visitDate=" + visitDate
				+ "]";
	}

}
