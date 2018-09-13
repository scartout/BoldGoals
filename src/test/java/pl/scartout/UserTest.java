package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Test
	public void create() {
		User user = new User();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(User.class).verify();
	}
	
}
