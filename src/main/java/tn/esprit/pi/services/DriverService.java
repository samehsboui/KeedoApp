package tn.esprit.pi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Driver;
import tn.esprit.pi.repositories.BusRepository;
import tn.esprit.pi.repositories.DriverRepository;

@Service
public class DriverService implements IDriverService {

	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private BusRepository  busRepository;
	
	 public List<Driver> getAllDriver() {
	        return driverRepository.findAll();
	    }

	
	@Override
	public Driver saveDriver(Driver driver) {
		return driverRepository.save(driver);
		
	}

	@Override
	public Driver getDriverById(int id) {
		return driverRepository.findById(id).get();
	}

	@Override
	public void deleteDrivereById(int id) {
		driverRepository.deleteById(id);
		
	}
	
    @Override
	public Driver updateDriver (Driver driver, int idDriver) {
		// TODO Auto-generated method stub
    	Driver d =driverRepository.findById(idDriver).get();
    	d.setAddress(d.getAddress());
    	d.setFirstName(d.getFirstName());
    	d.setLastName(d.getLastName());
    	d.setTelNum(d.getTelNum());
    	    	
		return driverRepository.save(d);
	}
 
}
