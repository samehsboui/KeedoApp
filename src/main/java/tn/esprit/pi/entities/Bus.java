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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "bus")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Bus implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int idBus;
	@Column(name="departure")
	private String departure;
	@Column(name="destination")
	private String destination;
	@Column(name="timeDep")
	@Temporal(TemporalType.TIME)
	private Date timeDep;
	@Column(name="timeA")
	@Temporal(TemporalType.TIME)
	private Date timeA;
	@Column(name="capacity")
	private int capacity;
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	@ManyToOne
	@JoinColumn(name= "id_driver")
	private Driver driver;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "bus", fetch= FetchType.EAGER)
	private Set<Kid> kids;
	
	public Bus() {
		super();
	}

	public int getIdBus() {
		return idBus;
	}

	public void setIdBus(int idBus) {
		this.idBus = idBus;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getTimeDep() {
		return timeDep;
	}

	public void setTimeDep(Date timeDep) {
		this.timeDep = timeDep;
	}

	public Date getTimeA() {
		return timeA;
	}

	public void setTimeA(Date timeA) {
		this.timeA = timeA;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	@Override
	public String toString() {
		return "Bus [idBus=" + idBus + ", departure=" + departure + ", destination=" + destination + ", timeDep="
				+ timeDep + ", timeA=" + timeA + ", capacity=" + capacity + ", user=" + user + ", driver=" + driver
				+ ", kids=" + kids + "]";
	}
	
}
