package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ConsultationRepository;
import tn.esprit.pi.repositories.KidRepository;
import tn.esprit.pi.repositories.UserRepository;

@Service
public class ConsultationService implements IConsultationService{

	@Autowired
	ConsultationRepository consultationRepository;
	@Autowired
	KidRepository kidRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Kid affectConsultationToKid(Consultation consultation, int idK, int idU) {
		User user = userRepository.findById(idU).get();
		Kid kid = kidRepository.findById(idK).get();
		List<Consultation> consults = consultationRepository.findConsultationByDoctor(user, consultation.getDateConsultation(), consultation.getTime());
		if(consults.isEmpty()){
			consultation.setKid(kid);
			consultation.setUser(user);
			consultationRepository.save(consultation);
			System.out.println("Dooone!!");
		}else{
			System.out.println("Doctor is not available!!");
		}
		
		return kid;
	}

	
	@Override
	public void deleteConsultation(int id) {
		consultationRepository.deleteConsultById(id);
	}

	@Override
	public Consultation updateConsultation(int idC, Consultation consultation) {
		Consultation c = consultationRepository.findById(idC).get();
		c.setDateConsultation(consultation.getDateConsultation());
		//Doctor
		c.setTime(consultation.getTime());
		consultationRepository.save(c);
		return c;
	}

	@Override
	public List<Consultation> displayAllConsultations() {
		return consultationRepository.displayAllConsultations();
	}

	@Override
	public List<Consultation> displayConsultationsByKid(int idK) {
		Kid kid = kidRepository.findById(idK).get();
		return consultationRepository.findConsultationByKid(kid);
	}

}
