package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Chat;
import tn.esprit.pi.entities.ChatKeyWord;;

public interface IChatService {
	public Chat addChat(Chat chat, int idU);

	public String addKeyWords(List<ChatKeyWord> keyWords, int idC);

	public List<Chat> displayAll();

	public List<ChatKeyWord> diplayByChatId(int idC);

	public Chat displayChatById(int idC);

	public Chat updateChat(Chat chat, int idC);

	public void deleteChat(int idC);

	public String getRespenseBasedOnWord(String word);

	public String connectToChat();

	public List<Chat> getChatsByMostRec();
}
