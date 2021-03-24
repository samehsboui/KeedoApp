package tn.esprit.pi.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.pi.entities.NotificationMsg;

public interface INotificationMsgService {
	public NotificationMsg addNotif(int idS, int idR, NotificationMsg notification);

	public List<NotificationMsg> findAllByUserReceive(int idU);

	public NotificationMsg findByNotificationId(@PathVariable("idU") int id);
}
