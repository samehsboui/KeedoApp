package tn.esprit.pi.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Notification;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.INotificationRepository;
import tn.esprit.pi.repositories.IUserRepository;

@Service
public class NotificationServiceImpl  implements INotificationService{

	INotificationRepository iNotificationRepository;
	
	IUserRepository iUserRepository;
	

	/**********************************Admin**********************************/
	//Send Notifications to all users for contribution
	@Override
	public void notifyAllUser(Event ev) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		List<User> allUsers = (List<User>)iUserRepository.findAll();
		
		for(User u : allUsers) {
			Notification n = new Notification();
			n.setEvent(ev);
			n.setUser(u);
			n.setDescription("Dear "+u.getLastName()+" "+u.getFirstName()+", we invite you to contribute by an amount of money to the event "+
					  ev.getTitle()+" for "+ev.getDescription()+".Thank you.");
			n.setDate(date);
			n.setStatus("Not Seen Yet");
			iNotificationRepository.save(n);
		}		
	}

	/**********************************User**********************************/
	//retrieve my own notifications
	@Override
	public List<Notification> myNotifications() {
		//List<Notification> list = NR.myNotifications(UserController.USERCONNECTED);
		//return list;
		return null;
	}

}