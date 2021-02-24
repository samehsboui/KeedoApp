package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.repositories.IParticipationRepository;

public class ParticipationServiceImpl implements IParticipationService {

	
	@Autowired
	IParticipationRepository iParticipationRepository;
	/********************************** Admin **********************************/
	// retrieve all participations
	@Override
	public List<Participation> participationsList() {
		List<Participation> list = (List<Participation>) iParticipationRepository.findAll();
		return list;
	}

	@Override
	public String addParticipation(Long eid) {
		return null;
	}

	@Override
	public List<Participation> myParticipations() {
		return null;
	}
}