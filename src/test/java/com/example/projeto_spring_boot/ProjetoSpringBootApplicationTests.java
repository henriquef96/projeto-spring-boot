package com.example.projeto_spring_boot;

import com.example.projeto_spring_boot.entity.Todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjetoSpringBootApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testeCreateTodoSuccess() {
		var todo = new Todo("todo 1", "description 1", false, 1);

		webTestClient
				.post()
				.uri("/api/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$.nome").isEqualTo(todo.getNome())
				.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$.realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testeCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/api/todos")
				.bodyValue(
						new Todo("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}
}
