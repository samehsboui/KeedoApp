package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.pi.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findBylogin(String userName);
    List<User> findBylastName(String userName);
	User findByidUser(long idUser);}
