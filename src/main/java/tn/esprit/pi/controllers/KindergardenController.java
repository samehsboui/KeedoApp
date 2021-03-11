package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.services.KindergardenService;

@RestController
public class KindergardenController {
	
	@Autowired
	KindergardenService kindergardenService;
	

}
