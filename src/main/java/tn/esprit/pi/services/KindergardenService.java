package tn.esprit.pi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.repositories.UserRepository;
@Service
public class KindergardenService implements IKindergardenService{

	
	@Autowired 
	KindergardenRepository kindergardenRepository;
	
	@Autowired
	UserRepository ur;
	
	private static final Logger L=LogManager.getLogger(Kindergarden.class);
	@Override
	public Kindergarden addKindergarden(Kindergarden kindergarden, int director) {
		// TODO Auto-generated method stub
		User u=ur.findById(director).get();
		
		kindergarden.setDirector(u);
		kindergarden.setCreatedAt(LocalDateTime.now());
		
		return kindergardenRepository.save(kindergarden);

	
	}

	@Override
	public List<Kindergarden> retrieveAllKindergardens() {
		List <Kindergarden> kindergardens=(List<Kindergarden>) kindergardenRepository.findAll();

		return kindergardens;
	}

	@Override
	public void removeKindergarden(int id) {
		// TODO Auto-generated method stub
		Kindergarden k=kindergardenRepository.findById(id).get();
		kindergardenRepository.delete(k);
	}

	@Override
	public Kindergarden updateKindergarden(Kindergarden kindergarden, int id) {
		// TODO Auto-generated method stub
		
	
		Kindergarden k=kindergardenRepository.findById(id).get();

		k.setName(kindergarden.getName());
		k.setCreatedAt(k.getCreatedAt());
		k.setUpdatedAt(LocalDateTime.now());
		k.setDirector(k.getDirector());
		return kindergardenRepository.save(k);
	}

	@Override
	public Kindergarden getKindergardenById(int id) {
		// TODO Auto-generated method stub
		return kindergardenRepository.findById(id).get();
	}

	@Override
	public int countKindergardens() {
		List <Kindergarden> kindergardens=(List<Kindergarden>) kindergardenRepository.findAll();

		return kindergardens.size();
	}

	@Override
	public Kindergarden getKindergardenByName(String name) {
		// TODO Auto-generated method stub
		return kindergardenRepository.findByName(name);
	}

	@Override
	public Kindergarden getKindergardenByDirector(int director) {
		// TODO Auto-generated method stub
		
		User u=ur.findByidUser(director);
		return kindergardenRepository.findByDirector(u);
	}

}
