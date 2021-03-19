package tn.esprit.pi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.Liking;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.ILikingRepository;

@Service
public class LikingServiceImpl implements ILikingService {
	@Autowired 
	IPostRepository IPostRepository;

	@Autowired 
	private IUserRepository IUserRepository;
	
	@Autowired 
	private ILikingRepository ILikingRepository;
	
	@Override
	public Boolean addLiking(Liking l, int idU, int idP){
		if (IsLikeExists(idU, idP)){
			return false;
		}
		else{
		User user=IUserRepository.findById(idU).get();
		Post post=IPostRepository.findById(idP).get();
		l.setUser(user);
		l.setPost(post);
	    LocalDateTime creationDate = LocalDateTime.now();
		l.setLikeDate(creationDate);
		ILikingRepository.save(l);
		return true;
	}}

	@Override
	public void deleteLiking(int id) {
		ILikingRepository.deleteById(id);
		
	}

	@Override
	public List<Liking> getAllLikings() {
		List<Liking>Likes = new ArrayList<Liking>();
		ILikingRepository.findAll().forEach(e ->Likes.add(e));
		return Likes;
	}
	

	@Override
	public Liking getLikingById(int id) {
		return ILikingRepository.findById(id).get();  

	}

	@Override
	public int CountLikings() {
		List <Liking> likes=(List<Liking>) ILikingRepository.findAll();
		return likes.size();
	}

	@Override
	public List<Liking> getLikingsByUserId(int id) {
		return ILikingRepository.getLikesByUserId(id);
	}

	@Override
	public int CountLikingsByUser(int id) {
		List <Liking> likes=(List<Liking>) ILikingRepository.getLikesByUserId(id);
		return likes.size();
	}

	@Override
	public List<Liking> getLikingsByPostId(int id) {
		return ILikingRepository.getLikesByPostId(id);

	}
	

	@Override
	public int CountLikingsByPost(int id) {
		List <Liking> likes=(List<Liking>) ILikingRepository.getLikesByPostId(id);
		return likes.size();
	}


	//useful for the add method (a user can't like the same post twice)
	@Override
	public boolean IsLikeExists(int idu, int idp) {
	 int count =ILikingRepository.isLikeExists(idu, idp);
	 if (count==0){
		return false;
	}
	 else {
		 return true;
	 }
	 }



}