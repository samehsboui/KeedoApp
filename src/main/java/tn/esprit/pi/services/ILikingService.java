package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Liking;
public interface ILikingService {
	
	public Liking addLiking(Liking c, int idU);
	public void deleteLiking(int id);
	public Liking updateLiking(Liking c, int id);
	public List<Liking> getAllLikings();
	public Liking getLikingById(int id);
	public int CountLikings();
	public List<Liking> getLikingsByUserId(int id);
	public int CountLikingsByUser(int id);
	public List<Liking> getLikingsByPostId(int id);
	public int CountLikingsByPost(int id);
    public List<Liking> searchLikings(String text);
}