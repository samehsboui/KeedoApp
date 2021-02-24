package tn.esprit.pi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name= "claim")
public class Claim implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idClaim;
	@Column(name= "description")
	private String description;
	@Column(name= "category")
	@Enumerated(EnumType.STRING)
	private ClaimCategory category;
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name= "id_kindergarden")
	private Kindergarden kindergarden;
	
	public Claim() {
		super();
	}

	public int getIdClaim() {
		return idClaim;
	}

	public void setIdClaim(int idClaim) {
		this.idClaim = idClaim;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ClaimCategory getCategory() {
		return category;
	}

	public void setCategory(ClaimCategory category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

	public Kindergarden getKindergarden() {
		return kindergarden;
	}

	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}

	@Override
	public String toString() {
		return "Claim [idClaim=" + idClaim + ", description=" + description + ", category=" + category + ", user="
				+ user + ", kindergarden=" + kindergarden + "]";
	}
	
	
	
}
