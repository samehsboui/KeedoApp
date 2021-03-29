package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.services.IEvaluationService;

@RestController
public class EvaluationController {
	@Autowired
	IEvaluationService iEvaluationService;
	
		
	
    @PostMapping("/evaluation/add-evaluation/{idevent}/{iduser}/{rate}")  
	@ResponseBody()
    public  ResponseEntity<?> EvaluateProduct(@PathVariable("idevent") int idevent,
			@PathVariable("iduser") int idUser, @PathVariable("rate") int rate
	)   
	{  
		return  iEvaluationService.evaluateEvent(idUser, idevent, rate);
	}
    
    @PutMapping("/evaluation/update-evaluation/{idevent}/{iduser}/{idrate}/{rate}")  
	@ResponseBody()
    public  String UpdateEvaluate(@PathVariable("idevent") int idevent,
			@PathVariable("iduser") int idUser, @PathVariable("idrate") int idrate,@PathVariable("rate") int rate)   
	{  
		return  iEvaluationService.updateRating(idUser, idevent, idrate,rate);
	}
	
	
	
	
	

	

}
