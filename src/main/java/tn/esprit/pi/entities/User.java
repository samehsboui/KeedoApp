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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "user")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name= "id")
	private int idUser;	
	@Column(name= "firstName")
	private String firstName;
	@Column(name= "lastName")
	private String lastName;
	@Column(name="telNum")
	private int telNum;
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthdate;
	@Column(name="address")
	private String address;
	@Column(name="mail")
	private String mail;
	@Column(name="login")
	private String login;
	@Column(name="password")
	private String password;
	@Column(name="delegate")
	private boolean delegate;
	@Column(name="logo")
	private byte[] logo;
	@ManyToOne(cascade= CascadeType.DETACH)
    @JoinColumn(name = "role")
	private Role role;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Kid> kids;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Post> posts;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Follow> follows;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Workshop> workshops;
	@JsonIgnore
	@ManyToMany(cascade= CascadeType.ALL)
	private Set<Event> events;
	@JsonIgnore
	@ManyToMany(cascade= CascadeType.ALL)
	private Set<Meeting> meetings; 
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Claim> claims;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Message> messages;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Bus> bus;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Answer> answers;
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Topic> topics;
	//added by amal
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
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
	
	public User() {
		super();
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

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
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

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", telNum=" + telNum
				+ ", birthdate=" + birthdate + ", address=" + address + ", mail=" + mail + ", login=" + login
				+ ", password=" + password + ", delegate=" + delegate + ", logo=" + Arrays.toString(logo) + ", role="
				+ role + ", kids=" + kids + ", posts=" + posts + ", follows=" + follows + ", workshops=" + workshops
				+ ", events=" + events + ", meetings=" + meetings + ", claims=" + claims + ", messages=" + messages
				+ ", bus=" + bus + ", answers=" + answers + ", topics=" + topics + ", reports=" + reports + ", valid="
				+ valid + ", accountNonLocked=" + accountNonLocked + ", failedAttempt=" + failedAttempt + ", lockTime="
				+ lockTime + ", resettoken=" + resettoken + "]";
	}


	
}
