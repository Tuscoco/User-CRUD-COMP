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
@ActiveProfiles("test")
class CompApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createUserTest(){

		User user = new User("Teste", "Teste", "Teste");

		ResponseEntity<String> response = restTemplate.postForEntity("/user/", user, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Usuário criado com sucesso!", response.getBody());

	}

}
