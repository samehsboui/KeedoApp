package tn.esprit.pi.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "participant")
public class Participation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "participant_id")
	private int id;
	
	private float price;
	
	private String participationDate;
	
	@ManyToOne
	@JsonIgnore
	private Event event;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public Participation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Participation(int id, float price, String participationDate, Event event, User user) {
		super();
		this.id = id;
		this.price = price;
		this.participationDate = participationDate;
		this.event = event;
		this.user = user;
	}

	public Participation(float price, String participationDate, Event event, User user) {
		super();
		this.price = price;
		this.participationDate = participationDate;
		this.event = event;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getParticipationDate() {
		return participationDate;
	}

	public void setParticipationDate(String participationDate) {
		this.participationDate = participationDate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + id;
		result = prime * result + ((participationDate == null) ? 0 : participationDate.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participation other = (Participation) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (id != other.id)
			return false;
		if (participationDate == null) {
			if (other.participationDate != null)
				return false;
		} else if (!participationDate.equals(other.participationDate))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Participation [id=" + id + ", price=" + price + ", participationDate=" + participationDate + ", event="
				+ event + ", user=" + user + "]";
	}
	
	
}
	
	