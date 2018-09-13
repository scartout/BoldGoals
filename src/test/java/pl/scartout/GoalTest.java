package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Goal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoalTest {

	@Test
	public void create() {
		Goal goal = new Goal();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Goal.class).verify();
	}
	
}
