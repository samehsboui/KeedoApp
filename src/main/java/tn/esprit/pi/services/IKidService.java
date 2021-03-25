package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;

public interface IKidService {

	Kid addKid(Kid kid, int idU);

	String deleteKid(int id);

	Kid displayKid(int id);

	List<Kid> displayAllKids();

	Kid updateKid(int id, Kid kid);

	List<Kid> orderKidsByNameAsc();

	List<Kid> orderKidsByNameDesc();

	Retour<Kid> affectKidToDaycare(int idK, int idD);

	Retour<Kid> deleteKidFromDaycare(int idK, int idD);

	int nbrKid();
}
