package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "Consultation")
public class Consultation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int idConsultation;
	@Temporal(TemporalType.DATE)
	@Column(name="dateConsultation")
	private Date dateConsultation;
	@Temporal(TemporalType.TIME)
	@Column(name="time")
	private Date time;
	@Column(name="doctorName")
	private String doctorName;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "consultation", fetch= FetchType.EAGER)
	private Set<Kid> kids;
	
	public Consultation() {
		super();
	}

	public int getIdConsultation() {
		return idConsultation;
	}

	public void setIdConsultation(int idConsultation) {
		this.idConsultation = idConsultation;
	}

	public Date getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(Date dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	@Override
	public String toString() {
		return "Consultation [idConsultation=" + idConsultation + ", dateConsultation=" + dateConsultation + ", time="
				+ time + ", doctorName=" + doctorName + ", kids=" + kids + "]";
	}
	
}
