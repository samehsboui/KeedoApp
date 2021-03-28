package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="meeting")
public class Meeting implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idMeeting;
	
	@Column(name = "start")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime start;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "end")
	private LocalDateTime end;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "canceled_at")
	private LocalDateTime canceledAt;
	@OneToOne
	@JoinColumn(name = "id_canceler")
	private User canceler;
	@Column(name = "status")
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	@Temporal(TemporalType.TIME)
	@Column(name= "time")
	private Date time;
	@Column(name= "typeMeeting")
	private String typeMeeting;
	@Column(name= "description")
	private String description;

	private Date date;
	
	
	
	
	
	@OneToOne(cascade= CascadeType.ALL, mappedBy="meeting")
	private Feedback feedback;
	
	//@ManyToMany(cascade= CascadeType.ALL, mappedBy="meetings", fetch= FetchType.EAGER)
	//private Set<User> users;
	
	@ManyToOne
    @JoinColumn(name = "id_kindergarden")
    private Kindergarden kindergarden;

    @ManyToOne
    @JoinColumn(name = "id_parent")
    private User users;

	
	public Meeting() {
		super();
	}


	public Meeting(int idMeeting, LocalDateTime start,Date date, LocalDateTime end, LocalDateTime canceledAt, User canceler,
			AppointmentStatus status, Date time, String typeMeeting, String description, Feedback feedback,
			Kindergarden kindergarden, User users) {
		super();
		this.idMeeting = idMeeting;
		this.start = start;
		this.end = end;
		this.date =date;
		this.canceledAt = canceledAt;
		this.canceler = canceler;
		this.status = status;
		this.time = time;
		this.typeMeeting = typeMeeting;
		this.description = description;
		this.feedback = feedback;
		this.kindergarden = kindergarden;
		this.users = users;
	}


	public Meeting(LocalDateTime start, Date date ,LocalDateTime end, LocalDateTime canceledAt, User canceler,
			AppointmentStatus status, Date time, String typeMeeting, String description, Feedback feedback,
			Kindergarden kindergarden, User users) {
		super();
		this.start = start;
		this.end = end;
		this.canceledAt = canceledAt;
		this.canceler = canceler;
		this.status = status;
		this.time = time;
		this.date =date;
		this.typeMeeting = typeMeeting;
		this.description = description;
		this.feedback = feedback;
		this.kindergarden = kindergarden;
		this.users = users;
	}


	public int getIdMeeting() {
		return idMeeting;
	}


	public void setIdMeeting(int idMeeting) {
		this.idMeeting = idMeeting;
	}


	public LocalDateTime getStart() {
		return start;
	}


	public void setStart(LocalDateTime start) {
		this.start = start;
	}


	public LocalDateTime getEnd() {
		return end;
	}


	public void setEnd(LocalDateTime end) {
		this.end = end;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public LocalDateTime getCanceledAt() {
		return canceledAt;
	}


	public void setCanceledAt(LocalDateTime canceledAt) {
		this.canceledAt = canceledAt;
	}


	public User getCanceler() {
		return canceler;
	}


	public void setCanceler(User canceler) {
		this.canceler = canceler;
	}


	public AppointmentStatus getStatus() {
		return status;
	}


	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public String getTypeMeeting() {
		return typeMeeting;
	}


	public void setTypeMeeting(String typeMeeting) {
		this.typeMeeting = typeMeeting;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Feedback getFeedback() {
		return feedback;
	}


	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}


	public Kindergarden getKindergarden() {
		return kindergarden;
	}


	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}





	public User getUsers() {
		return users;
	}


	public void setUsers(User users) {
		this.users = users;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Meeting [idMeeting=" + idMeeting + ", start=" + start + ", end=" + end + ", canceledAt=" + canceledAt
				+ ", canceler=" + canceler + ", status=" + status + ", time=" + time + ", typeMeeting=" + typeMeeting
				+ ", description=" + description + ", feedback=" + feedback + ", kindergarden=" + kindergarden
				+ ", users=" + users + "]";
	}


	
	
	
	
}
