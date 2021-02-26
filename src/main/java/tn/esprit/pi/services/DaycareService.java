package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.repositories.DaycareRepository;
import tn.esprit.pi.repositories.UserRepository;

@Service
public class DaycareService implements IDaycareService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	DaycareRepository daycareRepository;
	
	@Override
	public Daycare addDaycare(Daycare daycare) {
		// Set periode
		long period = Math.round((daycare.getDateEnd().getTime()-daycare.getDateBegin().getTime()) / (double) 86400000);
		daycare.setPeriode(period);
		// Set total price
		if(period < 30){
			daycare.setPrice_T(daycare.getPrice_M());
		} else if (period> 30 && period<60){
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.05));
		} else if (period> 60 && period<90){
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.1));
		} else if (period> 90 && period<120){
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.15));
		} else {
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.15));
		}
		
		daycareRepository.save(daycare);
		return daycare;
	}

	@Override
	public void deleteDaycare(int id) {
		daycareRepository.deleteById(id);
	}

	@Override
	public Daycare updateDaycare(int id, Daycare daycare) {
		Daycare d = daycareRepository.findById(id).get();
		d.setCapacity(daycare.getCapacity());
		d.setDateBegin(daycare.getDateBegin());
		d.setDateEnd(daycare.getDateEnd());
		d.setPrice_M(daycare.getPrice_M());
		d.setNbInscrit(daycare.getNbInscrit());
		// Set periode
		long period = Math.round((daycare.getDateEnd().getTime()-daycare.getDateBegin().getTime()) / (double) 86400000);
		d.setPeriode(period);
		// Set total price
		if(period < 30){
			daycare.setPrice_T(daycare.getPrice_M());
		} else if (period> 30 && period<60){
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.05));
		} else if (period> 60 && period<90){
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.1));
		} else if (period> 90 && period<120){
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.15));
		} else {
			daycare.setPrice_T(daycare.getPrice_M()-(daycare.getPrice_M()*0.15));
		}
		
		daycareRepository.save(d);
		return d;
	}

	@Override
	public Daycare displayById(int id) {
		return daycareRepository.findById(id).get();
	}

	@Override
	public List<Daycare> displayAll() {
		return (List<Daycare>) daycareRepository.findAll();
	}

	@Override
	public List<Daycare> displayDaycareSaturated() {
		return daycareRepository.displayDaycareSaturated();
	}

	@Override
	public List<Daycare> displayDaycareNonSaturated() {
		return daycareRepository.displayDaycareNonSaturated();
	}

	@Override
	public List<Daycare> displayDaycareByDate() {
		return daycareRepository.displayDaycareByDate();
	}

}
