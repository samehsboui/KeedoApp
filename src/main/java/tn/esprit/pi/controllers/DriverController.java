package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Driver;

import tn.esprit.pi.services.DriverService;

@RestController

@RequestMapping("/driver")

public class DriverController {
	
	
	@Autowired
	private DriverService driverservice;
	
	
	 @GetMapping("/showDriver")
	    public List<Driver>showDriver() {
	        return driverservice.getAllDriver();
	    }
	
	@PostMapping("/saveDriver")
		private String saveDriver(@RequestBody Driver driver)   
		{  
			driverservice.saveDriver(driver);  
			return "redirect:/";
		}
	 
	
	
	 
	@RequestMapping(value = "/deleteDriver/{id}", method = RequestMethod.DELETE)
	     void deleteDriver(@PathVariable int id) {

		  driverservice.deleteDrivereById(id);
           
	}
	@PutMapping("/updatedriver/{idDriver}")
	private Driver updateDriver(@RequestBody Driver driver, @PathVariable("idDriver")int idDriver) {

		return driverservice.updateDriver(driver,idDriver);
	 
	}
	
	
	@GetMapping("/showbyid/{id}")
    public Driver showbyid(@PathVariable int id) {
        return driverservice.getDriverById(id);
    }

	
	
}
