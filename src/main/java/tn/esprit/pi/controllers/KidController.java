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

import tn.esprit.pi.entities.Kid;
//import static tn.esprit.pi.security.CustomLoginSuccessHandler.idCurrent;
import tn.esprit.pi.services.KidService;

@RestController
@RequestMapping("kid/")
public class KidController {

	@Autowired
	KidService kidService;
	
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@PostMapping("add")
	public Kid addKid(@RequestBody Kid kid, @RequestBody int idU) {
		return kidService.addKid(kid, idU);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@DeleteMapping("del/{idK}")
	public void deleteKid(@PathVariable("idK") int id) {
		kidService.deleteKid(id);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("get/{idK}")
	public Kid displayKidById(@PathVariable("idK") int id) {
		return kidService.displayKid(id);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("getAll")
	public List<Kid> displayAllKids() {
		return kidService.displayAllKids();
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@PutMapping("up/{idK}")
	public Kid updateKid(@PathVariable("idK") int id, @RequestBody Kid kid) {
		return kidService.updateKid(id, kid);
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("Asc")
	public List<Kid> orderKidsByNameAsc() {
		return kidService.orderKidsByNameAsc();
	}

	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("Desc")
	public List<Kid> orderKidsByNameDesc() {
		return kidService.orderKidsByNameDesc();
	}
	
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@PutMapping("daycare/aff/{idK}/{idD}")
	public Kid affectKidToDaycare(@PathVariable("idK") int idK, @PathVariable("idD") int idD){
		return kidService.affectKidToDaycare(idK, idD);
	}
	
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@PutMapping("daycare/del/{idK}/{idD}")
	public Kid deleteKidFromDaycare(@PathVariable("idK") int idK, @PathVariable("idD") int idD){
		return kidService.deleteKidFromDaycare(idK, idD);
	}
	
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("static/nb")
	public int nbrKid() {
		return kidService.nbrKid();
	}
}
