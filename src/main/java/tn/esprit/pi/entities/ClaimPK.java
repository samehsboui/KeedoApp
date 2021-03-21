package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ClaimPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idUser;
	private int idKindergarden;
	@Temporal(TemporalType.DATE)
	private Date date;

	public ClaimPK() {
		super();
	}

	public ClaimPK(int iduser, int idkindergarden, Date date) {
		super();
		this.idUser = iduser;
		this.idKindergarden = idkindergarden;
		this.date = date;
	}

	public int getIduser() {
		return idUser;
	}

	public void setIduser(int iduser) {
		this.idUser = iduser;
	}

	public int getIdkindergarden() {
		return idKindergarden;
	}

	public void setIdkindergarden(int idkindergarden) {
		this.idKindergarden = idkindergarden;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}