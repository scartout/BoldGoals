package pl.scartout.repo;
 
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
 
import pl.scartout.model.Role;
 
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Role findByRole(String role);

}