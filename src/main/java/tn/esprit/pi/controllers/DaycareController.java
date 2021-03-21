package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.services.DaycareService;

@RestController
@RequestMapping("daycare/")
public class DaycareController {
	
	@Autowired
	DaycareService daycareService;
	
	@PostMapping("add")
	public Retour<Daycare> addDaycare(@RequestBody Daycare daycare) {
		return daycareService.addDaycare(daycare);
	}
	
	@DeleteMapping("del/{id}")
	public void deleteDaycare(@PathVariable int id) {
		daycareService.deleteDaycare(id);
	}
	
	@PutMapping("update/{id}")
	public Daycare updateDaycare(@PathVariable int id, @RequestBody Daycare daycare) {
		return daycareService.updateDaycare(id, daycare);
	}
	
	@GetMapping("get/{id}")
	public Daycare displayById(@PathVariable int id) {
		return daycareService.displayById(id);
	}

	@GetMapping("getAll")
	public List<Daycare> displayAll() {
		return daycareService.displayAll();
	}
	
	@GetMapping("getAll/sat")
	public List<Daycare> displayDaycareSaturated() {
		return daycareService.displayDaycareSaturated();
	}

	@GetMapping("getAll/Nsat")
	public List<Daycare> displayDaycareNonSaturated() {
		return daycareService.displayDaycareNonSaturated();
	}
	
	@GetMapping("getAll/date")
	public List<Daycare> displayDaycareByDate() {
		return daycareService.displayDaycareByDate();
	}

	// STATIC
	@GetMapping("static/revenue/{year}")
	public Double daycareRevenuePerYear(@PathVariable("year") String year){
		return daycareService.daycareRevenuePerYear(year);
	}
}
