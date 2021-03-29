package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@DateTimeFormat(style = "hh:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
	private Date hour;
	@Column(name= "description")
	private String description;
	@Column(name= "status")
	private boolean status;
	@Column(name= "address")
	private String address;
	
	
	@Column(name= "image")
    @Lob
    private byte[] image;
	
	
	@Column(name= "ticketPrice")
	private float ticketPrice;


	@Column(name= "collAmount")
	private float collAmount;


	@Column(name= "participantsNbr")
	private int participantsNbr;
	
	@Column(name= "placesNbr")
	private int placesNbr;
	
	
	private boolean earlyParticipants;
	private int nbrTicketEarlyParticipants;

	@Enumerated(EnumType.STRING)
	private EventCategory category;
	private int views;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "event")
	private Set<Participation> participations;

	
	
	 @OneToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER )
	 Jackpot jackpot;
	
	@JsonIgnore
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy="event")
	private Set<Notification> notifications;
	
	
	private float discountPercentage;
	
	@OneToMany(mappedBy = "event", cascade=CascadeType.ALL)
	private List<Advertisement>advertisements;
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="event")
	private List<Donation> donation;
	
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "event")
	private Set<Evaluation> evaluations;


	public Event(int idEvenement, String title, Date date, Date hour, String description, boolean status,
			String address, byte[] image, float ticketPrice, float collAmount, int participantsNbr, int placesNbr,	boolean earlyParticipants,int nbrTicketEarlyParticipants,
			EventCategory category, int views, Set<Participation> participations, Jackpot jackpot,
			Set<Notification> notifications, float discountPercentage, List<Advertisement> advertisements,
			List<Donation> donation) {
		super();
		this.idEvenement = idEvenement;
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.description = description;
		this.status = status;
		this.address = address;
		this.image = image;
		this.ticketPrice = ticketPrice;
		this.collAmount = collAmount;
		this.participantsNbr = participantsNbr;
		this.placesNbr = placesNbr;
		this.category = category;
		this.views = views;
		this.participations = participations;
		this.jackpot = jackpot;
		this.notifications = notifications;
		this.discountPercentage = discountPercentage;
		this.advertisements = advertisements;
		this.donation = donation;
		this.earlyParticipants = earlyParticipants;
		this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
	}





	public List<Donation> getDonation() {
		return donation;
	}





	public void setDonation(List<Donation> donation) {
		this.donation = donation;
	}





	public Event(String title, Date date, Date hour, String description, boolean status, String address, byte[] image,
			float ticketPrice, float collAmount, int participantsNbr, int placesNbr, EventCategory category, int views,
			boolean earlyParticipants,int nbrTicketEarlyParticipants,
			Set<Participation> participations, Jackpot jackpot, Set<Notification> notifications,
			float discountPercentage, List<Advertisement> advertisements, List<Donation> donation) {
		super();
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.description = description;
		this.status = status;
		this.address = address;
		this.image = image;
		this.ticketPrice = ticketPrice;
		this.collAmount = collAmount;
		this.participantsNbr = participantsNbr;
		this.placesNbr = placesNbr;
		this.category = category;
		this.views = views;
		this.participations = participations;
		this.jackpot = jackpot;
		this.notifications = notifications;
		this.discountPercentage = discountPercentage;
		this.advertisements = advertisements;
		this.donation = donation;
		this.earlyParticipants = earlyParticipants;
		this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
	}





	public Event() {
		super();
	}
	
	

	
	
	public float getDiscountPercentage() {
		return discountPercentage;
	}



	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}



	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}



	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
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



	public boolean isEarlyParticipants() {
		return earlyParticipants;
	}





	public void setEarlyParticipants(boolean earlyParticipants) {
		this.earlyParticipants = earlyParticipants;
	}





	public int getNbrTicketEarlyParticipants() {
		return nbrTicketEarlyParticipants;
	}





	public void setNbrTicketEarlyParticipants(int nbrTicketEarlyParticipants) {
		this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
	}





	public byte[] getImage() {
		return image;
	}



	public void setImage(byte[] image) {
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