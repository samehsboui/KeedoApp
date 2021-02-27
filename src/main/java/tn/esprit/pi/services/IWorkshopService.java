package tn.esprit.pi.services;
import java.util.List;

import tn.esprit.pi.entities.Workshop;
public interface IWorkshopService {
	public Workshop addWorkshop(Workshop w, int idU);
	public void deleteWorkshop(int id);
	public Workshop updateWorkshop(Workshop w, int id);
	public List<Workshop> getAllWorkshops();
	public Workshop getWorkshopById(int id);
	public int CountWorkshops();
	public List<Workshop> getWorkshopsByUserId(int id);
	public List<Workshop> getWorkshopsByKindergartenName(String name);
	public int CountWorkshopsByUser(int id);
    public List<Workshop> searchWorkshops(String text);
}