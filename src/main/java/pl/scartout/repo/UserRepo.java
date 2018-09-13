package pl.scartout.repo;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import pl.scartout.model.User;
 
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
	@Query("SELECT id FROM User c WHERE c.username=:username")
	Long idByUsername(@Param("username") String username);
}