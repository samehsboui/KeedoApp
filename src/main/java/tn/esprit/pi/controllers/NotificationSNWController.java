package tn.esprit.pi.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping("/Notif/get-notif/{idN}")
	private NotificationSNW getNotif(@PathVariable("idN") int idN)   
	{  
		return NotifServiceImpl.getNotifById(idN);  
	}  
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/get-all-notif
	@GetMapping("/Notif/get-all-notif")
	private List<NotificationSNW> getAllNotif()   
	{  
		return NotifServiceImpl.getAllNotif();  
	}  
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/get-notif-by-user/{idU}
	@GetMapping("/Notif/get-notif-by-user/{idU}")
	private List<NotificationSNW> getNotifByUser(@PathVariable("idU") int idU)   
	{  
		return NotifServiceImpl.getNotifByUser(idU);  
	}  
	
	//URL: http://localhost:9293/SpringMVC/servlet/Notif/delete-notif/{idN}
	@DeleteMapping("/Notif/delete-notif/{idN}")  
	private void deleteNotif(@PathVariable("idN") int idN)   
	{  
		System.out.println("this is the id"+ idN);
		NotifServiceImpl.deleteNotif(idN);  
	} 
}
