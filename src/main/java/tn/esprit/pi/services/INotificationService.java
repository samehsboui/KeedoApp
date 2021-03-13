package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Notification;

public interface INotificationService {
	
	public void notifyAllUser(Event ev);
	public List<Notification> myNotifications(); 

}
