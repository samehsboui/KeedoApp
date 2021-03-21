package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("add/{idU}")
	public Chat addContent(@RequestBody Chat chat, @PathVariable int idU) {
		return chatService.addChat(chat, idU);
	}

	// Add key words by chat
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("addKW/{idC}")
	public String addKeyWords(@RequestBody List<ChatKeyWord> keyWords, @PathVariable int idC) {
		return chatService.addKeyWords(keyWords, idC);
	}

	// Delete chat with key word
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("delete/{idC}")
	public void deleteChat(@PathVariable int idC) {
		chatService.deleteChat(idC);
	}

	// Display All chats
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("getAll")
	public List<Chat> displayAll() {
		return chatService.displayAll();
	}

	// Display words by response
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("displayKeWords/{idC}")
	public List<ChatKeyWord> diplayByChatId(@PathVariable int idC) {
		return chatService.diplayByChatId(idC);
	}

	// Display chat by id
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("displayChatId/{idC}")
	public Chat displayChatById(@PathVariable int idC) {
		return chatService.displayChatById(idC);
	}

	// Edit chat by id
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("updateC/{idC}")
	public Chat updateChat(@RequestBody Chat chat, @PathVariable int idC) {
		return chatService.updateChat(chat, idC);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')  ")
	@GetMapping("replayBasedOnWords")
	public String getRespenseBasedOnWord(@RequestBody String word) {
		return chatService.getRespenseBasedOnWord(word.intern());
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')  ")
	@GetMapping("connectToChat")
	public String connectToChat() {
		return chatService.connectToChat();
	}

}
