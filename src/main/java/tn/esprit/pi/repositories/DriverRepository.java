package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
