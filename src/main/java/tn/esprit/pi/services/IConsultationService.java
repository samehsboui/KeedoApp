package tn.esprit.pi.services;

import java.util.List;
import java.util.Map;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.UserAndString;

public interface IConsultationService {

	UserAndString affectConsultationToKid(Consultation consultation, int idK, int idA, int idD);
	void deleteConsultation(int id);
	Consultation updateConsultation(int idC, Consultation consultation);
	List<Consultation> displayAllConsultations();
	List<Consultation> displayConsultationsToDay();
	List<Consultation> displayConsultationsByKid(int idK);
	List<Consultation> displayConsultationsByDoctor(int idD);
	Map<String, Integer> percentageParticipationByDoctor();
	int nbrConsultPerMonth(int m);
}
