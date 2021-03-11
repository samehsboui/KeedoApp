package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

}
