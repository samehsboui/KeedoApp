package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@PostMapping("add")
	public Retour<Daycare> addDaycare(@RequestBody Daycare daycare) {
		return daycareService.addDaycare(daycare);
	}
	
	@PreAuthorize("hasAuthority('DaycareManager')")
	@DeleteMapping("del/{id}")
	public void deleteDaycare(@PathVariable int id) {
		daycareService.deleteDaycare(id);
	}
	
	@PreAuthorize("hasAuthority('DaycareManager')")
	@PutMapping("update/{id}")
	public Daycare updateDaycare(@PathVariable int id, @RequestBody Daycare daycare) {
		return daycareService.updateDaycare(id, daycare);
	}
	
	@PreAuthorize("hasAuthority('DaycareManager')")
	@GetMapping("get/{id}")
	public Daycare displayById(@PathVariable int id) {
		return daycareService.displayById(id);
	}

	@PreAuthorize("hasAuthority('DaycareManager')")
	@GetMapping("getAll")
	public List<Daycare> displayAll() {
		return daycareService.displayAll();
	}
	
	@PreAuthorize("hasAuthority('DaycareManager')")
	@GetMapping("getAll/sat")
	public List<Daycare> displayDaycareSaturated() {
		return daycareService.displayDaycareSaturated();
	}

	@PreAuthorize("hasAuthority('DaycareManager')")
	@GetMapping("getAll/Nsat")
	public List<Daycare> displayDaycareNonSaturated() {
		return daycareService.displayDaycareNonSaturated();
	}
	
	@PreAuthorize("hasAuthority('DaycareManager')")
	@GetMapping("getAll/date")
	public List<Daycare> displayDaycareByDate() {
		return daycareService.displayDaycareByDate();
	}

	// STATIC
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("static/revenue/{year}")
	public Double daycareRevenuePerYear(@PathVariable("year") String year){
		return daycareService.daycareRevenuePerYear(year);
	}
}
