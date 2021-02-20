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
@Table(name= "Daycare")
public class Daycare implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idDaycare;
	@Column(name= "price")
	private int price;
	@Column(name= "periode")
	private String periode;
	@Temporal(TemporalType.DATE)
	@Column(name= "dateBegin")
	private Date dateBegin;
	@Temporal(TemporalType.DATE)
	@Column(name= "dateEnd")
	private Date dateEnd;
	@Column(name= "capacity")
	private int capacity;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "daycare", fetch= FetchType.EAGER)
	private Set<Kid> kids;
	
	public Daycare() {
		super();
	}

	public int getIdDaycare() {
		return idDaycare;
	}

	public void setIdDaycare(int idDaycare) {
		this.idDaycare = idDaycare;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	@Override
	public String toString() {
		return "Daycare [idDaycare=" + idDaycare + ", price=" + price + ", periode=" + periode + ", dateBegin="
				+ dateBegin + ", dateEnd=" + dateEnd + ", capacity=" + capacity + ", kids=" + kids + "]";
	}
	
}
