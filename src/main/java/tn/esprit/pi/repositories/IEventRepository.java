package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;

@Repository
public interface IEventRepository  extends CrudRepository<Event,Integer >{

	
	/***************************FindEventByName*****************
	 * 
	 * @param title
	 * @return Event
	 * 
	 */
	@Query("SELECT ev FROM Event ev WHERE ev.title = :title")
	public Event findEventByName(@Param("title")String title);
	
	
	/***************************FindEventByCategory*****************
	 * 
	 * @param EventCategory
	 * @return Event
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.category =:category")
	public List<Event> filterByCategory(@Param("category")EventCategory eventCategory);
	
	/***************************upcomingEvents*********************
	 * 
	 * @param 
	 * @return List<Events>
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.date >= CURRENT_DATE() and ev.hour >= CURRENT_TIME()")
	public List<Event> upcomingEvents();
	
	
	/***************************passedEvents*********************
	 * 
	 * @param 
	 * @return List<Events>
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.date <= current_timestamp()")
	public List<Event> passedEvents();
	
}
