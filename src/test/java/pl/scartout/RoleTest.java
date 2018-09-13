package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {

	@Test
	public void create() {
		Role role = new Role();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Role.class).verify();
	}
	
}
