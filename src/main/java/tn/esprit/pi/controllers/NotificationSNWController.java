package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.services.NotificationSNWServiceImpl;

@RestController
public class NotificationSNWController {
	@Autowired  
	NotificationSNWServiceImpl NotifServiceImpl;
	
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/get-notif/{idN}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Notif/get-notif/{idN}")
		public NotificationSNW getNotif(@PathVariable("idN") int idN)   
		{  
			return NotifServiceImpl.getNotifById(idN);  
		}  
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/get-all-notif
	@PreAuthorize("hasAuthority('Admin')")	
		@GetMapping("/Notif/get-all-notif")
		public List<NotificationSNW> getAllNotif()   
		{  
			return NotifServiceImpl.getAllNotif();  
		}  
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/get-notif-by-user/{idU}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@GetMapping("/Notif/get-notif-by-user/{idU}")
		public List<NotificationSNW> getNotifByUser(@PathVariable("idU") int idU)   
		{  
			return NotifServiceImpl.getNotifByUser(idU);  
		}  
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/delete-notif/{idN}
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
		@DeleteMapping("/Notif/delete-notif/{idN}")  
		public void deleteNotif(@PathVariable("idN") int idN)   
		{  
			//System.out.println("this is the id"+ idN);
			NotifServiceImpl.deleteNotif(idN);  
		} 
}
