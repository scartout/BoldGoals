package pl.scartout.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Item;
 
@Transactional
@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
	
	List<Item> findAllByGoalId(long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item c SET c.status = 'complete' WHERE c.id = :item_id")
    int updateItem(@Param("item_id") long itemId);
	
	@Modifying(clearAutomatically = true)
    @Query("DELETE FROM Item c WHERE c.id = :item_id")
    int deleteItem(@Param("item_id") long itemId);
	
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Item c WHERE c.id = :goal_id")
    int deleteItems(@Param("goal_id") long goalId);
	
	@Query("SELECT i FROM Item i, Goal g JOIN g.user u WHERE g.id=i.goal AND i.status='incomplete' AND u.username=:username AND i.date<Now()")
	List <Item> itemByUsername(@Param("username") String username);
	
}