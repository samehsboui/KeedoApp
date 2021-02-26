package tn.esprit.pi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "claim")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Claim implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClaimPK claimpk;
	@Column(name = "description")
	private String description;
	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private ClaimCategory category;
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)

	private User user;

	@ManyToOne
	@JoinColumn(name = "idKindergarden", referencedColumnName = "id", insertable = false, updatable = false)
	private Kindergarden kindergarden;

	public Claim() {
		super();
	}

	public Claim(ClaimPK claimpk, String description, ClaimCategory category, User user, Kindergarden kindergarden) {
		super();
		this.claimpk = claimpk;
		this.description = description;
		this.category = category;
		this.user = user;
		this.kindergarden = kindergarden;
	}

	public Claim(ClaimCategory category, String description, Kindergarden kg, User u) {
		super();

		this.description = description;
		this.category = category;
		this.user = u;
		this.kindergarden = kg;
	}

	public ClaimPK getClaimpk() {
		return claimpk;
	}

	public void setClaimpk(ClaimPK claimpk) {
		this.claimpk = claimpk;
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
		return "Claim [claimpk=" + claimpk + ", description=" + description + ", category=" + category + ", user="
				+ user + ", kindergarden=" + kindergarden + "]";
	}

}
