package tn.esprit.pi.entities;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="post")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idPost;
	@Column(name= "postContent")
	private String postContent;
	@Column(name= "createDate")
	private Date createDate;
	@Column(name= "modifyDate")
	private Date modifyDate;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "post", fetch= FetchType.EAGER)
	private Set<Comment> comments;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "post", fetch= FetchType.EAGER)
	private Set<Liking> likes;
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	
	public Post() {
		super();
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Liking> getLikes() {
		return likes;
	}

	public void setLikes(Set<Liking> likes) {
		this.likes = likes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [idPost=" + idPost + ", postContent=" + postContent + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", comments=" + comments + ", likes=" + likes + ", user=" + user + "]";
	}
	
}
