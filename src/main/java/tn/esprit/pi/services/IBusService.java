package tn.esprit.pi.services;

import java.util.List;

import org.springframework.data.domain.Page;



import tn.esprit.pi.entities.Bus;



public interface IBusService {
	List<Bus> getAllBus();
	
	Bus  AffectBusDriver(Bus bus,long idDriver,int idU);
	Bus getBusById(long id);
	void deleteBusById(long id);
	Bus updateBus(Bus bus, long id);
	List<Bus> getBusByDriver(String D);
	
}
