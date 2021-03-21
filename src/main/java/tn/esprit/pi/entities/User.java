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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "user")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Kid> kids;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Post> posts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Follow> follows;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Workshop> workshops;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Event> events;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Meeting> meetings;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Claim> claims;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<Message> messagesS;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<Message> messagesR;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Bus> bus;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Answer> answers;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Topic> topics;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "director", fetch = FetchType.EAGER)
	private Set<Consultation> consultations;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.EAGER)
	private Set<Consultation> doctorConsultations;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Chat> chats;

	public User() {
		super();
	}

	public User(int idUser, String firstName, String lastName, int telNum, Set<Consultation> doctorConsultations) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telNum = telNum;
		this.doctorConsultations = doctorConsultations;
	}
	
	/*public User(int idUser, String firstName, String lastName, int telNum, Date birthdate, String address, Role role,
			Set<Consultation> doctorconsultations) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telNum = telNum;
		this.address = address;
		this.role = role;
		Doctorconsultations = doctorconsultations;
	}*/

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

	public Set<Follow> getFollows() {
		return follows;
	}

	public void setFollows(Set<Follow> follows) {
		this.follows = follows;
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

	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}

	public Set<Consultation> getDoctorConsultations() {
		return doctorConsultations;
	}

	public void setDoctorConsultations(Set<Consultation> doctorConsultations) {
		this.doctorConsultations = doctorConsultations;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", telNum=" + telNum
				+ ", birthdate=" + birthdate + ", address=" + address + ", mail=" + mail + ", login=" + login
				+ ", password=" + password + ", delegate=" + delegate + ", logo=" + Arrays.toString(logo) + ", role="
				+ role + ", kids=" + kids + ", posts=" + posts + ", follows=" + follows + ", workshops=" + workshops
				+ ", events=" + events + ", meetings=" + meetings + ", claims=" + claims + ", messagesS=" + messagesS
				+ ", messagesR=" + messagesR + ", bus=" + bus + ", answers=" + answers + ", topics=" + topics
				+ ", consultations=" + consultations + ", Doctorconsultations=" + doctorConsultations + ", chats="
				+ chats + "]";
	}

}
