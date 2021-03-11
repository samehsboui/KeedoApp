package tn.esprit.pi.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IDonationRepository;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IJackPotRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.entities.Donation;


//Add Donation to a jackpot an event
@Service
public class DonationServiceImpl implements IDonationService  {

	@Autowired
	IEventRepository iEventRepository;
	
	@Autowired
	IJackPotRepository iJackpotRepository;
	
	@Autowired
	IDonationRepository iDonationRepository;
	
	@Autowired
	IUserRepository iUserRepository;
	
	//Donation lel event w kol event endo jackpot w kol jackpot fiha somme donation
	@Override
	public String Donation(int eid, float amount) {
		float totale=0;
		float newCollAmount=0;
		Donation donation = new Donation();
		Event ev = iEventRepository.findById(eid).get();
		User user = iUserRepository.findById(1).get();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//attribut static 
		int balanceAmount = 1450;
		Jackpot jackpot = new Jackpot();
		System.out.println(ev.getJackpot());
		if(balanceAmount >=amount) {
			totale = balanceAmount-amount;
			jackpot = iEventRepository.findJackpot(ev.getJackpot());
			System.out.println("sum="+jackpot.getSum());
			donation.setAmount(amount);
			donation.setContributionDate(dateFormat.format(date));
			donation.setEvent(ev);
			donation.setUser(user);
			jackpot.setSum(jackpot.getSum()+amount);
			
			
			
			
			
			iJackpotRepository.save(jackpot);
			iEventRepository.save(ev);

			iDonationRepository.save(donation);
			//bch n3ayt lel user bch ton9s compte mt3o mta3 flous
			//iUserRepository.save()
			
			return "Donation saved successfully!!";
			
			
			
		}
		return "Balance amount below than amount of donation we are sorry ";
	}

	@Override
	public List<tn.esprit.pi.entities.Donation> getContributionEvention(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<tn.esprit.pi.entities.Donation> getHistoryContribution() {
		// TODO Auto-generated method stub
		return null;
	}


}