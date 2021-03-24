package tn.esprit.pi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ConsultationRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.services.ConsultationService;

//import static tn.esprit.pi.security.CustomLoginSuccessHandler.idCurrent;

@RestController
public class ConsultationController {

	@Autowired
	ConsultationService consultationService;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	ConsultationRepository consultationRepository;

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('Doctor')")
	@PostMapping("consult/add/{idK}/{idA}/{idD}")
	public Retour<User> affectConsultationToKid(@RequestBody Consultation consultation, @PathVariable("idK") int idK,
			@PathVariable("idA") int idA, @PathVariable("idD") int idD) {
		return consultationService.affectConsultationToKid(consultation, idK, idA, idD);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('Doctor')")
	@DeleteMapping("consult/del/{id}")
	public void deleteConsultation(@PathVariable int id) {
		consultationService.deleteConsultation(id);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('Doctor')")
	@PutMapping("consult/up/{idC}")
	public Consultation updateConsultation(@PathVariable("idC") int idC, @RequestBody Consultation consultation) {
		return consultationService.updateConsultation(idC, consultation);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("consult/getAll")
	public List<Consultation> displayAllConsultations() {
		return consultationService.displayAllConsultations();
	}

	@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("consult/getAll/ToDay")
	public List<Consultation> displayConsultationsToDay() {
		return consultationService.displayConsultationsToDay();
	}

	@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("consult/kid/getAll/{idK}")
	public List<Consultation> displayConsultationsByKid(@PathVariable("idK") int idK) {
		return consultationService.displayConsultationsByKid(idK);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('Doctor')")
	@GetMapping("consult/doctor/getAll")
	public List<Consultation> displayConsultationsByDoctor(int idD) {
		return consultationService.displayConsultationsByDoctor(idD);
	}

	// STATIC

	@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("consult/static/doctor")
	public Map<String, Integer> percentageParticipationByDoctor() {
		return consultationService.percentageParticipationByDoctor();
	}

	@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("consult/static/nbC/{m}")
	public int getPerMonth(@PathVariable("m") String m) {
		List<Consultation> consult = consultationRepository.findByMatchMonthAndMatchDay("-" + m + "-");
		return consult.size();
	}
}
