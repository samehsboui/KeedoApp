package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="post")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idPost;
	@Column(name= "postContent")
	private String postContent;
	@Enumerated(EnumType.STRING)              
	private PostMediaType media;
	@Column(name= "createDate")
	private LocalDateTime createDate;
	@Column(name= "modifyDate")
	private LocalDateTime modifyDate;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    @OrderBy("desc")
    private List<Comment> comments = new ArrayList<Comment>();
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch= FetchType.EAGER)
	private Set<Liking> likes = new HashSet<Liking>();
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	// @DateTimeFormat(pattern = "YYYY-MM-dd")
    //private LocalDate creationDate = LocalDate.now();
	
    //    private boolean unhealthy = false;
	//	  private Integer likeCount = 0;
	//    private Integer commentCount = 0;
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

	public PostMediaType getMedia() {
		return media;
	}

	public void setMedia(PostMediaType media) {
		this.media = media;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
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
		return "Post [idPost=" + idPost + ", postContent=" + postContent + ", media=" + media + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", comments=" + comments + ", likes=" + likes + ", user="
				+ user + "]";
	}

}