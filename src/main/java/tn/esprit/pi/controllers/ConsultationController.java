package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.services.ConsultationService;

@RestController
public class ConsultationController {
	
	@Autowired
	ConsultationService consultationService;
	
	@PostMapping("consult/add/{idK}")
	public Kid affectConsultationToKid(@RequestBody Consultation consultation,@PathVariable("idK") int idK) {
		return consultationService.affectConsultationToKid(consultation, idK);
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

	@GetMapping("consult/kid/getAll/{idK}")
	public List<Consultation> displayConsultationsByKid(@PathVariable("idK")int idK) {
		return consultationService.displayConsultationsByKid(idK);
	}

}
