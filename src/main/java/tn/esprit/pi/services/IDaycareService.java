package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Retour;

public interface IDaycareService {
	Retour<Daycare> addDaycare(Daycare daycare);

	void deleteDaycare(int id);

	Daycare updateDaycare(int id, Daycare daycare);

	Daycare displayById(int id);

	List<Daycare> displayAll();

	List<Daycare> displayDaycareSaturated();

	List<Daycare> displayDaycareNonSaturated();

	List<Daycare> displayDaycareByDate();

	Double daycareRevenuePerYear(String year);
}
