package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Chat;
import tn.esprit.pi.entities.ChatKeyWord;
import tn.esprit.pi.services.ChatService;

@RestController
@RequestMapping("chat/")
public class ChatController {

	@Autowired
	ChatService chatService;

	// Add Chat
	@PostMapping("add/{idU}")
	public Chat addContent(@RequestBody Chat chat, @PathVariable int idU) {
		return chatService.addChat(chat, idU);
	}

	// Add key words by chat
	@PostMapping("addKW/{idC}")
	public String addKeyWords(@RequestBody List<ChatKeyWord> keyWords, @PathVariable int idC) {
		return chatService.addKeyWords(keyWords, idC);
	}

	// Delete chat with key word
	@DeleteMapping("delete/{idC}")
	public void deleteChat(@PathVariable int idC) {
		chatService.deleteChat(idC);
	}

	// Display All chats
	@GetMapping("getAll")
	public List<Chat> displayAll() {
		return chatService.displayAll();
	}

	// Display words by response
	@GetMapping("displayKeWords/{idC}")
	public List<ChatKeyWord> diplayByChatId(@PathVariable int idC) {
		return chatService.diplayByChatId(idC);
	}

	// Display chat by id
	@GetMapping("displayChatId/{idC}")
	public Chat displayChatById(@PathVariable int idC) {
		return chatService.displayChatById(idC);
	}

	// Edit chat by id
	@PutMapping("updateC/{idC}")
	public Chat updateChat(@RequestBody Chat chat, @PathVariable int idC) {
		return chatService.updateChat(chat, idC);
	}

	@GetMapping("replayBasedOnWords")

	public String getRespenseBasedOnWord(@RequestBody String word) {
		return chatService.getRespenseBasedOnWord(word.intern());
	}

	@GetMapping("connectToChat")
	public String connectToChat() {
		return chatService.connectToChat();
	}

}
