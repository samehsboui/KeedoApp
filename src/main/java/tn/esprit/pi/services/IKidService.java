package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Kid;

public interface IKidService {

	Kid addKid(Kid kid, int idU);

	void deleteKid(int id);

	Kid displayKid(int id);

	List<Kid> displayAllKids();

	Kid updateKid(int id, Kid kid);

	List<Kid> orderKidsByNameAsc();

	List<Kid> orderKidsByNameDesc();

	Kid affectKidToDaycare(int idK, int idD);

	Kid deleteKidFromDaycare(int idK, int idD);

	int nbrKid();
}
