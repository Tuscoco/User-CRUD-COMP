package br.com.lucasmarinho;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.lucasmarinho.model.User;
import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("dev")
class CompApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createUserTest(){

		User user = new User("TesteCREATE", "Teste", "Teste");

		ResponseEntity<User> response = restTemplate.postForEntity("/user/", user, User.class);

		assertEquals(user.getUsername(), response.getBody().getUsername());
		assertEquals(HttpStatus.OK, response.getStatusCode());

		response = restTemplate.postForEntity("/user/", user, User.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(null, response.getBody());

	}

	@Test
	public void readUserTest(){
		
		User user = new User("TesteREAD", "Teste", "Teste");
		ResponseEntity<User> criado = restTemplate.postForEntity("/user/", user, User.class);

		ResponseEntity<User> response = restTemplate.getForEntity("/user/" + criado.getBody().getId(), User.class); //Id existe
		assertEquals(HttpStatus.OK, response.getStatusCode());

		response = restTemplate.getForEntity("/user/" + 9999, User.class); //Id não existe
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void readUserByUsernameTest(){
		
		User user = new User("TesteREADBYUSERNAME", "Teste", "Teste");
		restTemplate.postForEntity("/user/", user, String.class);

		ResponseEntity<User> response = restTemplate.getForEntity("/user/username/TesteREADBYUSERNAME", User.class); //Username existe
		assertEquals(HttpStatus.OK, response.getStatusCode());

		response = restTemplate.getForEntity("/user/username/Teste2", User.class); //Username não existe
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void updateUserTest(){

		User user = new User("TesteUPDATE", "Teste", "Teste");
		ResponseEntity<User> criado = restTemplate.postForEntity("/user/", user, User.class);

		restTemplate.put("/user/" + criado.getBody().getId(), new User("TesteUpdate2", "TesteUpdate", "TesteUpdate"));

		ResponseEntity<User> response = restTemplate.getForEntity("/user/" + criado.getBody().getId(), User.class);
		assertEquals("TesteUpdate2", response.getBody().getUsername());

	}

	@Test
	public void deleteUserTest(){

		User user = new User("TesteDELETE", "Teste", "Teste");
		restTemplate.postForEntity("/user/", user, String.class);

		restTemplate.delete("/user/1");

		ResponseEntity<User> response = restTemplate.getForEntity("/user/1", User.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

}
