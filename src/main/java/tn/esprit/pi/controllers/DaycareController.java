package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.services.DaycareService;

@RestController
public class DaycareController {
	
	@Autowired
	DaycareService daycareService;
	
	@PostMapping("daycare/add")
	public Daycare addDaycare(@RequestBody Daycare daycare) {
		daycareService.addDaycare(daycare);
		return daycare;
	}
	
	@DeleteMapping("daycare/del/{id}")
	public void deleteDaycare(@PathVariable int id) {
		daycareService.deleteDaycare(id);
	}
	
	@PutMapping("daycare/up/{id}")
	public Daycare updateDaycare(@PathVariable int id, @RequestBody Daycare daycare) {
		return daycareService.updateDaycare(id, daycare);
	}
	
	@GetMapping("daycare/get/{id}")
	public Daycare displayById(@PathVariable int id) {
		return daycareService.displayById(id);
	}

	@GetMapping("daycare/getAll")
	public List<Daycare> displayAll() {
		return daycareService.displayAll();
	}
	
	@GetMapping("daycare/getAll/sat")
	public List<Daycare> displayDaycareSaturated() {
		return daycareService.displayDaycareSaturated();
	}

	@GetMapping("daycare/getAll/Nsat")
	public List<Daycare> displayDaycareNonSaturated() {
		return daycareService.displayDaycareNonSaturated();
	}
	
	@GetMapping("daycare/getAll/date")
	public List<Daycare> displayDaycareByDate() {
		return daycareService.displayDaycareByDate();
	}

}
