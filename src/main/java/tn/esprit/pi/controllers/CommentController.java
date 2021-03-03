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
import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.services.CommentServiceImpl;

@RestController
public class CommentController {
	
		@Autowired  
		CommentServiceImpl CommentServiceImpl;
		
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/add-comment/{idU}/{idP}
				@PostMapping("/Comment/add-comment/{idU}/{idP}")  
				private int addComment(@RequestBody Comment Comments, @PathVariable("idU")int idU, @PathVariable("idP")int idP )   
				{  
					CommentServiceImpl.addComment(Comments, idU, idP);  
					return Comments.getIdComment();  
				} 
				

				//URL: http://localhost:9293/SpringMVC/servlet/Comment/delete-comment/{Commentid}
				@DeleteMapping("/Comment/delete-comment/{Commentid}")  
				private void deleteComment(@PathVariable("Commentid") int Commentid)   
				{  
					System.out.println("this is the id"+ Commentid);
					CommentServiceImpl.deleteComment(Commentid);  
				} 


				//URL: http://localhost:9293/SpringMVC/servlet/Comment/update-comment/{Commentid}
				@PutMapping("/Comment/update-comment/{Commentid}")  
				private Comment updateComment(@RequestBody Comment comments, @PathVariable("Commentid")int Commentid)   
				{  
					return CommentServiceImpl.updateComment(comments,Commentid);  
						  
					}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/get-all-comments
				@GetMapping("/Comment/get-all-comments")  
				private List<Comment> getAllComments()   
				{  
					return CommentServiceImpl.getAllComments();  
				}  
		
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/detail-comment/{Commentid}
				@GetMapping("/Comment/detail-comment/{Commentid}")  
				private Comment getComment(@PathVariable("Commentid") int Commentid)   
				{  
					return CommentServiceImpl.getCommentById(Commentid);  
				}  
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/count-all-comments
				@GetMapping("/Comment/count-all-comments")
				private int getcommentscount() {
				return CommentServiceImpl.CountComments();
				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/comments-by-user/{idU}
				@GetMapping("/Comment/comments-by-user/{idU}")
				private List<Comment> getCommentsByUser(@PathVariable("idU") int idU) {
					return CommentServiceImpl.getCommentsByUserId(idU);

				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/count-user-comments/{idU}
				@GetMapping("/Comment/count-user-comments/{idU}")
				private int getusercommentscount(@PathVariable("idU") int idU) {
				return CommentServiceImpl.CountCommentsByUser(idU);
				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/comments-by-post/{idP}
				@GetMapping("/Comment/comments-by-post/{idP}")
				private List<Comment> getCommentsByPost(@PathVariable("idP") int idP) {
					return CommentServiceImpl.getCommentsByPostId(idP);

				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/count-post-comments/{idP}
				@GetMapping("/Comment/count-post-comments/{idP}")
				private int getpostcommentscount(@PathVariable("idP") int idP) {
				return CommentServiceImpl.CountCommentsByPost(idP);
				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Comment/search/?pattern=
				@GetMapping("/Comment/search/")
				private List<Comment> commentSearch(@RequestParam("pattern")String pattern){
				System.out.println(pattern);
				return CommentServiceImpl.searchComments(pattern);
				
				}
				
				
				
				
	}
