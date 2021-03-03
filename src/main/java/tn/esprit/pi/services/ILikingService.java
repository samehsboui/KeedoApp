package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Liking;
public interface ILikingService {
	
	public String addLiking(Liking c, int idU, int idP);
	public void deleteLiking(int id);
	public List<Liking> getAllLikings();
	public Liking getLikingById(int id);
	public int CountLikings();
	public List<Liking> getLikingsByUserId(int id);
	public int CountLikingsByUser(int id);
	public List<Liking> getLikingsByPostId(int id);
	public int CountLikingsByPost(int id);
	public List<Liking> getReceivedLikesByUserId(int id);
	public boolean IsLikeExists(int idu, int idp);
}