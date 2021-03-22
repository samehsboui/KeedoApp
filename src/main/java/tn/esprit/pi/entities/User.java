package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int idUser;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "telNum")
	private int telNum;
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")
	private Date birthdate;
	@Column(name = "address")
	private String address;
	@Column(name = "mail")
	private String mail;
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@Column(name = "delegate")
	private boolean delegate;
	@Column(name = "logo")
	private byte[] logo;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "role")
	private Role role;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Kid> kids;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Post> posts;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Workshop> workshops;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Event> events;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Meeting> meetings;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<Message> messagesS;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<Message> messagesR;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Bus> bus;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Answer> answers;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Topic> topics;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "director", fetch = FetchType.EAGER)
	private Set<Consultation> consultations;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.EAGER)
	private Set<Consultation> doctorConsultations;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Chat> chats;

	// added by amal
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Report> reports;
	boolean valid;
	@Column(name = "accountLocked", columnDefinition = "boolean default false")
	private boolean accountNonLocked;
	@Column(name = "failedAttempt", columnDefinition = "int default 0")
	private int failedAttempt;
	@Column(name = "lockTime")
	private Date lockTime;
	@Column(name = "resettoken")
	private String resettoken;



/**** Roua******/
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Claim> claims;
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "follower")
	private Set<Follow> follower;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "following")
	private Set<Follow> following;
	@JsonIgnore
	@OneToOne(mappedBy="director")
	private Kindergarden  kindergarden;

	 private boolean isPrivate;



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getTelNum() {
		return telNum;
	}

	public void setTelNum(int telNum) {
		this.telNum = telNum;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDelegate() {
		return delegate;
	}

	public void setDelegate(boolean delegate) {
		this.delegate = delegate;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}


	public Set<Workshop> getWorkshops() {
		return workshops;
	}

	public void setWorkshops(Set<Workshop> workshops) {
		this.workshops = workshops;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}

	public Set<Claim> getClaims() {
		return claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	public Set<Message> getMessagesS() {
		return messagesS;
	}

	public void setMessagesS(Set<Message> messagesS) {
		this.messagesS = messagesS;
	}

	public Set<Message> getMessagesR() {
		return messagesR;
	}

	public void setMessagesR(Set<Message> messagesR) {
		this.messagesR = messagesR;
	}

	public Set<Bus> getBus() {
		return bus;
	}

	public void setBus(Set<Bus> bus) {
		this.bus = bus;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Set<Consultation> getDoctorConsultations() {
		return doctorConsultations;
	}

	public void setDoctorConsultations(Set<Consultation> doctorConsultations) {
		this.doctorConsultations = doctorConsultations;
	}

	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getResettoken() {
		return resettoken;
	}

	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}

/***** Roua ********/
	public Set<Follow> getFollower() {
		return follower;
	}

	public void setFollower(Set<Follow> follower) {
		this.follower = follower;
	}

	public Set<Follow> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Follow> following) {
		this.following = following;
	}

	public Kindergarden getKindergarden() {
		return kindergarden;
	}

	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	
	
	/************************/
	
	
	
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", telNum=" + telNum
				+ ", birthdate=" + birthdate + ", address=" + address + ", mail=" + mail + ", login=" + login
				+ ", password=" + password + ", delegate=" + delegate + ", logo=" + Arrays.toString(logo) + ", role="
				+ role + ", kids=" + kids + ", posts=" + posts + ", workshops=" + workshops + ", events=" + events
				+ ", meetings=" + meetings + ", messagesS=" + messagesS + ", messagesR=" + messagesR + ", bus=" + bus
				+ ", answers=" + answers + ", topics=" + topics + ", consultations=" + consultations
				+ ", doctorConsultations=" + doctorConsultations + ", chats=" + chats + ", reports=" + reports
				+ ", valid=" + valid + ", accountNonLocked=" + accountNonLocked + ", failedAttempt=" + failedAttempt
				+ ", lockTime=" + lockTime + ", resettoken=" + resettoken + ", claims=" + claims + ", follower="
				+ follower + ", following=" + following + ", kindergarden=" + kindergarden + ", isPrivate=" + isPrivate
				+ "]";
	}

	


}
