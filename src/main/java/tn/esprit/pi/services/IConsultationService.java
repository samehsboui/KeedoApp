package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;

public interface IConsultationService {

	Kid affectConsultationToKid(Consultation consultation, int idK);
	void deleteConsultation(int id);
	Consultation updateConsultation(int idC, Consultation consultation);
	List<Consultation> displayAllConsultations();
	List<Consultation> displayConsultationsByKid(int idK);
}
