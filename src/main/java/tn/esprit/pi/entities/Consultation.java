package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "Consultation")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
	private Date  time;
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	@ManyToOne
	@JoinColumn(name= "id_kid")
	private Kid kid;
	
	public Consultation() {
		super();
	}

	public Consultation(Date dateConsultation, Date time) {
		super();
		this.dateConsultation = dateConsultation;
		this.time = time;
	}

	public Consultation(int idConsultation, Date dateConsultation, Date time) {
		super();
		this.idConsultation = idConsultation;
		this.dateConsultation = dateConsultation;
		this.time = time;
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

	public void setTime(Date  time) {
		this.time = time;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}

	@Override
	public String toString() {
		return "Consultation [idConsultation=" + idConsultation + ", dateConsultation=" + dateConsultation + ", time="
				+ time + ", user=" + user + ", kid=" + kid + "]";
	}

}
