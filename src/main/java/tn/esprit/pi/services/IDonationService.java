package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Donation;

public interface IDonationService {

	public String Donation(int eid, float amount);
	public List<Donation> getContributionEvention(Event event);
	public List<Donation> getHistoryContribution();
}