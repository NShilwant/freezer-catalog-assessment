package freezer.catalog.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.northrow.freezer.catalog.api.Application;
import com.northrow.freezer.catalog.api.model.Freezer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	public void contextLoads() {

	}
	
	
	@Test
	public void testSaveFreezerItem() {
		Freezer freezer = new Freezer();
		freezer.setFoodName("Egg");
		freezer.setFoodType("Breakfast");
		freezer.setQuantity(5);
		freezer.setDateAdded("01/01/2020");
		

		ResponseEntity<Freezer> postResponse = restTemplate.postForEntity( getRootUrl() +"/freezer", freezer, Freezer.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testGetItemsById() {
		Freezer freezer = restTemplate.getForObject(getRootUrl() + "/freezer/1", Freezer.class);
		System.out.println(freezer.getFoodName());
		assertNotNull(freezer);
	}
	
	@Test
	public void testUpdateFreezerItem() {
		int id = 1;
		Freezer freezer = restTemplate.getForObject(getRootUrl() + "/freezer/" + id, Freezer.class);
		freezer.setFoodName("Egg");
		freezer.setFoodType("Breakfast");

		restTemplate.put(getRootUrl() + "/freezer/" + id, freezer);

		Freezer updatedFreezer = restTemplate.getForObject(getRootUrl() + "/freezer/" + id, Freezer.class);
		assertNotNull(updatedFreezer);
	}
	
	
	
}
