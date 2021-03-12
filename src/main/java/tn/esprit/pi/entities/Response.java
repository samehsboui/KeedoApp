package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "response")
public class Response implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;	
	
	@Column(name = "createdAt")
    private LocalDateTime createdAt;
	

	@Column(name= "response")
	private String response;	
	/*
	@Enumerated(EnumType.STRING)
	private Satistfaction satisfactionDegree;
	
	@Column(name= "rating")
	private int rating;
	
	*/
	  @Column(name = "isText")
	    private boolean isText;

	    @Column(name = "isNumeric")
	    private boolean isNumeric;

	    @Column(name = "isYesNo")
	    private boolean isYesNo;
	@JsonIgnore
	
	 @ManyToOne
	  
	@JoinColumn(name= "IdQuestion")
	private Question question;


	
	
	public Response(int id, LocalDateTime createdAt, String response, Question question) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.response = response;
		this.question = question;
		this.isText = false;
		this.isNumeric = false;
		this.isYesNo = false;
		
if (question.getQuestion().compareTo("text")==0){
	this.isText = true;
}else if(question.getQuestion().compareTo("numeric")==0){
	this.isNumeric = true;
}else{
	this.isYesNo = true;
}
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public String getResponse() {
		return response;
	}




	public void setResponse(String response) {
		this.response = response;
	}
	/*

	public Satistfaction getSatisfactionDegree() {
		return satisfactionDegree;
	}


	public void setSatisfactionDegree(Satistfaction satisfactionDegree) {
		this.satisfactionDegree = satisfactionDegree;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}

*/
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "Response [id=" + id + ", createdAt=" + createdAt + ", response=" + response + ", isText=" + isText
				+ ", isNumeric=" + isNumeric + ", isYesNo=" + isYesNo + ", question=" + question + "]";
	}


	


 



	
	
}
