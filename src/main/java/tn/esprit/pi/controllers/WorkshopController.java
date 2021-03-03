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
import tn.esprit.pi.entities.Workshop;
import tn.esprit.pi.entities.WorkshopCategory;
import tn.esprit.pi.services.WorkshopServiceImpl;

@RestController
public class WorkshopController {
	@Autowired  
	WorkshopServiceImpl WorkshopServiceImpl;

	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/get-all-Workshops
	@GetMapping("/Workshop/get-all-Workshops")  
	private List<Workshop> getAllWorkshops()   
	{  
		return WorkshopServiceImpl.getAllWorkshops();  
	}  
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/detail-Workshop/{Workshopid}
	@GetMapping("/Workshop/detail-Workshop/{Workshopid}")  
	private Workshop getWorkshop(@PathVariable("Workshopid") int Workshopid)   
	{  
		return WorkshopServiceImpl.getWorkshopById(Workshopid);  
	}  
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/delete-Workshop/{Workshopid}
	@DeleteMapping("/Workshop/delete-Workshop/{Workshopid}")  
	private void deleteWorkshop(@PathVariable("Workshopid") int Workshopid)   
	{  
		WorkshopServiceImpl.deleteWorkshop(Workshopid);  
	} 

	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/add-Workshop/{idU}
	@PostMapping("/Workshop/add-Workshop/{idU}")  
	private int addWorkshop(@RequestBody Workshop Workshops, @PathVariable("idU")int idU)   
	{  
		WorkshopServiceImpl.addWorkshop(Workshops, idU);  
		return Workshops.getIdWorkshop();  
	}  
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/update-Workshop/{Workshopid}
	@PutMapping("/Workshop/update-Workshop/{Workshopid}")  
	private Workshop updateWorkshop(@RequestBody Workshop Workshops, @PathVariable("Workshopid")int Workshopid)   
	{  
			WorkshopServiceImpl.updateWorkshop(Workshops,Workshopid);  
			return Workshops;  
		}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/count-all-Workshops
	@GetMapping("/Workshop/count-all-Workshops")
	private int getWorkshopscount() {
	return WorkshopServiceImpl.CountWorkshops();
	}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/Workshops-by-user/{idU}
	@GetMapping("/Workshop/Workshops-by-user/{idU}")
	private List<Workshop> getWorkshopsByUser(@PathVariable("idU") int idU) {
		return WorkshopServiceImpl.getWorkshopsByUserId(idU);

	}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/count-user-Workshops/{idU}
	@GetMapping("/Workshop/count-user-Workshops/{idU}")
	private int getuserWorkshopscount(@PathVariable("idU") int idU) {
	return WorkshopServiceImpl.CountWorkshopsByUser(idU);
	}
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/search/?pattern=
	@GetMapping("/Workshop/search/")
	private List<Workshop> WorkshopSearch(@RequestParam("pattern")String pattern){
	System.out.println(pattern);
	return WorkshopServiceImpl.searchWorkshops(pattern);
	
	}
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/get-by-category/{category}

	@GetMapping("/Workshop/get-by-category/{category}")
	public List<Workshop> getWorkshopByCategory(@PathVariable WorkshopCategory category) {
		 List<Workshop> w = WorkshopServiceImpl.filterWorkshop(category);
		return w;
		}
	
	//needs fixing
	
	//URL: http://localhost:9293/SpringMVC/servlet/Workshop/Workshops-by-username/?name=
	@GetMapping("/Workshop/Workshops-by-user")
	private List<Workshop> getWorkshopsByKindergartenName(@RequestParam("name")String name){
	return WorkshopServiceImpl.getWorkshopsByKindergartenName(name);
	
	}
	
}
