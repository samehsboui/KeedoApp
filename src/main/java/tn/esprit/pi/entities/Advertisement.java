package tn.esprit.pi.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Advertisement implements Serializable{
	
	
	private static final long serialVersionUID = 2329896158303253039L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Canal;
	@Temporal(TemporalType.DATE)
	private Date beginningDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private int targetViews;
	private int views;
	@ManyToOne
	private Event event;
	public Advertisement() {
		super();
	}

	public Advertisement(Long id, String canal, Date beginningDate, Date endDate, int targetViews, int views) {
		super();
		Id = id;
		Canal = canal;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.targetViews = targetViews;
		this.views = views;
	}

	public Advertisement(String canal, Date beginningDate, Date endDate, int targetViews, int views) {
		super();
		Canal = canal;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.targetViews = targetViews;
		this.views = views;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCanal() {
		return Canal;
	}

	public void setCanal(String canal) {
		Canal = canal;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTargetViews() {
		return targetViews;
	}

	public void setTargetViews(int targetViews) {
		this.targetViews = targetViews;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Canal == null) ? 0 : Canal.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((beginningDate == null) ? 0 : beginningDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + targetViews;
		result = prime * result + views;
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
		Advertisement other = (Advertisement) obj;
		if (Canal == null) {
			if (other.Canal != null)
				return false;
		} else if (!Canal.equals(other.Canal))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (beginningDate == null) {
			if (other.beginningDate != null)
				return false;
		} else if (!beginningDate.equals(other.beginningDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (targetViews != other.targetViews)
			return false;
		if (views != other.views)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Advertisement [Id=" + Id + ", Canal=" + Canal + ", beginningDate=" + beginningDate + ", endDate="
				+ endDate + ", targetViews=" + targetViews + ", views=" + views + "]";
	}
	
	
	
}
