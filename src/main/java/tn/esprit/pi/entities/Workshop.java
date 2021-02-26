package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "workshop")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Workshop implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idWorkshop;
	@Column(name="")
	private String category;
	@Column(name="content")
	private String content;
	@Temporal(TemporalType.DATE)
	@Column(name="createDate")
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name="modifyDate")
	private Date modifyDate;
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	
	public Workshop() {
		super();
	}

	public int getIdWorkshop() {
		return idWorkshop;
	}

	public void setIdWorkshop(int idWorkshop) {
		this.idWorkshop = idWorkshop;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user= user;
	}

	@Override
	public String toString() {
		return "Workshop [idWorkshop=" + idWorkshop + ", category=" + category + ", content=" + content
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", user=" + user + "]";
	}
	
}
