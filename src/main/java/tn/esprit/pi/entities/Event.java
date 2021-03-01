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
import javax.persistence.OneToOne;
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
	
	@Column(name= "ticketPrice")
	private float ticketPrice;


	@Column(name= "collAmount")
	private float collAmount;


	@Column(name= "participantsNbr")
	private int participantsNbr;
	
	@Column(name= "placesNbr")
	private int placesNbr;

	@Enumerated(EnumType.STRING)
	private EventCategory category;
	private int views;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "event")
	private Set<Participation> participations;

	 @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER )
	 Jackpot jackpot;
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy="event", fetch= FetchType.EAGER)
	private Set<Notification> notifications;
	
	
	
	public Event(int idEvenement, String title, Date date, Date hour, String description, int status,float ticketPrice, String address,
			String image, float collAmount, int participantsNbr, int placesNbr, EventCategory category, int views,
			Set<Participation> participations, Jackpot jackpot, Set<Notification> notifications) {
		super();
		this.idEvenement = idEvenement;
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.ticketPrice = ticketPrice;
		this.description = description;
		this.status = status;
		this.address = address;
		this.image = image;
		this.collAmount = collAmount;
		this.participantsNbr = participantsNbr;
		this.placesNbr = placesNbr;
		this.category = category;
		this.views = views;
		this.participations = participations;
		this.jackpot = jackpot;
		this.notifications = notifications;
	}



	public Event(String title, Date date, Date hour, String description, int status,float ticketPrice, String address, String image,
			float collAmount, int participantsNbr, int placesNbr, EventCategory category, int views,
			Set<Participation> participations, Jackpot jackpot, Set<Notification> notifications) {
		super();
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.description = description;
		this.status = status;
		this.address = address;
		this.image = image;
		this.collAmount = collAmount;
		this.ticketPrice = ticketPrice;
		this.participantsNbr = participantsNbr;
		this.placesNbr = placesNbr;
		this.category = category;
		this.views = views;
		this.participations = participations;
		this.jackpot = jackpot;
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

	


	public float getTicketPrice() {
		return ticketPrice;
	}



	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
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

	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}



	public float getCollAmount() {
		return collAmount;
	}



	public void setCollAmount(float collAmount) {
		this.collAmount = collAmount;
	}



	public int getParticipantsNbr() {
		return participantsNbr;
	}



	public void setParticipantsNbr(int participantsNbr) {
		this.participantsNbr = participantsNbr;
	}



	public int getPlacesNbr() {
		return placesNbr;
	}



	public void setPlacesNbr(int placesNbr) {
		this.placesNbr = placesNbr;
	}



	public EventCategory getCategory() {
		return category;
	}



	public void setCategory(EventCategory category) {
		this.category = category;
	}



	public Jackpot getJackpot() {
		return jackpot;
	}



	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}



	@Override
	public String toString() {
		return "Event [idEvenement=" + idEvenement + ", title=" + title + ", date=" + date + ", hour=" + hour
				+ ", description=" + description + ", status=" + status + ", address=" + address + ", image=" + image
				+ ", collAmount=" + collAmount + ", participantsNbr=" + participantsNbr + ", placesNbr=" + placesNbr
				+ ", category=" + category + ", views=" + views + ", participations=" + participations + ", jackpot="
				+ jackpot + ", notifications=" + notifications + "]";
	}


}