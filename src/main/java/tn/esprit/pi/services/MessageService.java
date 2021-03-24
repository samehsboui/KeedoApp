package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.NotificationMsg;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.MessageRepository;

@Service
public class MessageService implements IMessageService {

	@Autowired
	IUserRepository userRepository;
	@Autowired
	FollowService followService;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	FollowRepository followRepository;
	@Autowired
	NotificationMsgService notificationMsgService;

	@Override
	public List<User> connected(int id) {
		User user = userRepository.findById(id).get();
		user.setStatus(true);
		userRepository.save(user);
		List<User> myFriends = followService.getUserFollowing(id).getUserList();
		return myFriends;
	}

	@Override
	public Retour<Message> sendMessage(int idS, int idR, Message message) {
		Retour<Message> rt = new Retour<>();

		User sender = userRepository.findById(idS).get();
		User receiver = userRepository.findById(idR).get();

		Message m = new Message();
		System.out.println("tst1: ");
		List<Message> messages1 = messageRepository.getMessages(sender, receiver);
		System.out.println("messages1=> " + messages1.size());
		System.out.println("tst2: ");
		List<Message> messages2 = messageRepository.getMessages(receiver, sender);
		System.out.println("messages2=> " + messages2.size());
		List<Message> messages = new ArrayList<Message>();
		messages.addAll(messages1);
		messages.addAll(messages2);
		System.out.println("messages=> " + messages.size());
		// Delete the oldest message
		if (messages1.size() >= 100) {
			m = messages1.get(0);
			messageRepository.deleteMessageById(m.getIdMessage());
		}

		List<User> myFriends = followRepository.listFollowing(sender);
		if (!myFriends.contains(receiver)) {
			rt.setStringValue("Sorry you can't connect him!, follow him at the first");
		} else {
			message.setSender(sender);
			message.setReceiver(receiver);
			message.setDate(new Date(System.currentTimeMillis()));
			message.setTime(new Date(System.currentTimeMillis()));
			messageRepository.save(message);
			NotificationMsg notificationMsg = new NotificationMsg();
			notificationMsg
					.setContent("You get new message from " + sender.getFirstName() + " " + sender.getLastName());
			notificationMsgService.addNotif(idS, idR, notificationMsg);
			rt.setStringValue("message sent successfully!!");
			rt.getObjectValue().add(message);
		}
		return rt;
	}

	@Override
	public List<Message> checkMessage(int idS, int idR) {
		User sender = userRepository.findById(idS).get();
		User receiver = userRepository.findById(idR).get();
		return messageRepository.getMessages(sender, receiver);
	}

}
