package com.ejemplo.demo;

import com.ejemplo.demo.model.Profesor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfesorIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void deberiaAgregarProfesor() {
		// Crear un nuevo profesor
		Profesor nuevoProfesor = new Profesor(100L, "Carlos Torres");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Profesor> request = new HttpEntity<>(nuevoProfesor, headers);

		// Enviar la solicitud POST para agregar el profesor
		ResponseEntity<Profesor> postResponse =
				restTemplate.postForEntity("http://localhost:" + port + "/profesores", request, Profesor.class);

		// Verificar la respuesta de la solicitud POST
		assertEquals(HttpStatus.OK, postResponse.getStatusCode());
		assertNotNull(postResponse.getBody());
		assertEquals("Carlos Torres", postResponse.getBody().getNombre());
	}

	@Test
	void deberiaListarProfesores() {
		// Obtener la lista de profesores
		ResponseEntity<Profesor[]> getResponse =
				restTemplate.getForEntity("http://localhost:" + port + "/profesores", Profesor[].class);

		// Verificar la respuesta de la solicitud GET
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertNotNull(getResponse.getBody());

		// Verificar si el profesor previamente agregado está en la lista
		boolean encontrado = false;
		for (Profesor p : getResponse.getBody()) {
			if (p.getId() == 100L && "Carlos Torres".equals(p.getNombre())) {
				encontrado = true;
				break;
			}
		}

		// Asegurarse de que el profesor esté en la lista
		assertTrue(encontrado, "El profesor agregado debería estar en la lista.");
	}
}
