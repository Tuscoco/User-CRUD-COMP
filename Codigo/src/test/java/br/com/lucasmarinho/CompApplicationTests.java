package br.com.lucasmarinho;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.lucasmarinho.model.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class CompApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createUserTest(){

		User user = new User("Teste", "Teste", "Teste");

		ResponseEntity<String> response = restTemplate.postForEntity("/user/", user, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Usuário criado com sucesso!", response.getBody());

		response = restTemplate.postForEntity("/user/", user, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Erro ao criar usuário!\nUsername existente!", response.getBody());

	}

	@Test
	public void readUserTest(){
		
		User user = new User("Teste", "Teste", "Teste");
		restTemplate.postForEntity("/user/", user, String.class);

		ResponseEntity<User> response = restTemplate.getForEntity("/user/" + 0, User.class); //Id existe
		assertEquals(HttpStatus.OK, response.getStatusCode());

		response = restTemplate.getForEntity("/user/" + 10, User.class); //Id não existe
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void readUserByUsernameTest(){
		
		User user = new User("Teste", "Teste", "Teste");
		restTemplate.postForEntity("/user/", user, String.class);

		ResponseEntity<User> response = restTemplate.getForEntity("/user/username/Teste", User.class); //Username existe
		assertEquals(HttpStatus.OK, response.getStatusCode());

		response = restTemplate.getForEntity("/user/username/Teste2", User.class); //Username não existe
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

	@Test
	public void updateUserTest(){

		User user = new User("Teste", "Teste", "Teste");
		restTemplate.postForEntity("/user/", user, String.class);

		restTemplate.put("/user/0", new User("TesteUpdate", "TesteUpdate", "TesteUpdate"));

		ResponseEntity<User> response = restTemplate.getForEntity("/user/0", User.class);
		assertEquals("TesteUpdate", response.getBody().getUsername());

	}

	@Test
	public void deleteUserTest(){

		User user = new User("Teste", "Teste", "Teste");
		restTemplate.postForEntity("/user/", user, String.class);

		restTemplate.delete("/user/1");

		ResponseEntity<User> response = restTemplate.getForEntity("/user/1", User.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}

}
