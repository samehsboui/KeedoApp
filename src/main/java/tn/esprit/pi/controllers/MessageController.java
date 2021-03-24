package tn.esprit.pi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.pi.config.FileUploadUtil;
import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.MessageService;

@RestController
@RequestMapping("message/")
public class MessageController {

	ObjectMapper objM = new ObjectMapper();

	@Autowired
	MessageService messageService;

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PostMapping("connect/{id}")
	public List<User> connected(@PathVariable("id") int id) {
		return messageService.connected(id);

	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PostMapping(value = "sendMessage/{idS}/{idR}")
	public Retour<Message> sendMessage(@PathVariable("idS") int idS, @PathVariable("idR") int idR,
			@RequestBody Message message) {

		return messageService.sendMessage(idS, idR, message);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PostMapping(value = "sendFile/{idS}/{idR}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Retour<Message> sendFile(@PathVariable("idS") int idS, @PathVariable("idR") int idR,
			@RequestPart("image") MultipartFile file) throws IOException {

		Message message = new Message();
		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploadYass/";
		System.out.println("image name =" + fileNameRepo);
		try {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploadYass/")
					.path(fileNameRepo).toUriString();
			System.out.println("file url =====>" + fileDownloadUri);
			message.setImage(fileDownloadUri.getBytes());
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return messageService.sendMessage(idS, idR, message);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@GetMapping("checkMsg/{idS}/{idR}")
	public List<Message> checkMessage(@PathVariable("idS") int idS, @PathVariable("idR") int idR) {
		return messageService.checkMessage(idS, idR);
	}

}
