package tn.esprit.pi.controllers;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Book;
import tn.esprit.pi.entities.EmpruntBook;
import tn.esprit.pi.entities.EmpruntCreation;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.BookRepository;
import tn.esprit.pi.repositories.EmpruntBookRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.services.EmpruntBookService;
import tn.esprit.pi.services.UserService;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

	private static final Logger logger = LoggerFactory.getLogger(EmpruntController.class);

	@Autowired
	private EmpruntBookRepository empruntBookRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookRepository bookRepository;

	

	@Autowired
	private EmpruntBookService empruntBookService;

	// http://localhost:9293/SpringMVC/servlet/emprunt/showAllEmprunt
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/showAllEmprunt")
	public List<EmpruntBook> listeEmprunts() {
		List<EmpruntBook> emprunts = empruntBookRepository.findAll();
		logger.info("[REST] Request emprunt list");
		return emprunts;
	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/getEmpruntsEncours
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/getEmpruntsEncours")
	public List<EmpruntBook> listEmpruntEncours() {
		List<EmpruntBook> emprunts = empruntBookRepository.findEmpruntsEncours(false);
		logger.info("[REST] Request emprunt list is in cours ");

		return emprunts;
	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/detailsEmprunt/1
	@PreAuthorize("hasAuthority('Parent') or hasAuthority('Admin')")
	@GetMapping(value = "/detailsEmprunt/{id}")
	public EmpruntBook detailEMprunt(@PathVariable int id) {

		logger.info("[REST] Details emprunt n° " + id);
		return empruntBookService.findById(id);
		// return empruntBookRepository.findById(id);
	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/findEmpruntsByUser/1
	@PreAuthorize("hasAuthority('Admin')")
	@RequestMapping(value = "/findEmpruntsByUser/{UserId}")
	public List<EmpruntBook> findEmpruntsByUser(@PathVariable int userId) {
		User user = userService.findById(userId);

		List<EmpruntBook> listeEmprunts = empruntBookRepository.findEmpruntsByUser(user);

		logger.info("[REST]  list emprunt with  user " + user.getMail());

		return listeEmprunts;
	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/creerEmprunt
	@PreAuthorize("hasAuthority('Parent')")
	@PostMapping(value = "/creerEmprunt")
	public String creerEmprunt(@RequestBody EmpruntCreation creationEmprunt) {

		System.out.println("dataJSON = " + creationEmprunt);
		int userId = Integer.parseInt(creationEmprunt.getUserId());
		int bookId = Integer.parseInt(creationEmprunt.getBookId());

		System.out.println("bookId = " + bookId + "userId = " + userId);

		Date dateDebut = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime((dateDebut));
		c.add(Calendar.DATE, 28);
		Date dateFin = c.getTime();
		System.out.println("date début = " + dateDebut + ", date de fin = " + dateFin);

		User user = userService.findById(userId);
		Book book = bookRepository.findById(bookId);

		if (book.getStockDisponible() == 0) {
			// le livre n'est pas disponible, on ne peut pas l'emprunter !
			logger.error(
					"[REST] Impossible de créer l'emprunt : User =" + user.getMail() + " Book =" + book.getId());
			// return null;
			return (" Impossible to create emprunt:  User =" + user.getMail() + " Book =" + book.getId());

		}

		else {

			EmpruntBook nouvelEmprunt = new EmpruntBook();

			nouvelEmprunt.setDebutDate(dateDebut);
			nouvelEmprunt.setFinDate(dateFin);
			nouvelEmprunt.setBook(book);
			nouvelEmprunt.setUser(user);

			empruntBookRepository.save(nouvelEmprunt);

			// l'emprunt est validé, on sauvegarde le livre en diminuant son
			// stock de 1
			book.emprunterBook();
			bookRepository.save(book);
			logger.info(" [REST] Nouvel emprunt créé id = " + nouvelEmprunt.getIdEmprunt() + " user = "
					+ nouvelEmprunt.getUser().getFirstName() + " book = " + nouvelEmprunt.getBook().getTitre());
			// return nouvelEmprunt;
			return ("New emprunt create id = " + nouvelEmprunt.getIdEmprunt() + " user = "
					+ nouvelEmprunt.getUser().getFirstName() + " book = " + nouvelEmprunt.getBook().getTitre());

		}

	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/listeEmpruntsExpires
	@PreAuthorize("hasAuthority('Admin')")
	@RequestMapping(value = "/listeEmpruntsExpires", method = RequestMethod.GET)
	public List<EmpruntBook> empruntsExpires() {
		Date today = new Date();
		List<EmpruntBook> emprunts = empruntBookRepository.findEmpruntsExpires(false, today);
		logger.info("[REST] Request for the list of expire emprunt");

		return emprunts;
	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/stopperEmprunt/1

	@PreAuthorize("hasAuthority('Parent')")
	@RequestMapping(value = "/stoppedEmprunt/{id}")
	public Book livreRendu(@PathVariable int id) {

		// on tope l'emprunt à "rendu true" + modif de la date de fin
		EmpruntBook emprunt = empruntBookService.findById(id);
		emprunt.setFinDate(new Date());
		emprunt.setRendu(true);
		empruntBookRepository.save(emprunt);
		logger.info("[REST] Arrêt de l'emprunt " + emprunt);

		// RG un livre est rendu, avant de le rentrer en stock 
		Book livreRendu = bookRepository.findById(emprunt.getBook().getId());

		List<EmpruntBook> listeResaBook = empruntBookRepository.trouverResaEncoursParBook(livreRendu);

		boolean absenceNewOption = true;

		if (absenceNewOption) {

			// on remplit le stock du livre +1
			logger.info("[REST] Rentrée en stock du libre " + livreRendu.getId());
			livreRendu.restituerBook();
			bookRepository.save(livreRendu);
		}
		return livreRendu;
	}
	

	@PreAuthorize("hasAuthority('Admin')")
	// batch qui va nettoyer les emprnts échues :
	@GetMapping(value = "/cleanEmpruntExpired")
	public List<EmpruntBook> cleanEmpruntExpired(@RequestHeader("Authorization") String token) {
		// récupération de la liste des emprnts qui sont en cours et qui ont
		// une date > date du jour
		//Par exp: nous sommes le 15/12 : toutes les réservations en cours avec pour
		// échéance le 14/12 doivent être passée en "expirées"
		List<EmpruntBook> listeResaPurge = empruntBookRepository.listerResaExpiree(new Date());

		// on parcourt chaque réservation et on l'annule :
		// le livre doit revenir en stock : on contrôle comme une restitution si
		// un autre membre l'a réservé ou pas
		for (EmpruntBook resa : listeResaPurge) {
			resa.setRendu(true);
			resa.setDetail("Expirée");
			Book livreLibere = resa.getBook();
			gererListeAttente(livreLibere);
		}
		logger.info(" ***  EMPRUNT ***  emprunt have been put in expired status #check base # : "
				+ listeResaPurge);
		return listeResaPurge;
	}

	// méthode qui va traiter la liste d'attente d'un livre quand une option est
	// annulée par le batch ou un membre directement
	private void gererListeAttente(Book BookParam) {
		List<EmpruntBook> listeResaLivre = empruntBookRepository.trouverResaEncoursByBook(BookParam);
		// si on trouve une liste de réservations sur le livre on va parcourir
		// cette liste et opérer les traitements adequats
		boolean absenceNewOption = true;

		if (absenceNewOption) {

			// on remplit le stock du livre +1
			BookParam.restituerBook();
			bookRepository.save(BookParam);
			logger.info("[REST]Book must be put back in stock : " + BookParam.getTitre() + " Id :"
					+ BookParam.getId());
		}

	}

}
