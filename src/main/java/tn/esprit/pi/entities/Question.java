package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "question")
public class Question implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;	
	
	
	@Column(name= "question")
	private String question;
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL,mappedBy="question")
	private Set<Response>  responses;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "Idfeedback")
	private Feedback feedback;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
	public Set<Response> getResponses() {
		return responses;
	}
	public void setResponse(Set<Response> responses) {
		this.responses = responses;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
}