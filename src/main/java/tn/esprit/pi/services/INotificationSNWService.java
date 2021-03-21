package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.NotificationSNW;

public interface INotificationSNWService {
	String addLikeNotif(int idL);
	String addCommentNotif(int idC);

	NotificationSNW getNotifById(int id);

	List<NotificationSNW> getAllNotif();

	List<NotificationSNW> getNotifByUser(int id);

	void deleteNotif(int id);


}
