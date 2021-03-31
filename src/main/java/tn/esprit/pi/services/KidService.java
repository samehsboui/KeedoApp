package tn.esprit.pi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.BusRepository;
import tn.esprit.pi.repositories.DaycareRepository;
import tn.esprit.pi.repositories.KidRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.repositories.IUserRepository;

@Service
public class KidService implements IKidService {

	@Autowired
	KidRepository kidRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	DaycareRepository daycarRepository;

	@Override
	public Retour<Kid> addKid(Kid kid) throws Exception {
		Retour<Kid> rt =new Retour<>();
		User user = getCurrentUser();
		if(kid.getBirthDate().after(new Date(System.currentTimeMillis()))){
			rt.setStringValue("Date is wrong!!");
		}else{
			kid.setUser(user);
			kidRepository.save(kid);
			rt.setStringValue("Kid added successfuly!");
			rt.getObjectValue().add(kid);
		}
		
		return rt;
	}

	@Override
	public String deleteKid(int id) {
		kidRepository.deleteKidById(id);
		return "Kid deleted successfully";
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
	public Retour<Kid> updateKid(int id, Kid kid) {
		Retour<Kid> rt =new Retour<>();
		Kid kido = kidRepository.findById(id).get();
		if(kid.getBirthDate().after(new Date(System.currentTimeMillis()))){
			rt.setStringValue("Date is wrong!!");
		}else{
			kido.setAddress(kid.getAddress());
			kido.setBirthDate(kid.getBirthDate());
			kido.setFirstName(kid.getFirstName());
			kido.setGender(kid.getGender());
			kido.setLastName(kid.getLastName());
			kidRepository.save(kido);
			rt.setStringValue("Kid updated successfuly!");
			rt.getObjectValue().add(kid);
		}
		
		return rt;
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
	public Retour<Kid> affectKidToDaycare(int idK, int idD) {
		Retour<Kid> rt = new Retour<>();
		Daycare daycare = daycarRepository.findById(idD).get();
		Kid kid = kidRepository.findById(idK).get();
		// Test if kid has already a daycare
		Daycare d = kid.getDaycare();
		if (d != null) {
			if (d.getIdDaycare() == daycare.getIdDaycare()) {
				rt.setStringValue("Kid already exists");
			} else {
				if (daycare.getNbInscrit() < daycare.getCapacity()) {
					kid.setDaycare(null);
					d.setNbInscrit(d.getNbInscrit() - 1);
					daycarRepository.save(d);
					kid.setDaycare(daycare);
					daycare.setNbInscrit(daycare.getNbInscrit() + 1);
					kidRepository.save(kid);
					daycarRepository.save(daycare);
					rt.setStringValue("Kid affected successfully");
					rt.getObjectValue().add(kid);
				} else {
					rt.setStringValue("Daycare saturated");
				}
			}

		} else if (daycare.getNbInscrit() < daycare.getCapacity()) {
			kid.setDaycare(daycare);
			daycare.setNbInscrit(daycare.getNbInscrit() + 1);
			kidRepository.save(kid);
			daycarRepository.save(daycare);
			rt.setStringValue("Kid affected successfully");
			rt.getObjectValue().add(kid);
		} else {
			rt.setStringValue("Daycare saturated");
		}

		return rt;
	}

	@Override
	public Retour<Kid> deleteKidFromDaycare(int idK, int idD) {
		Retour<Kid> rt = new Retour<>();

		Daycare daycare = daycarRepository.findById(idD).get();
		Kid kid = kidRepository.findById(idK).get();
		kid.setDaycare(null);
		daycare.setNbInscrit(daycare.getNbInscrit() - 1);
		kidRepository.save(kid);
		daycarRepository.save(daycare);

		rt.setStringValue("Kid deleted from daycare successfully");
		rt.getObjectValue().add(kid);
		return rt;
	}

	@Override
	public int nbrKid() {
		return (int) kidRepository.count();
	}

	public User getCurrentUser() throws Exception {
		Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (follower instanceof UserDetailsImpl) {
			User user = ((UserDetailsImpl) follower).getUser();
			System.out.println("iD::: " + user.getIdUser());
			System.out.println("namee::: " + user.getFirstName());
			return user;
		}
		return null;
	}
	//dhekra
	@Autowired
	BusRepository busRepository;
	
		@Override
		public String affectKidToBus(Kid kid,int idBus) {
			// TODO Auto-generated method stub
			
			Bus bus =busRepository.findById(idBus).get();
			
			
			if (bus.getDisponible() == 0) {
			
				System.out.println(
						"[REST] Impossible to affect kid to bus");
				//return null;
			return (" Impossible to affect kid to bus");

			}
			else {
				Kid kids=new Kid();
				kids.setBus(bus);
				
				bus.desaffectDispo();
				kidRepository.save(kid);
				return ("congratulations the Kid "+kids.getFirstName()+"is affected to "+kids.getBus().getIdBus());
			}
		}
}
