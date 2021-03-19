package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.services.PostServiceImpl;

@RestController
public class PostController {
	
		@Autowired  
		PostServiceImpl PostServiceImpl;

		//URL: http://localhost:9293/SpringMVC/servlet/Post/get-all-posts
		@GetMapping("/Post/get-all-posts")  
		private List<Post> getAllPosts()   
		{  
			return PostServiceImpl.getAllPosts();  
		}  
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/detail-post/{Postid}
		@GetMapping("/Post/detail-post/{Postid}")  
		private Post getPost(@PathVariable("Postid") int Postid)   
		{  
			return PostServiceImpl.getPostById(Postid);  
		}  
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/delete-post/{Postid}
		@DeleteMapping("/Post/delete-post/{Postid}")  
		private void deletePost(@PathVariable("Postid") int Postid)   
		{  
			PostServiceImpl.deletePost(Postid);  
		} 
	
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/add-post/{idU}
		@PostMapping("/Post/add-post/{idU}")  
		private String addPost(@RequestBody Post Posts, @PathVariable("idU")int idU) throws Exception   
		{  
			return  (PostServiceImpl.addPost(Posts, idU));  
			
		}  
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/update-post/{Postid}
		@PutMapping("/Post/update-post/{Postid}")  
		private Post updatePost(@RequestBody Post posts, @PathVariable("Postid")int Postid)   
		{  
			return PostServiceImpl.updatePost(posts,Postid);  
			 
			}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/count-all-posts
		@GetMapping("/Post/count-all-posts")
		private int getpostscount() {
			return PostServiceImpl.CountPosts();
		}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/posts-by-user/{idU}
		@GetMapping("/Post/posts-by-user/{idU}")
		private List<Post> getPostsByUser(@PathVariable("idU") int idU) {
			return PostServiceImpl.getPostsByUserId(idU);

		}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/count-user-posts/{idU}
		@GetMapping("/Post/count-user-posts/{idU}")
		private int getuserpostscount(@PathVariable("idU") int idU) {
			return PostServiceImpl.CountPostsByUser(idU);
		}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/search/?pattern=
		@GetMapping("/Post/search/")
		private List<Post> postSearch(@RequestParam("pattern")String pattern){
		System.out.println(pattern);
			return PostServiceImpl.searchPosts(pattern);
		
		}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/posts-commented-by-user/{idU}
		@GetMapping("/Post/posts-commented-by-user/{idU}")
		private List<Post> getPostsCommentedByUser(@PathVariable("idU") int idU) {
			return PostServiceImpl.getPostsCommentedByUser(idU);

		}
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/posts-liked-by-user/{idU}
		@GetMapping("/Post/posts-liked-by-user/{idU}")
		private List<Post> getPostsLikedByUser(@PathVariable("idU") int idU) {
			return PostServiceImpl.getPostsLikedByUser(idU);

		}
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/share-post/{idP}/{idU}
		@PostMapping("/Post/share-post/{idP}/{idU}")  
		private String sharePost(@PathVariable("idP")int idP, @PathVariable("idU")int idU)   
		{  
			return  (PostServiceImpl.sharePost(idP, idU));  
					
		}  
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/report-post/{idP}/{idU}
		@PostMapping("/Post/report-post/{idP}/{idU}")  
		private String reportPost(@PathVariable("idP")int idP, @PathVariable("idU")int idU)   
		{  
			return  (PostServiceImpl.reportPost(idP, idU));  
							
		} 
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/detectimg/?img=
		@GetMapping("/Post/detectimg/")  
		private List<String> moderation(@RequestParam("img")String img) throws Exception 
		{  
			return PostServiceImpl.detect(img);  
		}
		
//admin 
		//URL: http://localhost:9293/SpringMVC/servlet/Post/reported
		@GetMapping("/Post/reported")
		private List<Post> getReportedPosts() {
		return PostServiceImpl.getReportedPosts();

				}
		//URL: http://localhost:9293/SpringMVC/servlet/Post/approve-reported/{Postid}
		@DeleteMapping("/Post/approve-reported/{Postid}")  
		private void approveReportedPost(@PathVariable("Postid") int Postid)   
		{  
			PostServiceImpl.approveReportedPost(Postid);  
		}

		
	}