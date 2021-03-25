package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;

public interface IMessageService {
	List<User> connected(int id);

	List<User> disConnected(int id);

	Retour<Message> sendMessage(int idS, int idR, Message message);

	List<Message> checkMessage(int idS, int idR);
}
