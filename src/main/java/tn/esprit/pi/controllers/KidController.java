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

import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.services.KidService;

@RestController
public class KidController {

	@Autowired
	KidService kidService;
	
	@PostMapping("kid/add/{idU}")
	public Kid addKid(@RequestBody Kid kid, @PathVariable("idU") int idU) {
		return kidService.addKid(kid, idU);
	}

	@DeleteMapping("kid/del/{idK}")
	public void deleteKid(@PathVariable("idK") int id) {
		kidService.deleteKid(id);
	}

	@GetMapping("kid/get/{idK}")
	public Kid displayKid(@PathVariable("idK") int id) {
		return kidService.displayKid(id);
	}

	@GetMapping("kid/getAll")
	public List<Kid> displayAllKids() {
		return kidService.displayAllKids();
	}

	@PutMapping("kid/up/{idK}")
	public Kid updateKid(@PathVariable("idK") int id, @RequestBody Kid kid) {
		return kidService.updateKid(id, kid);
	}

	@GetMapping("kids/Asc")
	public List<Kid> orderKidsByNameAsc() {
		return kidService.orderKidsByNameAsc();
	}

	@GetMapping("kids/Desc")
	public List<Kid> orderKidsByNameDesc() {
		return kidService.orderKidsByNameDesc();
	}
	
	@PutMapping("kid/daycare/aff/{idK}/{idD}")
	public Kid affectKidToDaycare(@PathVariable("idK") int idK, @PathVariable("idD") int idD){
		return kidService.affectKidToDaycare(idK, idD);
	}
	
	@PutMapping("kid/daycare/del/{idK}/{idD}")
	public Kid deleteKidFromDaycare(@PathVariable("idK") int idK, @PathVariable("idD") int idD){
		return kidService.deleteKidFromDaycare(idK, idD);
	}
}
