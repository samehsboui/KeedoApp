package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date hour;
	@Column(name= "description")
	private String description;
	@Column(name= "status")
	private int status;
	@Column(name= "address")
	private String address;
	@Column(name= "image")
	private String image;
	
	
	@Enumerated(EnumType.STRING)
	private EventCategory category;
	private int views;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="event")
	private Set<Participation> participants;	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy="event", fetch= FetchType.EAGER)
	private Set<Notification> notifications;
	public Event(int idEvenement, String title, Date date, Date hour, String description, int status, String address,
			String image, int views, Set<Participation> participants, Set<Notification> notifications) {
		super();
		this.idEvenement = idEvenement;
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.description = description;
		this.status = status;
		this.address = address;
		this.image = image;
		this.views = views;
		this.participants = participants;
		this.notifications = notifications;
	}

	
	public Event(String title, Date date, Date hour, String description, int status, String address, String image,
			int views, Set<Participation> participants, Set<Notification> notifications) {
		super();
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.description = description;
		this.status = status;
		this.address = address;
		this.image = image;
		this.views = views;
		this.participants = participants;
		this.notifications = notifications;
	}


	
	
	
	public Event() {
		super();
	}
	
	

	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Date getHour() {
		return hour;
	}



	public void setHour(Date hour) {
		this.hour = hour;
	}






	public int getViews() {
		return views;
	}


	public void setViews(int views) {
		this.views = views;
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

	public Set<Participation> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participation> participants) {
		this.participants = participants;
	}


}