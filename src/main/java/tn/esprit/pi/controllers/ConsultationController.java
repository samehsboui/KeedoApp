package tn.esprit.pi.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ConsultationRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.services.ConsultationService;
import tn.esprit.pi.services.GoogleCalService;

@RestController
public class ConsultationController {

	@Autowired
	ConsultationService consultationService;
	@Autowired
	GoogleCalService googleCalService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ConsultationRepository consultationRepository;

	@PostMapping("consult/add/{idK}/{idA}/{idD}")
	public Retour<User> affectConsultationToKid(@RequestBody Consultation consultation, @PathVariable("idK") int idK,
			@PathVariable("idA") int idA, @PathVariable("idD") int idD) {
		return consultationService.affectConsultationToKid(consultation, idK, idA, idD);
	}

	@DeleteMapping("consult/del/{id}")
	public void deleteConsultation(@PathVariable int id) {
		consultationService.deleteConsultation(id);
	}

	@PutMapping("consult/up/{idC}")
	public Consultation updateConsultation(@PathVariable("idC") int idC, @RequestBody Consultation consultation) {
		return consultationService.updateConsultation(idC, consultation);
	}

	@GetMapping("consult/getAll")
	public List<Consultation> displayAllConsultations() {
		return consultationService.displayAllConsultations();
	}

	@GetMapping("consult/getAll/ToDay")
	public List<Consultation> displayConsultationsToDay() {
		return consultationService.displayConsultationsToDay();
	}

	@GetMapping("consult/kid/getAll/{idK}")
	public List<Consultation> displayConsultationsByKid(@PathVariable("idK") int idK) {
		return consultationService.displayConsultationsByKid(idK);
	}

	@GetMapping("consult/doctor/getAll/{idD}")
	public List<Consultation> displayConsultationsByDoctor(@PathVariable("idD") int idD) {
		return consultationService.displayConsultationsByDoctor(idD);
	}

	// STATIC

	@GetMapping("consult/static/doctor")
	public Map<String, Integer> percentageParticipationByDoctor() {
		return consultationService.percentageParticipationByDoctor();
	}

	@GetMapping("consult/static/nbC/{m}")
	public int getPerMonth(@PathVariable("m") String m) {
		List<Consultation> consult = consultationRepository.findByMatchMonthAndMatchDay("-" + m + "-");
		return consult.size();
	}

	// To login
	@RequestMapping(value = "/login/google", method = RequestMethod.GET)
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
		return new RedirectView(googleCalService.authorize());
	}

	// Calando
	@RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
	public String authenticateCal(@RequestParam(value = "code") String code) {
		return googleCalService.addEvent(code);
	}

}
