package tn.esprit.pi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.Liking;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.repositories.INotificationSNWRepository;
import tn.esprit.pi.repositories.ICommentRepository;
import tn.esprit.pi.repositories.ILikingRepository;

@Service
public class NotificationSNWServiceImpl implements INotificationSNWService{
	@Autowired 
	INotificationSNWRepository INotificationSNWRepository;

	@Autowired 
	ICommentRepository ICommentRepository;
	
	@Autowired 
	ILikingRepository ILikingRepository;
	
	@Override
    public String addLikeNotif (int idL) {
		NotificationSNW notif = new NotificationSNW();
		Liking liking=ILikingRepository.findById(idL).get();
		notif.setLike(liking);
		notif.setSubject(liking.getUser().getLogin()+" liked your post");
		LocalDateTime notifDate = LocalDateTime.now();
		notif.setDate(notifDate);
		notif.setSender(liking.getUser().getIdUser());
		notif.setReceiver(liking.getPost().getUser().getIdUser());
		INotificationSNWRepository.save(notif);
        return ("like notification sent from " +liking.getUser().getLogin() +" to " +liking.getPost().getUser().getLogin()+ " successfully" );
    }
	
	
	@Override	
	  public String addCommentNotif (int idC) {
		NotificationSNW notif = new NotificationSNW();
		Comment comment=ICommentRepository.findById(idC).get();
		notif.setComment(comment);
		notif.setSubject(comment.getUser().getLogin()+" commented on your post");
		LocalDateTime notifDate = LocalDateTime.now();
		notif.setDate(notifDate);
		notif.setSender(comment.getUser().getIdUser());
		notif.setReceiver(comment.getPost().getUser().getIdUser());
		INotificationSNWRepository.save(notif);
		return ("comment notification sent from " +comment.getUser().getLogin() +" to " +comment.getPost().getUser().getLogin()+ " successfully" );
	    }

    @Override
    public NotificationSNW getNotifById(int id) {

        return INotificationSNWRepository.findById(id).get();
    }

    @Override
    public List<NotificationSNW> getAllNotif() {
		List<NotificationSNW>Notifs = new ArrayList<NotificationSNW>();
		INotificationSNWRepository.findAll().forEach(e ->Notifs.add(e));
        return Notifs;

    }

    @Override
    public List<NotificationSNW> getNotifByUser(int id) {

        return INotificationSNWRepository.getNotifByUser(id);

    }


    @Override
    public void deleteNotif(int id) {

       INotificationSNWRepository.deleteById(id);
    }
}

