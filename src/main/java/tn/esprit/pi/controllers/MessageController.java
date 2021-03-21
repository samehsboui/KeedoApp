package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.storage.UserStorage;

@RestController
public class MessageController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	IUserRepository userRepository;

	@MessageMapping("/chat/{idTo}")
	public void sendMessage(@DestinationVariable int idTo, @RequestBody Message message) {
		System.out.println("handling send message: " + message + " to: " + idTo);
		boolean isExists = UserStorage.getInstance().getUsers().contains(idTo);
		if (isExists) {
			User user = userRepository.findById(idTo).get();
			simpMessagingTemplate.convertAndSend("/topic/messages/" + user, message);
		}
	}
}
