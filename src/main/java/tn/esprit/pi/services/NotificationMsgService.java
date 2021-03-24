package tn.esprit.pi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.pi.entities.NotificationMsg;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.NotificationMsgRepository;

@Service
public class NotificationMsgService implements INotificationMsgService {
	@Autowired
	private NotificationMsgRepository notificationRepository;
	@Autowired
	IUserRepository userRepository;

	// Add notification
	@Override
	public NotificationMsg addNotif(int idS, int idR, NotificationMsg notification) {
		User s = userRepository.findById(idS).get();
		User r = userRepository.findById(idR).get();
		notification.setUserSend(s);
		notification.setUserReceive(r);
		notification.setCreatedAt(new Date(System.currentTimeMillis()));
		notification.setRead(false);
		return notificationRepository.save(notification);
	}

	@Override
	public List<NotificationMsg> findAllByUserReceive(int idU) {
		User user = userRepository.findById(idU).get();
		return notificationRepository.userNotification(user);
	}

	@Override
	public NotificationMsg findByNotificationId(@PathVariable("idU") int id) {
		NotificationMsg notif = notificationRepository.findById(id).get();
		notif.setRead(true);
		notificationRepository.save(notif);
		return notif;
	}
}
