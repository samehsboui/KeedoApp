package tn.esprit.pi.services;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.Workshop;
import tn.esprit.pi.entities.WorkshopCategory;
import tn.esprit.pi.repositories.IWorkshopRepository;
import tn.esprit.pi.repositories.IUserRepository;


@Service
public class WorkshopServiceImpl implements IWorkshopService{

	
	@Autowired 
	IWorkshopRepository IWorkshopRepository;
	
	@Autowired 
	private IUserRepository iUserRepository;
	
	@Override
	public Workshop addWorkshop(Workshop w, int idU) {
		User user=iUserRepository.findById(idU).get();
		w.setUser(user);
		LocalDateTime creationDate = LocalDateTime.now();
		w.setCreateDate(creationDate);
		IWorkshopRepository.save(w);
		return w;
	}
	
	
	@Override
	public void deleteWorkshop(int id) {
		Workshop w = IWorkshopRepository.findById(id).get();
		IWorkshopRepository.delete(w);							
	}
	
	
	@Override
	public Workshop updateWorkshop(Workshop w, int id) {
		Workshop workshop = IWorkshopRepository.findById(id).get();
		LocalDateTime modificationDate = LocalDateTime.now();
		workshop.setModifyDate(modificationDate);
		workshop.setContent(w.getContent());
		IWorkshopRepository.save(workshop);
		return IWorkshopRepository.findById(id).get();
	}
	
	
	@Override
	public List<Workshop> getAllWorkshops() {
		List<Workshop>workshops = new ArrayList<Workshop>();
		IWorkshopRepository.findAll().forEach(e ->workshops.add(e));
		return workshops;
	}
	
	
	@Override
	public Workshop getWorkshopById(int id) {
		return IWorkshopRepository.findById(id).get();  
	}
	
	
	@Override
	public int CountWorkshops() {
		List <Workshop> workshops=(List<Workshop>) IWorkshopRepository.findAll();
		return workshops.size();
	}
	
	
	@Override
	public List<Workshop> getWorkshopsByUserId(int id) {
		return IWorkshopRepository.getWorkshopByUserId(id);
	}
	
	//needs fixing
	
	@Override
	public List<Workshop> getWorkshopsByKindergartenName(String name) {
		return IWorkshopRepository.getWorkshopByKindergartenName(name);
	}
	
	
	@Override
	public int CountWorkshopsByUser(int id) {
		List <Workshop> workshops=(List<Workshop>) IWorkshopRepository.getWorkshopByUserId(id);
		return workshops.size();
	}
	
	
	@Override
	public List<Workshop> searchWorkshops(String text) {
        return IWorkshopRepository.findWorkshopsByTextContaining(text);

	}
	
	@Override	
	public List<Workshop> filterWorkshop(WorkshopCategory category) {
		return IWorkshopRepository.getByCategory(category);
	}

}

