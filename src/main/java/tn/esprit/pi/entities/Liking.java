package tn.esprit.pi.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name= "liking")
public class Liking implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idLiking;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name= "id_post")
	private Post post;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;

	
	
	public Liking() {
		super();
	}

	public int getIdLiking() {
		return idLiking;
	}

	public void setIdLiking(int idLiking) {
		this.idLiking = idLiking;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Liking [idLiking=" + idLiking + ", post=" + post + "]";
	}
	
}
