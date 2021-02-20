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
@Table(name= "event")
public class Event implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idEvenement;
	@Column(name= "title")
	private String title;
	@Temporal(TemporalType.DATE)
	@Column(name= "startDate")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name= "endDate")
	private Date endDate;
	@Column(name= "description")
	private String description;
	@Column(name= "status")
	private int status;
	@Column(name= "address")
	private String address;
	@Column(name= "image")
	private String image;
	@ManyToMany(cascade= CascadeType.ALL, mappedBy="eventsParticpants", fetch= FetchType.EAGER)
	private Set<Participant> participants;
	
	
	@ManyToMany(cascade= CascadeType.ALL, mappedBy="events", fetch= FetchType.EAGER)
	private Set<User> users;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="event", fetch= FetchType.EAGER)
	private Set<Notification> notifications;
	
	
	
	public Event() {
		super();
	}

	public int getIdEvenement() {
		return idEvenement;
	}

	public void setIdEvenement(int idEvenement) {
		this.idEvenement = idEvenement;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	@Override
	public String toString() {
		return "Event [idEvenement=" + idEvenement + ", title=" + title + ", startDate=" + startDate + ", endDate="
				+ endDate + ", description=" + description + ", status=" + status + ", address=" + address + ", users="
				+ users + ", notifications=" + notifications + "]";
	}
	 
}