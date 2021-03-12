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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="meeting")
public class Meeting implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idMeeting;
	@Temporal(TemporalType.DATE)
	@Column(name= "date")
	private Date date;
	@Temporal(TemporalType.TIME)
	@Column(name= "time")
	private Date time;
	@Column(name= "typeMeeting")
	private String typeMeeting;
	@Column(name= "description")
	private String description;
	@Column(name= "status")
	private boolean status;
	@OneToMany(cascade= CascadeType.ALL, mappedBy="meeting")
	private Set<Feedback> feedbacks;
	@ManyToMany(cascade= CascadeType.ALL, mappedBy="meetings")
	private Set<User> users;
	
	public Meeting() {
		super();
	}

	public int getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(int idMeeting) {
		this.idMeeting = idMeeting;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTypeMeeting() {
		return typeMeeting;
	}

	public void setTypeMeeting(String typeMeeting) {
		this.typeMeeting = typeMeeting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Meeting [idMeeting=" + idMeeting + ", date=" + date + ", time=" + time + ", typeMeeting=" + typeMeeting
				+ ", description=" + description + ", status=" + status + ", feedbacks=" + feedbacks + ", users="
				+ users + "]";
	}
	
	
}
