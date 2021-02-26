package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "kindergarden")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Kindergarden implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idKindergarden;	
	
	@Column(name= "Name")
	private String name;
	
	
	@OneToMany(mappedBy= "kindergarden")
	private Set<Claim> claims;
	
	@OneToOne
	private User director;
	
	public Kindergarden() {
		super();
	
	}
	
	public Kindergarden(int id, String name) {
		super();
		this.idKindergarden = id;
		this.name = name;
	}

	public int getIdKindergarden() {
		return idKindergarden;
	}

	public void setIdKindergarden(int idKindergarden) {
		this.idKindergarden = idKindergarden;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Claim> getClaims() {
		return claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	public User getDirector() {
		return director;
	}

	public void setDirector(User director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Kindergarden [idKindergarden=" + idKindergarden + ", name=" + name + ", claims=" + claims
				+ ", director=" + director + "]";
	}

	
}
