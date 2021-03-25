package tn.esprit.pi.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Chat;
import tn.esprit.pi.entities.ChatKeyWord;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ChatKeyWordRepository;
import tn.esprit.pi.repositories.ChatReposiroty;
import tn.esprit.pi.repositories.IUserRepository;

@Component
@Service
public class ChatService implements IChatService {

	@Autowired
	ChatReposiroty chatRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	ChatKeyWordRepository chatKeyWordRepository;
	@Autowired
	ChatSuggestionService chatSuggestionService;

	// Add chat
	@Override
	public Chat addChat(Chat chat, int idU) {
		System.out.println("IDU=> " + idU);
		User user = userRepository.findById(idU).get();
		chat.setUser(user);
		chatRepository.save(chat);
		return chat;
	}

	// Add key words to a chat
	@Override
	public String addKeyWords(List<ChatKeyWord> keyWords, int idC) {
		Chat chat = chatRepository.findById(idC);
		if (keyWords.size() < 4) {
			return "It's not much, you have to add some key words";
		} else {
			for (ChatKeyWord chatKeyWord : keyWords) {
				chatKeyWord.setChat(chat);
				chatKeyWordRepository.save(chatKeyWord);
			}
		}

		return "Key words added with success!!";
	}

	// Display all chats
	@Override
	public List<Chat> displayAll() {
		List<Chat> chats = chatRepository.displayAll();
		for (Chat chat : chats) {
			List<ChatKeyWord> words = chatKeyWordRepository.diplayByChatId(chat);
			chat.setChatKeyWord(words);
		}
		return chats;
	}

	// Display words by response
	@Override
	public List<ChatKeyWord> diplayByChatId(int idC) {
		Chat chat = chatRepository.findById(idC);
		return chatKeyWordRepository.diplayByChatId(chat);
	}

	// Display chat by id
	@Override
	public Chat displayChatById(int idC) {
		Chat chat = chatRepository.findById(idC);
		return chat;
	}

	// Edit chat by id
	@Override
	public Chat updateChat(Chat chat, int idC) {
		Chat c = chatRepository.findById(idC);
		List<ChatKeyWord> words = c.getChatKeyWord();
		for (ChatKeyWord chatKeyWord : words) {
			// chatKeyWord.setChat(chat);
			chatKeyWordRepository.deleteChatKeyWord(chatKeyWord.getId());
		}
		c.setRespense(chat.getRespense());
		c.setChatKeyWord(null);
		c.setChatKeyWord(chat.getChatKeyWord());

		List<ChatKeyWord> words2 = c.getChatKeyWord();
		System.out.println("WORDS=> " + words2);
		for (ChatKeyWord chatKeyWord : words2) {
			chatKeyWord.setChat(c);

		}
		chatRepository.save(c);
		return c;
	}

	// Delete chat and his key words
	@Override
	public void deleteChat(int idC) {
		Chat chat = chatRepository.findById(idC);
		for (ChatKeyWord word : chat.getChatKeyWord()) {
			chatKeyWordRepository.delete(word);
		}
		chatRepository.deleteChat(idC);
	}

	// Find the response
	@Override
	public String getRespenseBasedOnWord(String word) {
		double max = 0.0;
		int index = 0;
		Set<String> mainWords = new HashSet<>();
		for (String s : word.split("\\W")) {
			if (s.length() > 1) {
				mainWords.add(s);
			}
		}

		List<Chat> chats = chatRepository.displayAll();
		for (Chat chat : chats) {
			Set<String> mainWordsToFind = new HashSet<>(mainWords);
			List<ChatKeyWord> words = chatKeyWordRepository.diplayByChatId(chat);
			for (ChatKeyWord keyWord : words) {
				if (keyWord.getWord().length() > 1) {
					mainWordsToFind.remove(keyWord.getWord());
				}
			}
			double count = (double) (mainWords.size() - mainWordsToFind.size()) / mainWords.size();
			if (count > max) {
				max = count;
				index = chat.getId();
			}
			System.out.println("Count nbr: " + (double) (mainWords.size() - mainWordsToFind.size()) / mainWords.size());
		}
		if (index != 0 || max > 0.4) {
			Chat c = chatRepository.findById(index);
			c.setNbRequest(c.getNbRequest() + 1);
			chatRepository.save(c);
			return c.getRespense();

		} else {
			chatSuggestionService.addChatSuggestion(word);
			return "Sorry, here we can't help you, please contact us with mail";
		}
	}

	@Override
	public String connectToChat() {
		return "Hi, how Can we help you?";
	}

	@Override
	public List<Chat> getChatsByMostRec() {
		return chatRepository.getChatsByMostRec();
	}
}
