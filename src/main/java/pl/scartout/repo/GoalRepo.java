package pl.scartout.repo;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Goal;

@Transactional
@Repository
public interface GoalRepo extends JpaRepository<Goal, Long> {
	
	List<Goal> findAllByUserId(long id);
	
	Goal getById(Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("DELETE FROM Goal c WHERE c.id = :goal_id")
    int deleteGoal(@Param("goal_id") long goalId);
	
}