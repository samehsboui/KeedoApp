package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Driver;

public interface IDriverService {
	
    List<Driver> getAllDriver();
 	Driver saveDriver(Driver driver);
	Driver getDriverById(long id);
	void deleteDrivereById(long id);
	Driver updateDriver(Driver driver, long id);
	
	//void affecteBusToDriver(long idBus, long idDriver);

}
