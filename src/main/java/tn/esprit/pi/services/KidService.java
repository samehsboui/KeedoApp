package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.DaycareRepository;
import tn.esprit.pi.repositories.KidRepository;
import tn.esprit.pi.repositories.UserRepository;

@Service
public class KidService implements IKidService {

	@Autowired
	KidRepository kidRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DaycareRepository daycarRepository;
	
	@Override
	public Kid addKid(Kid kid, int idU) {
		User user = userRepository.findById(idU).get();
		kid.setUser(user);
		kidRepository.save(kid);
		return kid;
	}

	@Override
	public void deleteKid(int id) {
		kidRepository.deleteById(id);
	}

	@Override
	public Kid displayKid(int id) {
		return kidRepository.findById(id).get();
	}

	@Override
	public List<Kid> displayAllKids() {
		List<Kid> kids = kidRepository.DisplayAll();
		return kids;
	}

	@Override
	public Kid updateKid(int id, Kid kid) {
		Kid kido = kidRepository.findById(id).get();
		kido.setAddress(kid.getAddress());
		kido.setBirthDate(kid.getBirthDate());
		kido.setFirstName(kid.getFirstName());
		kido.setGender(kid.getGender());
		kido.setLastName(kid.getLastName());
		kidRepository.save(kido);
		return kido;
	}

	@Override
	public List<Kid> orderKidsByNameAsc() {
		return kidRepository.orderKidsByNameAsc();
	}

	@Override
	public List<Kid> orderKidsByNameDesc() {
		return kidRepository.orderKidsByNameDESC();
	}

	@Override
	public Kid affectKidToDaycare(int idK, int idD) {
		Daycare daycare = daycarRepository.findById(idD).get();
		Kid kid = kidRepository.findById(idK).get();
		//Test if kid has already a daycare
		Daycare d = kid.getDaycare();
		if(d != null){
			d.setNbInscrit(d.getNbInscrit()-1);
			daycarRepository.save(d);
		}
		if(daycare.getNbInscrit() < daycare.getCapacity()){
			kid.setDaycare(daycare);
			daycare.setNbInscrit(daycare.getNbInscrit()+1);
			kidRepository.save(kid);
			daycarRepository.save(daycare);
		}else{
			System.out.println("Daycare saturée");
		}
		
		return kid;
	}

	@Override
	public Kid deleteKidFromDaycare(int idK, int idD) {
		Daycare daycare = daycarRepository.findById(idD).get();
		Kid kid = kidRepository.findById(idK).get();
		kid.setDaycare(null);
		daycare.setNbInscrit(daycare.getNbInscrit()-1);
		kidRepository.save(kid);
		daycarRepository.save(daycare);
		
		return kid;
	}
	

}
