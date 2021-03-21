package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Workshop;
import tn.esprit.pi.entities.WorkshopCategory;
import tn.esprit.pi.services.WorkshopServiceImpl;

@RestController
public class WorkshopController {
	@Autowired  
	WorkshopServiceImpl WorkshopServiceImpl;

	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/get-all-Workshops
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Workshop/get-all-Workshops")  
		public List<Workshop> getAllWorkshops()   
		{  
			return WorkshopServiceImpl.getAllWorkshops();  
		}  
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/detail-Workshop/{Workshopid}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Workshop/detail-Workshop/{Workshopid}")  
		public Workshop getWorkshop(@PathVariable("Workshopid") int Workshopid)   
		{  
			return WorkshopServiceImpl.getWorkshopById(Workshopid);  
		}  
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/delete-Workshop/{Workshopid}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
		@DeleteMapping("/Workshop/delete-Workshop/{Workshopid}")  
		public void deleteWorkshop(@PathVariable("Workshopid") int Workshopid)   
		{  
			WorkshopServiceImpl.deleteWorkshop(Workshopid);  
		} 

	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/add-Workshop/{idU}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
		@PostMapping("/Workshop/add-Workshop/{idU}")  
		public int addWorkshop(@RequestBody Workshop Workshops, @PathVariable("idU")int idU)   
		{  
			WorkshopServiceImpl.addWorkshop(Workshops, idU);  
			return Workshops.getIdWorkshop();  
		}  
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/update-Workshop/{Workshopid}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
		@PutMapping("/Workshop/update-Workshop/{Workshopid}")  
		public Workshop updateWorkshop(@RequestBody Workshop Workshops, @PathVariable("Workshopid")int Workshopid)   
		{  
			return WorkshopServiceImpl.updateWorkshop(Workshops,Workshopid);  
		}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/count-all-Workshops
	@PreAuthorize("hasAuthority('Admin')")	
		@GetMapping("/Workshop/count-all-Workshops")
		public int getWorkshopscount() {
			return WorkshopServiceImpl.CountWorkshops();
		}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/Workshops-by-user/{idU}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Workshop/Workshops-by-user/{idU}")
		public List<Workshop> getWorkshopsByUser(@PathVariable("idU") int idU) {
			return WorkshopServiceImpl.getWorkshopsByUserId(idU);
		}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/count-user-Workshops/{idU}
	@PreAuthorize("hasAuthority('Admin')")		
		@GetMapping("/Workshop/count-user-Workshops/{idU}")
		public int getuserWorkshopscount(@PathVariable("idU") int idU) {
			return WorkshopServiceImpl.CountWorkshopsByUser(idU);
		}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/search/?pattern=
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")	
		@GetMapping("/Workshop/search/")
		public List<Workshop> WorkshopSearch(@RequestParam("pattern")String pattern){
			//System.out.println(pattern);
			return WorkshopServiceImpl.searchWorkshops(pattern);
		}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/get-by-category/{category}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Workshop/get-by-category/{category}")
		public List<Workshop> getWorkshopByCategory(@PathVariable WorkshopCategory category) {
			List<Workshop> w = WorkshopServiceImpl.filterWorkshop(category);
			return w;
		}
	
	//needs fixing
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/Workshops-by-username/?name=
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Workshop/Workshops-by-user")
		public List<Workshop> getWorkshopsByKindergartenName(@RequestParam("name")String name){
		return WorkshopServiceImpl.getWorkshopsByKindergartenName(name);
	
		}
	
}