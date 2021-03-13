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
import tn.esprit.pi.entities.Liking;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.services.LikingServiceImpl;

@RestController
public class LikingController {
	
		@Autowired  
		LikingServiceImpl LikingServiceImpl;
		
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/add-like/{idU}/{idP}
				@PostMapping("/Liking/add-like/{idU}/{idP}")  
				private String addLike(@RequestBody Liking likes, @PathVariable("idU")int idU, @PathVariable("idP")int idP )   
				{    
					/*likes.getIdLiking()*/
					return (LikingServiceImpl.addLiking(likes, idU, idP)+ ", number of likes on this post: " +LikingServiceImpl.CountLikingsByPost(idP));  
				} 
				

				//URL: http://localhost:9293/SpringMVC/servlet/Liking/delete-like/{Likeid}
				@DeleteMapping("/Liking/delete-like/{Likeid}")  
				private void deleteLiking(@PathVariable("Likeid") int Likeid)   
				{  
					System.out.println("this is the id"+ Likeid);
					LikingServiceImpl.deleteLiking(Likeid);  
				} 
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/get-all-likes
				@GetMapping("/Liking/get-all-likes")  
				private List<Liking> getAllLikes()   
				{  
					return LikingServiceImpl.getAllLikings();  
				}  
		
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/detail-like/{Likingid}
				@GetMapping("/Liking/detail-like/{Likingid}")  
				private Liking getLike(@PathVariable("Likingid") int Likingid)   
				{  
					return LikingServiceImpl.getLikingById(Likingid);  
				}  
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/count-all-likes
				@GetMapping("/Liking/count-all-likes")
				private int getlikescount() {
				return LikingServiceImpl.CountLikings();
				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/likes-by-user/{idU}
				@GetMapping("/Liking/likes-by-user/{idU}")
				private List<Liking> getLikesByUser(@PathVariable("idU") int idU) {
					return LikingServiceImpl.getLikingsByUserId(idU);

				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/count-user-likes/{idU}
				@GetMapping("/Liking/count-user-likes/{idU}")
				private int getuserlikescount(@PathVariable("idU") int idU) {
				return LikingServiceImpl.CountLikingsByUser(idU);
				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/likes-by-post/{idP}
				@GetMapping("/Liking/likes-by-post/{idP}")
				private List<Liking> getLikingsByPost(@PathVariable("idP") int idP) {
					return LikingServiceImpl.getLikingsByPostId(idP);

				}
				
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/count-post-likes/{idP}
				@GetMapping("/Liking/count-post-likes/{idP}")
				private int getpostlikescount(@PathVariable("idP") int idP) {
				return LikingServiceImpl.CountLikingsByPost(idP);
				}
				
				//URL: http://localhost:9293/SpringMVC/servlet/Liking/exists-user-post/{idU}/{idP}
				@GetMapping("/Liking/exists-user-post/{idU}/{idP}")
				public boolean existsByUserAndPost(@PathVariable("idU")int idU, @PathVariable("idP")int idP ) {
					return LikingServiceImpl.IsLikeExists(idU, idP);

				}
}


				

				
				
	