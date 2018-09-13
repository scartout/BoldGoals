package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {

	@Test
	public void create() {
		Item item = new Item();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Item.class).verify();
	}
	
}
