package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name= "notificationSNW")
public class NotificationSNW implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	@Column(name= "subject")
	private String subject;
	@Column(name= "Date")
	private LocalDateTime date;
	@Column(name= "sender")
	private int sender;
	@Column(name= "receiver")
	private int receiver;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_comment")
	private Comment comment;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_like")
	private Liking like;
	
	public NotificationSNW() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Liking getLike() {
		return like;
	}

	public void setLike(Liking like) {
		this.like = like;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "NotificationSNW [id=" + id + ", subject=" + subject + ", date=" + date + ", sender=" + sender
				+ ", receiver=" + receiver + ", comment=" + comment + ", like=" + like + "]";
	}



}